package com.controller;

import com.entity.Menu;
import com.entity.User;
import com.service.MenuService;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


@RestController
public class MenuController {

    @Resource
    private MenuService menuService;
    private static final Logger log = Logger.getLogger(MenuController.class);

    @RequestMapping("/menuList")
    protected void menuList(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Menu> menuList = initMenuList(menuService.getEditMenuList());
            ResponseUtil.sendJsonResponse(response, menuList);
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
        }
    }

    @RequestMapping("/loadMenu")
    protected void loadMenu(HttpServletRequest request, HttpServletResponse response) {
        try {
            Hashtable<String, Object> ht = new Hashtable<>();
            User currentUser = (User) request.getSession().getAttribute("currentUser");
            ht.put("roleValue", currentUser.getRoleNumber());
            List<Menu> menuList = initMenuList(menuService.getMenuList(ht));
            ResponseUtil.sendJsonResponse(response, menuList);
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
        }
    }


    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
    protected void saveMenu(@RequestParam(value = "data", required = false) String menuListStr, HttpServletResponse response) throws Exception {
        try {
            List<Menu> menuList = JsonUtil.deserializeJsonToList(menuListStr, Menu.class);
            int id = 0;
            String name = null;
            int value = 0;
            Hashtable<String, Object> ht = new Hashtable<>();
            for (Menu _menu : menuList) {
                id = _menu.getId();
                name = _menu.getName();
                value = _menu.getValue();
                ht.put("id", id);
                ht.put("name", name);
                ht.put("value", value);

                menuService.updateMenuInfo(ht);
                ht.clear();
            }
            ResponseUtil.sendSuccessResponse(response, "操作成功");
        } catch (Exception ex) {
            System.out.println(ex);
            log.error(ex);
            ResponseUtil.sendFailureResponse(response, "操作失败");
        }
    }


    protected List<Menu> getChildren(int menuID, List<Menu> rootMenu) {
        List<Menu> childList = new ArrayList<>();
        for (Menu _menu : rootMenu) {
            if (_menu.getParentid() == menuID) {
                childList.add(_menu);
            }
        }
        for (Menu childMenu : childList) {
            childMenu.setChildren(getChildren(childMenu.getId(), rootMenu));
        }
        return childList;
    }


    protected List<Menu> initMenuList(List<Menu> rootMenu) {
        List<Menu> menuList = new ArrayList<>();
        for (Menu _menu : rootMenu) {
            if (_menu.getParentid() == 0) {
                menuList.add(_menu);
            }
        }
        for (Menu menuItem : menuList) {
            menuItem.setChildren(getChildren(menuItem.getId(), rootMenu));
        }
        return menuList;
    }

}

