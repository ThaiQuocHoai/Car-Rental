<%-- 
    Document   : otp
    Created on : Mar 3, 2021, 1:06:00 AM
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
        <title>Rental car | Verify account</title>
    </head>
    <body>
        <c:import url="nav.jsp"/>
        <div class="container">
            <form action="otp" method="POST">
                <p class="display-4 font-weight-bold text-center">
                    Verify your account
                </p>
                <div class="form-group">
                    <h2 style="color: red">We had sent your verify code into your email ${sessionScope.USER.username}. Please check your e-mail and back to verify. If you don't verify, you cannot user your account!!!</h2>
                    <font style="color: red">${requestScope.OTP_FAIL}</font><br/>
                    <input type="text" class="form-control" name="txtCode"  value="${param.txtCode}"/>
                </div>
                <div class="form-group text-center mt-5" style="margin-bottom: 50px">
                    <input type="submit" class="btn btn-success " value="Verify" name="btAction" />
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
