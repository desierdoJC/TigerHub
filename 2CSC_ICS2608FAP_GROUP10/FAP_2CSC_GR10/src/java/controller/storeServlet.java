/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class storeServlet extends HttpServlet {
    Connection conn;
    int stock;
    int totalPrice;
    @Override
    public void init(ServletConfig config) throws ServletException{
       super.init(config);
       
        //Establish a Connection to the Database
        try {
            Class.forName(config.getInitParameter("jdbcClassName"));
            System.out.println("jdbcClassName: " + config.getInitParameter("jdbcClassName"));
            String username = config.getInitParameter("dbUserName");
            String password = config.getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("databaseName"));
            conn = DriverManager.getConnection(url.toString(), username, password);
            
            
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession session = request.getSession();
           if(session.getAttribute("username")==null){
             response.sendRedirect("login.jsp");
             return;
           }
           
           //Finds what product the user wants to add to cart and adds it to the cart table
           if(request.getParameter("01")!=null){
            List<Model> prod =  Model.searchProd(conn,"001");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());          
            response.sendRedirect("store.jsp");
           }
           
           if(request.getParameter("02")!=null){
            List<Model> prod =  Model.searchProd(conn,"002");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }
           
           if(request.getParameter("03")!=null){
            List<Model> prod =  Model.searchProd(conn,"003");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }
           
           if(request.getParameter("04")!=null){
            List<Model> prod =  Model.searchProd(conn,"004");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("05")!=null){
            List<Model> prod =  Model.searchProd(conn,"005");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("06")!=null){
            List<Model> prod =  Model.searchProd(conn,"006");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("07")!=null){
            List<Model> prod =  Model.searchProd(conn,"007");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("08")!=null){
            List<Model> prod =  Model.searchProd(conn,"008");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("09")!=null){
            List<Model> prod =  Model.searchProd(conn,"009");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("10")!=null){
            List<Model> prod =  Model.searchProd(conn,"010");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("11")!=null){
            List<Model> prod =  Model.searchProd(conn,"011");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("12")!=null){
            List<Model> prod =  Model.searchProd(conn,"012");
            for(Model m :prod)
            Model.addToCart(conn,m.getProdcode() ,m.getPrice(),m.getProdname());
            response.sendRedirect("store.jsp");
           }

           if(request.getParameter("checkout")!= null){ //user wants to checkout
            List<Model> cart = Model.checkOut(conn);
            session.setAttribute("custOrder", cart);
                      
            java.util.Date date=new java.util.Date();
            java.sql.Date sqlDate=new java.sql.Date(date.getTime()); //get date in sql format
            String email = (String)session.getAttribute("email"); //get email of user
            
            for(Model m: cart){ //iterate through the cart, insert to order and decrease stock
               
               totalPrice += m.getPrice(); //computes the total price of the order
               session.setAttribute("totalPrice", totalPrice);
               
               Model.addToOrder(conn, sqlDate,m.getProdcode(),email,m.getPrice()); //insert to order
               
               List<Model> prod =  Model.searchProd(conn, m.getProdcode()); //decrease stock
               for(Model p : prod){
                  stock = p.getStock();
                  stock--;
                  Model.decStock(conn, stock, p.getProdcode());
               }
            }
            Model.clearCart(conn); //empties the cart for the next customer
            totalPrice = 0; //reset totalPrice for next customer
            
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
           }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
