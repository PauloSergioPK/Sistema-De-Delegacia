package Controller;

import Dao.Conexao;
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
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class updateBoletimController {

    @FXML
    private TextArea relato;
    private Boletim boletim;
    private Cidadao cidadao;
    private Endereco endereco;
    private ArrayList<Telefone> telefones = new ArrayList<>();

    @FXML
    void adiconarDelito(ActionEvent event) {
        try {
            if(!relato.getText().equals("")){
                boletim.setRelato(relato.getText());
            }
            Cidadao cidadao = new Cidadao();
            ArrayList<Telefone> telefones = new ArrayList<>();
            Endereco enderecoCidadao = new Endereco();
            carregaCidadao();
            carregaEndereco();
            carregaTelefones();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/cadastroDelito.fxml"));
            Parent fxmlDelito = loader.load();
            cadastroDelitoController controller = loader.getController();
            controller.start(cidadao, telefones, enderecoCidadao, boletim, 2,2);
            Main.changeScreen(new Scene(fxmlDelito));
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        try {
            if(!relato.getText().equals("")){
                boletim.setRelato(relato.getText());
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/removerDelito.fxml"));
            Parent scene = loader.load();
            removerDelitoController controller = loader.getController();
            controller.start(boletim);
            Main.changeScreen(new Scene(scene));
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    private void carregaCidadao() {
        Cidadao aux = new Cidadao();
        try {
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            banco.rs = banco.stmt.executeQuery("select * from Cidadao where cpf = '"+boletim.getIdVitima()+"'");
            while (banco.rs.next()) {
                aux.setCpf(banco.rs.getString("cpf"));
                aux.setDataNascimento(banco.rs.getString("dataNascimento"));
                aux.setRg(banco.rs.getString("rg"));
                aux.setNacionalidade(banco.rs.getString("nacionalidade"));
                aux.setSexo(banco.rs.getString("sexo"));
                aux.setEstadoCivil(banco.rs.getString("estadoCivil"));
                aux.setNome(banco.rs.getString("nome"));
                aux.setProfissao(banco.rs.getString("profissao"));
                aux.setNomeDaMae(banco.rs.getString("nomeDaMae"));
                aux.setNomeDoPai(banco.rs.getString("nomeDoPai"));
                aux.setIdade(banco.rs.getString("idade"));
                aux.setEndereco(banco.rs.getInt("endereco"));
            }
            banco.Desconectar();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        cidadao = aux;

    }

    private void carregaEndereco() {
        Endereco aux = new Endereco();
        try {
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            banco.rs = banco.stmt.executeQuery("select * from Endereco where idEndereco = "+cidadao.getEndereco()+"");
            while(banco.rs.next()){
                aux.setIdEndereco(banco.rs.getInt("idEndereco"));
                aux.setCep(banco.rs.getString("cep"));
                aux.setCidade(banco.rs.getString("cidade"));
                aux.setEstado(banco.rs.getString("estado"));
                aux.setPais(banco.rs.getString("pais"));
                aux.setNumero(banco.rs.getString("numero"));
                aux.setNumeroResidencia(banco.rs.getString("numeroResidencia"));
                aux.setBairro(banco.rs.getString("bairro"));
                aux.setComplemento(banco.rs.getString("complemento"));
                aux.setRua(banco.rs.getString("rua"));
            }
            banco.Desconectar();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        endereco = aux;
    }

    private void carregaTelefones() {
        try {
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            banco.rs = banco.stmt.executeQuery("select * from Telefone where cidadao = '"+boletim.getIdVitima()+"'");
            while(banco.rs.next()){
                Telefone aux = new Telefone();
                aux.setCpf(banco.rs.getString("cidadao"));
                aux.setNumTelefone(banco.rs.getString("numTelefone"));
                telefones.add(aux);
                System.out.println(aux);
            }
            banco.Desconectar();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
