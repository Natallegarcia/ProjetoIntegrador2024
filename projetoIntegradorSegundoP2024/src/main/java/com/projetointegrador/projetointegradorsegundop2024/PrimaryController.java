package com.projetointegrador.projetointegradorsegundop2024;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("TelaPrincipal");
    }
    
      @FXML
    private AnchorPane anchorPrincipalLogin;

    @FXML
    private Label lblBemVindo;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblSenha;

    @FXML
    private Button primaryButton;

    @FXML
    private TextField txtFildLogin;

    @FXML
    private TextField txtFiledSenha;

//    @FXML
//    void switchToSecondary(ActionEvent event) {
//
//    }

}


