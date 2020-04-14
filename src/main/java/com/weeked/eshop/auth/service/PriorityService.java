package com.weeked.eshop.auth.service;


import com.weeked.eshop.auth.domain.PriorityDTO;

import java.util.List;

/**
 * @author xiaoguang
 */
public interface PriorityService {

    /**
     * 查询根目录
     * @return
     */
    List<PriorityDTO> listRootPriorities();

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限id
     * @return 子权限
     */
    List<PriorityDTO> listChildPriorities(Long parentId);

    /**
     * 根据id查询权限
     * @param id 权限id
     * @return 权限
     */
    PriorityDTO getPriorityById(Long id);

    /**
     * 新增权限
     * @param priorityDTO 权限DO对象
     */
    Boolean savePriority(PriorityDTO priorityDTO);

    /**
     * 更新权限
     * @param priorityDTO 权限DO对象
     */
    Boolean updatePriority(PriorityDTO priorityDTO);

    /**
     * 删除权限
     * @param id 权限id
     * @return 处理结果
     */
    Boolean removePriority(Long id);

}
