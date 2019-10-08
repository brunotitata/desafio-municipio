package br.com.senior.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.model.Municipio;
import br.com.senior.model.MunicipioId;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:municipio_201910071408.sql")
public class MunicipioRepositoryTest {

    @After
    public void tearDown() {
	municipioRepository.deleteAll();
    }

    @Autowired
    private MunicipioRepository municipioRepository;

    @Test
    public void findByCapitalOrderByNomeMunicipioAsc() {

	List<Municipio> capitais = municipioRepository.findByCapitalOrderByNomeMunicipioAsc("SIM");

	assertEquals(false, capitais.isEmpty());
	assertEquals(27, capitais.size());

    }
    
    @Test
    public void findByMunicipioId() {

	Municipio municipio = municipioRepository.findByMunicipioId(new MunicipioId("B86ED7E9-9044-4C2D-9FDC-B401B55E1E2B")).get();

	assertEquals("5200050", municipio.getCodigoIbge());
	assertEquals("Abadia de Goiás", municipio.getNomeMunicipio());
	assertEquals("-16.7573", municipio.getLatitude());
	assertEquals("-49.4412", municipio.getLongitude());
	assertEquals("NAO", municipio.getCapital());
	assertEquals("52", municipio.getCodigoUf());
    }

    @Test
    public void findByCodigoIbge() {

	Municipio municipio = municipioRepository.findByCodigoIbge("3543402").get();

	assertEquals("3543402", municipio.getCodigoIbge());
	assertEquals("Ribeirão Preto", municipio.getNomeMunicipio());
	assertEquals("-21.1699", municipio.getLatitude());
	assertEquals("-47.8099", municipio.getLongitude());
	assertEquals("NAO", municipio.getCapital());
	assertEquals("35", municipio.getCodigoUf());
    }

    @Test
    public void findByNomeMunicipio() {

	Municipio municipio = municipioRepository.findByNomeMunicipio("Ribeirão Preto").get();

	assertEquals("3543402", municipio.getCodigoIbge());
	assertEquals("Ribeirão Preto", municipio.getNomeMunicipio());
	assertEquals("-21.1699", municipio.getLatitude());
	assertEquals("-47.8099", municipio.getLongitude());
	assertEquals("NAO", municipio.getCapital());
	assertEquals("35", municipio.getCodigoUf());
    }

    @Test
    public void buscarValoresColunaCodigoIbge() {

	List<String> codigosIbge = municipioRepository.buscarValoresColunaCodigoIbge("0355");

	assertEquals("2900355", codigosIbge.get(0));
	assertEquals("5203559", codigosIbge.get(1));
	assertEquals("2503555", codigosIbge.get(2));
	assertEquals("4303558", codigosIbge.get(3));
	assertEquals("2103554", codigosIbge.get(4));
    }

    @Test
    public void buscarValoresColunaNomeMunicipio() {

	List<String> nomesMunicipio = municipioRepository.buscarValoresColunaNomeMunicipio("Corren");

	assertEquals("Corrente", nomesMunicipio.get(0));
	assertEquals("Correntes", nomesMunicipio.get(1));
	assertEquals("Correntina", nomesMunicipio.get(2));
	assertEquals("Ribeirão Corrente", nomesMunicipio.get(3));
    }

    @Test
    public void buscarValoresColunaLatitude() {

	List<String> latitude = municipioRepository.buscarValoresColunaLatitude("240");

	assertEquals("-3.92403", latitude.get(0));
	assertEquals("-12.2408", latitude.get(1));
	assertEquals("-2.24078", latitude.get(2));
	assertEquals("-20.2401", latitude.get(3));
    }

    @Test
    public void buscarValoresColunaLongitude() {

	List<String> longitude = municipioRepository.buscarValoresColunaLongitude("2417");

	assertEquals("-41.2417", longitude.get(0));
	assertEquals("-39.2417", longitude.get(1));
	assertEquals("-35.2417", longitude.get(2));
    }

    @Test
    public void buscarValoresColunaCodigoUf() {

	List<String> codigosUf = municipioRepository.buscarValoresColunaCodigoUf("52");

	assertEquals(246, codigosUf.size());

    }

    @Test
    public void buscarValoresColunaCapital() {

	List<String> capitais = municipioRepository.buscarValoresColunaCapital("SIM");

	assertEquals(27, capitais.size());

    }

    @Test
    public void buscarTodosCodigoUf() {

	List<String> codigosUf = municipioRepository.buscarTodosCodigoUf();

	assertEquals(27, codigosUf.size());

    }

    @Test
    public void somarTotalRegistro() {

	Integer valorTodosOsRegistros = municipioRepository.somarTotalRegistro();

	assertEquals(Integer.valueOf(5570), valorTodosOsRegistros);

    }

}
