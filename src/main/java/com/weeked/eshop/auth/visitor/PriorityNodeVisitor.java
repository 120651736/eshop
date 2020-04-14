package com.weeked.eshop.auth.visitor;

import com.weeked.eshop.auth.composite.PriorityNode;

/**
 * @author xiaoguang
 */
public interface PriorityNodeVisitor {
    /**
     * 访问权限树节点
     * @param node 权限树节点
     */
    void visit(PriorityNode node);
}
