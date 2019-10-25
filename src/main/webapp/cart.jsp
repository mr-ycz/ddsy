<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<head>
	<title>天天生鲜-购物车</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
</head>
<body>

	<script>
		$(function () {
			$.post("${pageContext.request.contextPath}/cart/getNum","uid=${userid}",function(data){
				$("#show_count").html(data);
				$("#allgoods").html(data);
			})
		})
	</script>
	<script>

        function pNum(pid,p,num) {
            var number=parseInt(num);//数据库中取出的当前商品数量
            var price=parseFloat(p);
            $.post("${pageContext.request.contextPath}/cart/addcart","gid="+pid+"&uid=${userid}&gnum=1&money="+price,function(data){
                location.href="${pageContext.request.contextPath}/cart/tocart?uid=${userid}";
            })
        }

        function mNum(pid,p,num,count) {
            var newnum=document.getElementById("num"+count);//输入框的值
            var number=parseInt(num);//数据库中取出的当前商品数量
            var price=parseFloat(p);
            if (parseInt(newnum.value)>1){
                $.post("${pageContext.request.contextPath}/cart/addcart","gid="+pid+"&uid=${userid}&gnum=-1&money="+price,function(data){
                    location.href="${pageContext.request.contextPath}/cart/tocart?uid=${userid}";
                })
			}else {
                if (confirm("确认要删除掉这款商品吗?")){
                    $.post("${pageContext.request.contextPath}/cart/delcart","gid="+pid+"&uid=${userid}",function(data){
                        location.href="${pageContext.request.contextPath}/cart/tocart?uid=${userid}";
                    })
				}else {
                    return;
				}
			}
        }

        function del(gid) {
			if (confirm("确定要删除吗?")){
                $.post("${pageContext.request.contextPath}/cart/delcart","gid="+gid+"&uid=${userid}",function(data){
                    location.href="${pageContext.request.contextPath}/cart/tocart?uid=${userid}";
                })
			}else {
			    return;
			}
        }
        
        function tocheck(id,gid) {
			if (id!=-1){
                if(!$(".checknum").checked){
                    $("#allcheck").prop("checked",false); // 子复选框某个不选择，全选也被取消
                }
                var choicelength=$("input[type='checkbox'][class='checknum']").length;
                var choiceselect=$("input[type='checkbox'][class='checknum']:checked").length;

                if(choicelength==choiceselect){
                    $("#allcheck").prop("checked",true);
                }

                if ($("#check"+id).prop('checked')) {
                    $.post("${pageContext.request.contextPath}/cart/opcart","gid="+gid+"&uid=${userid}&op=1&allfee="+$("#total").html()+"&number="+$("#show_count").html(),function(data){
                        $("#show_count").html(${allGoodsNum});
                        $("#total").html(data);
                    })
				}else {
                    $.post("${pageContext.request.contextPath}/cart/opcart","gid="+gid+"&uid=${userid}&op=2&allfee="+$("#total").html()+"&number="+$("#show_count").html(),function(data){
                        $("#show_count").html(${allGoodsNum});
                        $("#total").html(data);
                    })
				}

			}else {
                var allcheck=document.getElementById("allcheck");
                var choice=document.getElementsByName("check");
                for(var i=0;i<choice.length;i++){
                    choice[i].checked=allcheck.checked;
                }
			}
        }
	</script>
	<div class="header_con">
		<div class="header">
			<div class="welcome fl">欢迎来到天天生鲜!</div>
			<div class="fr">
				<div class="login_info fl">
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
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>		
	</div>

	<script>
        $(function () {
            $.post("${pageContext.request.contextPath}/cart/getcart","uid=${userid}",function(data){

            })
        })
	</script>

	<div class="total_count">全部商品<em id="allgoods"></em>件</div>
	<ul class="cart_list_th clearfix">
		<li class="col01">商品名称</li>
		<li class="col02">商品单位</li>
		<li class="col03">商品价格</li>
		<li class="col04">数量</li>
		<li class="col05">小计</li>
		<li class="col06">操作</li>
	</ul>

	<c:forEach items="${carts}" var="ct" varStatus="i">
		<ul class="cart_list_td clearfix">
			<li class="col01"><input type="checkbox" id="check${i.count}" class="checknum" onclick="tocheck(${i.count},${ct.goods.id})" name="check" checked="checked"></li>
			<li class="col02"><img src="${pageContext.request.contextPath}/images/${ct.goods.picture}"></li>
			<li class="col03">${ct.goods.name}<br><em>${ct.goods.price}元/500g</em></li>
			<li class="col04">500g</li>
			<li class="col05">${ct.goods.price}元</li>
			<li class="col06">
				<div class="num_add">
					<a href="javascript:;" class="add fl" onclick="pNum(${ct.goods.id},${ct.goods.price},${ct.gnum})">+</a>
					<input type="text" class="num_show fl" onblur="changeNum()" readonly="readonly" id="num${i.count}" value="${ct.gnum}">
					<a href="javascript:;" class="minus fl" onclick="mNum(${ct.goods.id},${ct.goods.price},${ct.gnum},${i.count})">-</a>
				</div>
			</li>
			<li class="col07">${ct.money}元</li>
			<li class="col08"><a href="#" onclick="del(${ct.goods.id})">删除</a></li>
		</ul>
	</c:forEach>

	<ul class="settlements">
		<li class="col01"><input type="checkbox" id="allcheck" onclick="tocheck(-1)" name="allcheck" checked="checked"></li>
		<li class="col02">全选</li>
		<li class="col03">合计(不含运费)：<span>¥</span><em id="total">${cartfee}</em><br>共计<b id="show_count"></b>件商品</li>
		<li class="col04"><a href="place_order.jsp">去结算</a></li>
	</ul>

	<div class="footer">
		<div class="foot_link">
			<a href="#">关于我们</a>
			<span>|</span>
			<a href="#">联系我们</a>
			<span>|</span>
			<a href="#">招聘人才</a>
			<span>|</span>
			<a href="#">友情链接</a>		
		</div>
		<p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
		<p>电话：010-****888    京ICP备*******8号</p>
	</div>
	
</body>
</html>