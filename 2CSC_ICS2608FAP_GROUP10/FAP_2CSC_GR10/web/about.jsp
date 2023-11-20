<%-- 
    Document   : about
    Created on : 05 17, 22, 8:16:28 AM
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                            <li><a href ="index.jsp" >HOME</a></li>
                            <li><a href ="store.jsp">STORE</a></li>
                            <li><a href ="about.jsp" class = "active">ABOUT</a></li>
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
                        
        <div class="aboutMain">                
        
                   
        <section class="aboutSec">
            <div class ="aboutCont"  data-aos ="zoom-in-up">               
                <div class="aboutUs">
                    <h1><font color="#e69b2a">ABOUT</font> US</h1><br>
                    <p>
                    TigerHub Tech Center is a computer retail store in the Philippines providing innovative solutions, and cost-effective yet high-quality technologies that improve the lives of our customers.
                    It aims to provide the best PC parts to every computer users. 
                    We seek to provide innovative solutions, and cost-effective yet high-quality technologies that improve the lives of our customers.
                    </p>
                </div>     

                <div class="contactUsb">
                    <h1><font color="#e69b2a">CONTACT</font> US</h1><br>
                    <p>
                        Social Media Accounts of Creators:<br>
                        <a href="https://www.facebook.com/deguia.symon/" target="_blank">Charle Symon De Guia </a><br>
                        <a href="https://www.facebook.com/johnchristian.desierdo" target="_blank" >John Christian Desierdo </a><br>
                        <a href="https://www.facebook.com/RicciLorenzoPalmares/" target="_blank">Ricci Lorenzo Pasamba</a><br>                       
                    </p>
                </div>
            </div>
        </section>
            
            
        </div>   
                        
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
