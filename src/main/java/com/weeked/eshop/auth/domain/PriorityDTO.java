/**
 * Copyright (C), 2019-2020
 * FileName: PriorityDO
 * Author:   xiaoguang
 * Date:     2020/4/14 2:49 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.weeked.eshop.auth.domain;

import com.weeked.eshop.common.util.BeanCopierUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

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
@Slf4j
public class PriorityDTO {

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
     * 克隆方法
     * @param clazz 目标Class对象
     * @return 克隆后的对象
     */
    public <T> T clone(Class<T> clazz) {
        T target = null;

        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            log.error("error", e);
        }

        BeanCopierUtils.copyProperties(this, target);

        return target;
    }

}