<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>登陆</title>
</head>
<body>

<form class="login-form" action="j_spring_security_check" method="post"
      style="width:260px;text-align:center;" >
    <fieldset>
        <legend>登陆</legend>
        用户: <input type="text" name="j_username" style="width:150px;"
                   value=""/><br />
        密码: <input type="password" name="j_password" style="width:150px;" /><br />
        <input type="checkbox" name="_spring_security_remember_me " />两周之内不必登陆
        <br />
        <input type="submit" value="登陆"/>
        <input type="reset" value="重置"/>
    </fieldset>
</form>
</body>
</html>