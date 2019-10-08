
package br.com.senior.service.http.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mesorregiao {

    @JsonProperty("UF")
    private UF uF;

    @JsonProperty("id")
    private String id;

    @JsonProperty("nome")
    private String nome;

    public Mesorregiao(UF uF, String id, String nome) {
	this.uF = uF;
	this.id = id;
	this.nome = nome;
    }

    @SuppressWarnings("unused")
    private Mesorregiao() {
    }

    @JsonProperty("UF")
    public UF getUF() {
	return uF;
    }

    @JsonProperty("UF")
    public void setUF(UF uF) {
	this.uF = uF;
    }

    @JsonProperty("id")
    public String getId() {
	return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
	this.id = id;
    }

    @JsonProperty("nome")
    public String getNome() {
	return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
	this.nome = nome;
    }

    @Override
    public String toString() {
	return "Mesorregiao [uF=" + uF + ", id=" + id + ", nome=" + nome + "]";
    }

}
