<%-- 
    Document   : checkout
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
        <title>checkout</title>
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
                    <h2 class = "signInLabel">Order Details:</h2> <br>
                    <table border="1" align="center">
                    <tr class="tableHeader">
                    <th>Product Name</th>
                    <th>Product Price</th>
                    </tr>

                    <%
                    List<Model> results = (List) session.getAttribute("custOrder");
                    for (Model m: results) {%>
                        <tr class="tableValues">
                            <td><%=m.getProdname()%></td>
                            <td><%=m.getPrice()%></td>
                        </tr>	
                    <%	}
                    %>

        </table>
                    <p>Total Order Price: <% out.println(session.getAttribute("totalPrice")); %></p>
                    <a href="paymaya.jsp" target="_blank">Pay with PayMaya</a> <br>
                    <a href="gcash.jsp" target="_blank">Pay with GCash</a>
                    <form action="pdfServlet" method="POST" target="_blank">
                      <button class ="loginBT" name="invoice">Print Order Invoice</button>
                    </form>  
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

