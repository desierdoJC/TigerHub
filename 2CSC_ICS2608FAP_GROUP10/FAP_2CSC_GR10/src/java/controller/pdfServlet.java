/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class pdfServlet extends HttpServlet {

   Connection conn;
   double maxPage;
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
        response.setContentType("application/pdf");
        
        ServletOutputStream out = response.getOutputStream();
        
        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
  
        Document docs = new Document();
        HttpSession session = request.getSession();
        
        try{
            PdfWriter writer = PdfWriter.getInstance(docs, out);
            Rectangle rect = new Rectangle(PageSize.LETTER);
            writer.setBoxSize("rect", rect);
            
            if (request.getParameter("user")!=null){
                response.addHeader("Content-Disposition","inline; filename= User Records.pdf");
                List<Model> users = Model.adminSearchUser(conn);
                
                double size = users.size();
                maxPage = size / 24;
                maxPage = Math.ceil(maxPage);
                String header = getServletContext().getInitParameter("header");
                
                HeaderFooterPageEvent headerFooter = new HeaderFooterPageEvent(header,(String)session.getAttribute("fname"),datetime.format(now),maxPage);
                writer.setPageEvent(headerFooter);
                
                docs.open();
                docs.add(new Phrase("\n"));
                docs.add(new Paragraph("List of User Accounts:"));
                
                int num = 1;
                PdfPTable table = new PdfPTable(5);
                table.setTotalWidth(500F);
                table.setSpacingBefore(10f);
                table.addCell("No.");
                table.addCell("USERNAME");
                table.addCell("ROLE");
                table.addCell("FIRST NAME");
                table.addCell("LAST NAME");
                
                for(Model m : users){
                    table.addCell(String.valueOf(num));
                    table.addCell(m.getUsername());
                    table.addCell(m.getRole());
                    table.addCell(m.getFName());
                    table.addCell(m.getLName());
                    
                    if (num % 24 == 0) {
                        docs.add(table);
                        docs.newPage();
                        docs.add(new Phrase("\n"));
                        table = new PdfPTable(5);
                        table.setTotalWidth(500F);
                        table.setSpacingBefore(10f);
                    }
                    num++;
                }
                docs.add(table);
                docs.close();
            }else if (request.getParameter("admin")!=null){
                response.addHeader("Content-Disposition","inline; filename= Admin Records.pdf");
                
                List<Model> users = Model.adminSearchAdmin(conn);
                
                double size = users.size();
                maxPage = size / 24;
                maxPage = Math.ceil(maxPage);
                String header = getServletContext().getInitParameter("header");
                
                HeaderFooterPageEvent headerFooter = new HeaderFooterPageEvent(header,(String)session.getAttribute("fname"),datetime.format(now),maxPage);
                writer.setPageEvent(headerFooter);
                
                docs.open();
                docs.add(new Phrase("\n"));
                docs.add(new Paragraph("List of Admin Accounts:"));
                
                int num = 1;
                PdfPTable table = new PdfPTable(5);
                table.setTotalWidth(500F);
                table.setSpacingBefore(10f);
                table.addCell("No.");
                table.addCell("USERNAME");
                table.addCell("ROLE");
                table.addCell("FIRST NAME");
                table.addCell("LAST NAME");
                
                for(Model m : users){
                    table.addCell(String.valueOf(num));
                    table.addCell(m.getUsername());
                    table.addCell(m.getRole());
                    table.addCell(m.getFName());
                    table.addCell(m.getLName());
                    
                    if (num % 24 == 0) {
                        docs.add(table);
                        docs.newPage();
                        docs.add(new Phrase("\n"));
                        table = new PdfPTable(5);
                        table.setTotalWidth(500F);
                        table.setSpacingBefore(10f);
                    }
                    num++;
                }
                docs.add(table);
                docs.close();
            }else if(request.getParameter("products")!=null){
                response.addHeader("Content-Disposition","inline; filename= Product Records.pdf");
                
                List<Model> products = Model.adminSearchProd(conn);
                double size = products.size();
                maxPage = size / 24;
                maxPage = Math.ceil(maxPage);
                String header = getServletContext().getInitParameter("header");
                
                HeaderFooterPageEvent headerFooter = new HeaderFooterPageEvent(header,(String)session.getAttribute("fname"),datetime.format(now),maxPage);
                writer.setPageEvent(headerFooter);
                
                docs.open();
                docs.add(new Phrase("\n"));
                docs.add(new Paragraph("List of Products:"));
                
                int num = 1;
                PdfPTable table = new PdfPTable(4);
                table.setTotalWidth(500F);
                table.setSpacingBefore(10f);
                table.addCell("PRODUCT CODE");
                table.addCell("PRODUCT NAME");
                table.addCell("STOCK");
                table.addCell("PRICE");
                
                for(Model m : products){
                    table.addCell(m.getProdcode());
                    table.addCell(m.getProdname());
                    table.addCell(String.valueOf(m.getStock()));
                    table.addCell(String.valueOf(m.getPrice()));
                    
                    if (num % 24 == 0) {
                        docs.add(table);
                        docs.newPage();
                        docs.add(new Phrase("\n"));
                        table = new PdfPTable(5);
                        table.setTotalWidth(500F);
                        table.setSpacingBefore(10f);
                    }
                    num++;
                }
                docs.add(table);
                docs.close();
                
            }else if(request.getParameter("invoice")!=null){
              response.addHeader("Content-Disposition","inline; filename= Order Invoice.pdf");
              
              List<Model> invoice = (List) session.getAttribute("custOrder");
              double size = invoice.size();
              maxPage = size / 28;
              maxPage = Math.ceil(maxPage);
              String header = getServletContext().getInitParameter("header");
                
              HeaderFooterPageEvent headerFooter = new HeaderFooterPageEvent(header,(String)session.getAttribute("fname"),datetime.format(now),maxPage);
              writer.setPageEvent(headerFooter);
                
              int totalPrice = (Integer)session.getAttribute("totalPrice");
              String email = (String)session.getAttribute("email");
              
              docs.open();
              docs.add(new Phrase("\n"));
              docs.add(new Paragraph("Order Invoice of " + email + ":"));
                
                int num = 1;
                PdfPTable table = new PdfPTable(2);
                table.setTotalWidth(500F);
                table.setSpacingBefore(10f);
                table.addCell("PRODUCT NAME");
                table.addCell("PRODUCT PRICE");
                
                for(Model m : invoice){
                    table.addCell(m.getProdname());
                    table.addCell(String.valueOf(m.getPrice()));
                    
                    if (num % 28 == 0) {
                        docs.add(table);
                        docs.newPage();
                        docs.add(new Phrase("\n"));
                        table = new PdfPTable(2);
                        table.setTotalWidth(500F);
                        table.setSpacingBefore(10f);
                    }
                    num++;
                }
                docs.add(table);
                docs.add(new Paragraph("Total Price: " + totalPrice));
                docs.close();
              
            }else if(request.getParameter("sales")!=null){
                int totalSales = 0;
                String date = (String)request.getParameter("inputDate");
                
                response.addHeader("Content-Disposition","inline; filename= Sales Report for" + date + ".pdf");
                
                List<Model> report = Model.searchSalesReport(conn, date);
                double size = report.size();
                maxPage = size / 30;
                maxPage = Math.ceil(maxPage);
                String header = getServletContext().getInitParameter("header");
                
                HeaderFooterPageEvent headerFooter = new HeaderFooterPageEvent(header,(String)session.getAttribute("fname"),datetime.format(now),maxPage);
                writer.setPageEvent(headerFooter);
                              
                docs.open();
                docs.add(new Phrase("\n"));
                docs.add(new Paragraph("Sales Report on " + date + ":"));
                
                int num = 1;
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(500F);
                table.setSpacingBefore(10f);
                table.addCell("PRODUCT CODE");
                table.addCell("CUSTOMER EMAIL");
                table.addCell("PRICE");
                
                for(Model m : report){
                    table.addCell(m.getProdcode());
                    table.addCell(m.getUsername());
                    totalSales += m.getPrice();
                    table.addCell(String.valueOf(m.getPrice()));
                    
                    if (num % 30 == 0) {
                        docs.add(table);
                        docs.newPage();
                        docs.add(new Phrase("\n"));
                        table = new PdfPTable(3);
                        table.setTotalWidth(500F);
                        table.setSpacingBefore(10f);
                    }
                    num++;
                }
                docs.add(table);
                docs.add(new Paragraph("Total Sales: " + totalSales));
                docs.close();
                
                }
        }
       
        catch(DocumentException e){
          throw new ServletException (e.getMessage());  
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
