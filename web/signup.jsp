<%-- 
    Document   : signup
    Created on : Mar 2, 2021, 9:59:12 AM
    Author     : QH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
              integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
              crossorigin="anonymous" />
        <title>Rental car | Sign up</title>
    </head>
    <body>
        <c:import url="nav.jsp"/>
        <div class="container">
            <p class="display-4 font-weight-bold text-center">
                Sign up
            </p>
            <form action="signup" method="POST">
                <c:set var="error" value="${requestScope.REGISTER_FAIL}"/>
                <div class="form-group">
                    <p>Username</p>
                    <font style="color: red">${error.usernameError}</font><br/>
                    <input type="text" class="form-control" name="txtUsername" placeholder="abc@abc.com" value="${param.txtUsername}"/>
                </div>
                <div class="form-group">
                    <p>Password</p>
                    <font style="color: red">${error.passwordError}</font><br/>
                    <input type="password" class="form-control" name="txtPassword" placeholder="******" value=""/>
                </div>
                <div class="form-group">
                    <p>Confirm</p>
                    <font style="color: red">${error.confirmPasswordError}</font><br/>
                    <input type="password" class="form-control" name="txtConfirm" placeholder="******" value=""/>
                </div>
                <div class="form-group">
                    <p>Fullname</p>
                    <font style="color: red">${error.fullnameError}</font><br/>
                    <input type="text" class="form-control" name="txtFullname" placeholder="Nguyen Van A" value="${param.txtFullname}"/>
                </div>
                <div class="form-group">
                    <p>Address</p>
                    <font style="color: red">${error.addressError}</font><br/>
                    <input type="text" class="form-control" name="txtAddress" placeholder="9 - dictrict" value="${param.txtAddress}"/>
                </div>
                <div class="form-group">
                    <p>Phone number</p>
                    <font style="color: red">${error.phoneError}</font><br/>
                    <input type="tel" class="form-control" name="txtPhone" placeholder="0123456789" pattern="[0-9]{10}" value="${param.txtPhone}"/>
                </div>
                <div class="form-group text-center mt-5">
                    <input type="submit" class="btn btn-success " value="Sign up" name="btAction" />
                </div>
            </form>
        </div>
        <c:import url="footer.jsp"/>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
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
