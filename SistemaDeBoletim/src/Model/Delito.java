package Model;

public class Delito {
    private int idDelito;
    private String especie;
    private String natureza;
    private boolean flagrante;
    private String dataOcorrencia;
    private int localOcorrencia;
    private String tipoDeLocal;
    private int idBoletim;

    public Delito(){

    }

    public Delito(int idDelito, String especie, String natureza, boolean flagrante, String dataOcorrencia,int localOcorrencia, String tipoDeLocal,int idBoletim) {
        this.idDelito = idDelito;
        this.especie = especie;
        this.natureza = natureza;
        this.flagrante = flagrante;
        this.dataOcorrencia = dataOcorrencia;
        this.localOcorrencia = localOcorrencia;
        this.tipoDeLocal = tipoDeLocal;
        this.idBoletim = idBoletim;
    }

    public int  getIdDelito() {
        return idDelito;
    }

    public void setIdDelito(int idDelito) {
        this.idDelito = idDelito;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public boolean isFlagrante() {
        return flagrante;
    }

    public void setFlagrante(boolean flagrante) {
        this.flagrante = flagrante;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(String dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public int getLocalOcorrencia() {
        return localOcorrencia;
    }

    public void setLocalOcorrencia(int localOcorrencia) {
        this.localOcorrencia = localOcorrencia;
    }

    public String getTipoDeLocal() {
        return tipoDeLocal;
    }

    public void setTipoDeLocal(String tipoDeLocal) {
        this.tipoDeLocal = tipoDeLocal;
    }

    public int getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(int idBoletim) {
        this.idBoletim = idBoletim;
    }

    @Override
    public String toString() {
        return "Delito{" +
                "idDelito=" + idDelito +
                ", especie='" + especie + '\'' +
                ", natureza='" + natureza + '\'' +
                ", flagrante=" + flagrante +
                ", dataOcorrencia='" + dataOcorrencia + '\'' +
                ", localOcorrencia=" + localOcorrencia +
                ", tipoDeLocal='" + tipoDeLocal + '\'' +
                ", idBoletim=" + idBoletim +
                '}';
    }
}
