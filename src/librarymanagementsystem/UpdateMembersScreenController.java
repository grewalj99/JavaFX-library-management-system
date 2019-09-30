/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class UpdateMembersScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane basePane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXComboBox<String> campusInput;
    @FXML
    private JFXButton removeButton;
    @FXML
    private JFXTextField userIdInput;
    @FXML
    private JFXTextField usernameInput;
    @FXML
    private JFXTextField passwordInput;
    @FXML
    private JFXTextField fnameInput;
    @FXML
    private JFXTextField lnameInput;
    @FXML
    private DatePicker DOBInput;
    
    @FXML
    private JFXTextField removeUserIdInput;

    Boolean isAdmin = LoginScreenController.isAdmin;
    @FXML
    private Label addMemberStatus;
    @FXML
    private Label removeMemberStatus;
    
    @FXML
    private JFXComboBox<String> usertTypeInput;
    @FXML
    private JFXButton allMemberButton;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        campusInput.getItems().addAll("Cloverdale", "Langley", "Richmond", "Surrey");
        usertTypeInput.getItems().addAll("student", "faculty", "admin");
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
    private void addMember(MouseEvent event) throws SQLException, ClassNotFoundException {
        
        addMember();
        
    }
    
    @FXML
    private void addMemberEnter(KeyEvent event) throws SQLException, ClassNotFoundException {
         if (event.getCode() == KeyCode.ENTER)  {
            addMember();
        }
    }

    @FXML
    private void removeMember(MouseEvent event) throws SQLException, ClassNotFoundException {
        
        removeMember();
        
    }

    @FXML
    private void removeMemberEnter(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode() == KeyCode.ENTER)  {
            removeMember();
        }
    }
    
     @FXML
    private void allMembers(MouseEvent event) {
        Stage allMembersScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("AllMembersScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)allMemberButton.getScene().getWindow();
            Scene scene = new Scene(root);
            allMembersScreen.setScene(scene);
            
            current.hide();
            allMembersScreen.show();
        
    }
    
    public void addMember() throws SQLException, ClassNotFoundException {
        String userId = userIdInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String fname = fnameInput.getText();
        String lname = lnameInput.getText();
        String dob = DOBInput.toString();
        String userType = usertTypeInput.getValue();
        String campus = campusInput.getValue();
        
        
        Connection connection = DatabaseConnection.createConnection();

        String checkUserId = "SELECT userId FROM User WHERE userId ='"+userId+"'";
        String s = ((TextField)DOBInput.getEditor()).getText();
        
        String sql = "INSERT INTO User (userId, username, password,fname,lname, DOB, userType,campusName) VALUES ("
                + "'" + userId + "',"
                + "'" + username + "',"
                + "'" + password + "',"
                + "'" + fname + "',"
                + "'" + lname + "',"
                + "" + "STR_TO_DATE('" + s + "','%d/%m/%Y')" + ","
                + "'" + userType + "',"
                + "'" + campus + "'"
                + ")";
      
       
        PreparedStatement preparedStatement1 = connection.prepareStatement(checkUserId);
        ResultSet rset = preparedStatement1.executeQuery();
        
        if (rset.next()) {
            addMemberStatus.setVisible(true);
             addMemberStatus.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
            addMemberStatus.setText("Member already exist");
        }  
        
        else {
             PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.executeUpdate();
        addMemberStatus.setVisible(true);
        addMemberStatus.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
        addMemberStatus.setText("Member is added");
            
        }
        
        
        
    }
    
    public void removeMember() throws SQLException, ClassNotFoundException {
        String userId = removeUserIdInput.getText();
        
        String checkUserId = "SELECT userId FROM User WHERE userId ='"+userId+"'";
         
        String sql = "DELETE FROM User WHERE userId = '" + userId + "'";
        
        Connection connection = DatabaseConnection.createConnection();
        
        PreparedStatement preparedStatement1 = connection.prepareStatement(checkUserId);
        ResultSet rset = preparedStatement1.executeQuery();
        
        if (rset.next()) {
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.executeUpdate();
        removeMemberStatus.setVisible(true);
        removeMemberStatus.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
        removeMemberStatus.setText("Member is removed");
            
        }
        else {
             removeMemberStatus.setVisible(true);
             removeMemberStatus.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
            removeMemberStatus.setText("Member doesn't exist");
        }
        
    }

}
