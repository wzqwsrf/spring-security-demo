package com.wzq.security.acsecurity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wzqwsrf on 15/4/8.
 */
public class LdapFilter extends UsernamePasswordAuthenticationFilter {
    private String usernameParameter = "username";
    private String passwordParameter = "password";

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURL());
        System.out.println(request.getParameter("username"));
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);

        System.out.println(username);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }
}
