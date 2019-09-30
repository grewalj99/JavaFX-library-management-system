/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author grewal
 */
public class Issue extends RecursiveTreeObject<Issue> {
    StringProperty bookId;
    StringProperty userId;
    StringProperty issueTime;
    StringProperty dueDate;

    public Issue() {
    }

    public Issue(String bookId, String userId, String issueTime, String dueDate) {
        this.bookId = new SimpleStringProperty(bookId);
        this.userId = new SimpleStringProperty(userId);
        this.issueTime = new SimpleStringProperty(issueTime);
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    Issue(String bookId, String dueDate) {
        this.bookId = new SimpleStringProperty(bookId);
         this.dueDate = new SimpleStringProperty(dueDate);
    }
    
    
}
