package Controller;

import Exec.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class janelaBuscaController {

    @FXML
    private TextField textQuery;
    private String janela;
    private String query;
    private int opcao;

    //1 para inteiro
    //2 para string

    @FXML
    void confirmarQuery(ActionEvent event) {
        try{
            if(!textQuery.getText().equals("")) {
                findController();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            }
            else{
                //coloco um aviso aqui
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private Label textAviso;

    public void start(String text,String janela,String query,int opcao){
        try {
            this.textAviso.setText(text);
            this.janela = janela;
            this.query = query;
            this.opcao = opcao;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private String formata(){
        String aux = query;
        String valor = textQuery.getText();
        if(opcao == 1)
            aux = aux.replaceAll("valor",valor);
        else if(opcao == 2)
            aux = aux.replaceAll("valor","'"+valor+"'");
        return aux;
    }

    private void findController(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(janela));
            Parent fxmls = loader.load();
            String aux = formata();
            Object controller = loader.getController();
            if (controller instanceof mostrarCidadaoController) {
                ((mostrarCidadaoController) controller).start(aux);
                Main.changeScreen(new Scene(fxmls));
            }
            else if (controller instanceof mostrarBoletimController) {
                ((mostrarBoletimController)controller).start(aux);
                Main.changeScreen(new Scene(fxmls));
            }
            else if (controller instanceof mostrarDelitosController) {
                ((mostrarDelitosController)controller).start(aux);
                Main.changeScreen(new Scene(fxmls));
            }
            else if (controller instanceof mostrarSuspeitoController) {
                ((mostrarSuspeitoController)controller).start(aux);
                Main.changeScreen(new Scene(fxmls));
            }
            else if (controller instanceof mostrarTelefoneController) {
                ((mostrarTelefoneController)controller).start(aux);
                Main.changeScreen(new Scene(fxmls));
            }
            else if (controller instanceof mostrarEnderecoController) {
                ((mostrarEnderecoController)controller).start(aux);
                Main.changeScreen(new Scene(fxmls));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
