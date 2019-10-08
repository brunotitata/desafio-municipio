package br.com.senior.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "codigoIbge", "nomeMunicipio", "latitude", "longitude", "capital", "codigoUf" })
public class MunicipioResource {

    private String codigoIbge;
    private String nomeMunicipio;
    private String latitude;
    private String longitude;
    private String capital;
    private String codigoUf;

    public String getCodigoIbge() {
	return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
	this.codigoIbge = codigoIbge;
    }

    public String getNomeMunicipio() {
	return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
	this.nomeMunicipio = nomeMunicipio;
    }

    public String getLatitude() {
	return latitude;
    }

    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }

    public String getLongitude() {
	return longitude;
    }

    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }

    public String getCapital() {
	return capital;
    }

    public void setCapital(String capital) {
	this.capital = capital;
    }

    public String getCodigoUf() {
	return codigoUf;
    }

    public void setCodigoUf(String codigoUf) {
	this.codigoUf = codigoUf;
    }

    @Override
    public String toString() {
	return "Municipio [codigoIbge=" + codigoIbge + ", nomeMunicipio=" + nomeMunicipio + ", latitude=" + latitude
		+ ", longitude=" + longitude + ", capital=" + capital + ", codigoUf=" + codigoUf + "]";
    }

}
