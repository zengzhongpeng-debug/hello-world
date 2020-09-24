<%--
  Created by IntelliJ IDEA.
  User: zzp
  Date: 2020/9/24
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>


    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src="/day16/checkCodeServlet"+new Date().getTime();//this.src 当前对象的src变量
            }
        }

    </script>
    <style>
        div{
            color: red;
        }
    </style>

</head>
<body>
        <form action="/day16/loginServlet" method="post">
            <table>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password"></td>
                </tr>

                <tr>
                    <td>验证码</td>
                    <td><input type="text" name="checkCode"></td>
                </tr>

                <tr>
                    <td colspan="2"><img src="/day16/checkCodeServlet"></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" value="登陆"></td>
                </tr>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username"></td>
                </tr>



            </table>


        </form>

        <div><%=request.getAttribute("cc_error") == null ? " " :request.getAttribute("cc_error") %>></div>
        <div><%=request.getAttribute("login_error") == null ? " " :request.getAttribute("login_error") %>></div>

</body>
</html>
