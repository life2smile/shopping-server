<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">

        body {
            min-width: 600px;
        }

        input, .typeSelect {
            margin-bottom: 10px;
        }

        .normal, .typeSelect {
            width: 75%;
            height: 40px;
        }

        #addCoupon, #addCoupon-first {
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
        var selectType = -1;
        var itemList;
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
                    url: "${pageContext.request.contextPath}/v1/addPromotionItemBannerController.action",
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

            $(".typeSelect").change(function () {
                selectType = 0;
            });


            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/v1/getAllType.action",
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


            $("#addCoupon-first").click(function () {
                if (checkFormDataForFirst() != null) {
                    var msg = checkFormDataForFirst();
                    alert(msg);
                    return;
                }
                $("#addCoupon-first").attr({"disabled": "disabled"});
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/insertCustomItemController.action",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(getJsonDataForFirst()),
                    dataType: "json",
                    success: function (message) {
                        alert(message.message);
                        $("#addCoupon-first").removeAttr("disabled")
                    },
                    error: function (message) {
                        alert(message.message);
                        $("#addCoupon-first").removeAttr("disabled")
                    }

                });
            });
        });


        function checkFormDataForFirst() {
            var msg = null;

            if (!$(".desc").val()) {
                msg = "商品描述必填!";
                return msg;
            }

            if (!$(".originPrice").val() || isNaN($(".originPrice").val())) {
                msg = "原价格必填，格式如：10或者10.0或者10.00";
                return msg;
            }

            if (!$(".nowPrice").val() || isNaN($(".nowPrice").val())) {
                msg = "现价格必填，格式为：10或者10.0或者10.00";
                return msg;
            }

            if ($(".originPrice").val() - $(".nowPrice").val() < 0) {
                msg = "原价格不能低于现价格!";
                return msg;
            }

            if (!$(".imgUrl").val()) {
                msg = "商品url必填!";
                return msg;
            }

            if (!$(".actionUrl").val()) {
                msg = "购买链接必填!";
                return msg;
            }

            return msg;
        }

        function checkFormData() {
            var msg = null;

            if (selectType == -1 || !$(".typeSelect").val()) {
                msg = "商品品类必填!";
                return msg;
            }

            if (!$("#originPrice").val() || isNaN($("#originPrice").val())) {
                msg = "价格必填，格式如：10或者10.0或者10.00";
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

            return msg;
        }

        function getJsonData() {
            return {
                "type": $(".typeSelect").val(),
                "price": $("#originPrice").val(),
                "imgUrl": trimStr($("#imgUrl").val()),
                "actionUrl": trimStr($("#actionUrl").val())//跳转链
            }
        }

        function getJsonDataForFirst() {
            return {
                "description": $(".desc").val(),
                "imgUrl": trimStr($(".imgUrl").val()),
                "originPrice": $(".originPrice").val(),
                "promotionPrice": $(".nowPrice").val(),
                "actionUrl": trimStr($(".actionUrl").val())//跳转链
            }
        }


        function trimStr(str) {
            return $.trim(str)
        }
    </script>
</head>

<h1>精品区-热门好货/为您推荐/优选清单数据添加：</h1>

<form id="coupon-first" action="${pageContext.request.contextPath}/v1/insertCustomItemController.action"
      method="post">
    <div>
        <label>商品描述：</label><input class="normal desc" placeholder="请描述该商品"><br>
        <label>原价：</label><input class="normal originPrice" placeholder="输入商品原来的价格"><br>
        <label>现价：</label><input class="normal nowPrice" placeholder="输入商品当前的价格"><br>
        <label>商品图片url：</label><input class="normal imgUrl" placeholder="如：http://www.google.com"><br>
        <label>购买链接：</label><input class="normal actionUrl" placeholder="如：http://www.google.com"><br>
    </div>
</form>

<div id="addDiv"><input id="addCoupon-first" type="button" value="添加"></div>


<hr>

<h1>精品区-良品精选数据添加：</h1>

<form id="coupon" action="${pageContext.request.contextPath}/v1/addPromotionItemBannerController.action"
      method="post">
    <div>
        <label>选择商品品类：</label>
        <select class="typeSelect">
            <option value="">请选择</option>
        </select><br>
        <label>价格：</label><input class="normal" id="originPrice" placeholder="输入商品当前价格"><br>
        <label>商品图片url：</label><input class="normal" id="imgUrl" placeholder="如：http://www.google.com"><br>
        <label>购买链接：</label><input class="normal" id="actionUrl" placeholder="如：http://www.google.com"><br>
    </div>
</form>

<div id="addDiv"><input id="addCoupon" type="button" value="添加"></div>
</body>
</html>
