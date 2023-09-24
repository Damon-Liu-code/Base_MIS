package com.controller;

import com.entity.PageBean;
import com.entity.User;
import com.entity.jsonEntity.DefaultDataResult;
import com.service.UserService;
import com.util.ResponseUtil;
import com.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;
    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping("/getCurrentUser")
    public User getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping("/userList")
    public void userList(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, User user, HttpServletResponse response) throws Exception {
        try {
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", StringUtil.formatLike(user.getUserName()));
            map.put("trueName", StringUtil.formatLike(user.getTrueName()));
            map.put("email", StringUtil.formatLike(user.getEmail()));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
            DefaultDataResult ddr = new DefaultDataResult();
            ddr.rows = userService.getUserDetail(map);
            ddr.total = userService.getUserTotal(map);
            ResponseUtil.sendJsonResponse(response, ddr);
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "加载失败");
        }
    }

    @RequestMapping("/login")
    public void login(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            User resultUser = userService.login(user);
            log.error("user:" + resultUser);
            if (resultUser == null) {
                ResponseUtil.sendFailureResponse(response, "用户名或密码错误");
            } else {
                log.error(resultUser.getPassword());
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", resultUser);
                ResponseUtil.sendSuccessResponse(response, "登录成功");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "登录失败");
        }
    }

    @RequestMapping("/changePassword")
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oldPwd = request.getParameter("user_old_password");
        String newPwd = request.getParameter("user_new_password");
        String rePwd = request.getParameter("user_confirm_new_password");
        try {
            if (newPwd.equals(rePwd)) {
                if (newPwd.length() > 0) {
                    User currentUser = (User) request.getSession().getAttribute("currentUser");
                    User loginUser = new User();
                    loginUser.setPassword(oldPwd);
                    loginUser.setUserName(currentUser.getUserName());
                    User resultUser = userService.login(loginUser);
                    if (resultUser != null) {
                        resultUser.setPassword(newPwd);
                        int changeCount = userService.changePwd(resultUser);
                        if (changeCount > 0) {
                            ResponseUtil.sendSuccessResponse(response, "密码更改成功");
                        } else {
                            ResponseUtil.sendFailureResponse(response, "密码更改失败");
                        }
                    } else {
                        ResponseUtil.sendFailureResponse(response, "旧密码错误");
                    }
                } else {
                    ResponseUtil.sendFailureResponse(response, "请输入新密码");
                }
            } else {
                ResponseUtil.sendFailureResponse(response, "两次密码输入不相同");
            }
        } catch (Exception ex) {
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "更改密码失败");
        }
    }

    @RequestMapping("/saveUser")
    public void saveUser(User user, HttpServletResponse response) throws IOException {
        try {
            int resultTotal = 0; // 操作的记录条数
            if (user != null && user.getId() == null) {
                resultTotal = userService.addUser(user);
            } else {
                resultTotal = userService.updateUser(user);
            }
            if (resultTotal > 0) {
                ResponseUtil.sendSuccessResponse(response, "操作成功");
            } else {
                ResponseUtil.sendFailureResponse(response, "操作失败");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "操作失败");
        }
    }

    @RequestMapping("/deleteUser")
    public void deleteUser(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        try {
            int resultTotal = 0; // 操作的记录条数
            String[] idsStr = ids.split(",");//ids: "1,2,3,4"  split:拆分成数组
            for (int i = 0; i < idsStr.length; i++) {
                resultTotal += userService.deleteUser(Integer.parseInt(idsStr[i]));
            }
            if (resultTotal > 0) {
                ResponseUtil.sendSuccessResponse(response, "操作成功");
            } else {
                ResponseUtil.sendFailureResponse(response, "操作失败");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "操作失败");
        }
    }

    @RequestMapping("/userCount")
    public int getUserCount(User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int userCount = userService.getUserTotal(map);
        return userCount;
    }

}