<%-- 
    Document   : index
    Created on : 04 23, 22, 12:25:38 PM
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tiger Hub Landing Page</title>
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
                            <li><a href ="index.jsp" class = "active">HOME</a></li>
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

        <section class="home">
            <div class="slider">
                <img  src="images\pic1.jpg" class="slide active" data-aos="fade-left">
                <img  src="images\pic2.jpg" class="slide" data-aos="fade-left">
                <img  src="images\pic3.jpg" class="slide" data-aos="fade-left">
                <img  src="images\pic4.jpg" class="slide" data-aos="fade-left">
                <img  src="images\pic5.jpg" class="slide" data-aos="fade-left">
            </div>

            <div class="controls">
                <div class="prev" data-aos="fade-right"><</div>
                <div class="next" data-aos="fade-left">></div>
            </div>

            <div class="indicator">
            </div>
        </section>

        <section class ="footer">
            <img class="logoTextB"  src="images\tigerHubLogo.png"><h3 class="footerText"><% out.println(getServletContext().getInitParameter("footer")); %></h3>
        </section>
        <script src="jscript.js"></script>
        <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
        <script>
                    AOS.init();
        </script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>