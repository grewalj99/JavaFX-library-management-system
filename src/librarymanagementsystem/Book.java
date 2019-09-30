/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author grewal
 */
public class Book extends RecursiveTreeObject<Book>{
    
    StringProperty bookId;
    StringProperty ISBN;
    StringProperty title;
    StringProperty author;
    StringProperty edition;
    StringProperty subject;
    StringProperty campusName;
    BooleanProperty status;

    public Book() {
        super();
    }

    public Book(String bookId, String ISBN, String title, String author, String edition, String subject, String campusName, Boolean status) {
        this.bookId = new SimpleStringProperty(bookId);
        this.ISBN = new SimpleStringProperty(ISBN);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.edition = new SimpleStringProperty(edition);
        this.subject = new SimpleStringProperty(subject);
        this.campusName = new SimpleStringProperty(campusName);
        this.status = new SimpleBooleanProperty(status);
    }
    
    public Book(String bookId, String title) {
        this.bookId = new SimpleStringProperty(bookId);
        this.title = new SimpleStringProperty(title);
        
    }
    
}
