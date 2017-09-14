<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">
        label {
            display: inline-block;
            text-align: right;
        }

        .normal {
            width: 150px;
            height: 30px;
        }

        #login {
            background-color: cornflowerblue;
            color: white;
            width: 150px;
        }

        #username {
            margin-bottom: 10px;
        }

        .center {
            text-align: center;
        }

        .mainDiv {
            width: 400px;
            height: 400px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -200px;
            margin-top: -200px;
        }

    </style>

    <script type="text/javascript" src="./jquery/jquery-3.2.1.js">
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#login").click(function () {
                $("#login").attr({"disabled": "disabled"});
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/login.action",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(getData()),
                    dataType: "json",
                    success: function (message) {
                        if (message.code == 1001) {
                            window.location.href = "${pageContext.request.contextPath}/web/add.jsp";
                            return;
                        }
                        alert(message.message);
                        $("#login").removeAttr("disabled")
                    },
                    error: function (message) {
                        alert(message.message);
                        $("#login").removeAttr("disabled")
                    }

                });
            })
        });

        function getData() {
            return {
                "username": $("#username").val(),
                "password": $("#password").val()
            };
        }
    </script>
</head>

<body id="body">

<div class="mainDiv">
    <form action="${pageContext.request.contextPath}/v1/login.action" method="post">
        <div class="center">
            <label>username：</label><input id="username" class="normal" placeholder="请输入用户名"><br>
            <label>password：</label><input id="password" class="normal" placeholder="请输入密码"><br>
        </div>
    </form>

    <div class="center login"><input id="login" type="button" value="登录"></div>
</div>
</body>
</html>
