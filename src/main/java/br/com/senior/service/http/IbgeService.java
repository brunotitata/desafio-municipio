package br.com.senior.service.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.senior.model.Estado;
import br.com.senior.model.Municipio;
import br.com.senior.repository.MunicipioRepository;
import br.com.senior.service.http.json.CidadePorEstado;
import br.com.senior.service.http.json.CidadePorUf;
import br.com.senior.service.http.json.CidadeResource;
import br.com.senior.service.http.json.CidadesPorUf;
import br.com.senior.util.Utils;

@Component
public class IbgeService {

    private static final int PRIMEIRO_ELEMENTO_DA_LISTA = 0;
    private RestTemplate restTemplate;
    private String webServiceIbge;
    private MunicipioRepository municipioRepository;

    public IbgeService(RestTemplate restTemplate, @Value("${api.ibge.municipio.identificacao}") String webServiceIbge,
	    MunicipioRepository municipioRepository) {
	this.restTemplate = restTemplate;
	this.webServiceIbge = webServiceIbge;
	this.municipioRepository = municipioRepository;
    }

    private Map<String, List<Municipio>> cidadesAgrupadasPorUf() {

	Map<String, List<Municipio>> listUfPorCidade = new HashMap<>();

	Estado.listarTodosEstados().forEach(uf -> {

	    List<Municipio> cidadesPorUf = municipioRepository.findByCodigoUf(String.valueOf(uf.getCodigoUf()));
	    listUfPorCidade.put(String.valueOf(uf.getUf()), cidadesPorUf);

	});
	return listUfPorCidade;
    }

    public Map<String, Integer> buscarQuantidadesPorEstado() {

	Map<String, List<Municipio>> cidadesAgrupadasPorUf = cidadesAgrupadasPorUf();
	Map<String, Integer> quantidades = new HashMap<>();

	for (Entry<String, List<Municipio>> cidade : cidadesAgrupadasPorUf.entrySet()) {

	    quantidades.put(cidade.getKey(), cidade.getValue().size());

	}

	return quantidades;
    }

    private LinkedHashMap<String, Integer> ordernarEstados() {

	return buscarQuantidadesPorEstado().entrySet().stream().sorted(Map.Entry.comparingByValue())
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    public List<CidadesPorUf> listarEstadosComMaiorEMenorMunicipios() {

	List<String> estadosOrdenados = ordernarEstados().keySet().stream().collect(Collectors.toList());

	List<String> novoUf = new ArrayList<>();
	novoUf.add(estadosOrdenados.get(PRIMEIRO_ELEMENTO_DA_LISTA));
	novoUf.add(estadosOrdenados.get(estadosOrdenados.size() - 1));

	List<CidadesPorUf> listCidadesPorUf = new ArrayList<>();

	Map<String, Integer> codigosUf = new HashMap<>();

	novoUf.forEach(uf -> {
	    Estado estado = Estado.obterEstado(uf);
	    codigosUf.put(estado.getUf(), estado.getCodigoUf());
	});

	codigosUf.entrySet().forEach(uf -> {

	    List<Municipio> buscarCidadesPorUf = municipioRepository.findByCodigoUf(String.valueOf(uf.getValue()));

	    listCidadesPorUf.add(new CidadesPorUf(uf.getKey(), buscarCidadesPorUf.size(), buscarCidadesPorUf.stream()
		    .map(municipio -> municipio.getNomeMunicipio()).collect(Collectors.toList())));
	});

	return listCidadesPorUf;
    }

    public List<CidadePorEstado> listarQuantidadePorEstado() {

	List<CidadePorEstado> cidadePorEstado = new ArrayList<>();

	List<String> buscarTodosCodigoUf = municipioRepository.buscarTodosCodigoUf();

	for (String codigoUf : buscarTodosCodigoUf) {

	    List<Municipio> cidade = municipioRepository.findByCodigoUf(codigoUf);

	    cidadePorEstado.add(new CidadePorEstado(Estado.uf(Integer.valueOf(codigoUf)), cidade.size()));
	}

	return cidadePorEstado;

    }

    public CidadeResource obterDadosCidadePorIdentificador(String identificador) {

	Utils.assertArgumentNotEmpty(identificador, "Identificador não pode ser nulo ou vazio.");

	return restTemplate.getForObject(webServiceIbge + identificador, CidadeResource.class);

    }

    public CidadePorUf retornarCidadesBaseadoNoEstadoSelecionado(String estado) {

	List<Municipio> cidadesPorUf = municipioRepository
		.findByCodigoUf(String.valueOf(Estado.obterEstado(estado).getCodigoUf()));

	return new CidadePorUf(estado.toUpperCase(),
		cidadesPorUf.stream().map(p -> p.getNomeMunicipio()).collect(Collectors.toList()));
    }

}
