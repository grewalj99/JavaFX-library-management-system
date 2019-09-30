/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class AllMembersScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTreeTableView<User> usersTree;

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
            displayAllMembers("SELECT * FROM User");
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
            Logger.getLogger(AllMembersScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 public void displayAllMembers(String sql) throws SQLException, ClassNotFoundException {
        
        JFXTreeTableColumn<User, String> userId = new JFXTreeTableColumn<>("User ID");
        userId.setPrefWidth(150);
        userId.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(userId.validateValue(param)) return param.getValue().getValue().userId;
    else return userId.getComputedValue(param);
});
        
        
        JFXTreeTableColumn<User, String> username = new JFXTreeTableColumn<>("Username");
        username.setPrefWidth(150);
        username.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(username.validateValue(param)) return param.getValue().getValue().username;
    else return username.getComputedValue(param);
});
        
        JFXTreeTableColumn<User, String> password = new JFXTreeTableColumn<>("Password");
        password.setPrefWidth(150);
        password.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(password.validateValue(param)) return param.getValue().getValue().password;
    else return password.getComputedValue(param);
});
        
        JFXTreeTableColumn<User, String> fname = new JFXTreeTableColumn<>("First Name");
        fname.setPrefWidth(150);
        fname.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(fname.validateValue(param)) return param.getValue().getValue().fname;
    else return fname.getComputedValue(param);
});
        
        JFXTreeTableColumn<User, String> lname = new JFXTreeTableColumn<>("Last Name");
        lname.setPrefWidth(150);
        lname.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(lname.validateValue(param)) return param.getValue().getValue().lname;
    else return lname.getComputedValue(param);
});
        
        JFXTreeTableColumn<User, String> dob = new JFXTreeTableColumn<>("DOB");
        dob.setPrefWidth(150);
        dob.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(dob.validateValue(param)) return param.getValue().getValue().dob;
    else return dob.getComputedValue(param);
});
        
        JFXTreeTableColumn<User, String> userType = new JFXTreeTableColumn<>("User Type");
        userType.setPrefWidth(150);
        userType.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(userType.validateValue(param)) return param.getValue().getValue().userType;
    else return userType.getComputedValue(param);
});
        
        JFXTreeTableColumn<User, String> campusName = new JFXTreeTableColumn<>("Campus Name");
        campusName.setPrefWidth(150);
        campusName.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
    if(campusName.validateValue(param)) return param.getValue().getValue().campusName;
    else return campusName.getComputedValue(param);
});
        
        ObservableList<User> members = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.createConnection();
        try {
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            
            ResultSet rset = preparedStatement.executeQuery();
            
            while(rset.next()){
                members.add(new User(rset.getString(1),rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8)));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(AllMembersScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<User> root = new RecursiveTreeItem<User>(members, RecursiveTreeObject::getChildren);
        
        usersTree.getColumns().setAll(userId,username,password,fname,lname,dob,userType,campusName);
        usersTree.setRoot(root);
        usersTree.setShowRoot(false);
  
    }
  
}
