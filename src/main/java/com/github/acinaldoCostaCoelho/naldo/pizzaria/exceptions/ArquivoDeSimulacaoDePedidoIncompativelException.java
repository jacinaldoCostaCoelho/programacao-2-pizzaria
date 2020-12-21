package com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions;

public class ArquivoDeSimulacaoDePedidoIncompativelException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ArquivoDeSimulacaoDePedidoIncompativelException(int linha, String esperado) {
		super("Arquivo de simulação de pedidos incompativel na linha " + linha + ". Valor esperado " + esperado);
	}

}
