<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登陆</title>
    <link href="style.css" rel='stylesheet' type='text/css' />
</head>
<body>
<h1>表格汇总系统</h1>
<div class="login-form">
    <form method="post" name="form1" action="login">
        用户名：<input type="text" name="userId" id="user" placeholder="请输入用户名">
        <br><br>
        密 码：<input type="password" name="pwd" id="pwd" placeholder="请输入密码">
        <br><br>
        验证码：<input type="text" name="checkcode" id="code" placeholder="验证码">
        &nbsp;&nbsp;
        <img border=0 src="checkCode" id="checkcode" onclick="RefreshCode();"/>
        <br><br>
        <div>${info}</div>
        <input type="submit" value="登录" id="sub">&nbsp;&nbsp;
        <input type="button" value="注册" id="reg" onclick="location.href='register.jsp'">
    </form>
</div>
<script type="text/javascript">
    function RefreshCode() {
        document.getElementById("checkcode").src = document.getElementById("checkcode").src + "?nocache=" + new Date().getTime();
    }
</script>
</form>
</body>
</html>
