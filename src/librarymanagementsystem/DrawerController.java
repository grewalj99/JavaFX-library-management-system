/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class DrawerController implements Initializable {

    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton searchBookButton;
    @FXML
    private JFXButton issueBookButton;
    @FXML
    private JFXButton returnBookButton;
    @FXML
    private JFXButton allBooksButton;
    @FXML
    private JFXButton profileButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mouseExit1(MouseEvent event) {
        homeButton.setStyle("-fx-background-color: white");
        
    }

    @FXML
    private void mouseHover1(MouseEvent event) {
         homeButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit2(MouseEvent event) {
        profileButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover2(MouseEvent event) {
         profileButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit3(MouseEvent event) {
        allBooksButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover3(MouseEvent event) {
         allBooksButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit4(MouseEvent event) {
        searchBookButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover4(MouseEvent event) {
         searchBookButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit5(MouseEvent event) {
        issueBookButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover5(MouseEvent event) {
         issueBookButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit6(MouseEvent event) {
        returnBookButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover6(MouseEvent event) {
         returnBookButton.setStyle("-fx-background-color: #ffe0a3");
    }
    
    @FXML
    private void homeScreen(MouseEvent event) {
        Stage homeScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)homeButton.getScene().getWindow();
            Scene scene = new Scene(root);
            homeScreen.setScene(scene);
            
            current.hide();
            homeScreen.show();
    }

    @FXML
    private void profileScreen(MouseEvent event) {
        Stage profileScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("ProfileScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)homeButton.getScene().getWindow();
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
            Stage current = (Stage)allBooksButton.getScene().getWindow();
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
            Stage current = (Stage)searchBookButton.getScene().getWindow();
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
            Stage current = (Stage)issueBookButton.getScene().getWindow();
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
            Stage current = (Stage)returnBookButton.getScene().getWindow();
            Scene scene = new Scene(root);
            returnBookScreen.setScene(scene);
            
            current.hide();
            returnBookScreen.show();
    }

}
