package com.projetointegrador.projetointegradorsegundop2024;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaPrincipalController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
      @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnEnviarAtualizar;

    @FXML
    private Button btnEnviarCriar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnListar;

    @FXML
    private Button btnMostrarIDH;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lblAtualizar;

    @FXML
    private Label lblCriar;

    @FXML
    private Label lblMunicipiosBR;

    @FXML
    private TextField txtFAtualizar;
    
//      @FXML
//    private TextArea txtAreaPrincipal;
      
//      @FXML
//    private TextField txtFCriar; 

     @FXML
    private TextArea txtAREAprincipal;
     
     @FXML
    private TextField txtfCriar;
    
     @FXML
    void txtAreaPrincipal(MouseEvent event) {

    }

    @FXML
    void txtFCriar(ActionEvent event) {

    }
}