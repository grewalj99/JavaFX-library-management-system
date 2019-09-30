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
public class GuestHomeScreenController implements Initializable {

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;
    
    Boolean isGuest = LoginScreenController.isGuest;
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
    void logoutButton(ActionEvent event) {
        Stage loginScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane1.getScene().getWindow();
            Scene scene = new Scene(root);
            loginScreen.setScene(scene);
            
            current.hide();
            loginScreen.show();
            
            isGuest = false;
            
            LoginScreenController.isGuest = false;

    }

    @FXML
    private void allBooksScreen(MouseEvent event) {
        Stage allBooksScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("AllBooksScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)pane1.getScene().getWindow();
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
            Stage current = (Stage)pane2.getScene().getWindow();
            Scene scene = new Scene(root);
            searchBookScreen.setScene(scene);
            
            current.hide();
            searchBookScreen.show();
    }
    
}
