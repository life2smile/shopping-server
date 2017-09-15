<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">
        body {
            min-width: 600px;
        }

        input, .typeSelect, .platformSelect {
            margin-bottom: 10px;
        }

        .normal, .platformSelect, .typeSelect {
            width: 75%;
            height: 40px;
        }

        #addCoupon {
            height: 40px;
            width: 80px;
            font-size: 16px;
            color: white;
            background-color: cornflowerblue;
        }

        #addDiv {
            text-align: center;
        }

        label {
            display: inline-block;
            width: 150px;
            text-align: right;
        }

        span {
            text-align: center;;
        }
    </style>

    <script type="text/javascript" src="../jquery/jquery-3.2.1.js">
    </script>

    <script type="text/javascript">
        var hasTicket = -1;
        var selectType = -1;
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
                    url: "${pageContext.request.contextPath}/v1/addCouponController.action",
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

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/v1/getCategoryType.action",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(getJsonData()),
                dataType: "json",
                success: function (message) {
                    itemList = message.data;
                    for (var i = 0; i < itemList.length; i++) {
                        $(".typeSelect").append("<option value='" + itemList[i].type + "'>" + itemList[i].desc + "</option>")
                    }
                },
                error: function (message) {
                    alert("商品品类获取失败!请刷新重新获取!");
                }

            });

            $(".typeSelect").change(function () {
                selectType = 0;
            });

            $(".platformSelect").change(function () {
                selectPlatform = 0;
            })
        });

        function checkFormData() {
            var msg = null;
            if (selectType == -1 || !$(".typeSelect").val()) {
                msg = "商品品类必填!";
                return msg;
            }

            if (!$("#desc").val()) {
                msg = "商品描述必填!";
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

            if (hasTicket == -1) {
                msg = "请选择是否包含优惠券!";
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
                "type": $(".typeSelect").val(),
                "description": $("#desc").val(),//推广文案
                "originPrice": $("#originPrice").val(),
                "couponPrice": $("#couponPrice").val(),
                "couponValue": $("#originPrice").val() - $("#couponPrice").val(),
                "imgUrl": trimStr($("#imgUrl").val()),
                "actionUrl": trimStr($("#actionUrl").val()),//跳转链
                "hasTicket": hasTicket != 0,
                "platformImg": $(".platformSelect").val(),
                "platformDesc": $(".platformSelect").text() + "专享"
            };
        }

        function getTicketValue(val) {
            hasTicket = val;
        }

        function trimStr(str) {
            return $.trim(str)
        }

    </script>
</head>

<h1>特惠区商品添加：</h1>

<form id="coupon" action="${pageContext.request.contextPath}/v1/addCouponController.action"
      method="post">
    <div>
        <label>选择商品品类：</label>
        <select class="typeSelect">
            <option value="">请选择</option>
            <option value="1">零食</option>
            <option value="2">男装</option>
            <option value="3">女装</option>
            <option value="4">百货</option>
            <option value="5">母婴</option>
            <option value="6">家居</option>
            <option value="7">书籍</option>
        </select><br>
        <label>商品描述：</label><input class="normal" id="desc" placeholder="输入商品描述"><br>
        <label>原价：</label><input class="normal" id="originPrice" placeholder="输入商品原来价格"><br>
        <label>现价：</label><input class="normal" id="couponPrice" placeholder="输入商品打折后或者减去优惠券后的价格"><br>
        <label>商品图片url：</label><input class="normal" id="imgUrl" placeholder="如：http://www.google.com"><br>
        <label>购买链接：</label><input class="normal" id="actionUrl" placeholder="如：http://www.google.com"><br>
        <label>是否含有优惠券：</label><input id="noTicket" type="radio" name="ticket" value="0"
                                      onclick="getTicketValue(this.value)">无
        <input id="hasTicket" type="radio" name="ticket" value="1" onclick="getTicketValue(this.value)">有<br>

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
