<%-- 
    Document   : store
    Created on : 05 11, 22, 5:14:53 PM
    Author     : Christian
--%>

<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign In</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        %>
        <section class ="header">
            <div class ="wrapper">
                <div class ="mainHeader">
                    <div class ="logoHeader"><img class="logoTextA"  src="images\tigerHubLogo.png"></div>
                    <div class= "menuList">
                        <ul>
                            <li><a href ="index.jsp">HOME</a></li>
                            <li><a href ="store.jsp" class="active">STORE</a></li>
                            <li><a href ="about.jsp">ABOUT</a></li>
                                <%
                                    boolean test = (session.getAttribute("username") != null);
                                    if (!test) {
                                %>
                            <ion-icon name="people-sharp"></ion-icon><li><a href ="login.jsp"  class = "active">&nbsp&nbspLOGIN</a></li>
                             <li>&nbsp&nbsp&nbspGUEST</li>
                                    <%
                                    } else {
                                    %>
                             <li>                    
                                    <% out.println(session.getAttribute("fname")); %>                            
                             </li>
                            <li><a href ="logoutServlet.do">&nbsp&nbsp&nbspLOGOUT</a></li>
                                <%
                                    }
                                %>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <form action="storeServlet" method="POST">               
        <section class="products">
            <center>
                <div class ="storeLabel" data-aos="fade-down">Tiger Hub Store</div>
                <h1 data-aos="fade-up">Welcome and Enjoy Shopping</h1>

                <div data-aos="flip-left">
                    <div class="card card-1" >
                        <img src="images\store\prod01.png">
                        <p>GeForce RTX 2060</p>
                        Php 20000
                        <button class ="loginBT" name="01">Add to Cart</button>
                    </div>
                    <div class="card card-1">
                        <img src="images\store\prod02.png">
                        <p>AMD Ryzen 9 5900X</p>
                        Php 46999
                        <button class ="loginBT" name="02">Add to Cart</button>
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod03.png">
                        <p>TEAM XTREEM ARGB 16GB DDR4-3600MHz C14</p>
                        Php 7665
                        <button class ="loginBT" name="03">Add to Cart</button>
                    </div>
                    <br>
                    <div class="card card-1" >
                        <img src="images\store\prod04.png">
                        <p>Corsair RM750x</p>
                        Php 6349
                        <button class ="loginBT" name="04">Add to Cart</button> 
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod05.png">
                        <p>Alienware 34 QD-OLED (AW3423DW)</p>
                        Php 64000
                        <button class ="loginBT" name="05">Add to Cart</button>
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod06.png">
                        <p>EK-AIO Basic 240</p>
                        Php 3995
                        <button class ="loginBT" name="06">Add to Cart</button>
                    </div>
                    <br>
                    <div class="card card-1" >
                        <img src="images\store\prod07.png">
                        <p>Fractal Design Meshify 2 Compact</p>
                        Php 7000
                        <button class ="loginBT" name="07">Add to Cart</button>
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod08.png">
                        <p>Noctua NF-S12B redux-1200</p>
                        Php 1300
                        <button class ="loginBT" name="08">Add to Cart</button>
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod09.png">
                        <p>Logitech G Pro X SuperLight Wireless</p>
                        Php 5960
                        <button class ="loginBT" name="09">Add to Cart</button>
                    </div>
                    <br>
                    <div class="card card-1" >
                        <img src="images\store\prod11.jpg">
                        <p>Logitech G613 Wireless Mechanical Keyboard</p>
                        Php 4295
                        <button class ="loginBT" name="10">Add to Cart</button>
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod10.png">
                        <p>Logitech G435 LIGHTSPEED WIRELESS GAMING HEADSET</p>
                        Php 3350
                        <button class ="loginBT" name="11">Add to Cart</button>
                    </div>
                    <div class="card card-1" >
                        <img src="images\store\prod12.png">
                        <p>Shure MV7 Podcast Microphone</p>
                        Php 13500
                        <button class ="loginBT" name="12">Add to Cart</button>
                    </div>
                </div>
                
                <%
                    test = (session.getAttribute("username") != null);
                    if (test) {
                %>
                    <button class ="storeBT" name="checkout">Proceed to Checkout</button>
                <%
                } 
                %>
                
                
                
            </center>
        </section>
        </form>
        <section class ="footer">
            <img class="logoTextB"  src="images\tigerHubLogo.png"><h3 class="footerText"><% out.println(getServletContext().getInitParameter("footer")); %></h3>
        </section>

        <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
        <script>
                    AOS.init();
        </script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>


