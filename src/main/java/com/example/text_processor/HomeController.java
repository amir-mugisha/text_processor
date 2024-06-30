package com.example.text_processor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public StackPane content;

    @FXML
    public Button textManipulationButton;

    @FXML
    public Button dataCollectionsButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("textProcessing.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switchToTextManipulation(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("textProcessing.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switchToDataCollections(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dataCollections.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}