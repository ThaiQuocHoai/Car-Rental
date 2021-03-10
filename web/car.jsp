<%-- 
    Document   : car
    Created on : Mar 2, 2021, 11:18:53 PM
    Author     : QH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
              integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
              crossorigin="anonymous" />
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
        </style>
        <title>Rental car | Car</title>
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
        <c:if test="${not empty requestScope.SUCCESS}">
            <div class="text-center">
                <p class="display-5 alert alert-success">${requestScope.SUCCESS}</p>
            </div>
        </c:if>
        <section style="padding: 56px 0;">
            <c:if test="${not empty requestScope.RENTALERROR}">
                <p class="alert alert-danger text-center">${requestScope.RENTALERROR}</p>
            </c:if>
            <nav class="navbar">
                <div class="container-fluid">
                    <div class="container d-flex" style="justify-content: center; padding: 0 300px;">
                        <form style="width: 100%;" action="search" method="POST">
                            <div class="form-group">
                                <div class="d-flex">
                                    <input class="m-2" type="radio" name="rdo1" <c:if test="${sessionScope.SELECT eq '1'}">checked</c:if> value="1" />
                                        <select id="t1" class="form-control" name="dropCate">
                                        <c:forEach var="cate" items="${sessionScope.LISTCATE}">
                                            <option <c:if test="${sessionScope.CATECHOOSER eq cate}">selected</c:if>>${cate}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="d-flex form-group">
                                    <input class="m-2" type="radio" name="rdo1" <c:if test="${sessionScope.SELECT eq '2'}">checked</c:if> value="2" />
                                    <input class="form-control me-2" id="t2" type="text" name="txtName" value="${sessionScope.SEARCHNAME}" placeholder="Search" aria-label="Search">
                                </div>
                                <div class="form-group ml-4">
                                    <input class="form-control" type="date" name="txtRentalDate" value="${sessionScope.RENTALDATE}" />
                                </div>
                                <div class="form-group ml-4">
                                    <input class="form-control" type="date" name="txtReturnDate" value="${sessionScope.RETURNDATE}" />
                                </div>
                                <div class="form-group ml-4">
                                    <input class="form-control" type="number" name="txtQuantity" value="${sessionScope.QUANTITY}" min="1"/>
                                </div>
                            </div>
                            <div class="text-center">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </div>
                        </form>
                    </div>
                </div>
            </nav>
        </section>
        <section class="container">
            <c:if test="${not empty sessionScope.FLAG}">
                <c:if test="${not empty sessionScope.LISTCAR}">
                    <div class="row">
                        <c:forEach items="${sessionScope.LISTCAR}" var="car">

                            <div class="card col-3 mt-4" style="width: 18rem;">
                                <img src="${car.image}" style="width: 100%; height: 200px;" class="card-img-top">
                                <div class="card-body text-center">
                                    <h5 class="card-title">${car.carName}</h5>
                                    <p class="card-text">Color: ${car.color}</p>
                                    <p class="card-text">Year: ${car.year}</p>
                                    <p class="card-text">Quantity: ${car.quantity}</p>
                                    <p class="card-text">Price: ${car.price}$</p>
                                    <c:if test="${car.rate eq 0}">
                                        <p class="card-text">Rate: No review</p>
                                    </c:if>
                                    <c:if test="${car.rate > 0}">
                                        <p class="card-text">Rate: ${car.rate}</p>
                                    </c:if>
                                    <form action="addToCart" method="POST">
                                        <input type="hidden" name="hdId" value="${car.id}" />
                                        <input type="hidden" name="hdName" value="${car.carName}" />
                                        <input type="hidden" name="hdColor" value="${car.color}" />
                                        <input type="hidden" name="hdYear" value="${car.year}" />
                                        <input type="hidden" name="hdPrice" value="${car.price}" />
                                        <input type="hidden" name="hdQuantity" value="${car.quantity}" />
                                        <input type="hidden" name="hdRate" value="${car.rate}" />
                                        <input type="hidden" name="hdCate" value="${car.cate}" />
                                        <input type="hidden" name="hdImage" value="${car.image}" />
                                        <input type="number" name="txtQuantity1" value="1" min="1" max="${car.quantity}"/>
                                        <c:if test="${sessionScope.USER.role eq 0}">
                                            <div class="text-center mt-4">
                                                <button class="btn btn-outline-success" type="submit">Add to cart</button>
                                            </div>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty sessionScope.LISTCAR}">
                    <h2 class="text-secondary text-center">No car match</h2>
                </c:if>
            </c:if>
        </section>
        <section>
            <div class="lastsection text-center mt-5">
                <c:if test="${sessionScope.ENDPAGE > 1}">
                    <c:forEach var="i" begin="1" end="${sessionScope.ENDPAGE}">
                        <a class="ml-4" href="search?index=${i}" <c:if test="${i eq sessionScope.INDEX}">style="border: 1px solid green; background-color: green; color: white; text-decoration: none;"</c:if>>${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </section>

        <c:import url="footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
