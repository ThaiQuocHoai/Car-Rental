<%-- 
    Document   : nav
    Created on : Mar 3, 2021, 8:42:18 AM
    Author     : QH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
              integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
              crossorigin="anonymous" />
        <style>
            header {
                background-color: rgba(0, 0, 0, 0.2);
                width: 100%;
                position: fixed;
                z-index: 2;
            }

            header i {
                font-size: 50px;
                color: rgb(255, 208, 0);
            }

            header a {
                color: white;
            }

            header a.active {
                color: rgb(255, 208, 0);
            }

            header a:hover {
                color: rgb(255, 208, 0);
            }

            header a:focus {
                color: rgb(255, 208, 0);
            }

            .icon__header a:hover {
                color: white;
            }

            .rentalcar__carosel {
                background: url('https://preview.colorlib.com/theme/cardoor/assets/img/banner.jpg?fbclid=IwAR3CdRBypvVUMVkH0OPoc17UPctyINslPbiqqL_-GLCCfYBYXe8TYyoHiwk');
                background-size: cover;
                height: 700px;
                position: relative;
            }

            .content__carosel {
                width: 100%;
                height: 100%;
                background: rgba(2, 2, 2, 0.8);

            }

            .text__carosel {
                position: absolute;
                top: 50%;
                left: 10%;
                text-align: center;
            }
            .about{
                margin: 70px 0;
            }
            .about__top{
                text-align: center;
                margin-bottom: 50px;
            }
            
        </style>
    </head>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg">
                <div class="container-fluid container">
                    <div class="icon__header">
                        <a class="navbar-brand" href="#"><i class="fa fa-car"></i> <span>CAR DOOR</span></a>
                    </div>
                    <div class="nav__header">
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="home.jsp">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#ABOUT">About</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="car">Car</a>
                                </li>
                                <c:set var="user" value="${sessionScope.USER}"/>
                                <c:if test="${empty user}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="login.jsp">Login</a>
                                    </li> 
                                </c:if>
                                <c:if test="${not empty user}">
                                    <li class="nav-item">
                                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">${user.fullname}</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="logout">Logout</a>
                                    </li> 
                                    <li class="nav-item">
                                        <a class="nav-link" href="viewCart.jsp"><i class="fa fa-shopping-cart"></i> View Cart</a>
                                    </li> 
                                    <li class="nav-item">
                                        <a class="nav-link" href="viewOrder"> History</a>
                                    </li> 
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
