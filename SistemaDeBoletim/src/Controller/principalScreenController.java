package Controller;

import Dao.Conexao;
import Exec.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class principalScreenController implements Initializable {

    @FXML
    private Label totalBoletins;

    @FXML
    private Label totalSuspeitos;

    @FXML
    void atualizarOcorrencia(ActionEvent event) {

    }

    @FXML
    void cadastrarOcorrencia(ActionEvent event) throws IOException {
        try{
            Parent fxmlcadastrarVitima = FXMLLoader.load(getClass().getResource("../View/cadastroVitima.fxml"));
            Scene cadastro = new Scene(fxmlcadastrarVitima);
            Main.changeScreen(cadastro);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void realizarConsulta(ActionEvent event) {
        try{
            Parent fxmlbuscas = FXMLLoader.load(getClass().getResource("../View/menuBuscas.fxml"));
            Scene busca = new Scene(fxmlbuscas);
            Main.changeScreen(busca);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void removerOcorrencia(ActionEvent event) throws IOException {
        try {
            Parent fxmlremoverBoletim = FXMLLoader.load(getClass().getResource("../View/removerBoletimScreen.fxml"));
            Scene remocao = new Scene(fxmlremoverBoletim);
            Main.changeScreen(remocao);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void sair(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tem alguns erros por aqui
        //23/11/2019 as 5:05 da manha ACHO que concertei
        //agora so substituir o update aqui dentro
        Conexao banco = new Conexao();
        try{
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            String resultBoletins = "Select max(idboletim) from Boletim";
            String resultSuspeitos = "Select max(idSuspeito) from Suspeito";
            String resultEnderecos = "Select max(idendereco) from Endereco";
            String resultCidadaos = "select count (cpf) from Cidadao";
            String resultTelefones = "select count (numTelefone) from Telefone";
            String resultDelitos = "Select max(idDelito) from Delito";
            banco.rs = banco.stmt.executeQuery(resultBoletins);
            while(banco.rs.next()){
                try{
                    totalBoletins.setText(banco.rs.getString("max"));
                    Main.totalBoletins = Integer.valueOf(banco.rs.getString("max"));
                }
                catch(Exception e){
                    totalBoletins.setText("0");
                    Main.totalBoletins = 0;
                }
            }
            banco.rs = banco.stmt.executeQuery(resultSuspeitos);
            while(banco.rs.next()){
                try{
                    totalSuspeitos.setText(banco.rs.getString("max"));
                    Main.totalSuspeitos = Integer.valueOf(banco.rs.getString("max"));
                }
                catch(Exception e){
                    totalSuspeitos.setText("0");;
                    Main.totalSuspeitos = 0;
                }

            }
            banco.rs = banco.stmt.executeQuery(resultEnderecos);
            while(banco.rs.next()){
                try{
                    Main.totalEndereco = Integer.valueOf(banco.rs.getString("max"));
                }
                catch(Exception e){
                    Main.totalEndereco = 0;
                }
            }
            banco.rs = banco.stmt.executeQuery(resultCidadaos);
            while(banco.rs.next()){
                Main.totalCidadao = Integer.valueOf(banco.rs.getString("count"));
            }
            banco.rs = banco.stmt.executeQuery(resultTelefones);
            while(banco.rs.next()){
                Main.totalTelefones = Integer.valueOf(banco.rs.getString("count"));
            }
            banco.rs = banco.stmt.executeQuery(resultDelitos);
            while(banco.rs.next()){
                try{
                    Main.totalDelitos = Integer.valueOf(banco.rs.getString("max"));
                }
                catch(Exception e){
                    Main.totalBoletins = 0;
                }
            }
            banco.Desconectar();
            //System.out.println(Main.totalDelitos);
            //System.out.println(Main.totalEndereco);
            //System.out.println(Main.totalBoletins);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //String tb = String.valueOf(totaldeboletins);
        //String ts = String.valueOf(totaldesuspeitos);
        //this.totalBoletins.setText(tb);
        //this.totalSuspeitos.setText(ts);
    }

    public void update(int valor,int valor2){
        this.totalBoletins.setText(Integer.toString(valor));
        this.totalSuspeitos.setText(Integer.toString(valor2));
    }
}
