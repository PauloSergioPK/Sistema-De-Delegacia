package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import Model.Delito;
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

public class mostrarDelitosController {

    @FXML
    private TableView<Delito> tabelaBoletins;

    @FXML
    private TableColumn<Delito, String> colunaidDelito;

    @FXML
    private TableColumn<Delito, String> colunaEspecie;

    @FXML
    private TableColumn<Delito, String> colunaNatureza;

    @FXML
    private TableColumn<Delito, String> colunaDataOcorrencia;

    @FXML
    private TableColumn<Delito, String> colunaidLocalOcorrencia;

    @FXML
    private TableColumn<Delito, String> colunaTipoDeLocal;

    @FXML
    private TableColumn<Delito,String> colunaIdBoletim;

    @FXML
    private TableColumn<Delito,String> colunaFlagrante;

    @FXML
    void listarEndereco(ActionEvent event) {
        try {
            Delito delito = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(delito != null) {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarEndereco.fxml"));
                    Parent fxmls = loader.load();
                    mostrarEnderecoController controller = loader.getController();
                    String query = "select * from Endereco where idEndereco = "+delito.getLocalOcorrencia()+"";
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
    void listarSuspeito(ActionEvent event) {
        try {
            Delito delito = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(delito != null) {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarSuspeitos.fxml"));
                    Parent fxmls = loader.load();
                    mostrarSuspeitoController controller = loader.getController();
                    String query = "select * from Suspeito where delito in (select idDelito from Delito where boletim = "+delito.getIdBoletim()+" )"+"";
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

    private ArrayList<Delito> delitos = new ArrayList<>();
    private ObservableList<Delito> observableListBoletins;

    public void start(String query){
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            System.out.println(query);
            banco.rs = banco.stmt.executeQuery(query);
            while(banco.rs.next()){
                Delito aux = new Delito();
                aux.setIdBoletim(banco.rs.getInt("boletim"));
                aux.setDataOcorrencia(banco.rs.getString("dataOcorrencia"));
                aux.setEspecie(banco.rs.getString("especie"));
                aux.setFlagrante(banco.rs.getBoolean("flagrante"));
                aux.setIdDelito(banco.rs.getInt("idDelito"));
                aux.setLocalOcorrencia(banco.rs.getInt("localOcorrencia"));
                aux.setNatureza(banco.rs.getString("natureza"));
                aux.setTipoDeLocal(banco.rs.getString("tipoDeLocal"));
                delitos.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(delitos);
            colunaDataOcorrencia.setCellValueFactory(new PropertyValueFactory<>("dataOcorrencia"));
            colunaEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
            colunaidDelito.setCellValueFactory(new PropertyValueFactory<>("idDelito"));
            colunaIdBoletim.setCellValueFactory(new PropertyValueFactory<>("idBoletim"));
            colunaidLocalOcorrencia.setCellValueFactory(new PropertyValueFactory<>("LocalOcorrencia"));
            colunaNatureza.setCellValueFactory(new PropertyValueFactory<>("natureza"));
            colunaTipoDeLocal.setCellValueFactory(new PropertyValueFactory<>("tipoDeLocal"));
            colunaFlagrante.setCellValueFactory(new PropertyValueFactory<>("flagrante"));
            tabelaBoletins.setItems(observableListBoletins);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
