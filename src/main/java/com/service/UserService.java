package com.service;

import com.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(User user);

    int changePwd(User user);

    int getUserTotal(Map<String, Object> map);

    List<User> getUserDetail(Map<String, Object> map);

    int addUser(User user);

    int updateUser(User user);

    void updateUserRole(Map<String, Object> map);

    int deleteUser(Integer id);
}