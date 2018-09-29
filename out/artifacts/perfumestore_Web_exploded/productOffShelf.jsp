<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>off the shelf</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--gz --%>
<link rel="stylesheet" href="css/cart.css" type="text/css">
<link rel="stylesheet" href="css/buyleo.css" type="text/css">
<style type="text/css">

a:link, a:visited, a:active {
    text-decoration: none;
}
a:hover { color: #D93600; text-decoration: none;}
a {
    color: #555;
}

</style>
<%--gz --%>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.min.js"></script>
<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<!-- dropdown -->
<script src="js/jquery.easydropdown.js"></script>
</head>
<body>
<jsp:include page='FrontServlet?module=User&command=UserHeader' flush="true"></jsp:include>
	<br /><br /><br />
	<div class="encircle">
		<img src="images/jstjsb.jpg">
		<div class="checkout_order_right">
			<h1>
				Sorry, the product is off the shelf, please go to <a href="/FrontServlet?module=Cart&command=ViewCart">cart</a> to
				modify.
			</h1>
		</div>
	</div>

	<br /><br /><br /><br /><br /><br /><br />
</body>
</html>