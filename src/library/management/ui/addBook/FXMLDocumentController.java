/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.ui.addBook;

import library.management.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.beans.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import library.management.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author David
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXButton savebutton;
    @FXML
    private JFXButton cancelbutton;
    
    //creating object of database handler
    
    DatabaseHandler databasehandler;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            databasehandler = new DatabaseHandler();
        
    }    

    @FXML
    private void addbook(ActionEvent event) {
        
        try{
            
             String a,b,c,d;
        b=title.getText();
        a=id.getText();
        c=author.getText();
        d=publisher.getText();
        
        if(a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty()){
            JOptionPane.showMessageDialog(null, "FILL IN ALL FIELDS");
           
        }else{
             String qu="INSERT INTO BOOK VALUES ("
                 + "'" +a+ "',"
                 + "'" +b+ "',"
                 + "'" +c+ "',"
                 + "'" +d+ "',"
                 +  "" +"true"+ ""
                 + ")";
         System.out.println(qu);
         
          if(databasehandler.execAction(qu)){
              JOptionPane.showMessageDialog(null, "SUCCESSFUL");
              
          }else //error in inserting
          {
               JOptionPane.showMessageDialog(null, "FAILED");
          }
              
        }
            
        
          
        
            
        }catch(Exception e){
            
        }
           
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        
    }
    
}
