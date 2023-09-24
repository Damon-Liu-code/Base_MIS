package com.dao;

import com.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    int getRoleTotal(Map<String, Object> map);

    List<Role> getRoleDetail(Map<String, Object> map);

    int addRole(Role role);

    int updateRoleInfo(Map<String, Object> map);

    int deleteRole(Integer id);

    String getRoleNameById(Integer id);

}