package br.com.senior.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.senior.model.Municipio;
import br.com.senior.model.MunicipioId;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    default MunicipioId newIdentity() {
	return new MunicipioId(UUID.randomUUID().toString().toUpperCase());
    }

    @Transactional(readOnly = true)
    Optional<Municipio> findByMunicipioId(MunicipioId id);

    @Transactional(readOnly = true)
    List<Municipio> findByCapitalOrderByNomeMunicipioAsc(String obj);

    @Transactional(readOnly = true)
    Optional<Municipio> findByCodigoIbge(String codigoIbge);

    @Transactional(readOnly = true)
    Optional<Municipio> findByNomeMunicipio(String nome);

    @Transactional(readOnly = true)
    @Query(value = "SELECT codigo_ibge FROM MUNICIPIO where codigo_ibge like %?1%", nativeQuery = true)
    List<String> buscarValoresColunaCodigoIbge(String filtro);

    @Transactional(readOnly = true)
    @Query(value = "select nome_municipio from municipio where nome_municipio like %?1%", nativeQuery = true)
    List<String> buscarValoresColunaNomeMunicipio(String filtro);

    @Transactional(readOnly = true)
    @Query(value = "SELECT latitude FROM MUNICIPIO where latitude like %?1%", nativeQuery = true)
    List<String> buscarValoresColunaLatitude(String filtro);

    @Transactional(readOnly = true)
    @Query(value = "SELECT longitude FROM MUNICIPIO where longitude like %?1%", nativeQuery = true)
    List<String> buscarValoresColunaLongitude(String filtro);

    @Transactional(readOnly = true)
    @Query(value = "SELECT codigo_uf FROM MUNICIPIO where codigo_uf like %?1%", nativeQuery = true)
    List<String> buscarValoresColunaCodigoUf(String filtro);

    @Transactional(readOnly = true)
    @Query(value = "SELECT capital FROM MUNICIPIO where capital like %?1%", nativeQuery = true)
    List<String> buscarValoresColunaCapital(String filtro);

    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT codigo_uf FROM MUNICIPIO", nativeQuery = true)
    List<String> buscarTodosCodigoUf();

    List<Municipio> findByCodigoUf(String uf);

    @Transactional(readOnly = true)
    @Query(value = "SELECT count (*) FROM MUNICIPIO", nativeQuery = true)
    Integer somarTotalRegistro();

}
