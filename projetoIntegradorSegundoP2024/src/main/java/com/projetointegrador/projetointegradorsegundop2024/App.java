package com.projetointegrador.projetointegradorsegundop2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.projetointegrador.projetointegradorsegundop2024.models.Csv;
import com.projetointegrador.projetointegradorsegundop2024.models.Municipio;
import com.projetointegrador.projetointegradorsegundop2024.models.MunicipioServico;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        //Começa o código Marcus e instanciação
        
        
        Csv csv = new Csv();
        MunicipioServico municipioService = null;

        try {
            List<Municipio> municipios = csv.lerCsv("C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\01.ProjetoIntegrador_BaseMunicipios_In - 01.ProjetoIntegrador_BaseMunicipios_In.csv");
            municipioService = new MunicipioServico(municipios);
            municipioService.calcularInformacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("Escolha uma operação: 1-Listar 2-Criar 3-Atualizar 4-Excluir 5-Salvar 6-Mostrar Melhor/Pior PIBpC e IDH Educação 7-Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                switch (opcao) {
                    case 1:
                        for (Municipio municipio : municipioService.listarMunicipios()) {
                            System.out.println(municipio);
                        }
                        break;

                    case 2:
                        System.out.println("Digite os dados do novo município (separados por vírgula):");
                    String[] dados = scanner.nextLine().split(",");
                    try{
                    String codigoIBGENovo = dados[0].trim();
                    if (municipioService.buscarMunicipioPorCodigo(codigoIBGENovo) != null) {
                        System.out.println("Código IBGE já existente. Não é possível adicionar o município.");
                        break;
                    }
                    Municipio novoMunicipio = new Municipio(dados[0].trim(), dados[1].trim(), dados[2].trim(), dados[3].trim(), dados[4].trim(),
                            Double.parseDouble(dados[5].trim()), Double.parseDouble(dados[6].trim()), Double.parseDouble(dados[7].trim()),
                            Double.parseDouble(dados[8].trim()), Double.parseDouble(dados[9].trim()), Double.parseDouble(dados[10].trim()),
                            Double.parseDouble(dados[11].trim()), Double.parseDouble(dados[12].trim()), Double.parseDouble(dados[13].trim()),
                            Double.parseDouble(dados[14].trim()));
                    municipioService.adicionarMunicipio(novoMunicipio);
                    System.out.println("Município adicionado com sucesso.");
                    }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){ 
                         System.out.println("Erro ao adicionar o município: Dados inválidos.");
                
                    }
                    break;

                    case 3:
                        System.out.println("Digite o código IBGE do município a ser atualizado:");
                        String codigoIBGE = scanner.nextLine().trim();
                        Municipio municipioExistente = municipioService.buscarMunicipioPorCodigo(codigoIBGE);
                        if (municipioExistente == null) {
                            System.out.println("Código IBGE não encontrado. Por favor, tente novamente.");
                            break;
                        }
                        System.out.println("Digite os novos dados do município (separados por vírgula):");
                        String[] novosDados = scanner.nextLine().split(",");
                        try{
                        Municipio municipioAtualizado = new Municipio(
                                novosDados[0].trim(), novosDados[1].trim(), novosDados[2].trim(), novosDados[3].trim(), novosDados[4].trim(),
                                Double.parseDouble(novosDados[5].trim()), Double.parseDouble(novosDados[6].trim()), Double.parseDouble(novosDados[7].trim()),
                                Double.parseDouble(novosDados[8].trim()), Double.parseDouble(novosDados[9].trim()), Double.parseDouble(novosDados[10].trim()),
                                Double.parseDouble(novosDados[11].trim()), Double.parseDouble(novosDados[12].trim()), Double.parseDouble(novosDados[13].trim()),
                                Double.parseDouble(novosDados[14].trim()));
                        municipioService.atualizarMunicipio(codigoIBGE, municipioAtualizado);
                        System.out.println("Município atualizado: " + municipioService.listarMunicipios().stream()
                                .filter(m -> m.getCodigoIBGE().equals(codigoIBGE)).findFirst().orElse(null).formatarInfo());
                        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
                            System.out.println("Erro ao atualizar o município: Dados inválidos.");
                            
                        }
                        break;

                    case 4:
                        System.out.println("Digite o código IBGE do município a ser excluído:");
                        String codigoExclusao = scanner.nextLine().trim();
                        Municipio municipioParaExcluir = municipioService.buscarMunicipioPorCodigo(codigoExclusao);
                        if (municipioParaExcluir == null) {
                            System.out.println("Código IBGE não encontrado. Por favor, tente novamente.");
                            break;
                        }
                        municipioService.excluirMunicipio(codigoExclusao);
                        System.out.println("Município excluído com sucesso.");
                        break;

                    case 5:
                        try {
                            csv.escreverCsv(municipioService.listarMunicipios(), "C:\\Users\\natalle.santos\\Documents\\projetoIntergradorArquivo\\novoCsv\\novoProjetoIntegradorMunicipios.csv.csv");
                            System.out.println("Arquivo salvo com sucesso.");
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;

                    case 6:
                        Municipio melhorPIBpC = municipioService.getMunicipioComMelhorPIBpC();
                        Municipio piorPIBpC = municipioService.getMunicipioComPiorPIBpC();
                        Municipio melhorIDHEducacao = municipioService.getMunicipioComMelhorIDHEducacao();
                        Municipio piorIDHEducacao = municipioService.getMunicipioComPiorIDHEducacao();

                        System.out.println("Melhor PIB per Capita:");
                        System.out.println(melhorPIBpC.formatarInfo());

                        System.out.println("\nPior PIB per Capita:");
                        System.out.println(piorPIBpC.formatarInfo());

                        System.out.println("\nMelhor IDH Educação:");
                        System.out.println(melhorIDHEducacao.formatarInfo());

                        System.out.println("\nPior IDH Educação:");
                        System.out.println(piorIDHEducacao.formatarInfo());
                        break;

                    case 7: 
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();  // Consumir a entrada inválida
            }
        }

        scanner.close();
    }

        
    }

