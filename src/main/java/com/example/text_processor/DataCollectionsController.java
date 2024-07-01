package com.example.text_processor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DataCollectionsController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Label type;

    @FXML
    private Label warningLabel;

    @FXML
    private TextField indexTextField;

    @FXML
    private TextField itemTextField;

    @FXML
    private Label resultLabel;

    private DataCollection dataCollection;

    final String[] collections = {"ArrayList", "Set", "Map"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(collections);
        choiceBox.setOnAction(this::getCollection);
    }

    public void getCollection(ActionEvent event){
        String collection = choiceBox.getValue();
        resultLabel.setText("");
        type.setText(collection);

        switch (collection) {
            case "ArrayList":
                dataCollection = new DataCollection(DataCollection.CollectionType.ARRAYLIST);
                break;
            case "Set":
                dataCollection = new DataCollection(DataCollection.CollectionType.SET);
                break;
            case "Map":
                dataCollection = new DataCollection(DataCollection.CollectionType.MAP);
                break;
            default:
                warningLabel.setText("Invalid collection type");
                return;
        }
    }

    public void addItem(ActionEvent event){
        TextField[] fields = {itemTextField};
        // check for required fields to add data into our collection
        if(!this.validateFields(fields)){
            return;
        };
        //clear possible previous results
        warningLabel.setText("");
        resultLabel.setText("");
        if(dataCollection.type == DataCollection.CollectionType.MAP) {
            dataCollection.addItem(indexTextField.getText(), itemTextField.getText());
            displayData();
        }else{
            dataCollection.addItem(itemTextField.getText());
            displayData();
        }

    }

    public void removeItem(ActionEvent event){
        TextField[] fields = {indexTextField};
        if(!this.validateFields(fields)){
            return;
        };
        if(dataCollection.type == DataCollection.CollectionType.MAP) {
            String key = indexTextField.getText();
            if(!dataCollection.getMap().containsKey(key)){
                resultLabel.setText("");
                warningLabel.setText("Key does not exist in the map");
                displayData();
                return;
            }
            dataCollection.removeItem(key);
            displayData();
        }else{
            int indexInteger = Integer.parseInt(indexTextField.getText());
            //check if the index is within the bounds of the collection
            if(indexInteger < 0 || indexInteger >= dataCollection.size()){
                resultLabel.setText("");
                warningLabel.setText("Index out of bounds");
                displayData();
                return;
            }
            dataCollection.removeItem(indexInteger);
            displayData();
        }
    }

    public void editItem(ActionEvent event){
        TextField[] fields = {indexTextField, itemTextField};
        if(!this.validateFields(fields)){
            return;
        };
        warningLabel.setText("");
        resultLabel.setText("");
        if(dataCollection.type == DataCollection.CollectionType.MAP) {
            dataCollection.updateItem(indexTextField.getText(), itemTextField.getText());
            displayData();
        }else{
            int indexInteger = Integer.parseInt(indexTextField.getText());
            if(indexInteger < 0 || indexInteger >= dataCollection.size()){
                resultLabel.setText("");
                warningLabel.setText("Index out of bounds");
                displayData();
                return;
            }
            dataCollection.updateItem(Integer.parseInt(indexTextField.getText()),itemTextField.getText());
            displayData();
        }
    }

    public void clear(){
        dataCollection.clear();
        warningLabel.setText("");
        resultLabel.setText("");
    }

    public  boolean validateFields(TextField[] fields){
        if(choiceBox.getValue() == null){
            warningLabel.setText("Collection type is required");
            return false;
        }

        for(TextField field: fields){
            if(field.getText().isEmpty()){
                warningLabel.setText(field.getPromptText() + " is required");
                return false;
            }
            if (dataCollection.type != DataCollection.CollectionType.MAP&&field == indexTextField) {
                try {
                    Integer.parseInt(field.getText());
                } catch (NumberFormatException e) {
                    warningLabel.setText("Index must be a number");
                    return false;
                }
            }
        }
        return true;
    }

    public void displayData(){
        resultLabel.setText(dataCollection.toString());
    }
}
