<%-- 
    Document   : viewOrder
    Created on : Mar 7, 2021, 11:27:28 AM
    Author     : QH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Rental car | History</title>
        <style>
            header {
                background-color: black;
                width: 100%;
                /*position: relative;*/
                z-index: 2;
            }

            header .navbar-brand i {
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
            footer{
                margin-top: 70px;
                background-color: #1e2228;
                color: white;
            }
            .write p{
                margin: 0;
                text-align: center;
            }
            .lastsection a{
                padding-left: 10px;
                padding-right: 10px;
                color: green;
                transition: 0.5s;
            }
            .lastsection a:hover{
                background-color: Green;
                color: white;
                text-decoration: none;
                transition: 0.5s;
            }
            td{
                line-height: 100%;
                text-align: center;
            }
            .table th{
                text-align: center;
            }
            .lastsection a{
                padding-left: 10px;
                padding-right: 10px;
                color: green;
                transition: 0.5s;
            }
            .lastsection a:hover{
                background-color: Green;
                color: white;
                text-decoration: none;
                transition: 0.5s;
            }
        </style>
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
                                        <a class="nav-link" href="viewCart.jsp"><i style="font-size: 15px" class="fa fa-shopping-cart"></i> View Cart</a>
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

        <section>
            <form action="searchOrder" method="POST">
                <div class="d-flex justify-content-center my-5">
                    <div>
                        <input type="radio" <c:if test="${sessionScope.SELECTORDERSEARCH eq 1}">checked</c:if> name="rdo" value="1" />
                        <input type="text" id="name" name="txtSearch" placeholder="Search name..." value="${sessionScope.SEARCHNAMEORDER}" />
                    </div>
                    <div class="px-5">
                        <input type="radio" <c:if test="${sessionScope.SELECTORDERSEARCH eq 2}">checked</c:if> name="rdo" value="2" />
                        <input type="date" id="date" name="txtOrderDate" value="${sessionScope.NOWDATE}" />
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary" value="Search" />
                    </div>
                </div>
            </form>
        </section>                        

        <c:if test="${empty sessionScope.MAPORDER}">
            <div class="text-center container" style="margin-top: 191px; margin-bottom: 191px;">
                <h2 class="text-secondary">You're not bought any thing yet!!!</h2>
                <a href="car">Click here to back to Rental Car</a>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.MAPORDER}">
            <c:if test="${not empty requestScope.ERRORCANCEL}">
                <div class="text-center">
                    <p class="display-5 alert alert-danger">${requestScope.ERRORCANCEL}</p>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.ERRORFEEDBACK}">
                <div class="text-center">
                    <p class="display-5 alert alert-danger">${requestScope.ERRORFEEDBACK}</p>
                </div>
            </c:if>
            <c:forEach var="map" items="${sessionScope.MAPORDER}">
                <div class="text-center container">
                    <p class="display-5 text-secondary">${map.key.fullname} - ${map.key.phone} - ${map.key.address} - ${map.key.dateOrder}</p> 
                </div>
                <c:set value="0" var="total"/>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Color</th>
                            <th scope="col">Year</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Rental date</th>
                            <th scope="col">Return date</th>
                            <th scope="col">Cancel</th>
                            <th scope="col">Rate</th>
                            <th scope="col">Feedback</th>
                        </tr>
                    </thead>
                    <c:forEach var="orderDetail" items="${map.value}">

                        <tbody>
                            <tr>
                                <td>${orderDetail.carName}</td>
                                <td>${orderDetail.color}</td>
                                <td>${orderDetail.year}</td>
                                <td>${orderDetail.quantity}</td>
                                <td>${orderDetail.price*orderDetail.quantity}</td>
                                <td>${orderDetail.rental}</td>
                                <td>${orderDetail.back}</td>
                        <form action="cancel" method="POST">
                            <input type="hidden" name="cancelRental" value="${orderDetail.rental}" />
                            <input type="hidden" name="cancelReturn" value="${orderDetail.back}" />
                            <input type="hidden" name="cancelId" value="${orderDetail.idInvoice}" />
                            <input type="hidden" name="cancelIdCar" value="${orderDetail.idCar}" />
                            <td>
                                <input type="submit" class="btn btn-outline-danger" onclick="return confirm('Are you sure about that?')" <c:if test="${orderDetail.status eq 0}">disabled</c:if> name="btAction" value="Cancel" />
                                </td>

                            </form>
                            <form action="feedback" method="POST">
                                <td>
                                    <input type="number" min="1" max="10" name="txtRate" value="${orderDetail.rate}"/>
                            </td>
                            <input type="hidden" name="feedbackRental" value="${orderDetail.rental}" />
                            <input type="hidden" name="feedbackReturn" value="${orderDetail.back}" />
                            <input type="hidden" name="feedbackId" value="${orderDetail.idInvoice}" />
                            <input type="hidden" name="feedbackIdCar" value="${orderDetail.idCar}" />
                            <td>
                                <input type="submit" class="btn btn-outline-primary" <c:if test="${orderDetail.status eq 0}">disabled</c:if> name="btAction" value="Feedback" />
                                </td>
                            </form>
                        </tr> 
                    </tbody>
                <c:set var="total" value="${total+orderDetail.price*orderDetail.quantity}"/>

            </c:forEach>
            <tr>
                <td colspan="11" class="text-center">
                    <p class="text-success">Total price: ${total - (total*map.key.discountCode)/100}$</p>
                </td>
            </tr>
        </table>
    </c:forEach>

    <c:if test="${not empty sessionScope.VIEWORDER}">
        <section>
            <div class="lastsection text-center mt-5">
                <c:if test="${sessionScope.CENDPAGE > 1}">
                    <c:forEach var="i" begin="1" end="${sessionScope.CENDPAGE}">
                        <a class="ml-4" href="viewOrder?cindex=${i}" <c:if test="${i eq sessionScope.CINDEX}">style="border: 1px solid green; background-color: green; color: white; text-decoration: none"</c:if>>${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </section>
    </c:if>

    <c:if test="${not empty sessionScope.SEARCHORDER}">
        <section>
            <div class="lastsection text-center mt-5">
                <c:if test="${sessionScope.SENDPAGE > 1}">
                    <c:forEach var="i" begin="1" end="${sessionScope.SENDPAGE}">
                        <a class="ml-4" href="searchOrder?sindex=${i}" <c:if test="${i eq sessionScope.SINDEX}">style="border: 1px solid green; background-color: green; color: white; text-decoration: none;"</c:if>>${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </section>
    </c:if>

</c:if>

<c:import url="footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
