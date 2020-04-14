/**
 * Copyright (C), 2019-2020
 * FileName: PriorityNodeRemoveVisitor
 * Author:   xiaoguang
 * Date:     2020/4/14 4:26 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.weeked.eshop.auth.visitor;

import com.weeked.eshop.auth.composite.PriorityNode;
import com.weeked.eshop.auth.domain.PriorityDO;
import com.weeked.eshop.auth.mapper.PriorityMapper;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xiaoguang
 * @create 2020/4/14
 * @since 1.0.0
 */
public class PriorityNodeRemoveVisitor implements PriorityNodeVisitor {

    /**
     * 权限管理模块的DAO组件
     */
    private PriorityMapper priorityMapper;

    /**
     * 构造函数
     * @param priorityMapper 权限管理模块的DAO组件
     */
    public PriorityNodeRemoveVisitor(PriorityMapper priorityMapper) {
        this.priorityMapper = priorityMapper;
    }

    /**
     * 访问权限树节点
     * @param node 权限树节点
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

        removePriority(node);
    }

    /**
     * 删除权限
     * @param node 权限树节点
     */
    private void removePriority(PriorityNode node) {
        priorityMapper.removePriority(node.getId());
    }
}