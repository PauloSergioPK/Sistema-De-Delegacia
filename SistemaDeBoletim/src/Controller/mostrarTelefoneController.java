package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Telefone;
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

public class mostrarTelefoneController {

    @FXML
    private TableView<Telefone> tabelaBoletins;

    @FXML
    private TableColumn<Telefone, String> colunaNumero;

    @FXML
    private TableColumn<Telefone, String> colunaCpf;

    @FXML
    void listarCidadao(ActionEvent event) {
        try {
            Telefone telefone = tabelaBoletins.getSelectionModel().getSelectedItem();
            if (telefone != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarCidadao.fxml"));
                    Parent fxmls = loader.load();
                    mostrarCidadaoController controller = loader.getController();
                    String query = "select * from Cidadao where cpf = '" + telefone.getCpf() + "'";
                    controller.start(query);
                    Main.changeScreen(new Scene(fxmls));
                } catch (Exception e) {
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

    private ArrayList<Telefone> telefones = new ArrayList<>();
    private ObservableList<Telefone> observableListBoletins;

    public void start(String query){
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            System.out.println(query);
            banco.rs = banco.stmt.executeQuery(query);
            while(banco.rs.next()){
                Telefone aux = new Telefone();
                aux.setCpf(banco.rs.getString("cidadao"));
                aux.setNumTelefone(banco.rs.getString("numTelefone"));
                telefones.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(telefones);
            colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numTelefone"));
            tabelaBoletins.setItems(observableListBoletins);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}