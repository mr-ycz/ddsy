<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/24
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>\

<div>
    欢迎您：
    <em>
        <shiro:user>
            <shiro:principal/>
            <a href="${pageContext.request.contextPath}/user/logout">
                <span style="float: right;">注销</span>
            </a>
        </shiro:user>
        <shiro:guest>
            <a href="${pageContext.request.contextPath}/login.jsp">请登录</a>
        </shiro:guest>
    </em>
</div>
<div class="header_con">
    <div class="header">
        <div class="welcome fl">欢迎来到天天生鲜!</div>
        <div class="fr">
            <div class="user_link fl">
                <span>|</span>
                <a href="${pageContext.request.contextPath}/user_center_info.jsp">用户中心</a>
                <span>|</span>
                <a href="${pageContext.request.contextPath}/cart/tocart?uid=${userid}">我的购物车</a>
                <span>|</span>
                <a href="${pageContext.request.contextPath}/user_center_order.jsp">我的订单</a>
            </div>
        </div>
    </div>
</div>

<div class="search_bar clearfix">
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo fl"><img src="${pageContext.request.contextPath}/images/logo.png"></a>
    <div class="search_con fl">
        <input type="text" class="input_text fl" name="" placeholder="搜索商品">
        <input type="button" class="input_btn fr" name="" value="搜索">
    </div>
    <div class="guest_cart fr">
        <a href="${pageContext.request.contextPath}/cart/tocart?uid=${userid}" class="cart_name fl">我的购物车</a>
        <div class="goods_count fl" id="show_count"></div>
    </div>
</div>

<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/cart/getNum","uid=${userid}",function(data){
            $("#show_count").html(data);
        })
    })
</script>
