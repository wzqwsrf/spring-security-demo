package com.wzq.security.dao;

import com.wzq.security.model.Users;

import java.util.List;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-11-25 17:55:00
 * Description: userdao类
 */
public interface UserDaoMapper {
    Users getUserByName(String username);
    Users getUserById(int id);
    List<Users> getUserList();
}
