package com.wzq.security.model;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date:
 * Description:
 */
public class Role {
    private int id;
    private String name;
    private String descn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }
}
