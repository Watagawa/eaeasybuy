<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="../../common/pre/header.jsp" %>
</head>
<body>
<%@ include file="../../common/backend/searchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="../../common/backend/leftBar.jsp"%>
        <div class="m_right" id="content">
            <div class="mem_tit">投诉与建议</div>
            <br>
            <table border="0" class="guestbook_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td width="10%">id</td>
                    <td width="20%">标题</td>
                    <td width="10%">时间</td>
                    <td width="5%" colspan="1">操作</td>
                </tr>
                <c:forEach items="${guestbookList}" var="temp">
                    <tr>
                        <td>${temp.id}</td>
                        <td>
                            <a href="${ctx}/guestbook/getGuestById?id=${temp.id}" target="_blank">
                                <span>${temp.title}</span>
                            </a>
                        </td>
                        <td>${temp.createTime}</td>
                        <td><a href="javascript:void(0);" onclick="deleteById('${temp.id}');">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%@ include file="../../common/pre/pagerBar.jsp" %>
        </div>
    </div>
    <%@ include file="../../common/pre/footer.jsp" %>
</div>
<script src="//cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    function deleteById(id) {
        var bool=window.confirm("确认删除?");
        if(bool) {
                $.ajax({
                    type: 'POST',
                    url: "${pageContext.request.contextPath}/guestbook/delectGuestById",
                    data: {
                        id:id
                    }, dataType: "text",
                    success: function (data) {
                        alert(data)
                        window.location.reload();
                    }
                })
        }
    }
</script>
</body>
</html>


