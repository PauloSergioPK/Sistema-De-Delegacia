package Model;

public class Cidadao {
    private String cpf;
    private String rg;
    private String dataNascimento;
    private String nacionalidade;
    private String sexo;
    private String estadoCivil;
    private String nome;
    private String profissao;
    private String nomeDaMae;
    private String nomeDoPai;
    private String  idade ;
    private int Endereco ;

    public Cidadao(){

    }
    public Cidadao(String cpf, String rg, String dataNascimento, String nacionalidade,
                   String sexo, String estadoCivil, String nome, String profissao, String nomeDaMae,
                   String nomeDoPai, String idade, int  endereco) {
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.nome = nome;
        this.profissao = profissao;
        this.nomeDaMae = nomeDaMae;
        this.nomeDoPai = nomeDoPai;
        this.idade = idade;
        Endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public void setNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public int getEndereco() {
        return Endereco;
    }

    public void setEndereco(int endereco) {
        Endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cidadao{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", nome='" + nome + '\'' +
                ", profissao='" + profissao + '\'' +
                ", nomeDaMae='" + nomeDaMae + '\'' +
                ", nomeDoPai='" + nomeDoPai + '\'' +
                ", idade='" + idade + '\'' +
                ", Endereco=" + Endereco +
                '}';
    }
}
