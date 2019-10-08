
package br.com.senior.service.http.json;

public class Regiao {

    private String id;
    private String nome;
    private String sigla;

    public Regiao(String id, String nome, String sigla) {
	this.id = id;
	this.nome = nome;
	this.sigla = sigla;
    }

    @SuppressWarnings("unused")
    private Regiao() {
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSigla() {
	return sigla;
    }

    public void setSigla(String sigla) {
	this.sigla = sigla;
    }

    @Override
    public String toString() {
	return "Regiao [id=" + id + ", nome=" + nome + ", sigla=" + sigla + "]";
    }

}
