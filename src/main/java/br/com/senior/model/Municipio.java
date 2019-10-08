package br.com.senior.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.senior.util.Utils;

@Entity
@Table(name = "MUNICIPIO")
public class Municipio implements Serializable {
    private static final long serialVersionUID = 8212318699445912765L;

    public static final String ERR_MUNICIPIO_ID = "Municipio ID não pode ser vazio ou nulo.";
    public static final String ERR_CODIGO_IBGE = "Codigo IBGE não pode ser vazio ou nulo.";
    public static final String ERR_NOME_MUNICIPIO = "Nome do Municipio não pode ser vazio ou nulo.";
    public static final String ERR_LATITUDE = "Latitude não pode ser vazio ou nulo.";
    public static final String ERR_LONGITUDE = "Longitude não pode ser vazio ou nulo.";
    public static final String ERR_CAPITAL = "Capital não pode ser vazio ou nulo.";
    public static final String ERR_CODIGO_UF = "UF não pode ser vazio ou nulo.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Embedded
    private MunicipioId municipioId;
    private String codigoIbge;
    private String nomeMunicipio;
    private String latitude;
    private String longitude;
    private String capital;
    private String codigoUf;

    public Municipio(MunicipioId municipioId, String codigoIbge, String nomeMunicipio, String latitude,
	    String longitude, String capital, String codigoUf) {
	setMunicipioId(municipioId);
	setCodigoIbge(codigoIbge);
	setNomeMunicipio(nomeMunicipio);
	setLatitude(latitude);
	setLongitude(longitude);
	setCapital(capital);
	setCodigoUf(codigoUf);
    }

    @SuppressWarnings("unused")
    private Municipio() {
    }

    public MunicipioId getMunicipioId() {
	return municipioId;
    }

    public void setMunicipioId(MunicipioId municipioId) {
	Utils.assertArgumentNotNull(municipioId, ERR_MUNICIPIO_ID);
	this.municipioId = municipioId;
    }

    public String getCodigoIbge() {
	return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
	Utils.assertArgumentNotEmpty(codigoIbge, ERR_CODIGO_IBGE);
	this.codigoIbge = codigoIbge;
    }

    public String getNomeMunicipio() {
	return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
	Utils.assertArgumentNotEmpty(nomeMunicipio, ERR_NOME_MUNICIPIO);
	this.nomeMunicipio = nomeMunicipio;
    }

    public String getLatitude() {
	return latitude;
    }

    public void setLatitude(String latitude) {
	Utils.assertArgumentNotEmpty(latitude, ERR_LATITUDE);
	this.latitude = latitude;
    }

    public String getLongitude() {
	return longitude;
    }

    public void setLongitude(String longitude) {
	Utils.assertArgumentNotEmpty(longitude, ERR_LONGITUDE);
	this.longitude = longitude;
    }

    public String getCapital() {
	return capital;
    }

    public void setCapital(String capital) {
	Utils.assertArgumentNotEmpty(capital, ERR_CAPITAL);
	this.capital = capital;
    }

    public String getCodigoUf() {
	return codigoUf;
    }

    public void setCodigoUf(String codigoUf) {
	Utils.assertArgumentNotEmpty(codigoUf, ERR_CODIGO_UF);
	this.codigoUf = codigoUf;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Municipio [id=" + id + ", codigoIbge=" + codigoIbge + ", nomeMunicipio=" + nomeMunicipio + ", latitude="
		+ latitude + ", longitude=" + longitude + ", capital=" + capital + ", codigoUf=" + codigoUf + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((capital == null) ? 0 : capital.hashCode());
	result = prime * result + ((codigoIbge == null) ? 0 : codigoIbge.hashCode());
	result = prime * result + ((codigoUf == null) ? 0 : codigoUf.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
	result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
	result = prime * result + ((municipioId == null) ? 0 : municipioId.hashCode());
	result = prime * result + ((nomeMunicipio == null) ? 0 : nomeMunicipio.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Municipio other = (Municipio) obj;
	if (capital == null) {
	    if (other.capital != null)
		return false;
	} else if (!capital.equals(other.capital))
	    return false;
	if (codigoIbge == null) {
	    if (other.codigoIbge != null)
		return false;
	} else if (!codigoIbge.equals(other.codigoIbge))
	    return false;
	if (codigoUf == null) {
	    if (other.codigoUf != null)
		return false;
	} else if (!codigoUf.equals(other.codigoUf))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (latitude == null) {
	    if (other.latitude != null)
		return false;
	} else if (!latitude.equals(other.latitude))
	    return false;
	if (longitude == null) {
	    if (other.longitude != null)
		return false;
	} else if (!longitude.equals(other.longitude))
	    return false;
	if (municipioId == null) {
	    if (other.municipioId != null)
		return false;
	} else if (!municipioId.equals(other.municipioId))
	    return false;
	if (nomeMunicipio == null) {
	    if (other.nomeMunicipio != null)
		return false;
	} else if (!nomeMunicipio.equals(other.nomeMunicipio))
	    return false;
	return true;
    }

}
