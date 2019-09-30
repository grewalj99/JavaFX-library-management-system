/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author grewal
 */
public class LoginScreenController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    @FXML
    private Label invalidLoginAlert;
    @FXML
    private JFXButton loginButton;
    static String usernameText;
    
    static String userIdText;
    
    static Boolean isAdmin = false;
    static Boolean isMember = false;
    static Boolean isGuest = false;
    

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
    }    

    @FXML
    private void loginButton(MouseEvent event) throws SQLException, ClassNotFoundException, IOException {
       
        login();
             
    }
    
    @FXML
    private void loginEnter(KeyEvent event) throws SQLException, ClassNotFoundException {
       
        if (event.getCode() == KeyCode.ENTER)  {
            login();
        }
    }

    @FXML
    private void guestLoginButton(MouseEvent event) {
        
        guestLogin();
        
    }
    
    @FXML
    private void guestLoginEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)  {
            guestLogin();
        }
    }

    public void login() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.createConnection();
        String queryString = "select * from User where username = '" + username.getText().trim() + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
        ResultSet rset = preparedStatement.executeQuery();
        
        usernameText = username.getText();
        String userPassword = "";
        String userType = "";
        if (rset.next()){
          
                userPassword = rset.getString(3);
                userType = rset.getString(7);
                userIdText = rset.getString(1);
                if(password.getText().trim().equals(userPassword)) {
                
                    if (userType.equals("admin")) {
                        Stage AdminHomeScreen = new Stage();
                        Parent root = null;
                        
                        try {
                            root = FXMLLoader.load(getClass().getResource("AdminHomeScreen.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Stage current = (Stage)username.getScene().getWindow();
                        Scene scene = new Scene(root);
                        
                        AdminHomeScreen.setScene(scene);
                          
                        current.hide();
                        
                        AdminHomeScreen.show();
                        
                        isAdmin = true;
                        
                    }
                    
                    else {
                        Stage homeScreen = new Stage();
                        Parent root = null;
                        
                        try {
                            root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Stage current = (Stage)username.getScene().getWindow();
                        Scene scene = new Scene(root);
                        
                        homeScreen.setScene(scene);
                          
                        current.hide();
                        
                        homeScreen.show();
                        
                        isMember = true;
                        
                    }
                    
                
            }
                else {
                    invalidLoginAlert.setVisible(true);
                invalidLoginAlert.setText("Invalid Password. Try Again.");
                
            }
           
        }
        
        else {
                    invalidLoginAlert.setVisible(true);
                    invalidLoginAlert.setText("Invalid Username. Try Again.");
                }
             
    }
    
    public void guestLogin() {
        Stage GuestHomeScreen = new Stage();
                        Parent root = null;
                        
                        try {
                            root = FXMLLoader.load(getClass().getResource("GuestHomeScreen.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Stage current = (Stage)username.getScene().getWindow();
                        Scene scene = new Scene(root);
                        
                        GuestHomeScreen.setScene(scene);
                          
                        current.hide();
                        
                        GuestHomeScreen.show();
                        
                        isGuest = true;
        
    }
 
    
}
