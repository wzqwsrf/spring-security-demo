package com.wzq.security.service.impl;

import com.wzq.security.dao.RescDaoMapper;
import com.wzq.security.model.Resc;
import com.wzq.security.service.RescRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-12-02 18:40:53
 * Description: resc实现类
 */

@Service
public class RescRoleServiceImpl implements RescRoleService{

    private RescDaoMapper rescDaoMapper;

    @Override
    public List<Resc> getRescList() {
        System.out.println("getRescList...start");
        List<Resc> list = rescDaoMapper.getRescList();
        System.out.println("getRescList size:"+list.size());
        System.out.println("getRescList...end");
        return list;
    }

    public RescDaoMapper getRescDaoMapper() {
        return rescDaoMapper;
    }

    @Autowired
    public void setRescDaoMapper(RescDaoMapper rescDaoMapper) {
        this.rescDaoMapper = rescDaoMapper;
    }
}
