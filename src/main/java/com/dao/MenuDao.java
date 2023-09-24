package com.dao;

import com.entity.Menu;

import java.util.Hashtable;
import java.util.List;


public interface MenuDao {

    int getMenuCountFromMySQL();

    List<Menu> getMenuDetailFromMySQL();

    int isMonthBillMenuExist(Hashtable<String, Object> params);

    void createMonthBillMenu(Hashtable<String, Object> params);

    List<Menu> getMenuList(Hashtable<String, Object> params);

    List<Menu> getEditMenuList();

    void updateMenuInfo(Hashtable<String, Object> params);

    int getMenuAuthorityValue(String handlerName);

    List<Menu> getMenuSetList();
}
