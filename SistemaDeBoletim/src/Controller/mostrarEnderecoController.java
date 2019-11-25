package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import Model.Endereco;
import Model.Suspeito;
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

public class mostrarEnderecoController {

    @FXML
    private TableView<Endereco> tabelaBoletins;

    @FXML
    private TableColumn<Endereco, String> colunaID;

    @FXML
    private TableColumn<Endereco, String> colunaCEP;

    @FXML
    private TableColumn<Endereco, String> colunaCidade;

    @FXML
    private TableColumn<Endereco, String> colunaEstado;

    @FXML
    private TableColumn<Endereco, String> colunaPais;

    @FXML
    private TableColumn<Endereco, String> colunaRua;

    @FXML
    private TableColumn<Endereco, String> colunaNumero;

    @FXML
    private TableColumn<Endereco, String> colunaNumAp;

    @FXML
    private TableColumn<Endereco, String> colunaComplemento;

    @FXML
    private TableColumn<Endereco, String> colunaBairro;

    @FXML
    void listarDelitos(ActionEvent event) {
        try {
            Endereco endereco = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(endereco != null) {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarDelito.fxml"));
                    Parent fxmls = loader.load();
                    mostrarDelitosController controller = loader.getController();
                    String query = "select * from Delito where localOcorrencia = "+endereco.getIdEndereco()+"";
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
    void listarSuspeitos(ActionEvent event) {

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

    private ArrayList<Endereco> enderecos = new ArrayList<>();
    private ObservableList<Endereco> observableListBoletins;

    public void start(String query){
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            System.out.println(query);
            banco.rs = banco.stmt.executeQuery(query);
            while(banco.rs.next()){
                Endereco aux = new Endereco();
                aux.setIdEndereco(banco.rs.getInt("idEndereco"));
                aux.setCep(banco.rs.getString("cep"));
                aux.setCidade(banco.rs.getString("cidade"));
                aux.setEstado(banco.rs.getString("estado"));
                aux.setPais(banco.rs.getString("pais"));
                aux.setNumero(banco.rs.getString("numero"));
                aux.setNumeroResidencia(banco.rs.getString("numeroResidencia"));
                aux.setBairro(banco.rs.getString("bairro"));
                aux.setComplemento(banco.rs.getString("complemento"));
                aux.setRua(banco.rs.getString("rua"));
                enderecos.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(enderecos);
            colunaID.setCellValueFactory(new PropertyValueFactory<>("idEndereco"));
            colunaCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
            colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
            colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            colunaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
            colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            colunaNumAp.setCellValueFactory(new PropertyValueFactory<>("numResidencia"));
            colunaBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
            colunaComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
            colunaRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
            tabelaBoletins.setItems(observableListBoletins);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
