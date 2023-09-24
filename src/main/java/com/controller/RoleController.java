package com.controller;

import com.entity.Role;
import com.entity.jsonEntity.DefaultDataResult;
import com.service.RoleService;
import com.service.UserService;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    private static final Logger log = Logger.getLogger(RoleController.class);

    @RequestMapping("/roleList")
    public void roleList(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Role role, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            DefaultDataResult ddr = new DefaultDataResult();
            ddr.rows = roleService.getRoleDetail(map);
            ddr.total = roleService.getRoleTotal(map);
            ResponseUtil.sendJsonResponse(response, ddr);
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "加载失败");
        }
    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public void saveRole(@RequestParam(value = "data", required = false) String roleListStr, HttpServletResponse response) throws IOException {
        try {
            List<Role> roleList = JsonUtil.deserializeJsonToList(roleListStr, Role.class);
            int id = 0;
            String roleName = null;
            int roleNumber = 0;
            Hashtable<String, Object> ht = new Hashtable<>();
            if (roleList != null) {
                for (Role _role : roleList) {
                    id = _role.getId();
                    roleName = _role.getRoleName();
                    roleNumber = _role.getRoleNumber();
                    ht.put("id", id);
                    ht.put("roleName", roleName);
                    ht.put("roleNumber", roleNumber);
                    String old_roleName = roleService.getRoleNameById(id);
                    ht.put("old_roleName", old_roleName);
                    roleService.updateRoleInfo(ht);
                    userService.updateUserRole(ht);
                    ht.clear();
                }
            }
            ResponseUtil.sendSuccessResponse(response, "操作成功");
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "操作失败");
        }
    }

    @RequestMapping("/deleteRole")
    public void deleteRole(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        try {
            int resultTotal = 0; // 操作的记录条数
            String[] idsStr = ids.split(",");//ids: "1,2,3,4"  split:拆分成数组
            for (int i = 0; i < idsStr.length; i++) {
                resultTotal += roleService.deleteRole(Integer.parseInt(idsStr[i]));
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

}