/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 *
 * @author grewal
 */
public class FirstScreenController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ImageView startingimage;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        FadeTransition ft = new FadeTransition(Duration.millis(4000), startingimage);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.play();
        
        ft.setOnFinished((ActionEvent e) -> {
            Stage loginScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            } catch (IOException exception) {
            }
            Stage current = (Stage)startingimage.getScene().getWindow();
            Scene scene = new Scene(root);
            loginScreen.setScene(scene);
            
            current.hide();
            loginScreen.show();
        });
        
           
    
}}
