package com.wzq.security.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wzqwsrf on 15/4/8.
 * Description: 这里是根据用户获取权限信息，替代了xml配置文件中的类似这种配置
 * <authentication-manager>
 * <authentication-provider>
 * <authentication-provider>
 * <user-service>
 * <user name="admin" password="admin" authorities="ROLE_USER,ROLE_ADMIN"/>
 * <user name="zhenqing.wang" password="123" authorities="ROLE_USER"/>
 * </user-service>
 * </authentication-provider>
 * </authentication-manager>
 * 改为从数据库获取权限
 */
@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    private Log log = LogFactory.getLog(MyUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = new User("zhenqing.wang","123",true, true, true, true, getAuthorities("zhenqing.wang"));
        return user;
    }

    public Collection<GrantedAuthority> getAuthorities(String username){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
