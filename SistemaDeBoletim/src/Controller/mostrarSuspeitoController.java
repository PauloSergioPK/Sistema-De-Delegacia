package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Delito;
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

public class mostrarSuspeitoController {

    @FXML
    private TableView<Suspeito> tabelaBoletins;

    @FXML
    private TableColumn<Suspeito, String> colunaIdSuspeito;

    @FXML
    private TableColumn<Suspeito, String> colunaCpfSuspeito;

    @FXML
    private TableColumn<Suspeito, String> colunaDescricao;

    @FXML
    private TableColumn<Suspeito, String> colunaIdDelito;

    @FXML
    void listarDelitos(ActionEvent event) {

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

    private ArrayList<Suspeito> suspeitos = new ArrayList<>();
    private ObservableList<Suspeito> observableListBoletins;

    public void start(String query){
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            System.out.println(query);
            banco.rs = banco.stmt.executeQuery(query);
            while(banco.rs.next()){
                Suspeito aux = new Suspeito();
                aux.setIdSuspeito(banco.rs.getInt("idSuspeito"));
                aux.setDescricao(banco.rs.getString("descricao"));
                aux.setCpf(banco.rs.getString("investigado"));
                aux.setIdDelito(banco.rs.getInt("delito"));
                suspeitos.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(suspeitos);
            colunaCpfSuspeito.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaIdDelito.setCellValueFactory(new PropertyValueFactory<>("idDelito"));
            colunaIdSuspeito.setCellValueFactory(new PropertyValueFactory<>("idSuspeito"));
            tabelaBoletins.setItems(observableListBoletins);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
