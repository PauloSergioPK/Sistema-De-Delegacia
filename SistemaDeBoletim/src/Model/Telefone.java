package Model;

public class Telefone {
    private String numTelefone;
    private String cpf;

    public Telefone(){

    }

    public Telefone(String numTelefone, String cpf) {
        this.numTelefone = numTelefone;
        this.cpf = cpf;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "numTelefone='" + numTelefone + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
