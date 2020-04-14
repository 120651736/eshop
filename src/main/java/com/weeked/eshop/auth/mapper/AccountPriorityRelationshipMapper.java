package com.weeked.eshop.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiaoguang
 */
@Mapper
public interface AccountPriorityRelationshipMapper {

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) "
            + "FROM auth_account_priority_relationship "
            + "WHERE priority_id=#{priorityId}")
    Long getCountByPriorityId(@Param("priorityId") Long priorityId);

}
