package com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions;

public class FaltandoDefinicaoDeTamanhoParaPizzaException extends RuntimeException {

	private static final long serialVersionUID = -8550682490680329628L;

	public FaltandoDefinicaoDeTamanhoParaPizzaException(String nomeTamano) {
		super("Erro na configuração do cardápio. Faltando tamanho \"" + nomeTamano + "\"");
	}

}
