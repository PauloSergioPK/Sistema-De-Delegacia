package Model;

public class Suspeito {
    private String cpf;
    private String descricao;
    private int idDelito;
    private int idSuspeito;

    public Suspeito(){

    }

    public Suspeito(String cpf, String descricao, int idDelito) {
        this.cpf = cpf;
        this.descricao = descricao;
        this.idDelito = idDelito;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdDelito() {
        return idDelito;
    }

    public void setIdDelito(int idDelito) {
        this.idDelito = idDelito;
    }

    @Override
    public String toString() {
        return "Suspeito{" +
                "cpf='" + cpf + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idDelito=" + idDelito +
                '}';
    }

    public int getIdSuspeito() {
        return idSuspeito;
    }

    public void setIdSuspeito(int idSuspeito) {
        this.idSuspeito = idSuspeito;
    }
}
