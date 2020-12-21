package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.util.Objects;

public class TamanhoPizza {

	private String nome;

	private Integer qtdeFatias;

	public TamanhoPizza(String nome, Integer qtdeFatias) {
		this.nome = nome;
		this.qtdeFatias = qtdeFatias;
	}

	public String getNome() {
		return nome;
	}

	public Integer getQtdeFatias() {
		return qtdeFatias;
	}

	@Override
	public String toString() {
		return "TamanhoPizza [nome=" + nome + ", qtdeFatias=" + qtdeFatias + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, qtdeFatias);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TamanhoPizza other = (TamanhoPizza) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (qtdeFatias == null) {
			if (other.qtdeFatias != null)
				return false;
		} else if (!qtdeFatias.equals(other.qtdeFatias))
			return false;
		return true;
	}

}
