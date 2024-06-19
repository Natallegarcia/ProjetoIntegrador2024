package com.projetointegrador.projetointegradorsegundop2024;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {
    

    @FXML
    private void switchToSecondary() throws IOException {
        
        if (validarCamposLogin()){
        App.setRoot("TelaPrincipal");
        }
        
    }
    // método mostrar imagen IBGE
    @FXML
    private ImageView imgViewIBGE;
    @FXML
    public void initialize (URL location,ResourceBundle resources){
        Image image = new Image(getClass().getResourceAsStream("/Pictures/IBGE1.png"));
        imgViewIBGE.setImage(image);
    
    }
    
    //validações de login regras
    
    private boolean validarCamposLogin() {
        String login = txtFildLogin.getText().trim();
        String senha = txtFiledSenha.getText().trim();

        if(login.isEmpty()){
            mostrarAlerta("Erro de Validação" , "Campo de Login vazio", "Por favor, insira seu login.");
            return false;
        }
        
        
        if(!validarEmail(login)){
            mostrarAlerta("Erro de Validação" , "Email inválido", "Por favor, insira um email válido");
            return false;
        }
        
        if (senha.isEmpty()){
            mostrarAlerta("Erro de Validação" , "Campo de Senha vazio", "Por favor, insira sua sennha.");
            return false;
         }
        
        if(!validarSenha(login)){
            mostrarAlerta("Erro de Validação" , "Senha inválida", "A senha deve ter pelo menos 4 caracteres");
            return false;
        }
        return true;
    }
     private boolean validarEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean validarSenha(String senha) {
            if (senha.length() <= 4) {
                return false;
        }else if (senha.length() > 4){
            return true;
        }
        return false;
    }       
    //método alerta login 
    private void mostrarAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
    //declaração dos nós
    
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
    
   
  

}


