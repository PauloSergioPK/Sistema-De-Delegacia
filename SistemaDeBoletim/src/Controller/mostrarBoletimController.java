package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mostrarBoletimController implements Initializable {

    @FXML
    private TableView<Boletim> tabelaBoletins;

    @FXML
    private TableColumn<Boletim, String> colunaIdBoletim;

    @FXML
    private TableColumn<Boletim, String> colunaDataComunicacao;

    @FXML
    private TableColumn<Boletim, String> colunaDataElaboracao;

    @FXML
    private TableColumn<Boletim, String> colunaRelato;

    @FXML
    private TableColumn<Boletim, String> colunaDoCpf;

    private ArrayList<Boletim> boletins = new ArrayList<>();
    private ObservableList<Boletim> observableListBoletins;
    private String string;


    @FXML
    void listarDelitos(ActionEvent event) {
        try {
            Boletim boletim = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(boletim != null) {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarDelito.fxml"));
                    Parent fxmls = loader.load();
                    mostrarDelitosController controller = loader.getController();
                    String query = "select * from Delito D where d.boletim = "+boletim.getIdBoletim()+"";
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
        try {
            Boletim boletim = tabelaBoletins.getSelectionModel().getSelectedItem();

            if(boletim != null) {
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/mostrarSuspeitos.fxml"));
                    Parent fxmls = loader.load();
                    mostrarSuspeitoController controller = loader.getController();
                    String query = "select * from Suspeito where delito in (select idDelito from Delito where boletim = "+boletim.getIdBoletim()+" )"+"";
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

        @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            banco.rs = banco.stmt.executeQuery("select * from Boletim");
            while(banco.rs.next()){
                Boletim aux = new Boletim();
                aux.setIdBoletim(banco.rs.getInt("idBoletim"));
                aux.setDataComunicacao(banco.rs.getString("dataComunicacao"));
                aux.setDataElaboracao(banco.rs.getString("dataElaboracao"));
                aux.setRelato(banco.rs.getString("relato"));
                aux.setCpfVitima(banco.rs.getString("vitima"));
                boletins.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(boletins);
            colunaDataComunicacao.setCellValueFactory(new PropertyValueFactory<>("dataComunicacao"));
            colunaDataElaboracao.setCellValueFactory(new PropertyValueFactory<>("dataElaboracao"));
            colunaRelato.setCellValueFactory(new PropertyValueFactory<>("relato"));
            colunaIdBoletim.setCellValueFactory(new PropertyValueFactory<>("idBoletim"));
            colunaDoCpf.setCellValueFactory(new PropertyValueFactory<>("idVitima"));
            tabelaBoletins.setItems(observableListBoletins);


        }
        catch (Exception e){
            e.printStackTrace();
        }
        */

    }

    public void start(String teste){ //usado para reduzir interfaces
        this.string = teste;
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            System.out.println(string);
            banco.rs = banco.stmt.executeQuery(string);
            while(banco.rs.next()){
                Boletim aux = new Boletim();
                aux.setIdBoletim(banco.rs.getInt("idBoletim"));
                aux.setDataComunicacao(banco.rs.getString("dataComunicacao"));
                aux.setDataElaboracao(banco.rs.getString("dataElaboracao"));
                aux.setRelato(banco.rs.getString("relato"));
                aux.setCpfVitima(banco.rs.getString("vitima"));
                boletins.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
            observableListBoletins = FXCollections.observableArrayList(boletins);
            colunaDataComunicacao.setCellValueFactory(new PropertyValueFactory<>("dataComunicacao"));
            colunaDataElaboracao.setCellValueFactory(new PropertyValueFactory<>("dataElaboracao"));
            colunaRelato.setCellValueFactory(new PropertyValueFactory<>("relato"));
            colunaIdBoletim.setCellValueFactory(new PropertyValueFactory<>("idBoletim"));
            colunaDoCpf.setCellValueFactory(new PropertyValueFactory<>("idVitima"));
            tabelaBoletins.setItems(observableListBoletins);


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}
