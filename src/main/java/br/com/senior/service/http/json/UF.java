
package br.com.senior.service.http.json;

public class UF {

    private String id;
    private String nome;
    private Regiao regiao;
    private String sigla;

    public UF(String id, String nome, Regiao regiao, String sigla) {
	this.id = id;
	this.nome = nome;
	this.regiao = regiao;
	this.sigla = sigla;
    }

    @SuppressWarnings("unused")
    private UF() {
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

    public Regiao getRegiao() {
	return regiao;
    }

    public void setRegiao(Regiao regiao) {
	this.regiao = regiao;
    }

    public String getSigla() {
	return sigla;
    }

    public void setSigla(String sigla) {
	this.sigla = sigla;
    }

    @Override
    public String toString() {
	return "UF [id=" + id + ", nome=" + nome + ", regiao=" + regiao + ", sigla=" + sigla + "]";
    }

}
