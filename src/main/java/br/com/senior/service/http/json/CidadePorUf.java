package br.com.senior.service.http.json;

import java.util.List;

public class CidadePorUf {

    private String uf;
    private List<String> cidades;

    public CidadePorUf(String uf, List<String> cidades) {
        this.uf = uf;
        this.cidades = cidades;
    }

    @SuppressWarnings("unused")
    private CidadePorUf() {
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
    }

    @Override
    public String toString() {
        return "CidadePorUf [uf=" + uf + ", cidades=" + cidades + "]";
    }

}
