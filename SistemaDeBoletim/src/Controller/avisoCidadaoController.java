package Controller;

import Dao.Conexao;
import Exec.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class avisoCidadaoController {

    @FXML
    private Text textAviso;

    @FXML
    void cadastrarBoletim(ActionEvent event) {
        try{
            Parent fxmlcadastrarVitima = FXMLLoader.load(getClass().getResource("../View/pegarCidadao.fxml"));
            Scene cadastro = new Scene(fxmlcadastrarVitima);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(cadastro);
            stage.show();
            Node node = (Node) event.getSource();
            Stage stage2 = (Stage) node.getScene().getWindow();
            stage2.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrarCidadao(ActionEvent event) {
        try{
            Parent fxmlcadastrarVitima = FXMLLoader.load(getClass().getResource("../View/cadastroVitima.fxml"));
            Scene cadastro = new Scene(fxmlcadastrarVitima);
            Main.changeScreen(cadastro);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
