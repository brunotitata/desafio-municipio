package br.com.senior.service.http.json;

import java.util.List;

public class CidadesPorUf {

    private String uf;
    private Integer quantidadeMunicipios;
    private List<String> municipios;

    public CidadesPorUf(String uf, Integer quantidadeMunicipios,
            List<String> municipios) {
        this.uf = uf;
        this.quantidadeMunicipios = quantidadeMunicipios;
        this.municipios = municipios;
    }

    @SuppressWarnings("unused")
    private CidadesPorUf() {
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getQuantidadeMunicipios() {
        return quantidadeMunicipios;
    }

    public void setQuantidadeMunicipios(Integer quantidadeMunicipios) {
        this.quantidadeMunicipios = quantidadeMunicipios;
    }

    public List<String> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<String> municipios) {
        this.municipios = municipios;
    }

    @Override
    public String toString() {
        return "CidadesPorUf [uf=" + uf + ", quantidadeMunicipios="
                + quantidadeMunicipios + ", municipios=" + municipios + "]";
    }

}
