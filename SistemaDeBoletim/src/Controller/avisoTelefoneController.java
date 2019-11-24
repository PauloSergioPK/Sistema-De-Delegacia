package Controller;


import Exec.Main;
import Model.Telefone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class avisoTelefoneController {
    private static ArrayList<Telefone> telefones;

    @FXML
    private Text textAviso;


    @FXML
    void avancar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/cadastroVitima.fxml"));
        try{
            loader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //cadastroVitimaController controller = loader.getController();
        //controller.addTelefone(new Telefone("1","1"));
        //controller.getClass().notify();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cadastrarTelefone(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/cadastroVitima.fxml"));
        try{
            loader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        cadastroVitimaController controller = loader.getController();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            /*Parent fxmlcadastro = FXMLLoader.load(getClass().getResource("../View/adicionarTelefone.fxml"));
            Scene aviso = new Scene(fxmlcadastro);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            //stage.hide();

             */
            stage.setOpacity(0);
            telefones.add(adicionarTelefoneController.display());
            this.textAviso.setText("Deseja adicionar outro telefone ?");
            stage.setOpacity(1);

            //stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Telefone> display(){
        telefones = new ArrayList<>();
        try {
            Parent fxmlaviso = FXMLLoader.load(avisoTelefoneController.class.getResource("../View/avisoTelefone.fxml"));
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

        return telefones;
    }

}
