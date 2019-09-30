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
public class AdminDrawerController implements Initializable {

    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton searchBookButton;
    @FXML
    private JFXButton issueBookButton;
    @FXML
    private JFXButton returnBookButton;
    @FXML
    private JFXButton updateBooksButton;
    @FXML
    private JFXButton updateMembersButton;
    @FXML
    private JFXButton issuedBooksButton;

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
        updateMembersButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover2(MouseEvent event) {
        updateMembersButton.setStyle("-fx-background-color: #ffe0a3");
    }
    
    @FXML
    private void mouseExit3(MouseEvent event) {
        updateBooksButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover3(MouseEvent event) {
        updateBooksButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit4(MouseEvent event) {
        issuedBooksButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover4(MouseEvent event) {
         issuedBooksButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit5(MouseEvent event) {
        searchBookButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover5(MouseEvent event) {
        searchBookButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit6(MouseEvent event) {
        issueBookButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover6(MouseEvent event) {
        issueBookButton.setStyle("-fx-background-color: #ffe0a3");
    }

    @FXML
    private void mouseExit7(MouseEvent event) {
        returnBookButton.setStyle("-fx-background-color: white");
    }

    @FXML
    private void mouseHover7(MouseEvent event) {
        returnBookButton.setStyle("-fx-background-color: #ffe0a3");
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

    @FXML
    private void updateBooksScreen(MouseEvent event) {
        Stage updateBooksScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("UpdateBooksScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)updateBooksButton.getScene().getWindow();
            Scene scene = new Scene(root);
            updateBooksScreen.setScene(scene);
            
            current.hide();
            updateBooksScreen.show();
    }

    @FXML
    private void AdminHomeScreen(MouseEvent event) {
        Stage adminHomeScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("AdminHomeScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)homeButton.getScene().getWindow();
            Scene scene = new Scene(root);
            adminHomeScreen.setScene(scene);
            
            current.hide();
            adminHomeScreen.show();
    }

    @FXML
    private void updateMembersScreen(MouseEvent event) {
        Stage updateMembersScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("UpdateMembersScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)updateMembersButton.getScene().getWindow();
            Scene scene = new Scene(root);
            updateMembersScreen.setScene(scene);
            
            current.hide();
            updateMembersScreen.show();
    }

    @FXML
    private void issuedBooksScreen(MouseEvent event) {
        Stage issuedBooksScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("IssuedBooksScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)issuedBooksButton.getScene().getWindow();
            Scene scene = new Scene(root);
            issuedBooksScreen.setScene(scene);
            
            current.hide();
            issuedBooksScreen.show();
    }

}
