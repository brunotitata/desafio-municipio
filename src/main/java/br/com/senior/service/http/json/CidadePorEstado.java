package br.com.senior.service.http.json;

public class CidadePorEstado {

    private String estado;
    private Integer quantidadeMunicipio;

    public CidadePorEstado(String estado, Integer quantidadeMunicipio) {
        this.estado = estado;
        this.quantidadeMunicipio = quantidadeMunicipio;
    }

    @SuppressWarnings("unused")
    private CidadePorEstado() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getQuantidadeMunicipio() {
        return quantidadeMunicipio;
    }

    public void setQuantidadeMunicipio(Integer quantidadeMunicipio) {
        this.quantidadeMunicipio = quantidadeMunicipio;
    }

    @Override
    public String toString() {
        return "CidadePorEstado [estado=" + estado + ", quantidadeMunicipio="
                + quantidadeMunicipio + "]";
    }

}
