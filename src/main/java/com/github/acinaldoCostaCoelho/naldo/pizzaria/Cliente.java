package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.Serializable;
import java.util.Objects;

class Cliente implements Serializable {

	private static final long serialVersionUID = 8738995789223785323L;

	private final Integer id;
	private final String nome;
	private final String email;
	private final String telefone;
	private final String cpf;
	private final Endereco localEntrega;

	/**
	 * Cadastro do cliente
	 * 
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param cpf
	 * @param endereço
	 */
	public Cliente(Integer id, String nome, String email, String telefone, String cpf, Endereco localEntrega) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.localEntrega = localEntrega;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	/**
	 * Retona um novo objeto endereço para cliente
	 * 
	 * @return
	 */
	public Endereco getLocalEntrega() {
		return localEntrega;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf + ", localEntrega=" + localEntrega + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Cliente))
			return false;
		Cliente cliente = (Cliente) o;
		//@formatter:off
        return telefone == cliente.telefone &&
                cpf == cliente.cpf &&
                nome.equals(cliente.nome) &&
                email.equals(cliente.email) &&
                localEntrega.equals(cliente.localEntrega);
        //@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, email, telefone, cpf, localEntrega);
	}

}
