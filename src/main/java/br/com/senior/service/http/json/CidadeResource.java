
package br.com.senior.service.http.json;

public class CidadeResource {

    private String id;
    private Microrregiao microrregiao;
    private String nome;

    public CidadeResource(String id, Microrregiao microrregiao, String nome) {
	this.id = id;
	this.microrregiao = microrregiao;
	this.nome = nome;
    }

    public CidadeResource() {
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Microrregiao getMicrorregiao() {
	return microrregiao;
    }

    public void setMicrorregiao(Microrregiao microrregiao) {
	this.microrregiao = microrregiao;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

}
