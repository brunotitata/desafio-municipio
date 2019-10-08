package br.com.senior.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senior.model.MunicipioData;
import br.com.senior.model.MunicipioId;
import br.com.senior.service.MunicipioService;
import br.com.senior.service.http.IbgeService;
import br.com.senior.service.http.json.CidadePorEstado;
import br.com.senior.service.http.json.CidadePorUf;
import br.com.senior.service.http.json.CidadeResource;
import br.com.senior.service.http.json.CidadesDistantes;
import br.com.senior.service.http.json.CidadesPorUf;
import br.com.senior.service.http.json.TotalRegistro;

@RestController
@RequestMapping("/api")
public class MunicipioController {

    private MunicipioService municipioService;
    private IbgeService ibgeService;

    public MunicipioController(MunicipioService municipioService, IbgeService ibgeService) {
	this.municipioService = municipioService;
	this.ibgeService = ibgeService;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadArquivoCsv(@RequestParam("file") MultipartFile file) {

	try {
	    municipioService.salvarRegistros(file);
	} catch (IOException e) {
	    return ResponseEntity.badRequest().body("Falha ao realizar upload do arquivo: " + e.getMessage());
	}

	return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/estado/maior-e-menor")
    public ResponseEntity<List<CidadesPorUf>> listarEstadosComMaiorEMenorMunicipios() {

	return ResponseEntity.ok(ibgeService.listarEstadosComMaiorEMenorMunicipios());
    }

    @GetMapping("/quantidade-estado")
    public ResponseEntity<List<CidadePorEstado>> listarQuantidadeDeCidadesPorEstado() {

	return ResponseEntity.ok(ibgeService.listarQuantidadePorEstado());
    }

    @GetMapping("/info/cidade")
    public ResponseEntity<CidadeResource> obterDadosCidadePorIdentificacao(
	    @RequestParam(value = "identificador", required = true) String identificador) {

	return ResponseEntity.ok(ibgeService.obterDadosCidadePorIdentificador(identificador));
    }

    @GetMapping("/cidades/estado")
    public ResponseEntity<CidadePorUf> retornarCidadesBaseadoNoEstadoSelecionado(
	    @RequestParam(value = "uf", required = true) String uf) {

	return ResponseEntity.ok(ibgeService.retornarCidadesBaseadoNoEstadoSelecionado(uf));
    }

    @PostMapping("/cidade")
    public ResponseEntity<MunicipioId> cadastrarCidade(@RequestBody MunicipioData municipio) {

	MunicipioId municipioId = municipioService.cadastrarNovaCidade(municipio);

	return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(municipioId.getId()).toUri()).build();
    }

    // TODO endpoint para buscar cidade cadastrada (não possui listado no teste)
    @GetMapping("/cidade/{municipioId}")
    public ResponseEntity<MunicipioData> buscarCidadeCadastrada(@PathVariable MunicipioId municipioId) {

	return ResponseEntity.ok().body(municipioService.buscarCidadeCadastrada(municipioId));
    }

    @DeleteMapping("/excluir/cidade/{identificador}")
    public ResponseEntity<Void> deletarCidade(@PathVariable("identificador") String codigoIbge) {

	municipioService.removerCidade(codigoIbge);

	return ResponseEntity.noContent().build();

    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<String>> filtrarColunas(@RequestParam(value = "coluna", required = true) String coluna,
	    @RequestParam(value = "filtrar", required = true) String filtrar) {

	return ResponseEntity.ok().body(municipioService.listarResultadoDaColunaSelecionado(coluna, filtrar));

    }

    @GetMapping("/total-registros")
    public ResponseEntity<TotalRegistro> retornarQuantidadeTotalRegistro() {

	return ResponseEntity.ok().body(municipioService.retornarQuantidadeTotalRegistro());
    }

    @GetMapping("/distancia")
    public ResponseEntity<CidadesDistantes> descobrirDistanciaEntreCidades() {

	return ResponseEntity.ok().body(municipioService.descobrirDistanciaEntreCidades());
    }

}
