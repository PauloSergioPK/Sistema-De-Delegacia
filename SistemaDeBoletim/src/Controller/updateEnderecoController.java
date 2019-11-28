package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Boletim;
import Model.Cidadao;
import Model.Endereco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class updateEnderecoController {

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

    private boolean estadoValido(){
        if(!textEstado.getText().equals(""))
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

    private boolean ruaValido(){
        if(!textRua.getText().equals(""))
            return true;
        else
            return false;
    }

    @FXML
    private Label textStatus;


    @FXML
    void voltarUpdate(ActionEvent event) {
        if(valida()){
            aux.setCidade(textCidade.getText());
            aux.setRua(textRua.getText());
            aux.setComplemento(textComplemento.getText());
            aux.setBairro(textBairro.getText());
            aux.setNumeroResidencia(textNumeroResidencia.getText());
            aux.setNumero(textNumero.getText());
            aux.setPais(textPais.getText());
            aux.setEstado(textEstado.getText());
            aux.setCep(textCep.getText());
            try{
                Conexao banco = new Conexao();
                banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                banco.stmt.execute("update Endereco set cidade = '"+aux.getCidade()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set rua = '"+aux.getRua()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set complemento = '"+aux.getComplemento()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set bairro = '"+aux.getBairro()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set numeroResidencia = "+aux.getNumeroResidencia()+" where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set numero = "+aux.getNumero()+" where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set pais = '"+aux.getPais()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set estado = '"+aux.getEstado()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.stmt.execute("update Endereco set cep = '"+aux.getCep()+"' where idEndereco = "+aux.getIdEndereco()+"");
                banco.Desconectar();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/alterarEndereco.fxml"));
                Parent fxmls = loader.load();
                alterarEnderecoController controller = loader.getController();
                controller.start(boletim,cidadao);
                Main.changeScreen(new Scene(fxmls));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            this.textStatus.setText("Informacoes invalidas ou incompletas!!");
    }

    private boolean valida(){
        if(complementoValido() && ruaValido() && paisValido() && residenciaValido() && numeroValido() && bairroValido() && cepValido() && cidadeValido() &&  estadoValido())
            return true;
        return false;
    }

    private Boletim boletim;
    private Cidadao cidadao;
    private Endereco aux;

    public void start(Boletim boletim, Cidadao cidadao, Endereco endereco){
        this.textBairro.setText(endereco.getBairro());
        this.textCep.setText(endereco.getCep());
        this.textCidade.setText(endereco.getCidade());
        this.textComplemento.setText(endereco.getComplemento());
        this.textEstado.setText(endereco.getEstado());
        this.textNumero.setText(endereco.getNumero());
        this.textNumeroResidencia.setText(endereco.getNumeroResidencia());
        this.textPais.setText(endereco.getPais());
        this.textRua.setText(endereco.getRua());
        this.cidadao = cidadao;
        this.boletim = boletim;
        this.aux = endereco;
    }


}
