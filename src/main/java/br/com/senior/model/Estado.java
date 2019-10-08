package br.com.senior.model;

import java.util.Arrays;
import java.util.List;

public enum Estado {

    RONDONIA("RO", 11), 
    ACRE("AC", 12),
    AMAZONAS("AM", 13),
    RORAIMA("RM", 14),
    PARA("PA", 15),
    AMAPA("AP", 16),
    TOCANTINS("TO", 17),
    MARANHAO("MA", 21),
    PIAUI("PI", 22),
    CEARA("CE", 23),
    RIO_GRANDE_DO_NORTE("RN", 24),
    PARAIBA("PB", 25),
    PERNAMBUCO("PE", 26),
    ALAGOAS("AL", 27),
    SERGIPE("SE", 28),
    BAHIA("BA", 29),
    MINAS_GERAIS("MG", 31),
    ESPIRITO_SANTO("ES", 32),
    RIO_DE_JANEIRO("RJ", 33),
    SAO_PAULO("SP", 35),
    PARANA("PR", 41),
    SANTA_CATARINA("SC", 42),
    RIO_GRANDE_DO_SUL("RS", 43),
    MATO_GROSSO_DO_SUL("MS", 50),
    MATO_GROSSO("MT", 51),
    GOIAS("GO", 52);
    
    private String uf;
    private Integer codigoUf;

    private Estado(String uf, Integer codigoUf) {
        this.uf = uf;
        this.codigoUf = codigoUf;
    }

    public String getUf() {
        return uf;
    }

    public Integer getCodigoUf() {
        return codigoUf;
    }

    public static List<Estado> listarTodosEstados() {
        return Arrays.asList(Estado.values());
    }

    public static String uf(Integer uf) {

        if (uf.equals(53)) {
            return "DF";
        } else {
            return Arrays.asList(Estado.values()).stream()
                    .filter(estado -> estado.getCodigoUf().equals(uf))
                    .map(Estado::getUf).findFirst()
                    .orElseThrow(() -> new RuntimeException("UF não encontrado: " + uf));
        }

    }

    public static Estado obterEstado(String uf) {
        return Arrays.asList(Estado.values()).stream()
                .filter(estado -> estado.getUf().equals(uf)).findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Estado informado não foi encontrado: " + uf));
    }

    public static Estado obterEstadoSigla(Integer uf) {
        return Arrays.asList(Estado.values()).stream()
                .filter(estado -> estado.getCodigoUf().equals(uf)).findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Estado informado não foi encontrado: " + uf));
    }

}
