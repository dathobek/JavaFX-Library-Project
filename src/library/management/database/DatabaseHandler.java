/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.database;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class DatabaseHandler {
    private static DatabaseHandler handler;
    
   // private static final String DB_URL = "jdbc:derby:database;create=true ";
    private static final String DB_URL = "jdbc:derby:database;create=true";
   // static final String USER="root";
    //static final String PASS="";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public DatabaseHandler() {
        createConnection();
        setupBookTable();
    }
    
    void createConnection(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
           //Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(DB_URL);
            //stmt= conn.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
       
    }
    
    void setupBookTable(){
       
        String TABLE_NAME= "BOOK";
         
        try{
            stmt= conn.createStatement();
            
            DatabaseMetaData dbn = conn.getMetaData();
            ResultSet tables = dbn.getTables(null,null, TABLE_NAME.toUpperCase(),null);
            
              if(tables.next()){
                  System.out.println("Table " + TABLE_NAME + "Already exist.Ready for go");
              }else{
                  stmt.execute( "CREATE TABLE " +TABLE_NAME + "("
                  + "  id varchar(200) primary key, \n"
                  + "   title varchar(200),\n"
                  + "   author varchar(200),\n"
                  + "   publisher varchar(200)"
                  + "   isAvial boolean default true"        
                  + " )" );
                    
              }
        }catch(SQLException e){
            System.err.println(e.getMessage()+ "......setupDatabase");
        } finally{
        
    }
    } 
    
     public ResultSet execQuery(String query){
         //execute query is like Select Statement
            ResultSet result;
            try{
                stmt = conn.createStatement();
                result= stmt.executeQuery(query);
            }catch (SQLException ex){
                ex.printStackTrace();
                return null;   
            } finally {
                
            }
            return result;
            
        }
     
     public boolean execAction(String qu){
         //execute Action is like Insert statement
         try{
             stmt=conn.createStatement();
             stmt.execute(qu);
             return true;
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "error:" +ex.getMessage(), "Error Occured",JOptionPane.ERROR_MESSAGE);
             System.out.println("Exception at execQuery:dataHandler" +ex.getLocalizedMessage());
             return false;
         } finally {
             
         }
        
     }
    
}
