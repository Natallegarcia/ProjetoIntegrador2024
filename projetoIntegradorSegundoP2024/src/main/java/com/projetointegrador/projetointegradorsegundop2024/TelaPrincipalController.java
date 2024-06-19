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
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
  




public class TelaPrincipalController implements Initializable {
    
    //declarando as variáveis
    private Csv csv = new Csv();
    private MunicipioServico municipioService = null;
    private boolean isUpdating = false;
    private String novaEntrada;
     private boolean continuar = true;
    

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
           
    
    
      //nós(elementos) do front SceneBuillder  
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
    
    @FXML
    private TextField txtFExcluir;
    
  
    @FXML
    private Label lblExcluir;
        
    @FXML
    private Button btnExcluir2;
    
    @FXML
    private Button btnLimparGrid;
    
        
    
    //configuração dos eventos
    
   
    @FXML
    public void listarMunicipios(ActionEvent event) {


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
   
    @FXML
    public void atualizarMunicipios (ActionEvent event){ 
         
        
             try {
                // Carrega a lista de municípios do arquivo CSV
                List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
                municipioService = new MunicipioServico(municipios);
                municipioService.calcularInformacoes();
            } catch (IOException e) {
                e.printStackTrace();
                lblError.setText("Erro ao ler o arquivo CSV.");
                return;  // Saia do método se ocorrer um erro ao ler o arquivo CSV
            }
                   

                        
            if (!isUpdating) {          
                        String codigoIBGE = txtFAtualizar.getText().trim();
                        
                        txtAREAprincipal.appendText("Código IBGE digitado: '" + codigoIBGE + "'");
                        
                           
                    lblError.setText("Buscando município com código IBGE: " + codigoIBGE);
                    lblError.setText("                                                   " );
                    
                    Municipio municipioExistente = municipioService.buscarMunicipioPorCodigo(codigoIBGE);
                    if (municipioExistente == null) {
                        txtAREAprincipal.appendText("Código IBGE não encontrado. Por favor, tente novamente." + "\n");
                        txtAREAprincipal.appendText("Município não encontrado para o código IBGE: " + codigoIBGE);
                        return;
                    }  
                    
                    if (municipioExistente != null) {
                        txtAREAprincipal.appendText("Código IBGE  encontrado. " + "\n" + municipioExistente.toString() + "\n");
                        lblError.setText("Aperte a tecla 'ENTER' " + "Digite os novos dados do município (separados por vírgula):" + "\n" );
                         isUpdating = true;
                        return;
                    }
                    
} 
            
    }
    
  @FXML
   public void MostrarCaixaDialogo(KeyEvent event) {
       
       try {
                // Carrega a lista de municípios do arquivo CSV
                List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
                municipioService = new MunicipioServico(municipios);
                municipioService.calcularInformacoes();
            } catch (IOException e) {
                e.printStackTrace();
                lblError.setText("Erro ao ler o arquivo CSV.");
                return;  // Saia do método se ocorrer um erro ao ler o arquivo CSV
            }
       
       
         if (event.getCode() == KeyCode.ENTER && isUpdating) {
            String codigoIBGE = txtFAtualizar.getText().trim();
            novaEntrada = JOptionPane.showInputDialog(null, "Insira os dados do município com as alterações");

            if (novaEntrada == null || novaEntrada.trim().isEmpty()) {
                lblError.setText("Por favor, insira os novos dados do município.");
                txtAREAprincipal.appendText("Erro: Nenhuma entrada fornecida para atualização.\n");
                return;
            }

            String[] novosDados = novaEntrada.split(",");
            try {
                Municipio municipioAtualizado = new Municipio(
                        novosDados[0].trim(), novosDados[1].trim(), novosDados[2].trim(), novosDados[3].trim(), novosDados[4].trim(),
                        Double.parseDouble(novosDados[5].trim()), Double.parseDouble(novosDados[6].trim()), Double.parseDouble(novosDados[7].trim()),
                        Double.parseDouble(novosDados[8].trim()), Double.parseDouble(novosDados[9].trim()), Double.parseDouble(novosDados[10].trim()),
                        Double.parseDouble(novosDados[11].trim()), Double.parseDouble(novosDados[12].trim()), Double.parseDouble(novosDados[13].trim()),
                        Double.parseDouble(novosDados[14].trim())
                );

              
                 
//                         Municipio municipioExistente = municipioService.buscarMunicipioPorCodigo(codigoIBGE);
                         municipioService.atualizarMunicipio(codigoIBGE, municipioAtualizado);

                 
                txtAREAprincipal.appendText("Município atualizado: " + municipioService.listarMunicipios().stream()
                        .filter(m -> m.getCodigoIBGE().equals(codigoIBGE)).findFirst().orElse(null).formatarInfo());
                
                // Salvar as mudanças no CSV
                salvarMunicipios(null);
                   
                   
                isUpdating = false;
               
            } catch (ArrayIndexOutOfBoundsException e) {
                lblError.setText("Erro ao atualizar o município: Dados insuficientes.");
                txtAREAprincipal.appendText("Erro: " + e.getMessage() + "\n");
            } catch (NumberFormatException e) {
                lblError.setText("Erro ao atualizar o município: Dados numéricos inválidos.");
                txtAREAprincipal.appendText("Erro: " + e.getMessage() + "\n");
            }
            
            
        }
         
        
    }
    
   
    
    @FXML
    public void excluirMunicipios (ActionEvent event){
        
        try {
                // Carrega a lista de municípios do arquivo CSV
                List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
                municipioService = new MunicipioServico(municipios);
                municipioService.calcularInformacoes();
            } catch (IOException e) {
                e.printStackTrace();
                lblError.setText("Erro ao ler o arquivo CSV.");
                return;  // Saia do método se ocorrer um erro ao ler o arquivo CSV
            }
        
                        String codigoExclusao = txtFExcluir.getText().trim();
                        Municipio municipioParaExcluir = municipioService.buscarMunicipioPorCodigo(codigoExclusao);
                        if (municipioParaExcluir == null) {
                            System.out.println("Código IBGE não encontrado. Por favor, tente novamente.");
                            return;
                        }
                        municipioService.excluirMunicipio(codigoExclusao);
                        lblError.setText("Município excluído com sucesso.");
                       return;
           
    }
    //ação botão salvar
    @FXML
    public void salvarMunicipios (ActionEvent event){
        
    try {
                            csv.escreverCsv(municipioService.listarMunicipios(), "C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\novoCsv\\novoProjetoIntegradorMunicipios.csv.csv");
                            lblError.setText("Arquivo salvo com sucesso.");
                        } catch (IOException e) {
                             txtAREAprincipal.appendText("Erro ao salvar o arquivo: " + e.getMessage());
                            e.printStackTrace();
                        }
     
              //ao clicar em salvar retirar todos os campos 
            btnExcluir2.setVisible(false);
            lblExcluir.setVisible(false);
            txtFExcluir.setVisible(false);

            lblAtualizar.setVisible(false);
            txtFAtualizar.setVisible(false);
            btnEnviarAtualizar.setVisible(false);

             btnEnviarCriar.setVisible(false);
                        lblCriar.setVisible(false);
            txtfCriar.setVisible(false);
              
    
    
    
    }
    @FXML
    public void mostrarMelhor (ActionEvent event){
         Municipio melhorPIBpC = municipioService.getMunicipioComMelhorPIBpC();
                        Municipio piorPIBpC = municipioService.getMunicipioComPiorPIBpC();
                        Municipio melhorIDHEducacao = municipioService.getMunicipioComMelhorIDHEducacao();
                        Municipio piorIDHEducacao = municipioService.getMunicipioComPiorIDHEducacao();

                        txtAREAprincipal.appendText("Melhor PIB per Capita:");
                        txtAREAprincipal.appendText(melhorPIBpC.formatarInfo());

                        txtAREAprincipal.appendText("\nPior PIB per Capita:");
                        txtAREAprincipal.appendText(piorPIBpC.formatarInfo());

                        txtAREAprincipal.appendText("\nMelhor IDH Educação:");
                        txtAREAprincipal.appendText(melhorIDHEducacao.formatarInfo());

                        txtAREAprincipal.appendText("\nPior IDH Educação:");
                        txtAREAprincipal.appendText(piorIDHEducacao.formatarInfo());
    
    }

    //ao clicar em 2-Criar habilitar o campo de edição e o botão criar
     @FXML
   public void mostrarCriarMunicipios(MouseEvent event) {
            btnEnviarCriar.setVisible(true);
            lblCriar.setVisible(true);
            txtfCriar.setVisible(true);
            
    }
    //ao clicar em 3-Atualizar municípios habilitar o campo de edição e o botão enviar
      @FXML
    public void mostrarEditarMunicipios(MouseEvent event) {
            lblAtualizar.setVisible(true);
            txtFAtualizar.setVisible(true);
            btnEnviarAtualizar.setVisible(true);
            
    }
    //habilitar campos do menu Excluir
     @FXML
    void MotrarCampoExcluir(ActionEvent event) {
        lblError.setText("Digite o código IBGE do município a ser excluído:");
        btnExcluir2.setVisible(true);
        lblExcluir.setVisible(true);
        txtFExcluir.setVisible(true);
    }

    
    @FXML
    public void enviarCriar (ActionEvent event){
        
 
           
            try {
                // Carrega a lista de municípios do arquivo CSV
                List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
                municipioService = new MunicipioServico(municipios);
                municipioService.calcularInformacoes();
            } catch (IOException e) {
                e.printStackTrace();
                lblError.setText("Erro ao ler o arquivo CSV.");
                return;  // Sai do método se ocorrer um erro ao ler o arquivo CSV
            }

            // Tente obter os dados do novo município a partir do campo de texto
            String dados = txtfCriar.getText();
            String[] arrayDados = dados.split(",");

            // Verifica se a quantidade de dados fornecidos é suficiente
            if (arrayDados.length < 15) {
                lblError.setText("Erro ao adicionar o município: Dados insuficientes.");
                return;
            }

            try {
                String codigoIBGENovo = arrayDados[0].trim();
                if (municipioService.buscarMunicipioPorCodigo(codigoIBGENovo) != null) {
                    lblError.setText("Código IBGE já existente. Não é possível adicionar o município.");
                    return;  // Sai do método se o código IBGE já existir
                }

                // Cria um novo município com os dados fornecidos
                Municipio novoMunicipio = new Municipio(
                    arrayDados[0].trim(), arrayDados[1].trim(), arrayDados[2].trim(), arrayDados[3].trim(), arrayDados[4].trim(),
                    Double.parseDouble(arrayDados[5].trim()), Double.parseDouble(arrayDados[6].trim()), Double.parseDouble(arrayDados[7].trim()),
                    Double.parseDouble(arrayDados[8].trim()), Double.parseDouble(arrayDados[9].trim()), Double.parseDouble(arrayDados[10].trim()),
                    Double.parseDouble(arrayDados[11].trim()), Double.parseDouble(arrayDados[12].trim()), Double.parseDouble(arrayDados[13].trim()),
                    Double.parseDouble(arrayDados[14].trim())
                );

                // Adiciona o novo município à lista e atualiza a interface
                municipioService.adicionarMunicipio(novoMunicipio);
                lblError.setText("Município adicionado com sucesso!!");
                txtAREAprincipal.appendText(novoMunicipio.toString() + "\n");

            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                lblError.setText("Erro ao adicionar o município: Dados inválidos.");
            }      
    }
    
    // função para limpar o txtAreaPrincipal
   @FXML
   public void LimparGrid(ActionEvent event){
       txtAREAprincipal.clear();
       lblError.setText("   ");
   
   }
   
   //finalizar a execução 
   @FXML
   public void Sair(ActionEvent event){
       continuar = false;
        System.out.println("Aplicação encerrada.");
        // Opcional: Fechar a aplicação
         Platform.exit();
   
   }
     
}

   




