package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.Serializable;
import java.util.Objects;

class Endereco implements Serializable {

	private static final long serialVersionUID = 7609566688742556570L;

	private final Integer id;
	private final String rua;
	private final String bairro;
	private final int num;

	/**
	 * Casdatro do endereço do cliente
	 * 
	 * @param rua
	 * @param bairro
	 * @param numero
	 */
	public Endereco(Integer id, String rua, String bairro, int numero) {
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.num = numero;
	}

	public Integer getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", bairro=" + bairro + ", num=" + num + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Endereco))
			return false;
		Endereco endereço = (Endereco) o;
		//@formatter:off
		return getNum() == endereço.getNum() 
				&& Objects.equals(getRua(), endereço.getRua()) 
				&& getBairro().equals(endereço.getBairro());
		//@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRua(), getBairro(), getNum());
	}
}
