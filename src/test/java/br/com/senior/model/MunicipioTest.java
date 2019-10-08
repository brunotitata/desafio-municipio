package br.com.senior.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.senior.exception.IllegalArgumentException;

public class MunicipioTest {

    @Test
    public void criarMunicipio() {

        Municipio municipio = new Municipio(new MunicipioId("UUID-MUNICIPIO"), "123456", "Municipio Teste",
                "-00.0000", "-11.11111", "NAO", "SP");

        assertEquals("UUID-MUNICIPIO", municipio.getMunicipioId().getId());
        assertEquals("123456", municipio.getCodigoIbge());
        assertEquals("Municipio Teste", municipio.getNomeMunicipio());
        assertEquals("-00.0000", municipio.getLatitude());
        assertEquals("-11.11111", municipio.getLongitude());
        assertEquals("NAO", municipio.getCapital());
        assertEquals("SP", municipio.getCodigoUf());
    }
    
    @Test
    public void criarMunicipioComMunicipioIdNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(
        	null, "123456", "Municipio Teste",
                "-00.0000", "-11.11111", "NAO", "SP"))
                        .hasMessage(Municipio.ERR_MUNICIPIO_ID)
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void criarMunicipioComCodigoIbgeNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(
        	new MunicipioId("UUID-MUNICIPIO"), "", "Municipio Teste",
                "-00.0000", "-11.11111", "NAO", "SP"))
                        .hasMessage(Municipio.ERR_CODIGO_IBGE)
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void criarMunicipioComNomeMunicipioNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(
        	new MunicipioId("UUID-MUNICIPIO"), "123456", null, "-00.0000",
                "-11.11111", "NAO", "SP"))
                        .hasMessage(Municipio.ERR_NOME_MUNICIPIO)
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void criarMunicipioComLatitudeNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(new MunicipioId("UUID-MUNICIPIO"), "123456", "Municipio Teste",
                null, "-11.11111", "NAO", "SP"))
                        .hasMessage(Municipio.ERR_LATITUDE)
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void criarMunicipioComLongitudeNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(new MunicipioId("UUID-MUNICIPIO"), "123456", "Municipio Teste",
                "-00.0000", null, "NAO", "SP"))
                        .hasMessage(Municipio.ERR_LONGITUDE)
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void criarMunicipioComCapitalNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(new MunicipioId("UUID-MUNICIPIO"), "123456", "Municipio Teste",
                "-00.0000", "-11.11111", "", "SP"))
                        .hasMessage(Municipio.ERR_CAPITAL)
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void criarMunicipioComCodigoUFNuloOuVazioDeveRetornarException() {
        assertThatThrownBy(() -> new Municipio(new MunicipioId("UUID-MUNICIPIO"), "123456", "Municipio Teste",
                "-00.0000", "-11.11111", "NAO", null))
                        .hasMessage(Municipio.ERR_CODIGO_UF)
                        .isInstanceOf(IllegalArgumentException.class);
    }

}
