package com.wzq.security.acsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * Author: zhenqing.wang <wangzhenqing1008@163.com>
 * Date: 2015-12-02 18:03:24
 * Description: 自定义FILTER_SECURITY_INTERCEPTOR过滤器，主要更新了MySecurityMetadataSource
 */

//@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private MySecurityMetadataSource mySecurityMetadataSource;

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.mySecurityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        this.invoke(fi);
    }

    @Override
    public void destroy() {

    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
        }
        super.afterInvocation(token, (Object)null);
    }

    @Autowired
    public void setSecurityMetadataSource(MySecurityMetadataSource mySecurityMetadataSource) {
        this.mySecurityMetadataSource = mySecurityMetadataSource;
        System.out.println("securityMetadataSource:"+ this.mySecurityMetadataSource == null ? "True" : "false");
//        this.mySecurityMetadataSource.refreshResource();
    }
}
