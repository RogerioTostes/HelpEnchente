package com.helpenchentes.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Doacao {

	@Id
	@NotEmpty
	private String rg;

	@NotEmpty
	private String nomeDoador;

	@NotEmpty
	private String doacoes;

	@ManyToOne
	private Pessoa pessoa;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public String getNomeDoador() {
		return nomeDoador;
	}

	public void setNomeDoador(String nomeDoador) {
		this.nomeDoador = nomeDoador;
	}

	public String getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(String doacoes) {
		this.doacoes = doacoes;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
