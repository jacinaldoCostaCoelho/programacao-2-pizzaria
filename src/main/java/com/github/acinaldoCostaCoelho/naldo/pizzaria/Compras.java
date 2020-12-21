package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class Compras {

	// :: Singleton

	public static final Compras INSTANCIA = new Compras();

	private Compras() {
		// Nada a fazer aqui
	}

	// :: Instância

	Map<Integer, Pedido> confirmadosMap = new LinkedHashMap<>();

	Map<Integer, Pedido> canceladosMap = new LinkedHashMap<>();

	public void simular(Path file, PrintStream out) throws Exception {
		var cmd = new CarregaDadosDaSimulacao();
		cmd.compras = this;
		cmd.file = file;
		cmd.out = out;
		cmd.roda();
	}

	public void imprimeRelatorioPedidosConfirmados(PrintStream out) {
		out.println("-----------Relatório de pedidos confirmados ----------------");
		for (var pedido : confirmadosMap.values()) {
			pedido.imprime(out);
		}

	}

	public void imprimeRelatorioPedidosCancelados(PrintStream out) {
		out.println("-----------Relatório de pedidos cancelados ----------------");
		for (var pedido : canceladosMap.values()) {
			pedido.imprime(out);
		}
	}

	enum FasePedido {
		ID_PIZZA, TAMANHO_PIZZA, QTDE_PIZZA, CONTINUAR, CONFIRMAR
	}

}
