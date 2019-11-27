package Controller;

import Exec.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class menuBuscasController {

    @FXML
    private Pane apre;

    @FXML
    void ListarCrimesFromSuspeito(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o cpf do suspeito ","../View/mostrarDelito.fxml","select * from Delito where idDelito in (select delito from Suspeito where investigado = valor);",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o numero do boletim","../View/mostrarDelito.fxml","select * from Delito where boletim = valor",1);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllDelitosFromCidade(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o nome da cidade","../View/mostrarDelito.fxml","select * from Delito where localOcorrencia in (select idEndereco from Endereco where cidade = valor)",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllDelitosFromPais(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o nome do pais","../View/mostrarDelito.fxml","select * from Delito where localOcorrencia in (select idEndereco from Endereco where pais = valor)",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllDelitosFromRua(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o nome da rua","../View/mostrarDelito.fxml","select * from Delito where localOcorrencia in (select idEndereco from Endereco where rua = valor)",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o numero do boletim ","../View/mostrarSuspeitos.fxml","select * from Suspeito where delito in (select idDelito from Delito where boletim = valor);",1);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarAllVitima(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/mostrarCidadao.fxml"));
            Parent fxmls = loader.load();
            mostrarCidadaoController controller = loader.getController();
            String query = "select * from Cidadao where cpf = (select vitima from Boletim)";
            controller.start(query);
            Main.changeScreen(new Scene(fxmls));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarBoletimFromVitima(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o cpf da vitima ","../View/mostrarBoletins.fxml","select * from Boletim where vitima =  valor",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarCidadaoFromTelefone(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o telefone do cidadao ","../View/mostrarCidadao.fxml","select * from Cidadao where cpf in (select cidadao from Telefone where numTelefone = valor);",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void listarTelefonesFromCidadao(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/janelaBusca.fxml"));
            Parent scene = loader.load();
            janelaBuscaController controller = loader.getController();
            controller.start("Digite o cpf do cidadao ","../View/mostrarTelefones.fxml","select * from Telefone where cidadao = valor;",2);
            Scene aviso = new Scene(scene);
            Stage stage2 = new Stage();
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(aviso);
            stage2.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
