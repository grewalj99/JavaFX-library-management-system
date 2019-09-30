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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class UpdateBooksScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane basePane;
    @FXML
    private JFXTextField bookIdInput;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton removeButton;

    Boolean isAdmin = LoginScreenController.isAdmin;
    @FXML
    private JFXTextField ISBNInput;
    @FXML
    private JFXTextField titleInput;
    @FXML
    private JFXTextField authorInput;
    @FXML
    private JFXTextField editionInput;
    @FXML
    private JFXComboBox<String> campusInput;
    
    @FXML
    private JFXTextField removeBookIdInput;
    
    Boolean bookAlreadyExists = false;
    @FXML
    private JFXComboBox<String> subjectInput;
    
    @FXML
    private Label addBookstatus;
    @FXML
    private Label removeBookStatus;
    @FXML
    private JFXButton allBooksButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        campusInput.getItems().addAll("Cloverdale", "Langley", "Richmond", "Surrey");
        subjectInput.getItems().addAll("Business", "Information Technology", "Social Science", "English", "History");
        
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
    private void addBook(MouseEvent event) throws SQLException, ClassNotFoundException {
      
        addBook();
           
    } 
    
    @FXML
    private void addBookEnter(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode() == KeyCode.ENTER)  {
            addBook();
        }
              
    }

    @FXML
    private void removeBook(MouseEvent event) throws SQLException, ClassNotFoundException {
        
        removeBook();
        
    }

    @FXML
    private void removeBookEnter(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode() == KeyCode.ENTER)  {
            removeBook();
        }
    }
    
    @FXML
    private void allBooks(MouseEvent event) {
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
    
    public void addBook() throws SQLException, ClassNotFoundException {
        String bookId = bookIdInput.getText();
        String ISBN = ISBNInput.getText();
        String title = titleInput.getText();
        String author = authorInput.getText();
        String edition = editionInput.getText();
         String subject = subjectInput.getValue();
        String campus = campusInput.getValue();
      
        Connection connection = DatabaseConnection.createConnection();

        String checkBookId = "SELECT bookId FROM Book WHERE bookId ='"+bookId+"'";
        
        String sql = "INSERT INTO Book VALUES ("
                + "'" + bookId + "',"
                + "'" + ISBN + "',"
                + "'" + title + "',"
                + "'" + author + "',"
                + "'" + edition + "',"
                + "'" + subject + "',"
                + "'" + campus + "',"
                + "" + "true" + ""
                + ")";
       
        PreparedStatement preparedStatement1 = connection.prepareStatement(checkBookId);
        ResultSet rset = preparedStatement1.executeQuery();
        
        if (rset.next()) {
            addBookstatus.setVisible(true);
            addBookstatus.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
            addBookstatus.setText("Book already exist");
            
        }
        else {
             PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.executeUpdate();
        addBookstatus.setVisible(true);
         addBookstatus.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
        addBookstatus.setText("Book is added");
            
        }
            
        
    }
    
    public void removeBook() throws SQLException, ClassNotFoundException {
         String bookId = removeBookIdInput.getText();
        
        String checkBookId = "SELECT bookId FROM Book WHERE bookId ='"+bookId+"'";
        
        String sql = "DELETE FROM Book WHERE bookId = '" + bookId + "'";
        
        
        Connection connection = DatabaseConnection.createConnection();
        PreparedStatement preparedStatement1 = connection.prepareStatement(checkBookId);
        ResultSet rset = preparedStatement1.executeQuery();
        
        if (rset.next()) {
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        preparedStatement2.executeUpdate();
        removeBookStatus.setVisible(true);
         removeBookStatus.setStyle("-fx-background-color: #7bed9f; -fx-text-fill: #2b580c; -fx-background-radius: 5");
        removeBookStatus.setText("Book is removed");
            
        }
        else {
             removeBookStatus.setVisible(true);
             removeBookStatus.setStyle("-fx-background-color: #ffb6b9; -fx-text-fill: #c10000; -fx-background-radius: 5");
            removeBookStatus.setText("Book doesn't exist");
        }
    
    }

}
