package com.example.text_processor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessingController {
    @FXML
    private TextField regexTextField;

    @FXML
    private TextField repTextField;

    @FXML
    private TextField textTextField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private TextFlow highlightResultTextFlow;


    public void findMatch(ActionEvent event){
        try{
            TextField[] fields = {textTextField, regexTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");
            highlightResultTextFlow.getChildren().clear();
            Pattern pattern = Pattern.compile(regexTextField.getText(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(textTextField.getText());
            if(matcher.find()) {
                resultLabel.setText("Match found");
            }else{
                resultLabel.setText("No match found");
            }
            return;
        }catch(Exception e){
            warningLabel.setText(e.getMessage());
        }
    }

    public void replaceMatch(ActionEvent event){
        try{
            TextField[] fields = {textTextField, regexTextField, repTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");
            highlightResultTextFlow.getChildren().clear();
            Pattern pattern = Pattern.compile(regexTextField.getText(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(textTextField.getText());
            String replacedText = matcher.replaceAll(repTextField.getText());
            resultLabel.setText(replacedText);
        }catch(Exception e){
            warningLabel.setText(e.getMessage());
        }
    }

    public void highlightMatch(ActionEvent event) {
        try {
            TextField[] fields = {textTextField, regexTextField};
            this.validateTextFields(fields);
            warningLabel.setText("");

            Pattern pattern = Pattern.compile(regexTextField.getText(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(textTextField.getText());

            // Clear previous results
            highlightResultTextFlow.getChildren().clear();
            resultLabel.setText("");

            int lastMatchEnd = 0;
            while (matcher.find()) {
                // Add text before the match
                if (matcher.start() > lastMatchEnd) {
                    Text preMatchText = new Text(textTextField.getText().substring(lastMatchEnd, matcher.start()));
                    preMatchText.setStyle("-fx-font-size: 20px;-fx-font-weight:bold"); // set font size as needed
                    highlightResultTextFlow.getChildren().add(preMatchText);
                }

                // Add the matched text with highlighting and font size
                Text highlightedText = new Text(matcher.group());
                highlightedText.setStyle("-fx-fill: red; -fx-font-size: 20px; -fx-font-weight:bold"); // change color and font size as needed
                highlightResultTextFlow.getChildren().add(highlightedText);

                lastMatchEnd = matcher.end();
            }

            // Add remaining text after the last match
            if (lastMatchEnd < textTextField.getText().length()) {
                Text postMatchText = new Text(textTextField.getText().substring(lastMatchEnd));
                postMatchText.setStyle("-fx-font-size: 20px;-fx-font-weight:bold"); // set font size as needed
                highlightResultTextFlow.getChildren().add(postMatchText);
            }

            // If no matches were found, display a message
            if (lastMatchEnd == 0) {
                highlightResultTextFlow.getChildren().clear();
                resultLabel.setText("No match found");
            }
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }



    public void validateTextFields(TextField[] fields) throws Exception {
        for(TextField field: fields){
            if(field.getText().isEmpty()){
                highlightResultTextFlow.getChildren().clear();
                resultLabel.setText("");
                warningLabel.setText(field.getPromptText() + " is required");
                throw new Exception(field.getPromptText() + " is required");
            }
        }
    }


}
