package com.wzq.security.service;

import com.wzq.security.model.Users;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-11-26 16:19:08
 * Description: 获取用户名权限
 */
public interface UserRoleService {
    Users getUserByUserName(String username);
}
