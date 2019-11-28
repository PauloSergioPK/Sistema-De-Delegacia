package Dao;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Conexao {
    private Connection con = null; //Usada para a conexao com o banco de dados
    public Statement stmt; //Usada para realizar as instrucoes SQL
    public ResultSet rs; //Retorna os dados das tabelas do banco
    public PreparedStatement preparedStatement;
    private String endereco; //Usada para receber o endereco da base de dados
    private String usuario; //Usada para receber o nome do usuario do banco
    private String senha; //Usada para receber a senha do usuario do banco

    public void Conectar(String strEnd, String strUsuario, String strSenha) {
        endereco = strEnd;
        usuario = strUsuario;
        senha = strSenha;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(endereco, usuario, strSenha);
            stmt = con.createStatement();
        }
        catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o driver");
            cnfe.printStackTrace();
        }
        catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "erro na query");
            sqlex.printStackTrace();
        }
    }

    public void Desconectar() {
        try {
            con.close();
        }
        catch (SQLException onConClose) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar o banco");
            onConClose.printStackTrace();
        }
    }

    public void inserir(Cidadao cidadao, Endereco enderecoCidadao, ArrayList<Telefone> telefones, ArrayList<Endereco> enderecosDeCrimes, ArrayList<Investigado> investigados, ArrayList <Delito> delitos, Boletim b,int opcaoCidadao){
        try {
            String cadastrarEndereco;
            String cadastrarCidadao;
            if(opcaoCidadao == 1) {
                cadastrarEndereco = "insert into Endereco" +
                        " values (" + enderecoCidadao.getIdEndereco() + ",'" + enderecoCidadao.getCep() + "','" + enderecoCidadao.getCidade() + "','" + enderecoCidadao.getEstado() + "','" + enderecoCidadao.getPais() + "','" +
                        enderecoCidadao.getRua() + "'," + enderecoCidadao.getNumero() + "," + enderecoCidadao.getNumeroResidencia() + ",'" + enderecoCidadao.getComplemento() + "','" + enderecoCidadao.getBairro() + "')";
                this.stmt.executeUpdate(cadastrarEndereco);
                //System.out.println(cadastrarEndereco);
                cadastrarCidadao = "insert into Cidadao(cpf,rg,dataNascimento,nacionalidade,sexo,estadoCivil,nome,profissao,nomeDaMae,nomeDoPai,idade,endereco)" +
                        " values ('" + cidadao.getCpf() + "','" + cidadao.getRg() + "','" + cidadao.getDataNascimento() + "','" + cidadao.getNacionalidade() + "','" + cidadao.getSexo() + "','" + cidadao.getEstadoCivil() + "','" +
                        cidadao.getNome() + "','" + cidadao.getProfissao() + "','" + cidadao.getNomeDaMae() + "','" + cidadao.getNomeDoPai() + "'," + cidadao.getIdade() + "," + enderecoCidadao.getIdEndereco() + ")";
                //System.out.println(cidadao);
                this.stmt.executeUpdate(cadastrarCidadao);
                for (Telefone t : telefones) {
                    String cadastrarTelefone = "insert into Telefone(numTelefone,cidadao) values ('" + t.getNumTelefone() + "','" + t.getCpf() + "')";
                    //System.out.println(t);
                    this.stmt.executeUpdate(cadastrarTelefone);
                }
            }
            String cadastrarBoletim = "insert into Boletim values ("+b.getIdBoletim()+",'"+b.getDataComunicacao()+"','"+b.getDataElaboracao()+"','"+ b.getRelato()+"','"+ b.getIdVitima()+"')";
            //System.out.println(b);
            this.stmt.executeUpdate(cadastrarBoletim);

            for(Endereco e : enderecosDeCrimes){
                String numero = e.getNumero();
                String numeroResidencia = e.getNumeroResidencia();
                if(numero.equals("desconhecido"))
                    numero = "null";
                if(numeroResidencia.equals("desconhecido"))
                    numeroResidencia = "null";
                cadastrarEndereco = "insert into Endereco" +
                        " values ("+e.getIdEndereco()+",'"+ e.getCep()+"','"+e.getCidade()+"','"+e.getEstado()+"','"+e.getPais()+"','"+
                        e.getRua()+"',"+numero+","+numeroResidencia+",'"+e.getComplemento()+"','"+e.getBairro()+"')";
                //System.out.println(e);
                this.stmt.executeUpdate(cadastrarEndereco);
            }

            for (Delito d : delitos) {
                String cadastrarDelito = "insert into Delito" +
                        " values ("+d.getIdDelito()+",'"+d.getEspecie()+"','"+d.getNatureza()+"',"+d.isFlagrante()+",'"+d.getDataOcorrencia()+"',"+d.getLocalOcorrencia()+",'"+d.getTipoDeLocal()+"',"+d.getIdBoletim()+")";
                //System.out.println(d);
                this.stmt.executeUpdate(cadastrarDelito);
            }

            for (Investigado i : investigados) {
                String cadastro = i.getSuspeito().getCpf();
                if(!cadastro.equals("desconhecido") && i.isCadastro()){ //irei cadastrar como cidadao
                    String numero = i.getEndereco().getNumero();
                    String numeroResidencia = i.getEndereco().getNumeroResidencia();
                    if(numero.equals("desconhecido"))
                        numero = "null";
                    if(numeroResidencia.equals("desconhecido"))
                        numeroResidencia = "null";
                    cadastrarEndereco = "insert into Endereco" +
                            " values ("+i.getEndereco().getIdEndereco()+",'"+ i.getEndereco().getCep()+"','"+i.getEndereco().getCidade()+"','"+i.getEndereco().getEstado()+"','"+i.getEndereco().getPais()+"','"+
                            i.getEndereco().getRua()+"',"+numero+","+numeroResidencia+",'"+i.getEndereco().getComplemento()+"','"+i.getEndereco().getBairro()+"')";
                    //System.out.println(cadastrarEndereco);
                    this.stmt.executeUpdate(cadastrarEndereco);
                    cadastrarCidadao = "insert into Cidadao" +
                            " values ('"+i.getCidadao().getCpf()+"','"+i.getCidadao().getRg()+"','"+i.getCidadao().getDataNascimento()+"','"+i.getCidadao().getNacionalidade()+"','"+i.getCidadao().getSexo()+"','"+i.getCidadao().getEstadoCivil()+"','"+
                            i.getCidadao().getNome()+"','"+i.getCidadao().getProfissao()+"','"+i.getCidadao().getNomeDaMae()+"','"+i.getCidadao().getNomeDoPai()+"',"+i.getCidadao().getIdade()+","+i.getCidadao().getEndereco()+")";
                    //System.out.println(cadastrarCidadao);
                    this.stmt.executeUpdate(cadastrarCidadao);
                    for(Telefone t : i.getTelefones()){
                        String cadastrarTelefone = "insert into Telefone(numTelefone,cidadao) values ('"+t.getNumTelefone()+"','"+t.getCpf()+"')";
                        //System.out.println(cadastrarTelefone);
                        this.stmt.executeUpdate(cadastrarTelefone);
                    }
                }
                else if (cadastro.equals("desconhecido")){
                    cadastro = "null";
                }
                String cadastrarSuspeito;
                if(cadastro.equals("null"))
                    cadastrarSuspeito = "insert into Suspeito values ("+i.getSuspeito().getIdSuspeito()+","+cadastro+",'"+i.getSuspeito().getDescricao()+"',"+i.getSuspeito().getIdDelito()+")";
                else
                    cadastrarSuspeito = "insert into Suspeito values ("+i.getSuspeito().getIdSuspeito()+",'"+cadastro+"','"+i.getSuspeito().getDescricao()+"',"+i.getSuspeito().getIdDelito()+")";
                //System.out.println(cadastrarSuspeito);
                this.stmt.executeUpdate(cadastrarSuspeito);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}
