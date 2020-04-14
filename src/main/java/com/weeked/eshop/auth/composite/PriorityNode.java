/**
 * Copyright (C), 2019-2020
 * FileName: PriorityNode
 * Author:   xiaoguang
 * Date:     2020/4/14 4:23 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.weeked.eshop.auth.composite;

import com.weeked.eshop.auth.visitor.PriorityNodeVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xiaoguang
 * @create 2020/4/14
 * @since 1.0.0
 */
@Setter
@Getter
public class PriorityNode {

    /**
     * id
     */
    private Long id;
    /**
     * 权限编号
     */
    private String code;
    /**
     * 权限URL
     */
    private String url;
    /**
     * 权限备注
     */
    private String priorityComment;
    /**
     * 权限类型
     */
    private Integer priorityType;
    /**
     * 父权限id
     */
    private Long parentId;
    /**
     * 权限的创建时间
     */
    private Date gmtCreate;
    /**
     * 权限的修改时间
     */
    private Date gmtModified;
    /**
     * 子权限节点
     */
    private List<PriorityNode> children = new ArrayList<PriorityNode>();

    /**
     * 接收一个权限树访问者
     * @param visitor 权限树访问者
     */
    public void accept(PriorityNodeVisitor visitor) {
        visitor.visit(this);
    }

}