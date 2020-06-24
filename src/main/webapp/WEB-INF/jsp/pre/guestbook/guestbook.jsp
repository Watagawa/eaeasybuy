<%--
  Created by IntelliJ IDEA.
  User: hz
  Date: 2020/6/23
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="../../common/pre/header.jsp" %>
    <title>易买网</title>
</head>
<%@ include file="../../common/pre/searchBar.jsp" %>
<body>
<center><h3>投诉与建议</h3></center>
<center>
    标题:
    <input type="text" id="title" style="width:80%"></center>
<br/>
<center>
    内容：
    <input type="text" id="remake" style="width:80%; height: 500px">
</center>
<center>
    <button onclick="x()">提交</button>
</center>
<script src="//cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    function x() {
        $.ajax({
            type: 'POST',
            url: "${pageContext.request.contextPath}/guestbook/addGuest",
            data: {
                title: $("#title").val(),
                remake: $("#remake").val()
            }, dataType: "text",
            success: function (data) {
                alert(data)
                window.location.reload();
            }
        })
    }
</script>
</body>
<%@ include file="../../common/pre/footer.jsp" %>

</html>
