
## 关于spring-security-demo
---------------------------------------
1. spring, mybatis以及spring mvc, Spring security。
2. 主要是security的各种功能，权限管理空系统。

## 运行
---------------------------------------
1. git clone git@github.com:wzqwsrf/spring-security-demo.git
2. 修改`spring-security-demo/src/main/resources/jdbc.properties` 中的数据库配置。
3. 新建user表，参考`spring-security-demo/src/main/resources/table_sql` 中的创建以及删除表语句。
4. mvn tomcat:run 可以运行项目。
5. 访问 <http://localhost:8080/security/error/index>

## 其他
---------------------------------------
1. 也可以使用以下命令，运行在单独的tomcat服务器中。
2. 打war包，mvn clean install
3. 在tomcat/webapp/下新建dir mkdir spring-security-demo
4. 复制spring-security-demo.war至tomcat目录下的tomcat/webapp/spring-security-demo下。
5. war包解压，unzip spring-security-demo.war。
6. 启动tomcat即可。
