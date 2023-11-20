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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

/**
 *
 * @author Christian
 */
public class loginServlet extends HttpServlet {

    Connection conn;
    String accountPass,role,fname;
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
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
        
        if(conn != null){
        //get user inputs
        String email = request.getParameter("inputUsername");
        String pass = request.getParameter("inputPassword");
               
        request.setCharacterEncoding("UTF-8");
        String answer = request.getParameter("inputCaptcha");
        
        
        session.setAttribute("username",email);
        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
            
            if (captcha.isCorrect(answer)){ //correct captcha
               List<Model> account = Model.searchModel(conn, email); //search db with the username inputted
               
                if(!(account.isEmpty())){//account exists
                    for(Model m : account){
                    accountPass = Security.decrypt(m.getPassword()); //decrypted password
                    role = m.getRole();
                    fname = m.getFName();
                                          }
              
                    if(accountPass.equals(pass)){ //correct password
                        session.setAttribute("account", account);
                        session.setAttribute("role", role);
                        session.setAttribute("fname", fname);
                        session.setAttribute("email",email);
                        if(role.equalsIgnoreCase("user")){ 
                            request.getRequestDispatcher("store.jsp").forward(request, response); 
                            
                        }else if(role.equalsIgnoreCase("admin")){
                            request.getRequestDispatcher("adminHomepage.jsp").forward(request, response);  
                        }
                        
                    }else{ //incorrect password
                        session.setAttribute("errorType","Incorrect Password");
                        response.sendRedirect("errorPage.jsp");   
                    }
                    
                }else{ //account doesn't exist
                    session.setAttribute("errorType","Account does not exist");
                    response.sendRedirect("errorPage.jsp"); 
                }
                
            }else{ //incorrect captcha
            session.setAttribute("errorType","Incorrect Captcha");
            response.sendRedirect("errorPage.jsp");
            }
                                      
        }else{//null connection       
            session.setAttribute("errorType","Null Connection Error");
            response.sendRedirect("errorPage.jsp");
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