<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">

    </style>

    <%--<link rel="stylesheet" href="../jquery/jquery-ui/jquery-ui.css">--%>
    <%--<script src="../jquery/jquery-ui/jquery-ui.min.js"></script>--%>
    <%--<script type="text/javascript" src="../jquery/jquery-3.2.1.js">--%>
    <%--<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">--%>
    <%--</script>--%>

    <link rel="stylesheet" href="../jquery/jquery-ui.css">
    <script src="../jquery/jquery-1.10.2.js"></script>
    <script src="../jquery/jquery-ui.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#tabs").tabs();
        })
    </script>

    <style type="text/css">

        #couponDiv, #discoverDiv, #couponFrame, #discoverFrame, #promotionDiv, #promotionFrame {
            width: 100%;
            height: 100%;
        }
    </style>
</head>

<h1>券立减管理系统</h1>

<div id="tabs">

    <ul>
        <li id="coupon"><a href="#couponDiv">特惠区</a></li>
        <li id="discover"><a href="#discoverDiv">发现区</a></li>
        <li id="promotion"><a href="#promotionDiv">精品区</a></li>
    </ul>

    <div id="couponDiv">
        <iframe id="couponFrame" src="addCoupon.jsp" frameborder="0">
        </iframe>
    </div>

    <div id="discoverDiv">
        <iframe id="discoverFrame" src="addDiscount.jsp" frameborder="0"></iframe>
    </div>

    <div id="promotionDiv">
        <iframe id="promotionFrame" src="addPromotionItem.jsp" frameborder="0"></iframe>
    </div>

</div>

</body>
</html>
