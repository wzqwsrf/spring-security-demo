package com.wzq.security.dao;

import com.wzq.security.model.Role;

import java.util.List;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-11-26 15:20:35
 * Description: roledao类
 */
public interface RoleDaoMapper {
    Role getRoleById(int id);
    List<Role> getRoleList();
}
