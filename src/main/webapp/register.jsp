<%--
  Created by IntelliJ IDEA.
  User: Saber
  Date: 2017/12/10
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>

</head>
<body>
<form method="post" action="register">
    用户名：<input type="text" name="userId">
    <br><br>
    密码：<input type="password" name="pwd">
    <br><br>
    确认密码：<input type="password" name="pwd1">
    <br><br>
    <input type="radio" name="power" value="0">教师
    &nbsp;&nbsp;
    <input type="radio" name="power" value="1">管理员
    <br><br>
    <div>${info}</div>
    <input type="button" value="提交" onclick="validate()">
</form>
<script>
    function validate(){
        var userId=document.forms[0].userId.value;
        var pwd=document.forms[0].pwd.value;
        var pwd1=document.forms[0].pwd1.value;
        if(userId.length<=0)
            alert("用户名不能为空！");
        else if(pwd.length<6)
            alert("密码长度必须大于等于6！");
        else if(pwd!==pwd1)
            alert("两次密码不一致！");
        else
            document.forms[0].submit();
    }
</script>
</body>
</html>
