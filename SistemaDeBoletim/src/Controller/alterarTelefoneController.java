package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import Model.Cidadao;
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

public class alterarTelefoneController {
    @FXML
    private TableView<Telefone> tabelaBoletins;

    @FXML
    private TableColumn<Telefone, String> colunaNumero;

    @FXML
    private TableColumn<Telefone, String> colunaCpf;


    @FXML
    void voltarBuscas(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/alterarCidadao.fxml"));
            Parent scene = loader.load();
            alterarCidadaoController controller = loader.getController();
            String query = "select * from Cidadao where cpf = "+cidadao.getCpf()+"";
            controller.start(query,boletim);
            Main.changeScreen(new Scene(scene));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void adicionarTelefone(ActionEvent event) {
        try{
            ArrayList<Telefone> aux = avisoTelefoneController.display();
            if(aux != null){
                Conexao banco = new Conexao();
                banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                for(Telefone t : aux){
                    t.setCpf(cidadao.getCpf());
                    banco.stmt.execute("insert into Telefone values ('"+t.getNumTelefone()+"','"+cidadao.getCpf()+"')");
                }
                tabelaBoletins.getItems().addAll(aux);
                banco.Desconectar();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void removerTelefone(ActionEvent event) {
        try {
            Telefone telefone = tabelaBoletins.getSelectionModel().getSelectedItem();
            if(telefone != null) {
                tabelaBoletins.getItems().remove(telefone);
                Conexao banco = new Conexao();
                banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                banco.stmt.execute("delete from Telefone where cidadao = '"+cidadao.getCpf()+"'");
                banco.Desconectar();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Telefone> telefones = new ArrayList<>();
    private ObservableList<Telefone> observableListBoletins;
    private Cidadao cidadao;
    private Boletim boletim;

    public void start(Cidadao cidadao,Boletim boletim){
        try{
            Conexao banco = new Conexao();
            this.cidadao = cidadao;
            this.boletim = boletim;
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            banco.rs = banco.stmt.executeQuery("select * from Telefone where cidadao = '"+cidadao.getCpf()+"'");
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

    public void refresh(ArrayList<Telefone> aux){

    }
}
