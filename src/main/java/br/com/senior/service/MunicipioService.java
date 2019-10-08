package br.com.senior.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.lucene.util.SloppyMath;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import br.com.senior.exception.IllegalArgumentException;
import br.com.senior.exception.MunicipioServiceException;
import br.com.senior.exception.ReadFileCsvException;
import br.com.senior.exception.RecursoNaoEncontradoException;
import br.com.senior.exception.UploadFileException;
import br.com.senior.model.Municipio;
import br.com.senior.model.MunicipioData;
import br.com.senior.model.MunicipioId;
import br.com.senior.model.MunicipioResource;
import br.com.senior.repository.MunicipioRepository;
import br.com.senior.service.http.json.CidadesDistantes;
import br.com.senior.service.http.json.TotalRegistro;

@Service
public class MunicipioService {

    private static final String ERROR_LEITURA_CSV = "Não foi possivel realizar a leitura do CSV: ";
    private static final String ERROR_UPLOAD_CSV = "Arquivo enviado não pode ser nulo ou vazio!";
    private static final String MONTE_CABURAI_LONGITUDE = "-60.1999";
    private static final String MONTE_CABURAI_LATITUDE = "5.2666";
    private static final int KM = 1000;

    private MunicipioRepository municipioRepository;

    public MunicipioService(MunicipioRepository municipioRepository) {
	this.municipioRepository = municipioRepository;
    }

    public static List<MunicipioResource> lerArquivoCsv(MultipartFile csvFile) throws IOException {

	MappingIterator<MunicipioResource> personIter = null;

	if (csvFile.isEmpty() || csvFile == null) {
	    throw new UploadFileException(ERROR_UPLOAD_CSV);
	}
	try {
	    personIter = new CsvMapper().readerWithTypedSchemaFor(MunicipioResource.class)
		    .readValues(csvFile.getInputStream());

	    return personIter.readAll();
	} catch (IOException e) {
	    throw new ReadFileCsvException(ERROR_LEITURA_CSV + e.getMessage());
	} finally {
	    personIter.close();
	}

    }

    public void salvarRegistros(MultipartFile csvFile) throws IOException {

	municipioRepository.saveAll(lerArquivoCsv(csvFile).stream()
		.map(municipio -> new Municipio(municipioRepository.newIdentity(), municipio.getCodigoIbge(),
			municipio.getNomeMunicipio(), municipio.getLatitude(), municipio.getLongitude(),
			municipio.getCapital(), municipio.getCodigoUf()))
		.collect(Collectors.toList()));
    }

    public MunicipioId cadastrarNovaCidade(MunicipioData novaCidade) {

	municipioRepository.findByCodigoIbge(novaCidade.getCodigoIbge()).ifPresent(p -> {
	    throw new MunicipioServiceException(
		    "Codigo ibge " + novaCidade.getCodigoIbge() + " já cadastro no sistema.");
	});

	municipioRepository.findByNomeMunicipio(novaCidade.getNomeMunicipio()).ifPresent(p -> {
	    throw new MunicipioServiceException(
		    "Cidade com nome " + novaCidade.getNomeMunicipio() + " já cadastro no sistema.");
	});

	Municipio municipio = new Municipio(municipioRepository.newIdentity(), novaCidade.getCodigoIbge(),
		novaCidade.getNomeMunicipio(), novaCidade.getLatitude(), novaCidade.getLongitude(),
		novaCidade.getCapital(), novaCidade.getCodigoUf());

	municipioRepository.save(municipio);

	return municipio.getMunicipioId();

    }

    public MunicipioData buscarCidadeCadastrada(MunicipioId uuid) {

	Municipio municipio = municipioRepository.findByMunicipioId(uuid)
		.orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada: " + uuid.getId()));

	return new MunicipioData(municipio.getCodigoIbge(), municipio.getNomeMunicipio(), municipio.getLatitude(),
		municipio.getLongitude(), municipio.getCapital(), municipio.getCodigoUf());

    }

    public void removerCidade(String uuid) {

	Optional<Municipio> cidade = municipioRepository.findByMunicipioId(new MunicipioId(uuid));

	if (!cidade.isPresent()) {
	    throw new IllegalArgumentException("UUID do municipio " + uuid + " não encontrado na base de dados.");
	}

	municipioRepository.delete(cidade.get());
    }

    public List<String> listarResultadoDaColunaSelecionado(String coluna, String filtro) {

	return selecionarColuna(coluna, filtro);

    }

    public TotalRegistro retornarQuantidadeTotalRegistro() {

	return new TotalRegistro(municipioRepository.somarTotalRegistro());
    }

    private List<String> selecionarColuna(String coluna, String filtro) {

	switch (coluna) {
	case "codigo_ibge":
	    return municipioRepository.buscarValoresColunaCodigoIbge(filtro);
	case "codigo_uf":
	    return municipioRepository.buscarValoresColunaCodigoUf(filtro);
	case "latitude":
	    return municipioRepository.buscarValoresColunaLatitude(filtro);
	case "longitude":
	    return municipioRepository.buscarValoresColunaLongitude(filtro);
	case "nome_municipio":
	    return municipioRepository.buscarValoresColunaNomeMunicipio(filtro);
	case "capital":
	    return municipioRepository.buscarValoresColunaCapital(filtro);

	default:
	    throw new IllegalArgumentException("Coluna não encontrada: " + coluna);
	}
    }

    private BigDecimal calcularDistanciaEntreDuasCidades(String latitude, String longitude) {

	double distanciaEmMetros = SloppyMath.haversinMeters(Double.valueOf(MONTE_CABURAI_LATITUDE),
		Double.valueOf(MONTE_CABURAI_LONGITUDE), Double.valueOf(latitude), Double.valueOf(longitude));

	return BigDecimal.valueOf(distanciaEmMetros / KM).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public CidadesDistantes descobrirDistanciaEntreCidades() {

	CidadesDistantes cidadesDistantes = new CidadesDistantes(null, null, BigDecimal.ZERO);

	municipioRepository.findAll().forEach(cidade -> {
	    BigDecimal distancia = calcularDistanciaEntreDuasCidades(cidade.getLatitude(), cidade.getLongitude());

	    if (distancia.compareTo(cidadesDistantes.getDistanciaEntreAmbas()) == 1) {

		cidadesDistantes.setCidade("Monte Caburaí");
		cidadesDistantes.setOutraCidade(cidade.getNomeMunicipio());
		cidadesDistantes.setDistanciaEntreAmbas(distancia);

	    }
	});

	return cidadesDistantes;

    }

    public List<MunicipioData> listarTodasCapitaisOrdenadasPorNome() {

	return municipioRepository.findByCapitalOrderByNomeMunicipioAsc("SIM").stream()
		.map(municipio -> new MunicipioData(municipio.getCodigoIbge(), municipio.getNomeMunicipio(),
			municipio.getLatitude(), municipio.getLongitude(), municipio.getCapital(),
			municipio.getCodigoUf()))
		.collect(Collectors.toList());

    }

}
