package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import Model.Cidadao;
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

public class mostrarCriminosoController {

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
    void detalhesSuspeito(ActionEvent event) {
        try {
            Suspeito suspeito = tabelaBoletins.getSelectionModel().getSelectedItem();
            if (suspeito != null) {
                try { // agora falta melhorar a volta
                    Conexao banco = new Conexao();
                    banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                    String query = "select * from Cidadao where cpf = '" + suspeito.getCpf() + "'";
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
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../View/alterarCidadao.fxml"));
                        Parent fxmls = loader.load();
                        alterarCidadaoController controller = loader.getController();
                        controller.start(query, boletim);
                        banco.Desconectar();
                        Main.changeScreen(new Scene(fxmls));
                        return;
                    }
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/alterarCidadao.fxml"));
                    Parent fxmls = loader.load();
                    alterarCidadaoController controller = loader.getController();
                    controller.start("select * From Cidadao where cpf = 'desconhecido'", boletim);
                    banco.Desconectar();
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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/alterarDelito.fxml"));
            Parent scene = loader.load();
            alterarDelitoController controller = loader.getController();
            controller.start(boletim);
            Main.changeScreen(new Scene(scene));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Suspeito> suspeitos = new ArrayList<>();
    private ObservableList<Suspeito> observableListBoletins;
    private Boletim boletim;

    public void start(String query,Boletim boletim){
        try{
            this.boletim = boletim;
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
