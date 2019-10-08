package br.com.senior.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.senior.repository.MunicipioRepository;
import br.com.senior.service.http.IbgeService;
import br.com.senior.service.http.json.CidadeResource;
import br.com.senior.service.http.json.Mesorregiao;
import br.com.senior.service.http.json.Microrregiao;
import br.com.senior.service.http.json.Regiao;
import br.com.senior.service.http.json.UF;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IbgeServiceTest {

    private IbgeService ibgeService;

    @Mock
    private MunicipioRepository municipioRepository;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
	this.ibgeService = new IbgeService(restTemplate,
		"http://servicodados.ibge.gov.br/api/v1/localidades/municipios/", municipioRepository);
    }

    @Test
    public void obterDadosCidadePorIdentificador() {

	given(ibgeService
		.obterDadosCidadePorIdentificador("3500105"))
			.willReturn(
				(new CidadeResource("3500105",
					new Microrregiao("35035",
						new Mesorregiao(new UF("35", "São Paulo",
							new Regiao("3", "Sudeste", "SE"), "SP"), "35", "São Paulo"),
						"Adamantina"),
					"Adamantina")));

	CidadeResource cidade = ibgeService.obterDadosCidadePorIdentificador("3500105");

	assertEquals("3500105", cidade.getId());
	assertEquals("Adamantina", cidade.getNome());
	assertEquals("35035", cidade.getMicrorregiao().getId());
	assertEquals("Adamantina", cidade.getMicrorregiao().getNome());
	assertEquals("35", cidade.getMicrorregiao().getMesorregiao().getId());
	assertEquals("São Paulo", cidade.getMicrorregiao().getMesorregiao().getNome());
	assertEquals("35", cidade.getMicrorregiao().getMesorregiao().getUF().getId());
	assertEquals("São Paulo", cidade.getMicrorregiao().getMesorregiao().getUF().getNome());
	assertEquals("SP", cidade.getMicrorregiao().getMesorregiao().getUF().getSigla());
	assertEquals("3", cidade.getMicrorregiao().getMesorregiao().getUF().getRegiao().getId());
	assertEquals("Sudeste", cidade.getMicrorregiao().getMesorregiao().getUF().getRegiao().getNome());
	assertEquals("SE", cidade.getMicrorregiao().getMesorregiao().getUF().getRegiao().getSigla());

    }

}
