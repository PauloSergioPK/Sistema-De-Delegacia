package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Cidadao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class mostrarCidadaoController {

    @FXML
    private TableView<Cidadao> tabelaBoletins;

    @FXML
    private TableColumn<Cidadao, String> colunaCPF;

    @FXML
    private TableColumn<Cidadao, String> colunaRG;

    @FXML
    private TableColumn<Cidadao, String> colunaDataDeNascimento;

    @FXML
    private TableColumn<Cidadao, String> colunaNacionalidade;

    @FXML
    private TableColumn<Cidadao, String> colunaSexo;

    @FXML
    private TableColumn<Cidadao, String> colunaEstadoCivil;

    @FXML
    private TableColumn<Cidadao, String> colunaNome;

    @FXML
    private TableColumn<Cidadao, String> colunaProfissao;

    @FXML
    private TableColumn<Cidadao, String> colunaPai;

    @FXML
    private TableColumn<Cidadao, String> colunaMae;

    @FXML
    private TableColumn<Cidadao, String> colunaIdade;

    @FXML
    private TableColumn<Cidadao, String> colunaidEndereco;

    @FXML
    void listarBoletim(ActionEvent event) { //mostra todos que o cidadao fez
        try {
            Cidadao cidadao = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(cidadao != null && cidadao.getCpf() != "desconhecido") {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarBoletins.fxml"));
                    Parent fxmls = loader.load();
                    mostrarBoletimController controller = loader.getController();
                    String query = "select * from Boletim where vitima = '"+cidadao.getCpf()+"'";
                    controller.start(query);
                    Main.changeScreen(new Scene(fxmls));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarTelefones(ActionEvent event) {
        try {
            Cidadao cidadao = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(cidadao != null && cidadao.getCpf() != "desconhecido") {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarTelefones.fxml"));
                    Parent fxmls = loader.load();
                    mostrarTelefoneController controller = loader.getController();
                    String query = "select * from Telefone where cidadao = '"+cidadao.getCpf()+"'";
                    controller.start(query);
                    Main.changeScreen(new Scene(fxmls));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarDelitos(ActionEvent event) {
        try {
            Cidadao cidadao = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(cidadao != null && cidadao.getCpf() != "desconhecido") {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarDelito.fxml"));
                    Parent fxmls = loader.load();
                    mostrarDelitosController controller = loader.getController();
                    String query = "select * from Delito where idDelito in (select delito from Suspeito where investigado = '"+cidadao.getCpf()+"')";
                    controller.start(query);
                    Main.changeScreen(new Scene(fxmls));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarEndereco(ActionEvent event) {
        try {
            Cidadao cidadao = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(cidadao != null && cidadao.getCpf() != "desconhecido") {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarEndereco.fxml"));
                    Parent fxmls = loader.load();
                    mostrarEnderecoController controller = loader.getController();
                    String query = "select * from Endereco where idEndereco ="+cidadao.getEndereco()+"";
                    controller.start(query);
                    Main.changeScreen(new Scene(fxmls));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void voltarBuscas(ActionEvent event) {
        try{
            Parent fxmlbuscas = FXMLLoader.load(getClass().getResource("../View/menuBuscas.fxml"));
            Scene busca = new Scene(fxmlbuscas);
            Main.changeScreen(busca);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Cidadao> cidadaos = new ArrayList<>();
    private ObservableList<Cidadao> observableListBoletins;

    public void start(String query){
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            System.out.println(query);
            banco.rs = banco.stmt.executeQuery(query);
            while(banco.rs.next()){
                Cidadao aux = new Cidadao();
                aux.setCpf(banco.rs.getString("cpf"));
                aux.setDataNascimento(banco.rs.getString("dataNascimento"));
                aux.setRg(banco.rs.getString("rg"));
                aux.setNacionalidade(banco.rs.getString("nacionalidade"));
                aux.setSexo(banco.rs.getString("sexo"));
                aux.setEstadoCivil(banco.rs.getString("estadoCivil"));
                aux.setNome(banco.rs.getString("nome"));
                aux.setProfissao(banco.rs.getString("profissao"));
                aux.setNomeDaMae(banco.rs.getString("nomeDaMae"));
                aux.setNomeDoPai(banco.rs.getString("nomeDoPai"));
                aux.setIdade(banco.rs.getString("idade"));
                aux.setEndereco(banco.rs.getInt("endereco"));
                cidadaos.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(cidadaos);
            colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            colunaRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
            colunaDataDeNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
            colunaEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
            colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
            colunaProfissao.setCellValueFactory(new PropertyValueFactory<>("profissao"));
            colunaMae.setCellValueFactory(new PropertyValueFactory<>("nomeDaMae"));
            colunaPai.setCellValueFactory(new PropertyValueFactory<>("nomeDoPai"));
            colunaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
            colunaidEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
            tabelaBoletins.setItems(observableListBoletins);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
