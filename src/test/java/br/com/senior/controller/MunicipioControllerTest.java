package br.com.senior.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.senior.repository.MunicipioRepository;
import br.com.senior.service.MunicipioService;
import br.com.senior.service.http.IbgeService;
import br.com.senior.service.http.json.CidadePorEstado;
import br.com.senior.service.http.json.CidadePorUf;
import br.com.senior.service.http.json.CidadeResource;
import br.com.senior.service.http.json.Mesorregiao;
import br.com.senior.service.http.json.Microrregiao;
import br.com.senior.service.http.json.Regiao;
import br.com.senior.service.http.json.UF;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipioControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @MockBean
    private MunicipioService municipioService;

    @MockBean
    private MunicipioRepository municipioRepository;

    @MockBean
    private IbgeService ibgeService;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    public void uploadArquivoCsv() throws Exception {

	final MockMultipartFile file;

	try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("municipios.csv")) {
	    file = new MockMultipartFile("file", "municipios.csv", "text/csv", inputStream);
	}

	mockMvc.perform(fileUpload("/api/upload").file(file).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
		.andExpect(status().isCreated());
    }

    @Test
    public void listarQuantidadeDeCidadesPorEstado() throws Exception {

	given(ibgeService.listarQuantidadePorEstado()).willReturn(Arrays.asList(new CidadePorEstado("AC", 22),
		new CidadePorEstado("MA", 217), new CidadePorEstado("RN", 167), new CidadePorEstado("RJ", 92),
		new CidadePorEstado("AM", 62), new CidadePorEstado("TO", 139), new CidadePorEstado("SE", 75),
		new CidadePorEstado("PA", 144), new CidadePorEstado("GO", 246), new CidadePorEstado("SP", 645),
		new CidadePorEstado("CE", 184), new CidadePorEstado("RM", 15), new CidadePorEstado("PB", 223),
		new CidadePorEstado("PI", 224), new CidadePorEstado("PR", 399), new CidadePorEstado("MT", 141),
		new CidadePorEstado("DF", 1), new CidadePorEstado("AL", 102), new CidadePorEstado("RO", 52),
		new CidadePorEstado("ES", 78), new CidadePorEstado("MG", 853), new CidadePorEstado("MS", 79),
		new CidadePorEstado("RS", 497), new CidadePorEstado("AP", 16), new CidadePorEstado("PE", 185),
		new CidadePorEstado("SC", 295), new CidadePorEstado("BA", 417)

	));

	mockMvc.perform(get("/api/quantidade-estado").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.[0].estado", is("AC")))
		.andExpect(jsonPath("$.[0].quantidadeMunicipio", is(22)))

		.andExpect(jsonPath("$.[1].estado", is("MA")))
		.andExpect(jsonPath("$.[1].quantidadeMunicipio", is(217)))

		.andExpect(jsonPath("$.[2].estado", is("RN")))
		.andExpect(jsonPath("$.[2].quantidadeMunicipio", is(167)))

		.andExpect(jsonPath("$.[3].estado", is("RJ")))
		.andExpect(jsonPath("$.[3].quantidadeMunicipio", is(92)))

		.andExpect(jsonPath("$.[4].estado", is("AM")))
		.andExpect(jsonPath("$.[4].quantidadeMunicipio", is(62)))

		.andExpect(jsonPath("$.[5].estado", is("TO")))
		.andExpect(jsonPath("$.[5].quantidadeMunicipio", is(139)))

		.andExpect(jsonPath("$.[6].estado", is("SE")))
		.andExpect(jsonPath("$.[6].quantidadeMunicipio", is(75)))

		.andExpect(jsonPath("$.[7].estado", is("PA")))
		.andExpect(jsonPath("$.[7].quantidadeMunicipio", is(144)))

		.andExpect(jsonPath("$.[8].estado", is("GO")))
		.andExpect(jsonPath("$.[8].quantidadeMunicipio", is(246)))

		.andExpect(jsonPath("$.[9].estado", is("SP")))
		.andExpect(jsonPath("$.[9].quantidadeMunicipio", is(645)))

		.andExpect(jsonPath("$.[10].estado", is("CE")))
		.andExpect(jsonPath("$.[10].quantidadeMunicipio", is(184)))

		.andExpect(jsonPath("$.[11].estado", is("RM")))
		.andExpect(jsonPath("$.[11].quantidadeMunicipio", is(15)))

		.andExpect(jsonPath("$.[12].estado", is("PB")))
		.andExpect(jsonPath("$.[12].quantidadeMunicipio", is(223)))

		.andExpect(jsonPath("$.[13].estado", is("PI")))
		.andExpect(jsonPath("$.[13].quantidadeMunicipio", is(224)))

		.andExpect(jsonPath("$.[14].estado", is("PR")))
		.andExpect(jsonPath("$.[14].quantidadeMunicipio", is(399)))

		.andExpect(jsonPath("$.[15].estado", is("MT")))
		.andExpect(jsonPath("$.[15].quantidadeMunicipio", is(141)))

		.andExpect(jsonPath("$.[16].estado", is("DF"))).andExpect(jsonPath("$.[16].quantidadeMunicipio", is(1)))

		.andExpect(jsonPath("$.[17].estado", is("AL")))
		.andExpect(jsonPath("$.[17].quantidadeMunicipio", is(102)))

		.andExpect(jsonPath("$.[18].estado", is("RO")))
		.andExpect(jsonPath("$.[18].quantidadeMunicipio", is(52)))

		.andExpect(jsonPath("$.[19].estado", is("ES")))
		.andExpect(jsonPath("$.[19].quantidadeMunicipio", is(78)))

		.andExpect(jsonPath("$.[20].estado", is("MG")))
		.andExpect(jsonPath("$.[20].quantidadeMunicipio", is(853)))

		.andExpect(jsonPath("$.[21].estado", is("MS")))
		.andExpect(jsonPath("$.[21].quantidadeMunicipio", is(79)))

		.andExpect(jsonPath("$.[22].estado", is("RS")))
		.andExpect(jsonPath("$.[22].quantidadeMunicipio", is(497)))

		.andExpect(jsonPath("$.[23].estado", is("AP")))
		.andExpect(jsonPath("$.[23].quantidadeMunicipio", is(16)))

		.andExpect(jsonPath("$.[24].estado", is("PE")))
		.andExpect(jsonPath("$.[24].quantidadeMunicipio", is(185)))

		.andExpect(jsonPath("$.[25].estado", is("SC")))
		.andExpect(jsonPath("$.[25].quantidadeMunicipio", is(295)))

		.andExpect(jsonPath("$.[26].estado", is("BA")))
		.andExpect(jsonPath("$.[26].quantidadeMunicipio", is(417)))

		.andExpect(status().isOk());
    }

    @Test
    public void obterDadosCidadePorIdentificacao() throws Exception {

	given(ibgeService.obterDadosCidadePorIdentificador("3500105")).willReturn(new CidadeResource("3500105",
		new Microrregiao("35035",
			new Mesorregiao(new UF("35", "São Paulo", new Regiao("3", "Sudeste", "SE"), "SP"), "35",
				"São Paulo"),
			"Adamantina"),
		"Adamantina"));

	mockMvc.perform(get("/api/info/cidade?identificador=3500105").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is("3500105"))).andExpect(jsonPath("$.microrregiao.id", is("35035")))
		.andExpect(jsonPath("$.microrregiao.nome", is("Adamantina")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.id", is("35")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.nome", is("São Paulo")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.UF.id", is("35")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.UF.sigla", is("SP")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.UF.nome", is("São Paulo")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.UF.regiao.id", is("3")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.UF.regiao.sigla", is("SE")))
		.andExpect(jsonPath("$.microrregiao.mesorregiao.UF.regiao.nome", is("Sudeste")))
		.andExpect(status().isOk());
    }

    @Test
    public void retornarCidadesBaseadoNoEstadoSelecionado() throws Exception {

	given(ibgeService.retornarCidadesBaseadoNoEstadoSelecionado("SP"))
		.willReturn(new CidadePorUf("SP", Arrays.asList("Ribeirão Preto", "São Paulo", "Campinas")));

	mockMvc.perform(get("/api/cidades/estado?uf=SP").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.uf", is("SP")))
		.andExpect(jsonPath("$.cidades", is(Arrays.asList("Ribeirão Preto", "São Paulo", "Campinas"))))
		.andExpect(status().isOk());

    }

}
