/**
 * Copyright (C), 2019-2020
 * FileName: PriorityServiceImpl
 * Author:   xiaoguang
 * Date:     2020/4/14 3:10 下午
 * Description: 实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.weeked.eshop.auth.service.impl;

import com.weeked.eshop.auth.composite.PriorityNode;
import com.weeked.eshop.auth.domain.PriorityDO;
import com.weeked.eshop.auth.domain.PriorityDTO;
import com.weeked.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import com.weeked.eshop.auth.mapper.PriorityMapper;
import com.weeked.eshop.auth.mapper.RolePriorityRelationshipMapper;
import com.weeked.eshop.auth.service.PriorityService;
import com.weeked.eshop.auth.visitor.PriorityNodeRelateCheckVisitor;
import com.weeked.eshop.auth.visitor.PriorityNodeRemoveVisitor;
import com.weeked.eshop.common.util.BeanCopierUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈实现类〉
 *
 * @author xiaoguang
 * @create 2020/4/14
 * @since 1.0.0
 */
@Service
@Slf4j
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityMapper priorityMapper;

    @Autowired
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;

    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    @Override
    public List<PriorityDTO> listRootPriorities() {
        List<PriorityDO> priorityDOS = priorityMapper.listRootPriorities();
        List<PriorityDTO> priorityDTOS = new ArrayList<>();
        for (PriorityDO priorityDO: priorityDOS) {
            PriorityDTO priorityDTO = null;
            try {
                priorityDTO = PriorityDTO.class.newInstance();
            } catch (Exception e) {
                log.info("bean copy 异常", e);
            }
            BeanCopierUtils.copyProperties(priorityDO,priorityDTO);
            priorityDTOS.add(priorityDTO);
        }
        return priorityDTOS;
    }

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限id
     * @return 子权限
     */
    @Override
    public List<PriorityDTO> listChildPriorities(Long parentId) {
        try {
            List<PriorityDO> priorityDOs = priorityMapper.listChildPriorities(parentId);
            if(priorityDOs == null) {
                return null;
            }

            List<PriorityDTO> priorityDTOs = new ArrayList<PriorityDTO>(priorityDOs.size());
            for(PriorityDO priorityDO : priorityDOs) {
                priorityDTOs.add(priorityDO.clone(PriorityDTO.class));
            }

            return priorityDTOs;
        } catch (Exception e) {
            log.error("error", e);
        }
        return null;
    }

    /**
     * 根据id查询权限
     * @param id 权限id
     * @return 权限
     */
    @Override
    public PriorityDTO getPriorityById(Long id) {
        try {
            PriorityDO priorityDO = priorityMapper.getPriorityById(id);
            if(priorityDO == null) {
                return null;
            }

            return priorityDO.clone(PriorityDTO.class);
        } catch (Exception e) {
            log.error("error", e);
        }
        return null;
    }

    /**
     * 新增权限
     * @param priorityDTO 权限DO对象
     */
    @Override
    public Boolean savePriority(PriorityDTO priorityDTO) {
        try {
            priorityMapper.savePriority(priorityDTO.clone(PriorityDO.class));
        } catch (Exception e) {
            log.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新权限
     * @param priorityDTO 权限DO对象
     */
    @Override
    public Boolean updatePriority(PriorityDTO priorityDTO) {
        try {
            priorityMapper.updatePriority(priorityDTO.clone(PriorityDO.class));
        } catch (Exception e) {
            log.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除权限
     * @param id 权限id
     * @return 处理结果
     */
    @Override
    public Boolean removePriority(Long id) {
        try {
            // 根据id查询权限
            PriorityDO priorityDO = priorityMapper.getPriorityById(id);
            PriorityNode priorityNode = priorityDO.clone(PriorityNode.class);

            // 检查这个权限以及其下任何一个子权限，是否被角色或者账号给关联着
            PriorityNodeRelateCheckVisitor relateCheckVisitor = new PriorityNodeRelateCheckVisitor(
                    priorityMapper, rolePriorityRelationshipMapper, accountPriorityRelationshipMapper);
            relateCheckVisitor.visit(priorityNode);
            Boolean relateCheckResult = relateCheckVisitor.getRelateCheckResult();

            if(relateCheckResult) {
                return false;
            }

            // 递归删除当前权限以及其下所有的子权限
            PriorityNodeRemoveVisitor removeVisitor = new PriorityNodeRemoveVisitor(priorityMapper);
            removeVisitor.visit(priorityNode);
        } catch (Exception e) {
            log.error("error", e);
            return false;
        }

        return true;
    }
}