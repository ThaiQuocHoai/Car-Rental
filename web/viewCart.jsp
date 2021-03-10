<%-- 
    Document   : viewCart
    Created on : Mar 6, 2021, 8:59:29 AM
    Author     : QH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Car rental | Cart </title>
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
        <c:if test="${empty sessionScope.LISTCART}">
            <div class="text-center container" style="margin-top: 191px; margin-bottom: 191px;">
                <h2 class="text-secondary">No item in cart!!!</h2>
                <a href="car">Click here to back to Rental Car</a>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.LISTCART}">
            <div style="margin-top: 100px;">
                <c:if test="${not empty requestScope.QUANERROR}">
                    <p class="text-center alert alert-danger">${requestScope.QUANERROR}</p>
                </c:if>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Image</th>
                            <th scope="col">Color</th>
                            <th scope="col">Year</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Rental date</th>
                            <th scope="col">Return date</th>
                            <th scope="col">Update</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cart" items="${sessionScope.LISTCART}">
                            <tr>
                                <td>${cart.carName}</td>
                                <td><img width="200px" src="${cart.image}"/></td>
                                <td>${cart.color}</td>
                                <td>${cart.year}</td>
                        <form action="update" method="POST">
                            <td><input type="number" name="txtValue" value="${cart.quantity}" min="1"/></td>
                            <td>${cart.price*cart.quantity}</td>
                            <td>${cart.rentalDate}</td>
                            <td>${cart.returnDate}</td>
                            <td>
                                <input type="hidden" name="uId" value="${cart.id}" />
                                <input type="hidden" name="uRental" value="${cart.rentalDate}" />
                                <input type="hidden" name="uReturn" value="${cart.returnDate}" />
                                <input type="submit" class="btn btn-outline-primary" name="btAction" value="Update"/>
                        </form>
                        </td>
                        <td>
                            <form action="delete" method="POST">
                                <input type="hidden" name="dId" value="${cart.id}" />
                                <input type="hidden" name="dRental" value="${cart.rentalDate}" />
                                <input type="hidden" name="dReturn" value="${cart.returnDate}" />
                                <input type="submit" class="btn btn-outline-danger" onclick="return confirm('Are you sure to delete ${cart.carName} from cart?');" name="btAction" value="Delete"/>
                            </form>
                        </td>

                        </tr>
                        <c:set var="total" value="${total+cart.price*cart.quantity}"/>

                    </c:forEach>
                    <tr>
                        <td colspan="11" class="text-center">
                            <p class="text-success">Total price: ${total - (total*sessionScope.DISCOUNT.percent)/100}$</p>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <section>
                <form action="discount" method="POST">
                    <div class="d-flex form-group">
                        <h2 class="display-5 text-secondary mr-3">Discount code:</h2>
                        <input type="text" class=" mr-3" name="txtDiscount" value="${param.txtDiscount}" />
                        <input type="submit" class="btn btn-outline-secondary" name="btAction" value="Apply" />
                    </div>
                </form>
            </section>       
            <section>
                <form action="checkout" method="POST" class="container">
                    <input type="hidden" name="cTotal" value="${total}" />
                    <c:set value="${sessionScope.USER}" var="user"/>
                    <p class="text-secondary text-center font-weight-bold" style="font-size: 30px;">Your information</p>
                    <div class="form-group">
                        <p class="text-secondary" style="font-size: 30px;">Your mail: </p>
                        <input type="text" class="form-control" name="cMail" value="${user.username}" readonly placeholder="abc@abc.com"/>
                    </div>
                    <div class="form-group">
                        <p class="text-secondary" style="font-size: 30px;">Your name:</p>
                        <input type="text" class="form-control" name="cName" value="${param.cName}" required placeholder="Nguyen Van A"/>
                    </div>
                    <div class="form-group">
                        <p class="text-secondary" style="font-size: 30px;">Your phone number:</p>
                        <input type="tel" class="form-control" name="cNum" pattern="[0-9]{10}" value="${param.cNum}" />
                    </div>
                    <div class="form-group">
                        <p class="text-secondary" style="font-size: 30px;">Your address:</p>
                        <input type="tel" class="form-control" name="cAdd" value="${param.cAdd}" placeholder="123 ABC" required/>
                    </div>
                    <div style="width: 100%" class="text-center">
                        <input class="btn btn-outline-success text-center" type="submit" onclick="return confirm('Are you sure?')" name="btAction" value="Check out" />
                    </div>
                </form>
            </section>
        </c:if>


        <c:import url="footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
