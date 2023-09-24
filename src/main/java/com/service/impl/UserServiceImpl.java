package com.service.impl;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> getUserDetail(Map<String, Object> map) {
        return userDao.getUserDetail(map);
    }

    @Override
    public int getUserTotal(Map<String, Object> map) {
        return userDao.getUserTotal(map);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    public int changePwd(User user) {
        return userDao.changePwd(user);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void updateUserRole(Map<String, Object> map) {
        userDao.updateUserRole(map);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }
}
