<%-- 
    Document   : home
    Created on : Mar 2, 2021, 10:14:42 AM
    Author     : QH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
              integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
              crossorigin="anonymous" />
        <title>Car rental | Home</title>
        <style>
            header {
                background-color: rgba(0, 0, 0, 0.2);
                width: 100%;
                position: fixed;
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
        </style>
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-lg">
                <div class="container-fluid container">
                    <div class="icon__header">
                        <a class="navbar-brand" href="#"><i class="fa fa-car"></i> <span>CAR DOOR</span></a>
                    </div>
                    <!-- <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button> -->
                    <div class="nav__header">
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="#">Home</a>
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
        <section class="rentalcar__carosel">
            <div class="content__carosel">
                <div class="text__carosel container">
                    <p class="display-4 text-warning font-weight-bold">
                        BOOK A CAR TODAY!
                    </p>
                    <p class="text-white">
                        FOR AS LOW AS $10 A DAY PLUS 15% DISCOUNT
                        FOR OUR RETURNING CUSTOMERS
                    </p>
                </div>
            </div>
        </section>
        <section class="about" id="ABOUT">
            <div class="about__top">
                <h3>ABOUT US</h3>
                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Alias, suscipit.</p>
            </div>
            <div class="about__bottom container">
                <div class="row">
                    <div class="col-6">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Optio consequatur sapiente, error
                            voluptas facilis eveniet nam iusto architecto, incidunt quibusdam labore doloremque soluta quas
                            repellat explicabo dolorum rem esse assumenda consectetur. Cumque a ad sit autem quibusdam
                            vitae, facere aspernatur suscipit tempora nihil est maiores consequuntur ea alias pariatur
                            voluptatibus!</p>
                        <p>
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae mollitia vero facere aut enim nam
                            nostrum unde quisquam neque earum?
                        </p>
                        <a href="#CONTACT__US" class="btn btn-warning">CONTACT US</a>
                    </div>
                    <div class="col-6 px-5">
                        <iframe width="560" height="315" src="https://www.youtube.com/embed/940RzfSAfnU?autoplay=1" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </section>
        <footer id="CONTACT__US">
            <div class="container">
                <div class="row">
                    <div class="col-4">
                        <h3>ABOUT US</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga, aut magnam, error voluptatum sed, praesentium aspernatur natus voluptates fugiat hic commodi repellat. Repellendus aliquam harum esse iste, nemo reprehenderit laudantium.</p>
                    </div>
                    <div class="col-4">
                        <h3>RECENT POSTS</h3>
                        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Temporibus reiciendis a iure cumque nemo blanditiis quis expedita sint cupiditate ad iusto harum, et repudiandae dolor consequuntur, impedit nesciunt similique asperiores.</p>
                    </div>
                    <div class="col-4">
                        <h3>GET TOUCH</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolorem pariatur explicabo harum, odio sapiente assumenda officiis sunt, quibusdam odit consectetur fuga nesciunt sed nemo? Nemo non maiores saepe vitae aut.</p>
                    </div>
                </div>
            </div>

        </footer>
        <div class="write" style="background-color: black; color: white">
            <p>Written by Thai Quoc Hoai @2021</p>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>

</html>
