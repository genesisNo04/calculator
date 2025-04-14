package org.example.calculator;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    public MFXTextField calculateScreen;

    @FXML
    public MFXButton one;

    @FXML
    public MFXButton two;

    @FXML
    public MFXButton three;

    @FXML
    public MFXButton four;

    @FXML
    public MFXButton five;

    @FXML
    public MFXButton six;

    @FXML
    public MFXButton seven;

    @FXML
    public MFXButton eight;

    @FXML
    public MFXButton nine;

    @FXML
    public MFXButton zero;

    @FXML
    public MFXButton divide;

    @FXML
    public MFXButton multiply;

    @FXML
    public MFXButton minus;

    @FXML
    public MFXButton plus;

    @FXML
    public MFXButton equal;

    @FXML
    public MFXButton dot;

    @FXML
    public MFXButton clear;

    private String currentOp = "";
    private double firstNum = 0;
    private double secondNum = 0;
    private double result = 0;
    private boolean isNewInput;

    @FXML
    private void handleClick(ActionEvent event) {
        MFXButton button = (MFXButton) event.getSource();
        String value = button.getText();

        switch (value) {
            case "+": case "-": case "*": case "/":
                if (!currentOp.isEmpty()) {
                    // perform ongoing calculation
                    secondNum = Double.parseDouble(calculateScreen.getText());
                    firstNum = calculate(firstNum, secondNum, currentOp);
                    calculateScreen.setText(String.valueOf(firstNum));
                } else {
                    firstNum = Double.parseDouble(calculateScreen.getText());
                    calculateScreen.clear();
                }
                currentOp = value;
                isNewInput = true;
                break;
            case "=":
                if (!currentOp.isEmpty()) {
                    secondNum = Double.parseDouble(calculateScreen.getText());
                    double result = calculate(firstNum, secondNum, currentOp);
                    calculateScreen.setText(String.valueOf(result));
                    currentOp = "";
                    isNewInput = true;
                }
                break;
            case "C":
                calculateScreen.clear();
                currentOp = "";
                firstNum = 0;
                secondNum = 0;
                break;
            default: // numbers and dot
                if (isNewInput) {
                    calculateScreen.clear();
                    isNewInput = false;
                }
                calculateScreen.appendText(value);
                break;
        }
    }


    private double calculate(double a, double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : 0;
            default -> 0;
        };
    }
}