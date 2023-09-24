package com.service.impl;

import com.dao.RoleDao;
import com.entity.Role;
import com.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Transactional
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> getRoleDetail(Map<String, Object> map) {
        return roleDao.getRoleDetail(map);
    }

    @Override
    public int getRoleTotal(Map<String, Object> map) {
        return roleDao.getRoleTotal(map);
    }

    @Override
    public int addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public void updateRoleInfo(Hashtable<String, Object> params) {
        roleDao.updateRoleInfo(params);
    }

    @Override
    public int deleteRole(Integer id) {
        return roleDao.deleteRole(id);
    }

    @Override
    public String getRoleNameById(Integer id) {
        return roleDao.getRoleNameById(id);
    }
}
