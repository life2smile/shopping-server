<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">

        body {
            min-width: 600px;
        }

        input, .platformSelect {
            margin-bottom: 10px;
        }

        .normal, .platformSelect {
            width: 75%;
            height: 40px;
        }

        #addCoupon {
            height: 40px;
            width: 80px;
            text-align: center;
            font-size: 16px;
            color: white;
            background-color: cornflowerblue;
        }

        label {
            display: inline-block;
            width: 150px;
            text-align: right;
        }

        #addDiv {
            text-align: center;
        }
    </style>

    <script type="text/javascript" src="../jquery/jquery-3.2.1.js">
    </script>

    <script type="text/javascript">
        var selectPlatform = -1;
        $(document).ready(function () {

            $("#addCoupon").click(function () {
                if (checkFormData() != null) {
                    var msg = checkFormData();
                    alert(msg);
                    return;
                }
                $("#addCoupon").attr({"disabled": "disabled"});
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/addDiscountController.action",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(getJsonData()),
                    dataType: "json",
                    success: function (message) {
                        alert(message.message);
                        $("#addCoupon").removeAttr("disabled")
                    },
                    error: function (message) {
                        alert(message.message);
                        $("#addCoupon").removeAttr("disabled")
                    }

                });
            });

            $(".platformSelect").change(function () {
                selectPlatform = 0;
            })
        });

        function checkFormData() {
            var msg = null;

            if (!$("#title").val()) {
                msg = "请填写商品简介!";
                return msg;
            }

            if (!$("#desc").val()) {
                msg = "请详细描述该商品!";
                return msg;
            }

            if (!$("#originPrice").val() || isNaN($("#originPrice").val())) {
                msg = "原价格必填，格式如：10或者10.0或者10.00";
                return msg;
            }

            if (!$("#couponPrice").val() || isNaN($("#couponPrice").val())) {
                msg = "现价格必填，格式为：10或者10.0或者10.00";
                return msg;
            }

            if ($("#originPrice").val() - $("#couponPrice").val() < 0) {
                msg = "原价格不能低于现价格!";
                return msg;
            }

            if (!$("#imgUrl").val()) {
                msg = "商品url必填!";
                return msg;
            }

            if (!$("#actionUrl").val()) {
                msg = "购买链接必填!";
                return msg;
            }

            if (selectPlatform == -1 || !$(".platformSelect").val()) {
                msg = "请选择平台!";
                return msg;
            }

            return msg;
        }

        function getJsonData() {
            return {
                "title": $("#title").val(),//推广文案,
                "desc": $("#desc").val(),//推广文案
                "originPrice": $("#originPrice").val(),
                "promotionPrice": $("#couponPrice").val(),
                "discount": $("#originPrice").val() - $("#couponPrice").val(),
                "imgUrl": trimStr($("#imgUrl").val()),
                "actionUrl": trimStr($("#actionUrl").val()),//跳转链
                "platformImg": $(".platformSelect").val(),
                "platformDesc": $(".platformSelect").find("option:selected").text() + "专享",
            };
        }

        function trimStr(str) {
            return $.trim(str)
        }

    </script>
</head>

<h1>发现区商品添加：</h1>

<form id="coupon" action="${pageContext.request.contextPath}/v1/addDiscountController.action"
      method="post">
    <div>
        <label>商品简介：</label><input class="normal" id="title" placeholder="简单介绍商品"><br>
        <label>商品详细描述：</label><input class="normal" id="desc" placeholder="输入商品描述"><br>
        <label>原价：</label><input class="normal" id="originPrice" placeholder="输入商品原来价格"><br>
        <label>现价：</label><input class="normal" id="couponPrice" placeholder="输入商品打折后或者减去优惠券后的价格"><br>
        <label>商品图片url：</label><input class="normal" id="imgUrl" placeholder="如：http://www.google.com"><br>
        <label>购买链接：</label><input class="normal" id="actionUrl" placeholder="如：http://www.google.com"><br>
        <label>选择商品平台：</label>
        <select class="platformSelect">
            <option value="">请选择</option>
            <option value="tianmao_icon.png">天猫</option>
            <option value="taobao_icon.jpg">淘宝</option>
            <option value="jingdong_icon.png">京东</option>
            <option value="mogujie_icon.png">蘑菇街</option>
        </select>
    </div>
</form>

<div id="addDiv"><input id="addCoupon" type="button" value="添加"></div>
</body>
</html>
