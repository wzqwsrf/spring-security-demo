package com.wzq.security.acsecurity;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date:
 * Description:
 */

@Repository
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) throw new AccessDeniedException("对不起，您没有此权限");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + ":\t" + object.toString());

        for (ConfigAttribute ca : configAttributes) {
            String needRole = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority userGA : authentication.getAuthorities()) {
                if (needRole.equals(userGA.getAuthority())) {   // ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("对不起，您没有此权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
