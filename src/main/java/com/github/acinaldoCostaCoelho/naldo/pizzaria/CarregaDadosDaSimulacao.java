package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.github.acinaldoCostaCoelho.naldo.pizzaria.Compras.FasePedido;
import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.ArquivoDeSimulacaoDePedidoIncompativelException;
import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.FaltandoDefinicaoDeTamanhoParaPizzaException;

class CarregaDadosDaSimulacao {

	// Campos de configuracao

	public Compras compras;
	public Path file;
	public PrintStream out;

	// Instancias singletons

	private Clientes clientes = Clientes.INSTANCIA;

	private Cardapio cardapio = Cardapio.INSTANCIA;

	// Campos Internos

	private int linhaIdx;
	private List<String> linhas;
	private FasePedido state;
	private Pedido pedido;
	private Pizza pizza;
	private TamanhoPizza tamanho;

	void roda() throws Exception {
		linhas = Files.readAllLines(file);
		if (linhas.isEmpty()) {
			return;
		}

		while (linhaIdx < linhas.size()) {
			pedido = new Pedido(clientes.getPorId(linhas.get(linhaIdx++)));

			state = FasePedido.ID_PIZZA;
			pizza = null;
			tamanho = null;

			while (linhaIdx < linhas.size()) {
				var v = linhas.get(linhaIdx++);
				if ("R".equals(v)) {
					// Imprimir relatório parcial do pedido
					imprimeParcialDoPedido();
				} else if ("P".equals(v) || "M".equals(v) || "G".equals(v)) {
					leTamanhoPizza(v);
				} else if ("N".equals(v)) {
					if (interpresaNegacao(v))
						break;
				} else if ("S".equals(v)) {
					if (interpresaConfirmacao(v))
						break;
				} else {
					var num = Integer.valueOf(v);
					if (state == FasePedido.ID_PIZZA) {
						pizza = cardapio.getPorId(num);
						state = FasePedido.TAMANHO_PIZZA;
					} else if (state == FasePedido.QTDE_PIZZA) {
						pedido.add(pizza, tamanho, num);
						state = FasePedido.CONTINUAR;
					}
				}
			}
		}
	}

	private void leTamanhoPizza(String v) {
		if (state == FasePedido.TAMANHO_PIZZA) {
			var nomeTamanho = "P".equals(v) ? "Pequeno" : "M".equals(v) ? "Média" : "G".equals(v) ? "Grande" : "";
			tamanho = pizza.getTamanhoPorNome(nomeTamanho);
			if (tamanho == null) {
				throw new FaltandoDefinicaoDeTamanhoParaPizzaException(nomeTamanho);
			}
			state = FasePedido.QTDE_PIZZA;
		} else {
			throw new ArquivoDeSimulacaoDePedidoIncompativelException(linhaIdx, "Tamanho pizza");
		}
	}

	private boolean interpresaNegacao(String v) {
		if (state == FasePedido.CONTINUAR) {
			state = FasePedido.CONFIRMAR;
		} else if (state == FasePedido.CONFIRMAR) {
			compras.canceladosMap.put(pedido.getId(), pedido);
			return true;
		} else {
			state = FasePedido.CONFIRMAR;
		}
		return false;
	}

	private boolean interpresaConfirmacao(String v) {
		if (state == FasePedido.CONTINUAR) {
			state = FasePedido.ID_PIZZA;
		} else if (state == FasePedido.CONFIRMAR) {
			if (!pedido.temItens()) {
				compras.canceladosMap.put(pedido.getId(), pedido);
			} else {
				compras.confirmadosMap.put(pedido.getId(), pedido);
			}
			return true;
		} else {
			throw new ArquivoDeSimulacaoDePedidoIncompativelException(linhaIdx, "Continuar pedido ou confirmar");
		}
		return false;
	}

	private void imprimeParcialDoPedido() {
		out.println("Pedido parcial");
		pedido.imprime(out);
	}

}