package com.service;

import com.entity.Role;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public interface RoleService {

    int getRoleTotal(Map<String, Object> map);

    List<Role> getRoleDetail(Map<String, Object> map);

    int addRole(Role role);

    void updateRoleInfo(Hashtable<String, Object> params);

    int deleteRole(Integer id);

    String getRoleNameById(Integer id);
}