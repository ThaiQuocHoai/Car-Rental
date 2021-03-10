<%-- 
    Document   : login
    Created on : Mar 2, 2021, 12:54:59 PM
    Author     : QH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
              integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
              crossorigin="anonymous" />
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <title>Rental car | Login</title>
    </head>
    <body>
        <c:import url="nav.jsp"/>
        <div class="container">
            <p class="display-4 font-weight-bold text-center">
                Login
            </p>
            <form action="login" id="my_captcha_form" method="POST" >
                <font style="color: red">${requestScope.FAIL}</font><br/>
                <div class="form-group">
                    <p>Username</p>
                    <font style="color: red">${requestScope.LOGIN_ERROR.usernameError}</font><br/>
                    <input type="text" class="form-control" name="txtUsername" placeholder="abc@abc.com" value="${param.txtUsername}"/>
                </div>
                <div class="form-group">
                    <p>Password</p>
                    <font style="color: red">${requestScope.LOGIN_ERROR.passwordError}</font><br/>
                    <input type="password" class="form-control" name="txtPassword" placeholder="******" value=""/>
                </div>
                <div class="g-recaptcha" id="my_captcha_form" data-sitekey="6LdmFW4aAAAAAGQFlIgykQQ3Ui_pk_XD_D7d7yB8">
                </div>

                <div class="form-group text-center mt-5">
                    <input type="submit" class="btn btn-success " value="Login" name="btAction" />
                    <br/>
                    <br/>
                    <a href="signup.jsp">Create a new account?</a>
                </div>
            </form>
        </div>
        <c:import url="footer.jsp"/>

        <script>
            document.getElementById("my_captcha_form").addEventListener("submit", function (evt)
            {

                var response = grecaptcha.getResponse();
                if (response.length == 0)
                {

                    alert("Please verify you are not robot!");
                    evt.preventDefault();
                    return false;
                }


            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
                integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
                integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
        crossorigin="anonymous"></script>
    </body>
</html>
