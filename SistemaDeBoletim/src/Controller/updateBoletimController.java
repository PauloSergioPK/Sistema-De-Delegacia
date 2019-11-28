package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

public class updateBoletimController {

    @FXML
    private TextArea relato;
    private Boletim boletim;

    @FXML
    void adiconarDelito(ActionEvent event) {

    }

    @FXML
    void alterarDelitos(ActionEvent event) {
        try {
            if(!relato.getText().equals("")){
                boletim.setRelato(relato.getText());
            }
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

    @FXML
    void finalizarAlteracao(ActionEvent event) {
        try{
            if(!relato.getText().equals("")){
                String query = "update boletim set relato = '"+relato.getText()+"' where idboletim =  "+boletim.getIdBoletim()+"";
                Conexao banco = new Conexao();
                banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                banco.stmt.execute(query);
                banco.Desconectar();
            }

            Parent fxmlprincipal = FXMLLoader.load(getClass().getResource("../View/principalScreen.fxml"));
            Scene inicio = new Scene(fxmlprincipal);
            Main.changeScreen(inicio);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void removerDelito(ActionEvent event) {

    }

    @FXML
    void alterarVitima(ActionEvent event) {
        try {
            if(!relato.getText().equals("")){
                boletim.setRelato(relato.getText());
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/alterarCidadao.fxml"));
            Parent scene = loader.load();
            alterarCidadaoController controller = loader.getController();
            controller.start(boletim);
            Main.changeScreen(new Scene(scene));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(Boletim boletim){
        this.boletim = boletim;
        if(boletim != null)
            this.relato.setText(boletim.getRelato());
    }

}
