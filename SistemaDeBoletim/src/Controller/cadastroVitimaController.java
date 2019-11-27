package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class cadastroVitimaController{

    private ArrayList<Telefone> telefones = new ArrayList<>();
    @FXML
    private Button btAvancar;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textCpf;

    @FXML
    private TextField textRg;

    @FXML
    private TextField textDataNascimento;

    @FXML
    private TextField textNacionalidade;

    @FXML
    private TextField textSexo;

    @FXML
    private TextField textEstadoCivil;

    @FXML
    private TextField textProfissao;

    @FXML
    private TextField textPai;

    @FXML
    private TextField textMae;

    @FXML
    private TextField textIdade;

    @FXML
    private TextField textCep;

    @FXML
    private TextField textCidade;

    @FXML
    private TextField textEstado;

    @FXML
    private TextField textPais;

    @FXML
    private TextField textRua;

    @FXML
    private TextField textNumero;

    @FXML
    private TextField textNumeroResidencia;

    @FXML
    private TextField textComplemento;

    @FXML
    private TextField textBairro;

    private boolean cpfValido(){
        if(!textCpf.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean rgValido(){
        if(!textRg.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean nomeValido(){
        if(!textNome.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean estadoCivilValido(){
        if(!textEstadoCivil.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean estadoValido(){
        if(!textEstado.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean dataNascimentoValido(){
        if(!textDataNascimento.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean complementoValido(){
        if(textComplemento.getText().equals(""))
            return false;
        return true;
    }

    private boolean cidadeValido(){
        if(!textCidade.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean cepValido(){
        if(!textCep.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean bairroValido(){
        if(!textBairro.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean idadeValido(){
        if(!textIdade.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean maeValido(){
        if(!textMae.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean paiValido(){
        if(!textPai.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean nacionalidadeValido(){
        if(!textNacionalidade.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean numeroValido(){
        if(!textNumero.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean residenciaValido(){
        if(!textNumeroResidencia.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean paisValido(){
        if(!textPais.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean profissaoValido(){
        if(!textProfissao.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean ruaValido(){
        if(!textRua.getText().equals(""))
            return true;
        else
            return false;
    }

    private boolean sexoValido(){
        if(!textSexo.getText().equals(""))
            return true;
        else
            return false;
    }

    @FXML
    private Label textStatus;

    /*
    public void setTelefones(ArrayList<Telefone> t){
        this.telefones = t;
    }

    public void addTelefone(Telefone t){
        if(t != null)
            telefones.add(t);
    }

     */

    @FXML
    void cadastrarCivil(ActionEvent event) {
        try {
            if(cpfValido()){
                String aux = "";
                Conexao banco = new Conexao();
                banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                banco.rs = banco.stmt.executeQuery("select cpf from Cidadao where cpf = '"+textCpf.getText()+"'");
                while(banco.rs.next()){
                    aux = banco.rs.getString("cpf");
                }
                banco.Desconectar();
                if(aux.equals(textCpf.getText())){
                    this.textStatus.setText("Vitima ja cadastrada!!!");
                    return;
                }
            }
            if (complementoValido() && nomeValido() && sexoValido() && ruaValido() && profissaoValido() && paisValido() && residenciaValido() &&
                    numeroValido() && nacionalidadeValido() && paiValido() && maeValido() && idadeValido() && bairroValido() && cepValido() && cidadeValido() &&
                    dataNascimentoValido() && estadoValido() && estadoCivilValido() && rgValido() && cpfValido()) {
                Cidadao cidadao = new Cidadao(textCpf.getText(),textRg.getText(),textDataNascimento.getText(),textNacionalidade.getText(),textSexo.getText(),textEstadoCivil.getText(),textNome.getText(),textProfissao.getText(),
                        textMae.getText(),textPai.getText(),textIdade.getText(),Main.totalEndereco+1);
                Endereco endereco = new Endereco(Main.totalEndereco+1,textCep.getText(),textCidade.getText(),textEstado.getText(),textPais.getText(),textRua.getText(),
                        textNumero.getText(),textNumeroResidencia.getText(),textComplemento.getText(),textBairro.getText());
                this.textStatus.setText("");
                Main.totalEndereco++;


                /*Parent fxmlaviso = FXMLLoader.load(getClass().getResource("../View/avisoTelefone.fxml"));
                Scene aviso = new Scene(fxmlaviso);
                Stage stage2 = new Stage();
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(aviso);
                stage2.showAndWait();

                 */
                //Parent fxmlBoletim = FXMLLoader.load(getClass().getResource("../View/cadastroBoletim.fxml"));
                telefones = avisoTelefoneController.display();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/cadastroBoletim.fxml"));
                Parent fxmlBoletim = loader.load();
                cadastroBoletimController controller = loader.getController();
                if(telefones != null) {
                    for (Telefone t : telefones) {
                        t.setCpf(cidadao.getCpf());
                    }
                }
                controller.start(cidadao,telefones,endereco,1);
                System.out.println(endereco.getComplemento());
                Main.changeScreen(new Scene(fxmlBoletim));
                //Node node = (Node) event.getSource();
                //Stage stage = (Stage) node.getScene().getWindow();
                //stage.close();
            }
            else{
                this.textStatus.setText("Informacoes incompletas!!!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
