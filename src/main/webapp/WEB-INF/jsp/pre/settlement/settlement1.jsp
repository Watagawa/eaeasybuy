<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
  var contextPath = "${ctx}";
</script>
<div class="content mar_20">
    <img src="${ctx}/images/img1.jpg"/>
</div>
<!--Begin 第一步：查看购物车 Begin -->
<div class="content mar_20" >
    <table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
        <tr>
            <td class="car_th" width="200">商品名称</td>
            <td class="car_th" width="150">单价</td>
            <td class="car_th" width="150">购买数量</td>
            <td class="car_th" width="130">小计</td>
            <td class="car_th" width="150">操作</td>
        </tr>
        <c:forEach items="${sessionScope.cart.items}" var="temp">
            <tr>
                <td>
                    <div class="c_s_img">
                        <a href="${ctx}/Product?action=queryProductDeatil&id=${temp.product.id}"><img src="${ctx}/files/${temp.product.fileName}" width="73" height="73"/></a>
                    </div>
                        ${temp.product.name}
                </td>
                <td align="center" style="color:#ff4e00;">￥${temp.product.price}</td>
                <td align="center">
                    <div class="c_num">
                        <input type="button" value="" onclick="subQuantity(this,'${temp.product.id}');" class="car_btn_1"/>
                        <input type="text" value="${temp.quantity}" name="" class="car_ipt"/>
                        <input type="button" value="" onclick="addQuantity(this,'${temp.product.id}',${temp.product.stock});" class="car_btn_2"/>
                    </div>
                </td>
                <td align="center" style="color:#ff4e00;">￥${temp.cost}</td>
                <td align="center"><a href="javascript:void(0);" onclick="removeCart('${temp.product.id}');" >删除</a>&nbsp; &nbsp;</td>
            </tr>
        </c:forEach>
        <tr height="70">
            <td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
                <c:if test="${sessionScope.cart==null || sessionScope.cart.sum==null}">
                    <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;">￥0</b></span>
                </c:if>
                <c:if test="${sessionScope.cart!=null && sessionScope.cart.sum!=null}">
                    <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;">￥${sessionScope.cart.sum}</b></span>
                </c:if>
            </td>
        </tr>
        <tr valign="top" height="150">
            <td colspan="6" align="right">
                <a href="${ctx}/Home?action=index"><img src="${ctx}/images/buy1.gif" /></a>&nbsp;&nbsp;
                <c:if test="${sessionScope.cart!=null && sessionScope.cart.sum>0}">
                    <a href="javascript:void(0);" onclick="settlement2();"><img src="${ctx}/images/buy2.gif" /></a>
                </c:if>
            </td>
        </tr>
    </table>
</div>



