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
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author grewal
 */
public class AllBooksScreenController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane basePane;
    @FXML
    private JFXTreeTableView<Book> booksTree;
    
    Boolean isAdmin = LoginScreenController.isAdmin;
    Boolean isMember = LoginScreenController.isMember;
    Boolean isGuest = LoginScreenController.isGuest;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        drawer.setPrefSize(0, 0);
        
        try {
            displayAllBooks("SELECT * FROM Book");
        } catch (SQLException ex) {
            Logger.getLogger(AllBooksScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllBooksScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if (isAdmin) {
            LoadDrawer("AdminDrawer.fxml");
        }
        else if (isMember) {
            LoadDrawer("Drawer.fxml");
        }
        else if (isGuest) {
            LoadDrawer("GuestDrawer.fxml");
            
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
    
    public void displayAllBooks(String sql) throws SQLException, ClassNotFoundException {
        
        JFXTreeTableColumn<Book, String> bookId = new JFXTreeTableColumn<>("Book ID");
        bookId.setPrefWidth(150);
        bookId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(bookId.validateValue(param)) return param.getValue().getValue().bookId;
    else return bookId.getComputedValue(param);
});
        
        
        JFXTreeTableColumn<Book, String> ISBN = new JFXTreeTableColumn<>("ISBN");
        ISBN.setPrefWidth(150);
        ISBN.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(ISBN.validateValue(param)) return param.getValue().getValue().ISBN;
    else return ISBN.getComputedValue(param);
});
        
        JFXTreeTableColumn<Book, String> title = new JFXTreeTableColumn<>("Title");
        title.setPrefWidth(150);
        title.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(title.validateValue(param)) return param.getValue().getValue().title;
    else return title.getComputedValue(param);
});
        
        JFXTreeTableColumn<Book, String> author = new JFXTreeTableColumn<>("Author");
        author.setPrefWidth(150);
        author.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(author.validateValue(param)) return param.getValue().getValue().author;
    else return author.getComputedValue(param);
});
        
        JFXTreeTableColumn<Book, String> edition = new JFXTreeTableColumn<>("Edition");
        edition.setPrefWidth(150);
        edition.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(edition.validateValue(param)) return param.getValue().getValue().edition;
    else return edition.getComputedValue(param);
});
        
        JFXTreeTableColumn<Book, String> subject = new JFXTreeTableColumn<>("Subject");
        subject.setPrefWidth(150);
        subject.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(subject.validateValue(param)) return param.getValue().getValue().subject;
    else return subject.getComputedValue(param);
});
        
        JFXTreeTableColumn<Book, String> campusName = new JFXTreeTableColumn<>("Campus");
        campusName.setPrefWidth(150);
        campusName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
    if(campusName.validateValue(param)) return param.getValue().getValue().campusName;
    else return campusName.getComputedValue(param);
});
        
        JFXTreeTableColumn<Book, Boolean> status = new JFXTreeTableColumn<>("Status");
        status.setPrefWidth(150);
        status.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, Boolean> param) ->{
    if(status.validateValue(param)) return param.getValue().getValue().status;
    else return status.getComputedValue(param);
});
        
        ObservableList<Book> books = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.createConnection();
        try {
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            
            ResultSet rset = preparedStatement.executeQuery();
            
            while(rset.next()){
                books.add(new Book(rset.getString(1),rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getBoolean(8)));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(AllBooksScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Book> root = new RecursiveTreeItem<Book>(books, RecursiveTreeObject::getChildren);
        
        booksTree.getColumns().setAll(bookId,ISBN,title,author,edition,subject,campusName,status);
        booksTree.setRoot(root);
        booksTree.setShowRoot(false);
    
    }

}    
    

