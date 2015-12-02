package com.wzq.security.acsecurity;

import com.wzq.security.model.Resc;
import com.wzq.security.model.Role;
import com.wzq.security.service.RescRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-12-02 18:03:31
 * Description: 自定义MySecurityMetadataSource
 */

@Repository
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap;

    private RescRoleService rescRoleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher urlMatcher = new AntPathMatcher();
        String url =((FilterInvocation)object).getRequestUrl();
        if(resourceMap != null){
            Set<String> urlPatternSet = resourceMap.keySet();
            for(String urlPattern:urlPatternSet){
                if(urlMatcher.match(urlPattern, url)){
                    return resourceMap.get(urlPattern);
                }
            }
        }
        Collection <ConfigAttribute> atts  = new ArrayList < ConfigAttribute >();
        ConfigAttribute ca = new SecurityConfig("ROLE_NONE");
        atts.add(ca);
        return atts;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

//    public void refreshResource() {
//        System.out.println("loadResourceDefine");
//        loadResourceDefine();
//    }

//  加载url权限配置
    public void loadResourceDefine(){
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        List<Resc> rescList = rescRoleService.getRescList();
        for (Resc resc : rescList){
            String url = resc.getResString();
            List<Role> roleList = resc.getRoles();
            Collection <ConfigAttribute> atts  = new ArrayList<ConfigAttribute >();
            for (Role role : roleList){
                System.err.println("role.getName():"+role.getName());
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_ADMIN");
                atts.add(configAttribute);
            }
            resourceMap.put(url, atts);
        }
    }

    public RescRoleService getRescRoleService() {
        return rescRoleService;
    }

    @Autowired
    public void setRescRoleService(RescRoleService rescRoleService) {
        this.rescRoleService = rescRoleService;
    }
}
