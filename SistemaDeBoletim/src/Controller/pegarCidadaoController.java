package Controller;

import Dao.Conexao;
import Exec.Main;
import Model.Cidadao;
import Model.Endereco;
import Model.Telefone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class pegarCidadaoController {

    @FXML
    private TextField textNumTelefone;

    @FXML
    private Label textStatus;

    @FXML
    void confirmarCidadao(ActionEvent event) {
        Cidadao aux = new Cidadao();
        aux.setCpf("valor");
        try{
            Conexao banco = new Conexao();
            banco.Conectar("jdbc:postgresql://localhost:5432/Delegacia", "postgres", "123");
            if(!textNumTelefone.getText().equals("")) {
                banco.rs = banco.stmt.executeQuery("select * from Cidadao where cpf = '" + textNumTelefone.getText() + "'");
                while(banco.rs.next()){
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
                if(aux.getCpf() != null && !aux.getCpf().equals("valor")){
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
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../View/cadastroBoletim.fxml"));
                        Parent scene = loader.load();
                        cadastroBoletimController controller = loader.getController();
                        controller.start(aux,telefones,ad,2);
                        Main.changeScreen(new Scene(scene));
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                    }
                }
                else{
                    textStatus.setText("Nao Encontrado!!!");
                }
            }
            banco.Desconectar();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
