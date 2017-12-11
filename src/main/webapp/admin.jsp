<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">
    <script
            src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation"><a href="#profile"
                                   aria-controls="profile" role="tab" data-toggle="tab">汇总表单</a></li>
        <li role="presentation"><a href="#messages"
                                   aria-controls="messages" role="tab" data-toggle="tab">发布文件</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane" id="profile">
            <ul class="list-group">
                <%--@elvariable id="sheetColumnList" type="java.util.List<cn.medemede.excelcard.model.SheetColumn>"--%>
            <c:forEach var="sheetColumn" items="${sheetColumnList}">
                <li class="list-group-item">
                    <a href="/downloadadmin/${sheetColumn.sheetName}">${sheetColumn.sheetName}</a>&nbsp;&nbsp;
                        ${sheetColumn.locked==1?"开启":"关闭"}&nbsp;&nbsp;
                    <a href="/updateunlocked/${sheetColumn.sheetName}" class="btn btn-primary">开启</a>
                    <a href="/updatelocked/${sheetColumn.sheetName}" class="btn btn-warning">关闭</a>
                </li>
            </c:forEach>
            </ul>
        </div>
        <div role="tabpanel" class="tab-pane" id="messages">
            <h3>上传Excel文档</h3>
            <form action="uploadadmin" method="post" enctype="multipart/form-data">
                <input type="file" name="excelfile">&nbsp;&nbsp;
                <input type="date" name="setdate">
                <br><br>
                <input type="submit" value="提交">
            </form>
        </div>
    </div>

</div>
</body>
</html>
