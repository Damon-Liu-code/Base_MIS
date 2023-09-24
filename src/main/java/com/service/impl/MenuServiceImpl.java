package com.service.impl;
import com.dao.MenuDao;
import com.entity.Menu;
import com.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Hashtable;
import java.util.List;

@Transactional
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public int getMenuCount() {
        return menuDao.getMenuCountFromMySQL();
    }

    @Override
    public List<Menu> getMenuDetail() {
        return menuDao.getMenuDetailFromMySQL();
    }

    @Override
    public boolean isMonthBillMenuExist(Hashtable<String, Object> params) {
        return menuDao.isMonthBillMenuExist(params) > 0;
    }

    @Override
    public void createMonthBillMenu(Hashtable<String, Object> params) {
        menuDao.createMonthBillMenu(params);
    }

    @Override
    public List<Menu> getMenuList(Hashtable<String, Object> params) {
        return menuDao.getMenuList(params);
    }

    @Override
    public List<Menu> getEditMenuList() {
        return menuDao.getEditMenuList();
    }

    @Override
    public void updateMenuInfo(Hashtable<String, Object> params) {
        menuDao.updateMenuInfo(params);
    }

    @Override
    public int getMenuAuthorityValue(String handlerName) {
        return menuDao.getMenuAuthorityValue(handlerName);
    }

    @Override
    public List<Menu> getMenuSetList() {
        return menuDao.getMenuSetList();
    }
}
