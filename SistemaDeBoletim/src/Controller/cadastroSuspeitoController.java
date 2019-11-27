package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class cadastroSuspeitoController {

    private static  Investigado investigado;
    private int idDelito;

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

    @FXML
    private TextArea textDescricao;

    @FXML
    private Label avisoSuspeito;

    private void dataNascimentoValido(){
        if(textDataNascimento.getText().equals(""))
            textDataNascimento.setText("desconhecido");
    }

    private void cpfValido(){
        if(textCpf.getText().equals(""))
            textCpf.setText("desconhecido");
    }

    private void rgValido(){
        if(textRg.getText().equals(""))
            textRg.setText("desconhecido");
    }

    private void nomeValido(){
        if(textNome.getText().equals(""))
            textNome.setText("desconhecido");
    }

    private void estadoCivilValido(){
        if(textEstadoCivil.getText().equals(""))
            textEstadoCivil.setText("desconhecido");
    }

    private void idadeValido(){
        if(textIdade.getText().equals(""))
            textIdade.setText("desconhecido");
    }

    private void maeValido(){
        if(textMae.getText().equals(""))
            textMae.setText("desconhecido");
    }

    private void paiValido(){
        if(textPai.getText().equals(""))
            textPai.setText("desconhecido");
    }

    private void nacionalidadeValido(){
        if(textNacionalidade.getText().equals(""))
            textNacionalidade.setText("desconhecido");
    }

    private void sexoValido(){
        if(textSexo.getText().equals(""))
            textSexo.setText("desconhecido");
    }

    private void profissaoValido(){
        if(textProfissao.getText().equals(""))
            textProfissao.setText("desconhecido");
    }


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

    private void paisValido(){
        if(textPai.getText().equals(""))
            textPais.setText("desconhecido");
    }

    private void estadoValido(){
        if(textEstado.getText().equals(""))
            textEstado.setText("desconhecido");
    }

    private void cidadeValido(){
        if(textCidade.getText().equals(""))
            textCidade.setText("desconhecido");
    }

    private void cepValido(){
        if(textCep.getText().equals(""))
            textCep.setText("desconhecido");
    }

    private boolean descricaoValida(){
        if(textDescricao.getText().equals(""))
            return false;
        return true;
    }

    @FXML
    void cadastrarSuspeito(ActionEvent event) {
        if(descricaoValida()) {
            avisoSuspeito.setText("");
            cepValido();
            bairroValido();
            cidadeValido();
            complementoValido();
            cpfValido();
            dataNascimentoValido();
            estadoCivilValido();
            estadoValido();
            idadeValido();
            maeValido();
            nacionalidadeValido();
            nomeValido();
            numeroResidenciaValido();
            numeroValido();
            paisValido();
            paiValido();
            profissaoValido();
            rgValido();
            ruaValido();
            sexoValido();
            investigado.setEndereco(new Endereco(Main.totalEndereco+1,textCep.getText(),textCidade.getText(),textEstado.getText(),textPais.getText(),textRua.getText(),textNumero.getText(),
                    textNumeroResidencia.getText(),textComplemento.getText(),textBairro.getText()));
            investigado.setCidadao(new Cidadao(textCpf.getText(),textRg.getText(),textDataNascimento.getText(),textNacionalidade.getText(),textSexo.getText(),textEstadoCivil.getText(),textNome.getText(),
                    textProfissao.getText(),textMae.getText(),textPai.getText(),textIdade.getText(),investigado.getEndereco().getIdEndereco()));
            if(!investigado.getCidadao().getCpf().equals("desconhecido")) { //updates dia 27/11 talvez cause erros
                Cidadao aux= new Cidadao();
                aux.setCpf("valor");
                try{
                    Conexao banco = new Conexao();
                    banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
                    banco.rs = banco.stmt.executeQuery("select * from Cidadao where cpf = '"+investigado.getCidadao().getCpf()+ "'");
                    while(banco.rs.next()) {
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
                    if(aux.getCpf() != null && !aux.getCpf().equals("valor")) {
                        investigado.setCidadao(aux);
                        Endereco ad = new Endereco();
                        ad.setCidade("");
                        banco.rs = banco.stmt.executeQuery("select * from Endereco where idEndereco = "+aux.getEndereco()+"");
                        while(banco.rs.next()){
                            ad.setIdEndereco(banco.rs.getInt("idEndereco"));
                            ad.setCep(banco.rs.getString("cep"));
                            ad.setCidade(banco.rs.getString("cidade"));
                            ad.setEstado(banco.rs.getString("estado"));
                            ad.setPais(banco.rs.getString("pais"));
                            ad.setNumero(banco.rs.getString("numero"));
                            ad.setNumeroResidencia(banco.rs.getString("numeroResidencia"));
                            ad.setBairro(banco.rs.getString("bairro"));
                            ad.setComplemento(banco.rs.getString("complemento"));
                            ad.setRua(banco.rs.getString("rua"));
                        }
                        if(ad.getCidade() != null && !ad.getCidade().equals("")){
                            ArrayList<Telefone> telefones = new ArrayList<>();
                            banco.rs = banco.stmt.executeQuery("select * from Telefone where cidadao = '"+aux.getCpf()+"'");
                            while(banco.rs.next()){
                                Telefone te = new Telefone();
                                te.setCpf(banco.rs.getString("cidadao"));
                                te.setNumTelefone(banco.rs.getString("numTelefone"));
                                telefones.add(te);
                            }
                            investigado.setEndereco(ad);
                            investigado.setTelefones(telefones);
                            investigado.setCadastro(false);
                            investigado.getSuspeito().setCpf(aux.getCpf());
                        }
                    }
                    banco.Desconectar();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                Main.totalCidadao++;
                Main.totalEndereco++;
            }
            investigado.getSuspeito().setCpf(investigado.getCidadao().getCpf());
            investigado.getSuspeito().setDescricao(textDescricao.getText());
            try {
                if(!investigado.getCidadao().getCpf().equals("desconhecido") && investigado.isCadastro()) { // talvez esta alteracao cause um erro
                    investigado.setTelefones(avisoTelefoneController.display());
                    if (investigado.getTelefones() != null) {
                        for (Telefone t : investigado.getTelefones()) {
                            t.setCpf(investigado.getCidadao().getCpf());
                        }
                    }
                }
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            avisoSuspeito.setText("Digite uma descricao valida");
        }
    }

    public static Investigado display(int idDelito){
        investigado = new Investigado();
        investigado.setSuspeito(new Suspeito());
        investigado.getSuspeito().setIdDelito(idDelito);
        investigado.getSuspeito().setIdSuspeito(Main.totalSuspeitos+1);
        Main.totalSuspeitos++;
        try {
            Parent fxmlaviso = FXMLLoader.load(avisoTelefoneController.class.getResource("../View/cadastroSuspeito.fxml"));
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
        return investigado;
    }



}
