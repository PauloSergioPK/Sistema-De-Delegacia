package Controller;

import Exec.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class menuBuscasController {

    @FXML
    private Pane apre;

    @FXML
    void ListarCrimesFromSuspeito(ActionEvent event) {

    }

    @FXML
    void listarAllBoletim(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mostrarBoletins.fxml"));
            Parent fxmls = loader.load();
            mostrarBoletimController controller = loader.getController();
            String query = "select * from Boletim";
            controller.start(query);
            Main.changeScreen(new Scene(fxmls));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllCidadao(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mostrarCidadao.fxml"));
            Parent fxmls = loader.load();
            mostrarCidadaoController controller = loader.getController();
            String query = "select * from Cidadao";
            controller.start(query);
            Main.changeScreen(new Scene(fxmls));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllDelito(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mostrarDelito.fxml"));
            Parent fxmls = loader.load();
            mostrarDelitosController controller = loader.getController();
            String query = "select * from Delito";
            controller.start(query);
            Main.changeScreen(new Scene(fxmls));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllDelitosFromBoletim(ActionEvent event) {

    }

    @FXML
    void listarAllDelitosFromEndereco(ActionEvent event) {

    }

    @FXML
    void listarAllSuspeito(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mostrarSuspeitos.fxml"));
            Parent fxmls = loader.load();
            mostrarSuspeitoController controller = loader.getController();
            String query = "select * from Suspeito";
            controller.start(query);
            Main.changeScreen(new Scene(fxmls));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllSuspeitosFromBoletim(ActionEvent event) {

    }

    @FXML
    void listarAllVitima(ActionEvent event) {

    }

    @FXML
    void listarBoletimFromVitima(ActionEvent event) {

    }

    @FXML
    void listarCidadaoFromTelefone(ActionEvent event) {

    }

    @FXML
    void listarTelefonesFromCidadao(ActionEvent event) {

    }

    @FXML
    void voltarParaPrincipal(ActionEvent event) {
        try{
            Parent fxmlprincipal = FXMLLoader.load(getClass().getResource("../View/principalScreen.fxml"));
            Scene principal = new Scene(fxmlprincipal);
            Main.changeScreen(principal);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
