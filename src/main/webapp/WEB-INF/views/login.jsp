<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>登陆</title>
</head>
<body>
这里是登陆界面，验证信息!!!<br>
=====================<br>

<form class="login-form" action="j_spring_security_check" method="post">
    <label style="float:left;width: 80px">用户名：</label>
    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="请输入用户名"
           name="username" value=""/><br>
    <label style="float:left;width: 80px">密码:</label>
    <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="请输入密码"
           name="password" value=""/><br>
    <button type="submit" class="btn btn-success uppercase">登录</button>
</form>
</body>
</html>
