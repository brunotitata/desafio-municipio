package br.com.senior.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.exception.UploadFileException;
import br.com.senior.model.Municipio;
import br.com.senior.model.MunicipioData;
import br.com.senior.model.MunicipioId;
import br.com.senior.repository.MunicipioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipioServiceTest {

    private static final String MUNICIPIO_UUID = "86D039AD-7D77-4A96-9EF9-C6040BE32F28";

    private MunicipioService municipioService;

    @Mock
    private MunicipioRepository municipioRepository;

    @Before
    public void setUp() {
	this.municipioService = new MunicipioService(municipioRepository);
    }

    @Test
    public void lerArquivoCsv() throws IOException {

	final MockMultipartFile file;

	try (InputStream inputStream = this.getClass().getClassLoader()
		.getResourceAsStream("municipios-vazio-teste.csv")) {
	    file = new MockMultipartFile("file", "municipios-vazio-teste.csv", "text/csv", inputStream);
	}

	assertThatThrownBy(() -> MunicipioService.lerArquivoCsv(file))
		.hasMessage("Arquivo enviado não pode ser nulo ou vazio!").isInstanceOf(UploadFileException.class);

    }

    @Test
    public void salvarRegistros() {
	
	given(municipioRepository.newIdentity()).willReturn(new MunicipioId(MUNICIPIO_UUID));

	final MunicipioId cadastrarNovaCidade = municipioService.cadastrarNovaCidade(
		new MunicipioData("123456", "Municipio Teste", "-00.0000", "-11.11111", "NAO", "SP"));

	ArgumentCaptor<Municipio> argumentCaptor = ArgumentCaptor.forClass(Municipio.class);

	verify(municipioRepository).save(argumentCaptor.capture());

	Municipio municipio = argumentCaptor.getValue();

	assertEquals(cadastrarNovaCidade.getId(), municipio.getMunicipioId().getId());
	assertEquals("123456", municipio.getCodigoIbge());
	assertEquals("Municipio Teste", municipio.getNomeMunicipio());
	assertEquals("-00.0000", municipio.getLatitude());
	assertEquals("-11.11111", municipio.getLongitude());
	assertEquals("NAO", municipio.getCapital());
	assertEquals("SP", municipio.getCodigoUf());

    }

}
