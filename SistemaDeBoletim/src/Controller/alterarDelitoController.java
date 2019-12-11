package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import Model.Delito;
import Model.Investigado;
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

public class alterarDelitoController {

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

    private Boletim boletim;

    @FXML
    void voltarUpdate(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/updateBoletimScreen.fxml"));
            Parent scene = loader.load();
            updateBoletimController controller = loader.getController();
            controller.start(boletim);
            Main.changeScreen(new Scene(scene));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void mostrarSuspeito(ActionEvent event) {
        try{
            Delito delito = tabelaBoletins.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mostrarSuspeito.fxml"));
            Parent scene = loader.load();
            mostrarCriminosoController controller = loader.getController();
            String query = "select * from Suspeito where delito = "+delito.getIdDelito()+"";
            controller.start(query,boletim);
            Main.changeScreen(new Scene(scene));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Delito> delitos = new ArrayList<>();
    private ObservableList<Delito> observableListBoletins;

    public void start(Boletim boletim){
        try{
            this.boletim = boletim;
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            banco.rs = banco.stmt.executeQuery("select * from Delito where boletim = "+boletim.getIdBoletim()+"");
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
