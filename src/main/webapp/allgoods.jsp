<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/22
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

<ul class="goods_type_list clearfix">
    <c:forEach items="${goodsTypes3.goods}" var="g">
        <li>
            <a href="#"><img src="${pageContext.request.contextPath}/images/${g.picture}"></a>
            <h4><a href="#">${g.name}</a></h4>
            <div class="operate">
                <span class="prize">￥${g.price}</span>
                <span class="unit">5.50/500g</span>
                <a href="#" class="add_goods" title="加入购物车"></a>
            </div>
        </li>
    </c:forEach>
</ul>
