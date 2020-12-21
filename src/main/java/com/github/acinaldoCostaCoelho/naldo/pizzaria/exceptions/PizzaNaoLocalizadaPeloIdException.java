package com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions;

public class PizzaNaoLocalizadaPeloIdException extends RuntimeException {

	private static final long serialVersionUID = -8584940696576452315L;

	private Integer id;

	public PizzaNaoLocalizadaPeloIdException(Integer id) {
		super("Pizza não localizada pelo id " + id);
	}

	public Integer getId() {
		return id;
	}

}
