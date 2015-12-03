package com.wzq.security.acsecurity;

import com.wzq.security.model.Role;
import com.wzq.security.model.Users;
import com.wzq.security.service.UserRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private UserRoleService userRoleService;

    private Log log = LogFactory.getLog(LoginUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("====username:" + username);
        Users user = userRoleService.getUserByUserName(username);
        log.info("====username:" + user.getUsername());
        log.info("====password:" + user.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        UserDetails newUser = new User(user.getUsername(), user.getPassword(), user.getStatus() == 1,
                true, true, true, authorities);
        return newUser;

//        List<GrantedAuthority> author = new ArrayList<GrantedAuthority>();
//        author.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new User("admin","admin",true, true, true, true, author);
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
}
