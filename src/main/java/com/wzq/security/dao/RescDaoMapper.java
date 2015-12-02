package com.wzq.security.dao;

import com.wzq.security.model.Resc;
import com.wzq.security.model.Users;

import java.util.List;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-12-02 18:32:21
 * Description: rescdao类
 */
public interface RescDaoMapper {
    List<Resc> getRescList();
}
