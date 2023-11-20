<%-- 
    Document   : login
    Created on : 04 27, 22, 1:03:18 PM
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
                            <li><a href ="store.jsp">STORE</a></li>
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
                            <li><a href ="logoutServlet.do">&nbsp&nbsp&nbspLOGOUT</a></li>
                                <%
                                    }
                                %>
                        </ul>
                    </div>
                </div>
            </div>
        </section>


        <div class="signMain">            
            <div class ="signSide" data-aos="fade-right">
                <div class="loginBox">
                    <h2 class = "signInLabel">LOGIN</h2>
                    <form action="loginServlet.do" method="POST">

                        <label class = "unpwLabel">Username</label><br>
                        <input type="text" class="userpass" name="inputUsername">
                        <br><br>

                        <label class = "unpwLabel">Password</label><br>
                        <input type="password" class="userpass" name="inputPassword">
                        <br><br>

                        <img src="http://localhost:8080/FAP_2CSC_GR10/simpleImg" />
                        <br>
                        <input type="text" class="userpass" name="inputCaptcha" placeholder="Enter Captcha">
                        <br><br>
                        
                        <br>

                        <br>
                        
                        <button class ="loginBT">Login</button>
                        <br><br>
                        <p class = "signupText">Don't have an account?&nbsp<a href="signup.jsp" onmouseover="mOver()" onmouseout="mOut()">Sign Up</a></p>
                    </form>      
                </div>
                
            </div>
            <div class ="welcomeTXT">
               <h1 class ="explore" data-aos="fade-down">Welcome to Tiger Hub! </h1>
               <h1 class="explore2" data-aos="fade-left">Shop for your digital needs in the comfort of your home.</h1>
            </div>
        </div>  

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

