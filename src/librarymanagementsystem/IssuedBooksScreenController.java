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
public class IssuedBooksScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTreeTableView<Issue> booksTree;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Pane basePane;

    @FXML
    private JFXHamburger hamburger;
    
     Boolean isAdmin = LoginScreenController.isAdmin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        drawer.setPrefSize(0, 0);
        
        try {
            displayIssuedBooks("SELECT * FROM Issue");
        } catch (SQLException ex) {
            Logger.getLogger(AllMembersScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllMembersScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    public void displayIssuedBooks(String sql) throws SQLException, ClassNotFoundException {
        
        JFXTreeTableColumn<Issue, String> bookId = new JFXTreeTableColumn<>("Book ID");
        bookId.setPrefWidth(225);
        bookId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Issue, String> param) ->{
    if(bookId.validateValue(param)) return param.getValue().getValue().bookId;
    else return bookId.getComputedValue(param);
});
        
        
        JFXTreeTableColumn<Issue, String> userId = new JFXTreeTableColumn<>("User ID");
        userId.setPrefWidth(225);
        userId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Issue, String> param) ->{
    if(userId.validateValue(param)) return param.getValue().getValue().userId;
    else return userId.getComputedValue(param);
});
        
        JFXTreeTableColumn<Issue, String> issueTime = new JFXTreeTableColumn<>("Issue Time");
        issueTime.setPrefWidth(225);
        issueTime.setCellValueFactory((TreeTableColumn.CellDataFeatures<Issue, String> param) ->{
    if(issueTime.validateValue(param)) return param.getValue().getValue().issueTime;
    else return issueTime.getComputedValue(param);
});
        
        JFXTreeTableColumn<Issue, String> dueDate = new JFXTreeTableColumn<>("Due Date");
        dueDate.setPrefWidth(225);
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
                books.add(new Issue(rset.getString(1),rset.getString(2), rset.getString(3), rset.getString(4)));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(AllBooksScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Issue> root = new RecursiveTreeItem<Issue>(books, RecursiveTreeObject::getChildren);
        
        booksTree.getColumns().setAll(bookId,userId,issueTime,dueDate);
        booksTree.setRoot(root);
        booksTree.setShowRoot(false);
   
    }
    
}
