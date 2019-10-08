
package br.com.senior.service.http.json;

public class Microrregiao {

    private String id;
    private Mesorregiao mesorregiao;

    private String nome;

    public Microrregiao(String id, Mesorregiao mesorregiao, String nome) {
	this.id = id;
	this.mesorregiao = mesorregiao;
	this.nome = nome;
    }

    @SuppressWarnings("unused")
    private Microrregiao() {
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Mesorregiao getMesorregiao() {
	return mesorregiao;
    }

    public void setMesorregiao(Mesorregiao mesorregiao) {
	this.mesorregiao = mesorregiao;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

}
