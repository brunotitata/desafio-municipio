package br.com.senior.service.http.json;

import java.math.BigDecimal;

public class CidadesDistantes {

    private String cidade;
    private String outraCidade;
    private BigDecimal distanciaEntreAmbas;

    public CidadesDistantes(String cidade, String outraCidade,
            BigDecimal distanciaEntreAmbas) {
        this.cidade = cidade;
        this.outraCidade = outraCidade;
        this.distanciaEntreAmbas = distanciaEntreAmbas;
    }

    @SuppressWarnings("unused")
    private CidadesDistantes() {
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getOutraCidade() {
        return outraCidade;
    }

    public void setOutraCidade(String outraCidade) {
        this.outraCidade = outraCidade;
    }

    public BigDecimal getDistanciaEntreAmbas() {
        return distanciaEntreAmbas;
    }

    public void setDistanciaEntreAmbas(BigDecimal distanciaEntreAmbas) {
        this.distanciaEntreAmbas = distanciaEntreAmbas;
    }

    @Override
    public String toString() {
        return "CidadesDistantes [cidade=" + cidade + ", outraCidade="
                + outraCidade + ", distanciaEntreAmbas=" + distanciaEntreAmbas
                + "]";
    }

}
