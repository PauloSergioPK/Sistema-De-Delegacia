package Controller;

import Model.Telefone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class adicionarTelefoneController {

    public static Telefone telefone;
    @FXML
    private TextField textNumTelefone;

    @FXML
    void confirmarTelefone(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/cadastroVitima.fxml"));
        try{
            loader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        telefone = new Telefone(textNumTelefone.getText(),"");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static Telefone display(){
        try {
            Parent fxmlaviso = FXMLLoader.load(avisoTelefoneController.class.getResource("../View/adicionarTelefone.fxml"));
            Scene aviso = new Scene(fxmlaviso);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return telefone;
    }
}
