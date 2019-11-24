package Controller;

import Exec.Main;
import Model.Boletim;
import Model.Cidadao;
import Model.Endereco;
import Model.Telefone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class cadastroBoletimController {
    private Cidadao cidadao;
    private ArrayList<Telefone> telefones;
    private Endereco enderecoCidadao;

    @FXML
    private Button btAvancar;

    @FXML
    private TextField textComunicao;

    @FXML
    private TextField textElaboracao;;

    @FXML
    private TextArea textRelato;

    @FXML
    private Label textStatus;

    private boolean comunicacaoValida(){
        if(!textComunicao.getText().equals(""))
            return true;
        return false;
    }

    private  boolean colaboracaoValida(){
        if(!textElaboracao.getText().equals(""))
            return true;
        return false;
    }

    private boolean relatoValido(){
        if(!textRelato.getText().equals(""))
            return true;
        return false;
    }

    @FXML
    void cadastrarBoletim(ActionEvent event) {
        if(comunicacaoValida() && colaboracaoValida() && relatoValido()){
            Boletim boletim = new Boletim(Main.totalBoletins +1,textComunicao.getText(),textElaboracao.getText(),textRelato.getText(),cidadao.getCpf()); //id ta sendo provisorio
            Main.totalBoletins++;
            this.textStatus.setText("");
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/cadastroDelito.fxml"));
                Parent fxmlDelito = loader.load();
                cadastroDelitoController controller = loader.getController();
                controller.start(cidadao,telefones,enderecoCidadao,boletim);
                Main.changeScreen(new Scene(fxmlDelito));
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        else{
            this.textStatus.setText("Informacoes invalidas !!!");
        }
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Endereco getEnderecoCidadao() {
        return enderecoCidadao;
    }

    public void setEnderecoCidadao(Endereco enderecoCidadao) {
        this.enderecoCidadao = enderecoCidadao;
    }

    public void start(Cidadao cidadao,ArrayList<Telefone> telefones, Endereco enderecoCidadao){
        this.cidadao = cidadao;
        this.telefones = telefones;
        this.enderecoCidadao = enderecoCidadao;
    }
}
