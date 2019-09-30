/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class ProfileScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane basePane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label name;
    @FXML
    private JFXTreeTableView<Issue> booksTree;

    Boolean isAdmin = LoginScreenController.isAdmin;
    String usernameText = LoginScreenController.usernameText;
    String userIdText = LoginScreenController.userIdText;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            displayInformation();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            displayIssuedBooks("SELECT bookId, dueDate FROM Issue WHERE userId = '"+ userIdText +"'"); 
        } catch (SQLException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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

public void displayInformation() throws SQLException, ClassNotFoundException {
    String sql = "SELECT * FROM User WHERE username ='"+usernameText+"'";
   // name.setText(usernameText);
    Connection connection = DatabaseConnection.createConnection();
    PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
    ResultSet rset = preparedStatement1.executeQuery();
    
    if (rset.next()) {
            name.setText(rset.getString(4) + " " + rset.getString(5));
        } 
}


public void displayIssuedBooks(String sql) throws SQLException, ClassNotFoundException {
        
        JFXTreeTableColumn<Issue, String> bookId = new JFXTreeTableColumn<>("Book ID");
        bookId.setPrefWidth(250);
        bookId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Issue, String> param) ->{
    if(bookId.validateValue(param)) return param.getValue().getValue().bookId;
    else return bookId.getComputedValue(param);
});
       
       
        
        JFXTreeTableColumn<Issue, String> dueDate = new JFXTreeTableColumn<>("Due Date");
        dueDate.setPrefWidth(250);
        dueDate.setCellValueFactory((TreeTableColumn.CellDataFeatures<Issue, String> param) ->{
    if(dueDate.validateValue(param)) return param.getValue().getValue().dueDate;
    else return dueDate.getComputedValue(param);
});
        
        
        
        ObservableList<Issue> books = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.createConnection();
        try {
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            
            ResultSet rset = preparedStatement.executeQuery();
            
            while(rset.next()){
                books.add(new Issue(rset.getString(1),rset.getString(2)));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(AllBooksScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Issue> root = new RecursiveTreeItem<Issue>(books, RecursiveTreeObject::getChildren);
        
        booksTree.getColumns().setAll(bookId,dueDate);
        booksTree.setRoot(root);
        booksTree.setShowRoot(false);

    }
    
}
