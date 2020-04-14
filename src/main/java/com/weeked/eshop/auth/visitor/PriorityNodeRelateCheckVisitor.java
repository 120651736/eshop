/**
 * Copyright (C), 2019-2020
 * FileName: PriorityNodeRelateCheckVisitor
 * Author:   xiaoguang
 * Date:     2020/4/14 4:27 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.weeked.eshop.auth.visitor;

import com.weeked.eshop.auth.composite.PriorityNode;
import com.weeked.eshop.auth.domain.PriorityDO;
import com.weeked.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import com.weeked.eshop.auth.mapper.PriorityMapper;
import com.weeked.eshop.auth.mapper.RolePriorityRelationshipMapper;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xiaoguang
 * @create 2020/4/14
 * @since 1.0.0
 */
public class PriorityNodeRelateCheckVisitor implements PriorityNodeVisitor {

    /**
     * 关联检查结果
     */
    private Boolean relateCheckResult = false;
    /**
     * 权限管理模块的DAO组件
     */
    private PriorityMapper priorityMapper;
    /**
     * 角色和权限关系管理模块的DAO组件
     */
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;
    /**
     * 账号和权限关系管理模块的DAO组件
     */
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    /**
     * 构造函数
     * @param riorityMapper
     * @param rolePriorityRelationshipMapper
     * @param accountPriorityRelationshipMapper
     */
    public PriorityNodeRelateCheckVisitor(
            PriorityMapper riorityMapper,
            RolePriorityRelationshipMapper rolePriorityRelationshipMapper,
            AccountPriorityRelationshipMapper accountPriorityRelationshipMapper) {
        this.priorityMapper = priorityMapper;
        this.rolePriorityRelationshipMapper = rolePriorityRelationshipMapper;
        this.accountPriorityRelationshipMapper = accountPriorityRelationshipMapper;
    }

    /**
     * 访问权限树节点
     */
    @Override
    public void visit(PriorityNode node) {
        List<PriorityDO> priorityDOs = priorityMapper
                .listChildPriorities(node.getId());

        if(priorityDOs != null && priorityDOs.size() > 0) {
            for(PriorityDO priorityDO : priorityDOs) {
                PriorityNode priorityNode = priorityDO.clone(PriorityNode.class);
                priorityNode.accept(this);
            }
        }

        if(relateCheck(node)) {
            this.relateCheckResult = true;
        }
    }

    /**
     * 检查权限是否被任何一个角色或者是账号关联了
     * @param node 权限树节点
     * @return 是否被任何一个角色或者是账号关联了，如果有关联则为true；如果没有关联则为false
     */
    private Boolean relateCheck(PriorityNode node) {
        Long roleRelatedCount = rolePriorityRelationshipMapper
                .getCountByPriorityId(node.getId());
        if(roleRelatedCount != null && roleRelatedCount > 0) {
            return true;
        }

        Long accountRelatedCount = accountPriorityRelationshipMapper
                .getCountByPriorityId(node.getId());
        if(accountRelatedCount != null && accountRelatedCount > 0) {
            return true;
        }

        return false;
    }

    public Boolean getRelateCheckResult() {
        return relateCheckResult;
    }
}