package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class cadastroDelitoController {
    private Cidadao cidadao;
    private ArrayList<Telefone> telefones;
    private Endereco enderecoCidadao;
    private Boletim boletim;
    private ArrayList<Endereco> enderecosDeCrimes = new ArrayList<>();
    private ArrayList<Investigado> investigados = new ArrayList<>();
    private ArrayList<Delito> delitos = new ArrayList<>();

    @FXML
    private Button btAvancar;

    @FXML
    private TextField textEspecie;

    @FXML
    private TextField textNatureza;

    @FXML
    private CheckBox cbFlagrante;

    @FXML
    private TextField textDataOcorrencia;

    @FXML
    private TextField textTipoLocal;

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

    @FXML
    private Label textDica;

    int opcaoCidadao; // 1 para cadastrar, 2 para nao cadastrar

    private void bairroValido(){
        if(textBairro.getText().equals(""))
            textBairro.setText("desconhecido");
    }

    private void complementoValido(){
        if(textComplemento.getText().equals(""))
            textComplemento.setText("desconhecido");
    }

    private void numeroResidenciaValido(){
        if(textNumeroResidencia.getText().equals(""))
            textNumeroResidencia.setText("desconhecido");
    }

    private void numeroValido(){
        if(textNumero.getText().equals(""))
            textNumero.setText("desconhecido");
    }

    private void ruaValido(){
        if(textRua.getText().equals(""))
            textRua.setText("desconhecido");
    }

    private boolean paisValido(){
        if(textPais.getText().equals(""))
            return false;
        return true;
    }

    private boolean estadoValido(){
        if(textEstado.getText().equals(""))
            return false;
        return true;
    }

    private boolean cidadeValido(){
        if(textCidade.getText().equals(""))
            return false;
        return true;
    }

    private void cepValido(){
        if(textCep.getText().equals(""))
            textCep.setText("desconhecido");
    }

    private boolean tipolocalValido(){
        if(textTipoLocal.getText().equals(""))
            return false;
        return true;
    }

    private boolean dataoOcorrenciaValido(){
        if(textDataOcorrencia.getText().equals(""))
            return false;
        return true;
    }

    private boolean especieValido(){
        if(textEspecie.getText().equals(""))
            return false;
        return true;
    }

    private boolean naturezaValido(){
        if(textNatureza.getText().equals(""))
            return false;
        return true;
    }

    @FXML
    void cadastrarDelito(ActionEvent event) {
        if(cidadeValido() && especieValido() && naturezaValido() && dataoOcorrenciaValido() && estadoValido() && paisValido() && tipolocalValido()){
            textoAvisoDelito.setText("");
            bairroValido();
            cepValido();
            numeroResidenciaValido();
            numeroValido();
            ruaValido();
            complementoValido();
            boolean flagrante = cbFlagrante.selectedProperty().getValue();
            try{
                Endereco endereco = new Endereco(Main.totalEndereco+1,textCep.getText(),textCidade.getText(),textEstado.getText(),textPais.getText(),textRua.getText(),
                        textNumero.getText(),textNumeroResidencia.getText(),textComplemento.getText(),textBairro.getText());
                Main.totalEndereco++;
                Delito delito = new Delito(Main.totalDelitos+1,textEspecie.getText(),textNatureza.getText(),flagrante,textDataOcorrencia.getText(),endereco.getIdEndereco(),textTipoLocal.getText(),boletim.getIdBoletim());
                Main.totalDelitos++;
                if(flagrante) {
                    Investigado aux = cadastroSuspeitoController.display(delito.getIdDelito());
                    if (aux != null)
                        investigados.add(aux);
                }
                delitos.add(delito);
                enderecosDeCrimes.add(endereco);
                limpaTela();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            textoAvisoDelito.setText("Informacoes incompletas ou invalidas");
        }
    }

    @FXML
    private Label textoAvisoDelito;

    @FXML
    void cadastrarNoBanco(ActionEvent event) {
        try{
            if(delitos.isEmpty()){
                textoAvisoDelito.setText("Necessita cadastrar um delito");
            }
            else{
                Conexao banco = new Conexao();
                banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                banco.inserir(cidadao,enderecoCidadao,telefones,enderecosDeCrimes,investigados,delitos,boletim,opcaoCidadao);
                banco.Desconectar();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/principalScreen.fxml"));
                Parent fxmls = loader.load();
                principalScreenController controller = loader.getController();
                controller.update(Main.totalBoletins,Main.totalSuspeitos);
                Main.changeScreen(new Scene(fxmls));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Main.changeScreen("logou");
    }

    private void limpaTela(){
        textNumero.setText("");
        textNatureza.setText("");
        textEspecie.setText("");
        textDataOcorrencia.setText("");
        textTipoLocal.setText("");
        textCep.setText("");
        textCidade.setText("");
        textEstado.setText("");
        textRua.setText("");
        textNumeroResidencia.setText("");
        textComplemento.setText("");
        textBairro.setText("");
        textPais.setText("");
    }

    @FXML
    void cadastrarSuspeito(ActionEvent event) {
        if(cidadeValido() && especieValido() && naturezaValido() && dataoOcorrenciaValido() && estadoValido() && paisValido() && tipolocalValido()){
            textoAvisoDelito.setText("");
            bairroValido();
            cepValido();
            numeroResidenciaValido();
            numeroValido();
            ruaValido();
            complementoValido();
            boolean flagrante = cbFlagrante.selectedProperty().getValue();
            try{
                Endereco endereco = new Endereco(Main.totalEndereco+1,textCep.getText(),textCidade.getText(),textEstado.getText(),textPais.getText(),textRua.getText(),
                        textNumero.getText(),textNumeroResidencia.getText(),textComplemento.getText(),textBairro.getText());
                Main.totalEndereco++;
                Delito delito = new Delito(Main.totalDelitos+1,textEspecie.getText(),textNatureza.getText(),flagrante,textDataOcorrencia.getText(),endereco.getIdEndereco(),textTipoLocal.getText(),boletim.getIdBoletim());
                Main.totalDelitos++;
                Investigado aux = cadastroSuspeitoController.display(delito.getIdDelito());
                if(aux != null) {
                    investigados.add(aux);
                    System.out.println(investigados.get(0).getCidadao().getEndereco());
                }
                delitos.add(delito);
                enderecosDeCrimes.add(endereco);
                limpaTela();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            textoAvisoDelito.setText("Informacoes incompletas ou invalidas");
        }
    }

    public void start(Cidadao cidadao,ArrayList<Telefone> telefones, Endereco enderecoCidadao, Boletim boletim,int opcaoCidadao){
        this.cidadao = cidadao;
        this.telefones = telefones;
        this.enderecoCidadao = enderecoCidadao;
        this.boletim = boletim;
        this.opcaoCidadao = opcaoCidadao;
    }

}
