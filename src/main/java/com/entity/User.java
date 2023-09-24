package com.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id; // 编号
    private String userName; // 用户名
    private String password; // 密码
    private String trueName; // 真实姓名
    private String email; // 邮件
    private String phone; // 联系电话
    private String roleName; // 角色名称 系统管理员 销售主管 客户经理 高管
    private Integer roleNumber;

}