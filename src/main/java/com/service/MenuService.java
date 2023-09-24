package com.service;

import com.entity.Menu;

import java.util.Hashtable;
import java.util.List;

public interface MenuService {
    int getMenuCount();

    List<Menu> getMenuDetail();

    boolean isMonthBillMenuExist(Hashtable<String, Object> params);

    void createMonthBillMenu(Hashtable<String, Object> params);

    List<Menu> getMenuList(Hashtable<String, Object> params);

    List<Menu> getEditMenuList();

    void updateMenuInfo(Hashtable<String, Object> params);

    int getMenuAuthorityValue(String handlerName);

    List<Menu> getMenuSetList();
}
