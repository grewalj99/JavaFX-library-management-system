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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class IssueBookScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane basePane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton issueButton;
    @FXML
    private JFXTextField userIdInput;
    @FXML
    private JFXTextField bookIdInput;
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
            userIdInput.setEditable(false);
            userIdInput.setText(userIdText);
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
    private void issueButton(MouseEvent event) throws SQLException, ClassNotFoundException, ParseException {
        
      issue();
           
    }

    @FXML
    private void issueButtonEnter(KeyEvent event) throws SQLException, ClassNotFoundException {
       
        if (event.getCode() == KeyCode.ENTER)  {
            issue();
        }
        
    }
    
    public void issue() throws SQLException, ClassNotFoundException {
       
        displayBookStatus();
           
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        java.util.Date dueDate = calendar.getTime();
   
        String bookId = bookIdInput.getText();
        String userId = userIdInput.getText();
        
        Connection connection = DatabaseConnection.createConnection();
       

        String sql = "INSERT INTO Issue(bookId, userId, dueDate) VALUES (?,?,?)";
        String sql2 = "UPDATE Book SET status = false WHERE bookId = '" + bookId + "'";
   
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement1.setString(1, bookId);
        preparedStatement1.setString(2, userId);
        preparedStatement1.setDate(3, new java.sql.Date(dueDate.getTime()));
        preparedStatement1.executeUpdate();
        preparedStatement2.executeUpdate();
        
        
    }
    
    private void displayBookStatus() throws SQLException, ClassNotFoundException {
        
        if (isAdmin) {
             String userId = userIdInput.getText();
        String bookId = bookIdInput.getText();
        Boolean userExists = false;
        Boolean bookExists = false;
        Connection connection = DatabaseConnection.createConnection();
        
        String query1 = "SELECT * FROM User WHERE userId = '" + userId +"'";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        ResultSet rset1 = preparedStatement1.executeQuery();
        
        String query2 = "SELECT * FROM Book WHERE bookId = '" + bookId +"'";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        ResultSet rset2 = preparedStatement2.executeQuery();
     
        if (rset1.next()) {
            
            if (rset2.next()) {
                bookExists = true;
                Boolean bs = rset2.getBoolean("status");
                if (bs) {
                    status.setVisible(true);
                    status.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
                    status.setText("Book is Issued");
                    
                }
                else {
                    status.setVisible(true);
                    status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
                    status.setText("Book is not Available");
              
                    
                }
                
                
            }
            else {
                 status.setVisible(true);
                 status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
                status.setText("Book doesn't exist");
            }
        }
        
        else {
             status.setVisible(true);
             status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
             status.setText("User doesn't exist");
        }
        
        
        }
        
        else {
            String bookId = bookIdInput.getText();
        Boolean bookExists = false;
        Connection connection = DatabaseConnection.createConnection();
        String query = "SELECT * FROM Book WHERE bookId = '" + bookId +"'";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rset = preparedStatement.executeQuery();
        
        
        while(rset.next()){
            bookExists = true;
                Boolean bs = rset.getBoolean("status");
                if (bs) {
                    status.setVisible(true);
                     status.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
                    status.setText("Book is Issued");
                    
                }
                else {
                    status.setVisible(true);
                    status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
                    status.setText("Book is not Available");
              
                    
                }
                
            }
        
        if (!bookExists) {
            status.setVisible(true);
            status.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
            status.setText("Book doesn't exist");
        }
            
        }
        
        
    }
    
}
