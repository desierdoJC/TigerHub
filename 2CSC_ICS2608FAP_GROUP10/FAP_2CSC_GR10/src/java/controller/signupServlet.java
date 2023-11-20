
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
import nl.captcha.Captcha;

/**
 *
 * @author Christian
 */
public class signupServlet extends HttpServlet {
    Connection conn;
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
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            
            if(conn != null){
               //get user inputs
                String email = request.getParameter("emailSU");
                String fname = request.getParameter("fnSU");
                String lname = request.getParameter("lnSU");
                String pass = request.getParameter("pwSU");
                String conpass = request.getParameter("cpwSU");

                request.setCharacterEncoding("UTF-8");
                String answer = request.getParameter("inputCaptcha");
            
            
                
                session.setAttribute("username",email);
                Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
                
                if(captcha.isCorrect(answer)){
                    
                    List<Model> account = Model.searchModel(conn, email); //search db with the username inputted 
                   
                    if(!(account.isEmpty())){ //means an account exists with that username
                    session.setAttribute("errorType","Account already exists");
                    response.sendRedirect("errorPage.jsp");               
                    }
                    else{
                        //not empty so compare pass then save to db
                        if(pass.equals(conpass)){ //pass and confirm pass is equal
                            String encrpytedPass = Security.encrypt(pass);
                            Model.insertModel(conn, email, encrpytedPass,"user", fname, lname); //adds the user to the database
                            response.sendRedirect("login.jsp");              
                        } else{ //pass and confirm pass isnt equal
                            session.setAttribute("errorType","Password and Confirm Password not equal");
                            response.sendRedirect("errorPage.jsp"); 
                        }
                    }
                 }else{ //incorrect captcha
                    session.setAttribute("errorType","Incorrect Captcha");
                    response.sendRedirect("errorPage.jsp");
                    }
            }else{ //null connection error
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
