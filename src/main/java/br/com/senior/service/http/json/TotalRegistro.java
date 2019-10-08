package br.com.senior.service.http.json;

public class TotalRegistro {

    private Integer quantidadeRegistrosTotais;

    public TotalRegistro(Integer quantidadeRegistrosTotais) {
        this.quantidadeRegistrosTotais = quantidadeRegistrosTotais;
    }

    public Integer getQuantidadeRegistrosTotais() {
        return quantidadeRegistrosTotais;
    }

    public void setQuantidadeRegistrosTotais(
            Integer quantidadeRegistrosTotais) {
        this.quantidadeRegistrosTotais = quantidadeRegistrosTotais;
    }

    @Override
    public String toString() {
        return "TotalRegistro [quantidadeRegistrosTotais="
                + quantidadeRegistrosTotais + "]";
    }

}
