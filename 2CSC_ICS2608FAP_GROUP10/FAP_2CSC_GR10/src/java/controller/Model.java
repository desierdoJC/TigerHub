/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christian
 */
public class Model {
    String username,password,role,fname,lname;
    String prodcode,prodname;
    int stock,price;

    public Model(String username, String password, String role, String fname, String lname){
        this.username = username;
        this.password = password;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
    }
    
    public Model(String prodcode, int price, String prodname){
       this.prodcode = prodcode;
       this.price = price;
       this.prodname = prodname;
    }
    
    public Model(String prodcode, String prodname, int stock, int price){
        this.prodcode = prodcode;
        this.prodname = prodname;
        this.stock = stock;
        this.price = price;        
    }
    
     public Model(String prodcode, String username, int price){
       this.prodcode = prodcode;
       this.username = username;
       this.price = price;
    }
    
     public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role;
    }
    public String getFName(){
        return fname;
    }
    public String getLName(){
        return lname;
    }
    public String getProdcode(){
        return prodcode;
    }
    public String getProdname(){
        return prodname;
    }
    public int getStock(){
        return stock;
    }
    public int getPrice(){
        return price;
    }
   
    //method for searching in accounts db
    public static List<Model> searchModel(Connection con, String query){
        List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM accounts WHERE USERNAME = ?";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                ps.setString(1, query);
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("username"), records.getString("password"), records.getString("role"), records.getString("first_name"), records.getString("last_name")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    
    //method for user registration
     public static void insertModel(Connection con, String username, String password, String role, String fname, String lname){
        try{
            //Inserts the data into the database
            String qString = "INSERT INTO accounts VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(qString); 
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                ps.setString(4,fname);
                ps.setString(5,lname);
                ps.executeUpdate();
                ps.close();
      
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
     }
    
    //method to search all user accounts 
    public static List<Model> adminSearchUser(Connection con){
        List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM accounts WHERE role='user'";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("username"), records.getString("password"), records.getString("role"), records.getString("first_name"), records.getString("last_name")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    //method to search all admin accounts 
    public static List<Model> adminSearchAdmin(Connection con){
        List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM accounts WHERE role='admin'";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("username"), records.getString("password"), records.getString("role"), records.getString("first_name"), records.getString("last_name")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    
    
    //method to search all products
     public static List<Model> adminSearchProd(Connection con){
        List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM products";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("prodcode"), records.getString("prodname"), records.getInt("stock"), records.getInt("price")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    
    //add items into cart 
    public static void addToCart(Connection con, String prodcode, int price, String prodname){
         try{
            //Inserts the data into the database
            String qString = "INSERT INTO cart VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(qString); 
                ps.setString(1, prodcode);
                ps.setInt(2, price);
                ps.setString(3,prodname);
                ps.executeUpdate();
                ps.close();
      
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    //search a product in the db
     public static List<Model> searchProd(Connection con, String prodcode){
         List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM products WHERE prodcode = ?";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                ps.setString(1, prodcode);
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("prodcode"), records.getString("prodname"), records.getInt("stock"), records.getInt("price")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    
    //decrease stock of a product
    public static void decStock(Connection con, int currStock, String prodcode){
         try{
          //Updates the data in the database
          String qString = "UPDATE products SET stock=? WHERE prodcode=?";
          PreparedStatement ps = con.prepareStatement(qString);
          ps.setInt(1,currStock);
          ps.setString(2,prodcode);
          ps.executeUpdate();
          ps.close();
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
     
    //get all items for the cart 
     public static List<Model> checkOut(Connection con){
         List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM CART";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("prodcode"), records.getInt("price"), records.getString("prodname")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
     
    //clears cart for next customer
     public static void clearCart(Connection con){
         List<Model> res = new ArrayList<>();
        try{
            String qString = "DELETE FROM CART";
            PreparedStatement ps = con.prepareStatement(qString); 
            ps.executeUpdate(); 
            ps.close();           
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }       
    }
     
     //insert to order
    public static void addToOrder(Connection con, Date orderdate, String prodcode, String cust_email, int price){
         try{
            //Inserts the data into the database
            String qString = "INSERT INTO ORDERS VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(qString); 
                ps.setDate(1, orderdate);
                ps.setString(2, prodcode);
                ps.setString(3,cust_email);
                ps.setInt(4,price);
                ps.executeUpdate();
                ps.close();
      
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    //search a product in the db
     public static List<Model> searchSalesReport(Connection con, String date){
         List<Model> res = new ArrayList<>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM ORDERS WHERE order_date = ?";
            try (PreparedStatement ps = con.prepareStatement(qString)) {
                ps.setString(1, date);
                //Parses the ResultSet object and adds content to list as  object
                try (ResultSet records = ps.executeQuery()) {
                    //Parses the ResultSet object and adds content to list as object
                    while(records.next()){
                        res.add(new Model(records.getString("prodcode"), records.getString("cust_email"), records.getInt("price")));
                    }
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
   
}
