package com.wzq.security.service.impl;

import com.wzq.security.model.Users;
import com.wzq.security.dao.UserDaoMapper;
import com.wzq.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-11-26 16:30:07
 * Description: 实现类
 */

@Service
public class UserRoleServiceImpl implements UserRoleService{

    private UserDaoMapper userDao;

    @Override
    public Users getUserByUserName(String username) {
        System.out.println("UserRoleServiceImpl");
//        Users users = userDao.getUserById(1);
        Users users = userDao.getUserByName(username);
        System.out.println("users:"+username);
        return users;
    }

    public UserDaoMapper getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDaoMapper userDao) {
        this.userDao = userDao;
    }


}
