package Model;

public class Boletim {
    private int idBoletim ;
    private String dataComunicacao;
    private String dataElaboracao;
    private String relato;
    private String cpfVitima;

    public Boletim(){

    }

    public Boletim(int idBoletim, String dataComunicacao, String dataElaboracao, String relato, String cpfVitima) {
        this.idBoletim = idBoletim;
        this.dataComunicacao = dataComunicacao;
        this.dataElaboracao = dataElaboracao;
        this.relato = relato;
        this.cpfVitima = cpfVitima;
    }

    public int  getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(int  idBoletim) {
        this.idBoletim = idBoletim;
    }

    public String getDataComunicacao() {
        return dataComunicacao;
    }

    public void setDataComunicacao(String dataComunicacao) {
        this.dataComunicacao = dataComunicacao;
    }

    public String getDataElaboracao() {
        return dataElaboracao;
    }

    public void setDataElaboracao(String dataElaboracao) {
        this.dataElaboracao = dataElaboracao;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public String getIdVitima() {
        return cpfVitima;
    }

    public void setCpfVitima(String cpfVitima) {
        this.cpfVitima = cpfVitima;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "idBoletim=" + idBoletim +
                ", dataComunicacao='" + dataComunicacao + '\'' +
                ", dataElaboracao='" + dataElaboracao + '\'' +
                ", relato='" + relato + '\'' +
                ", cpfVitima='" + cpfVitima + '\'' +
                '}';
    }
}
