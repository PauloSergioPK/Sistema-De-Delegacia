package Model;

import java.util.ArrayList;

public class Investigado {
    private Cidadao cidadao;
    private Endereco endereco;
    private ArrayList<Telefone> telefones;
    private Suspeito suspeito;

    public Investigado(){

    }

    public Investigado(Cidadao cidadao, Endereco endereco, ArrayList<Telefone> telefones, Suspeito suspeito) {
        this.cidadao = cidadao;
        this.endereco = endereco;
        this.telefones = telefones;
        this.suspeito = suspeito;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Suspeito getSuspeito() {
        return suspeito;
    }

    public void setSuspeito(Suspeito suspeito) {
        this.suspeito = suspeito;
    }

    @Override
    public String toString() {
        return "Investigado{" +
                "cidadao=" + cidadao +
                ", endereco=" + endereco +
                ", telefones=" + telefones +
                ", suspeito=" + suspeito +
                '}';
    }
}
