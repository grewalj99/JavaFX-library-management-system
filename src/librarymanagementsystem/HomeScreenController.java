/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class HomeScreenController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    
    Boolean isMember = LoginScreenController.isMember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mouseExit1(MouseEvent event) {
        pane1.setStyle("-fx-background-color: white; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseHover1(MouseEvent event) {
        pane1.setStyle("-fx-background-color: #ffe0a3; -fx-background-radius: 5px;");
             
    }

    @FXML
    private void mouseExit2(MouseEvent event) {
        pane2.setStyle("-fx-background-color: white; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseHover2(MouseEvent event) {
        pane2.setStyle("-fx-background-color: #ffe0a3; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseExit3(MouseEvent event) {
        pane3.setStyle("-fx-background-color: white; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseHover3(MouseEvent event) {
        pane3.setStyle("-fx-background-color: #ffe0a3; -fx-background-radius: 5px;");
        
    }

    @FXML
    private void mouseExit4(MouseEvent event) {
        pane4.setStyle("-fx-background-color: white; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseHover4(MouseEvent event) {
        pane4.setStyle("-fx-background-color: #ffe0a3; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseExit5(MouseEvent event) {
        pane5.setStyle("-fx-background-color: white; -fx-background-radius: 5px;");
    }

    @FXML
    private void mouseHover5(MouseEvent event) {
        pane5.setStyle("-fx-background-color: #ffe0a3; -fx-background-radius: 5px;");
    }

    @FXML
    private void profileScreen(MouseEvent event) {
        Stage profileScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("ProfileScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane1.getScene().getWindow();
            Scene scene = new Scene(root);
            profileScreen.setScene(scene);
            
            current.hide();
            profileScreen.show();
    }

    @FXML
    private void allBooksScreen(MouseEvent event) {
        Stage allBooksScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("AllBooksScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane2.getScene().getWindow();
            Scene scene = new Scene(root);
            allBooksScreen.setScene(scene);
            
            current.hide();
            allBooksScreen.show();
        
    }

    @FXML
    private void searchBookScreen(MouseEvent event) {
        Stage searchBookScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("SearchBookScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane3.getScene().getWindow();
            Scene scene = new Scene(root);
            searchBookScreen.setScene(scene);
            
            current.hide();
            searchBookScreen.show();
    }

    @FXML
    private void issueBookScreen(MouseEvent event) {
        Stage issueBookScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("IssueBookScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane4.getScene().getWindow();
            Scene scene = new Scene(root);
            issueBookScreen.setScene(scene);
            
            current.hide();
            issueBookScreen.show();
    }

    @FXML
    private void returnBookScreen(MouseEvent event) {
        Stage returnBookScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("ReturnBookScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane5.getScene().getWindow();
            Scene scene = new Scene(root);
            returnBookScreen.setScene(scene);
            
            current.hide();
            returnBookScreen.show();
    }

    @FXML
    private void logoutButton(ActionEvent event) {
        Stage loginScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane5.getScene().getWindow();
            Scene scene = new Scene(root);
            loginScreen.setScene(scene);
            
            current.hide();
            loginScreen.show();
            
            LoginScreenController.isMember = false;
        
    }
  
}
