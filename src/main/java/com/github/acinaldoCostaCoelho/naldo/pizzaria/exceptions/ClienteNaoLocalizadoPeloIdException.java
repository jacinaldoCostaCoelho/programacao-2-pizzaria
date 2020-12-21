package com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions;

public class ClienteNaoLocalizadoPeloIdException extends RuntimeException {

	private static final long serialVersionUID = 940873725226543666L;

	private Integer id;

	public ClienteNaoLocalizadoPeloIdException(Object id) {
		super("Cliente não localizado pelo id/chave " + id);
	}

	public Integer getId() {
		return id;
	}

}
