<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>教师</title>
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
<h1>Teacher，你好</h1>
<%--@elvariable id="info" type="java.lang.String"--%>
<div>${info}</div>
<div>

    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation"><a href="#enableTable"
                                   aria-controls="enableTable" role="tab" data-toggle="tab">当前可填写的表</a></li>
        <li role="presentation"><a href="#uploadFinished"
                                   aria-controls="uploadFinished" role="tab" data-toggle="tab">完成的表单上传</a></li>
        <li role="presentation"><a href="#finishedView"
                                   aria-controls="finishedView" role="tab" data-toggle="tab">查看已经完成的表</a></li>
    </ul>

    <div class="tab-content">
        <div role="tabpanel" class="tab-pane" id="enableTable">
            <ul class="list-group">
                <%--@elvariable id="sheetColumnList" type="java.util.List<cn.medemede.excelcard.model.SheetColumn>"--%>
                <c:forEach var="sheetColumn" items="${sheetColumnList}">
                    <li class="list-group-item">
                        <a href="/downloadteacher/${sheetColumn.sheetName}">${sheetColumn.sheetName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div role="tabpanel" class="tab-pane" id="uploadFinished">
            <h3>上传Excel文档</h3>
            <form action="uploadteacher" method="post" enctype="multipart/form-data">
                <input type="file" name="excelfile">
                <br><br>
                <input type="submit" value="提交">
            </form>
        </div>
        <div role="tabpanel" class="tab-pane" id="finishedView">
            <%--@elvariable id="finishedSheetName" type="java.util.List<java.lang.String>"--%>
            <c:forEach var="sheetName" items="${finishedSheetName}">
                <li class="list-group-item">
                    <a href="/downloadfinished/${sheetName}">${sheetName}</a>
                </li>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>

