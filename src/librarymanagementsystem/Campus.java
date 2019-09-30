/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author grewal
 */
public class Campus {
    StringProperty name;

    public Campus() {
    }
    
    public Campus(String name) {
    this.name = new SimpleStringProperty(name);
}
    
    
    
}
