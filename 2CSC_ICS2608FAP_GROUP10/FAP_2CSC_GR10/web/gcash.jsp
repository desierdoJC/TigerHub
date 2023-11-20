<%-- 
    Document   : gcash
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
        <title>GCash</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
            
            if(session.getAttribute("username") == null)
              response.sendRedirect("login.jsp");
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
                            <ion-icon name="people-sharp"></ion-icon><li><a href ="login.jsp">&nbsp&nbspLOGIN</a></li>
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

                       
        <div class="signMain">       
            <div class ="adminSide" data-aos="fade-right">
                <div class="adminBox">
                    <h2 class = "signInLabel">GCash QR Code:</h2> <br>
                    <img src="images/gcash.jpg" width="350" height="350" style="vertical-align:middle">
                </div>
                
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

