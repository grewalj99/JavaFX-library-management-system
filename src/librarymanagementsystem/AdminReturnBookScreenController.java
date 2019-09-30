/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class AdminReturnBookScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane basePane;
    @FXML
    private JFXTextField userIdInput;
    @FXML
    private JFXTextField bookIdInput;
    @FXML
    private JFXButton returnButton;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label status;
    
    Boolean isAdmin = LoginScreenController.isAdmin;
    String usernameText = LoginScreenController.usernameText;
    String userIdText = LoginScreenController.userIdText;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        drawer.setPrefSize(0, 0);
         
        if (isAdmin) {
            LoadDrawer("AdminDrawer.fxml");
        }
        else {
            LoadDrawer("Drawer.fxml");
        }
      
        
    }    

    public void LoadDrawer(String s) {
        try {
            Pane box = FXMLLoader.load(getClass().getResource(s));
            drawer.setSidePane(box);
            
            HamburgerSlideCloseTransition slideCloseTransition = new HamburgerSlideCloseTransition(hamburger);
            slideCloseTransition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                slideCloseTransition.setRate(slideCloseTransition.getRate() * -1);
                slideCloseTransition.play();
                
                if (drawer.isClosed()) {
                    drawer.setPrefSize(240, 611);
                    drawer.open();
                    
                    
                } else {
                    drawer.close();
                    drawer.setPrefSize(0, 0);
                    
                }
            }); } catch (IOException ex) {
            Logger.getLogger(AllBooksScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    @FXML
    private void returnButton(MouseEvent event) throws SQLException, ClassNotFoundException, ParseException {
       
       returnBook();
       
    }
    
    @FXML
    private void returnButtonEnter(KeyEvent event) throws SQLException, ClassNotFoundException, ParseException {
        if(event.getCode() == KeyCode.ENTER) {
            returnBook();
        }
    }
    
    public void returnBook() throws SQLException, ClassNotFoundException, ParseException {
        status.setText("");
        displayBookStatus();
        
        String bookId = bookIdInput.getText();
        String userId = userIdInput.getText();
        
        
        String sql = "DELETE FROM Issue WHERE bookId = '" + bookId + "'" + "AND userId = '" + userId + "'";
        
        String sql2 = "UPDATE Book SET status = TRUE WHERE bookId = '" + bookId + "'";
        
        Connection connection = DatabaseConnection.createConnection();
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement1.executeUpdate();
        preparedStatement2.executeUpdate();
        
    }
    
    private void displayBookStatus() throws SQLException, ClassNotFoundException, ParseException {
        String userId = userIdInput.getText();
        String bookId = bookIdInput.getText();
        Boolean bookExists = false;
        Connection connection = DatabaseConnection.createConnection();
        
        String query1 = "SELECT * FROM User WHERE userId = '" + userId +"'";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        ResultSet rset1 = preparedStatement1.executeQuery();
        
        String query2 = "SELECT * FROM Issue WHERE bookId = '" + bookId +"'" + "AND userId = '" + userId + "'";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        ResultSet rset2 = preparedStatement2.executeQuery();
        
        if (rset1.next()) {
            
            
            if (rset2.next()) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        
        String date = rset2.getString(4);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dueDate = formatter.parse(date);
              
                if(currentDate.compareTo(dueDate)>0){
                status.setVisible(true);
                status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
                status.setText("Book is Overdue");
                }
                
                else {
                status.setVisible(true);
                status.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
                status.setText("Book is returned");
                }
               
                  
            }
            else {
                status.setVisible(true);
                status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
                status.setText("Book doesn't exist or Issued to other User");
            }
        }
        
        else {
            status.setVisible(true);
            status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
             status.setText("User doesn't exist");
        }
        
    }
   
}
