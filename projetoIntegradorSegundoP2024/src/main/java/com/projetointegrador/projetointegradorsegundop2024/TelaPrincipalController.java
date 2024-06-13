package com.projetointegrador.projetointegradorsegundop2024;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



import com.projetointegrador.projetointegradorsegundop2024.App;
import com.projetointegrador.projetointegradorsegundop2024.models.Csv;
import com.projetointegrador.projetointegradorsegundop2024.models.Municipio;
import com.projetointegrador.projetointegradorsegundop2024.models.MunicipioServico;
import java.net.URL;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.Initializable;




public class TelaPrincipalController implements Initializable {
    
    //declarando as variáveis
    private Csv csv = new Csv();
    private MunicipioServico municipioService = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
          
       
    }
    
    
        @FXML
    public void municipios(ActionEvent event) {
        try {
            List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
            municipioService = new MunicipioServico(municipios);
            municipioService.calcularInformacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Municipio municipio : municipioService.listarMunicipios()) {
            txtAREAprincipal.appendText(municipio.toString() + "\n");
        }                
    }
           
    
    
      //nós do front  
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
    private Label lblError;

    @FXML
    private Label lblCriar;

    @FXML
    private Label lblMunicipiosBR;

    @FXML
    private TextField txtFAtualizar;
     

     @FXML
    private TextArea txtAREAprincipal;
     
     @FXML
    private TextField txtfCriar;
     
     //Métodos 
    
     @FXML
    public void txtAreaPrincipal(MouseEvent event) {
       
    }

    @FXML
    public void txtFCriar(ActionEvent event) {
            
    }
    
    //configuração dos eventos
    
   
     @FXML
    public void listarMunicipios(ActionEvent event) {
//        Csv csv = new Csv();
//        MunicipioServico municipioService = null;

        try {
            List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
            municipioService = new MunicipioServico(municipios);
            municipioService.calcularInformacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Municipio municipio : municipioService.listarMunicipios()) {
                            txtAREAprincipal.appendText(municipio.toString() + "\n");
                          
                        }                
    }
    @FXML
    public void mensagemCriar (ActionEvent event){
        
        lblError.setText("Digite os dados do novo município (separados por vírgula):");
    }
    public void criarMunicipios (ActionEvent event){
         
        
      
    }
    @FXML
    public void atualizarMunicipios (ActionEvent event){ 
    
        String codigoIBGE = txtFAtualizar.getText().trim();
                        Municipio municipioExistente = municipioService.buscarMunicipioPorCodigo(codigoIBGE);
                        if (municipioExistente == null) {
                            System.out.println("Código IBGE não encontrado. Por favor, tente novamente.");
                       
                        }
                       lblError.setText("Digite os novos dados do município (separados por vírgula):");
                        String[] novosDados = txtFAtualizar.getText().split(",");
                        try{
                        Municipio municipioAtualizado = new Municipio(
                                novosDados[0].trim(), novosDados[1].trim(), novosDados[2].trim(), novosDados[3].trim(), novosDados[4].trim(),
                                Double.parseDouble(novosDados[5].trim()), Double.parseDouble(novosDados[6].trim()), Double.parseDouble(novosDados[7].trim()),
                                Double.parseDouble(novosDados[8].trim()), Double.parseDouble(novosDados[9].trim()), Double.parseDouble(novosDados[10].trim()),
                                Double.parseDouble(novosDados[11].trim()), Double.parseDouble(novosDados[12].trim()), Double.parseDouble(novosDados[13].trim()),
                                Double.parseDouble(novosDados[14].trim()));
                        municipioService.atualizarMunicipio(codigoIBGE, municipioAtualizado);
                        lblError.setText("Município atualizado: " + municipioService.listarMunicipios().stream()
                                .filter(m -> m.getCodigoIBGE().equals(codigoIBGE)).findFirst().orElse(null).formatarInfo());
                        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
                            lblError.setText("Erro ao atualizar o município: Dados inválidos.");
                            
                        }
    }
    public void excluirMunicipios (ActionEvent event){
            
    }
    //ação botão salvar
    @FXML
    public void salvarMunicipios (ActionEvent event){
        
    try {
                            csv.escreverCsv(municipioService.listarMunicipios(), "C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\novoCsv\\novoProjetoIntegradorMunicipios.csv.csv");
                            System.out.println("Arquivo salvo com sucesso.");
                        } catch (IOException e) {
                             txtAREAprincipal.appendText("Erro ao salvar o arquivo: " + e.getMessage());
                            e.printStackTrace();
                        }
        
    }
    
    public void mostrarMelhor (ActionEvent event){
    
    }
    
    public void sair (ActionEvent event){
    
    }
    
    @FXML
    public void enviarCriar (ActionEvent event){
//         Csv csv = new Csv();
//        MunicipioServico municipioService = null;
//
//        try {
//            List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
//            municipioService = new MunicipioServico(municipios);
//            municipioService.calcularInformacoes();
//        } catch (IOException e) {
//            e.printStackTrace();
//            

        
            String dados = txtfCriar.getText();
            String[] arrayDados = dados.split(","); 
              
        
                    try{
                            String codigoIBGENovo = arrayDados[0].trim();
                            if (municipioService.buscarMunicipioPorCodigo(codigoIBGENovo) != null) {
                              lblError.setText("Código IBGE já existente. Não é possível adicionar o município.");  
                            }Municipio novoMunicipio = new Municipio(arrayDados[0].trim(), arrayDados[1].trim(), arrayDados[2].trim(), arrayDados[3].trim(), arrayDados[4].trim(),
                                    Double.parseDouble(arrayDados[5].trim()), Double.parseDouble(arrayDados[6].trim()), Double.parseDouble(arrayDados[7].trim()),
                                    Double.parseDouble(arrayDados[8].trim()), Double.parseDouble(arrayDados[9].trim()), Double.parseDouble(arrayDados[10].trim()),
                                    Double.parseDouble(arrayDados[11].trim()), Double.parseDouble(arrayDados[12].trim()), Double.parseDouble(arrayDados[13].trim()),
                                    Double.parseDouble(arrayDados[14].trim()));
                            municipioService.adicionarMunicipio(novoMunicipio);
                            lblError.setText("Município adicionado com sucesso!!"); 
                              txtAREAprincipal.appendText(novoMunicipio.toString() + "\n");
                    } catch(ArrayIndexOutOfBoundsException | NumberFormatException i){ 
                             lblError.setText("Erro ao adicionar o município: Dados inválidos.");
                    }  
                    
//                            txtAREAprincipal.appendText(arrayDados.toString() + "\n");
                          
                        
            txtAREAprincipal.appendText(Arrays.toString(arrayDados) + "\n");
            
    }
    
}  


