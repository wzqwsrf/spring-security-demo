# spring-security demo
----------------------
#### 1. 初始化配置
- web.xml

```
<filter>
     <filter-name>springSecurityFilterChain</filter-name>
     <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
     <filter-name>springSecurityFilterChain</filter-name>
     <url-pattern>/*</url-pattern>
</filter-mapping>
```
- ApplicationContext-security.xml 

```
    <http auto-config="true">
            <intercept-url pattern="/admin.jsp" access="ROLE_ADMIN"/>
            <intercept-url pattern="/**" access="ROLE_USER"/>
        </http>
        <!-- 认证管理器。用户名密码都集成在配置文件中 -->
        <authentication-manager>
            <authentication-provider>
                <!--<user-service>-->
                    <!--<user name="admin" password="admin" authorities="ROLE_USER, ROLE_ADMIN"/>-->
                    <!--<user name="user" password="user" authorities="ROLE_USER" />-->
                <!--</user-service>-->
                <jdbc-user-service data-source-ref="dataSource"
                                   users-by-username-query=
                                           "select username,password,status as enabled
                                            from users
                                            where username=?"
                                   authorities-by-username-query=
                                           "select u.username,r.name as authority
                                            from users u
                                            join user_role ur
                                            on u.id=ur.user_id
                                            join role r
                                            on r.id=ur.role_id
                                            where u.username=?"/>
            </authentication-provider>
        </authentication-manager>
```
#### 2. 访问

- 这是第一步，所有的验证以及拦截都适用默认的```UsernamePasswordAuthenticationFilter```，每个url设有权限的url会被这个方法拦截，然后登陆用户名密码，和配置数据做匹配，根据匹配结果进入相应页面
- 下一步考虑，将用户权限放入数据库，考虑如果大量的用户名密码在这里存储，其实是不安全的。

#### 3. 优化，将权限存入数据库
- 获取用户权限的主要是```UserDetailsService```,我们重写```loadUserByUsername```
- 数据库表设置，目前只用一张表，替换掉xml文件中的配置。
- 数据库表设置```USER``` 
  
  id | username | admin
------------ | ------------- | ------------
1 | admin  | ROLE_ADMIN,ROLE_USER
2 | zhenqing.wang  | ROLE_USER
3 | other | ROLE_OTHER


	

