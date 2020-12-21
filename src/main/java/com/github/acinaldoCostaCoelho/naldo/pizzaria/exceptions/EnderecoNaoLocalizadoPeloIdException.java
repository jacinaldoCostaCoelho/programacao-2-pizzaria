package com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions;

public class EnderecoNaoLocalizadoPeloIdException extends RuntimeException {

	private static final long serialVersionUID = 940873725226543666L;

	private Integer id;

	public EnderecoNaoLocalizadoPeloIdException(Integer id) {
		super("Endereço não localizado pelo id " + id);
	}

	public Integer getId() {
		return id;
	}

}
