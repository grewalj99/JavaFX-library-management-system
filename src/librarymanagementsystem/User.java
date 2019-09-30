/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.StringProperty;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javax.print.attribute.standard.RequestingUserName;
/**
 *
 * @author grewal
 */
public class User extends RecursiveTreeObject<User> {
    StringProperty userId;
    StringProperty username;
    StringProperty password;
    StringProperty fname;
    StringProperty lname;
    StringProperty dob;
    StringProperty userType;
    StringProperty campusName;
    
    public User () {
        super();
        
    }
    
    public User (String userId, String username, String password, String fname, String lname, String dob, String userType, String campusName) {
        this.userId = new SimpleStringProperty(userId);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.fname = new SimpleStringProperty(fname);
        this.lname= new SimpleStringProperty(lname);
        this.dob = new SimpleStringProperty(dob);
        this.userType = new SimpleStringProperty(userType);
        this.campusName = new SimpleStringProperty(campusName);
        
    }
  
}
