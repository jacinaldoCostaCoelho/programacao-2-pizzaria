package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pedido {

	private static int idGen;

	private Integer id;

	private Cliente cliente;

	private Map<String, PedidoItem> itemMap;

	public Pedido(Cliente cliente) {
		this.id = idGen++;
		this.itemMap = new LinkedHashMap<String, PedidoItem>();
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public boolean temItens() {
		return this.itemMap.size() > 0;
	}

	public void add(Pizza pizza, TamanhoPizza tamanho, int quantidade) {
		var itemNovo = new PedidoItem(pizza, tamanho, quantidade);

		var itemAtual = this.itemMap.get(itemNovo.getId());
		if (itemAtual != null) {
			itemAtual.setQuantidade(itemAtual.getQuantidade() + quantidade);
		} else {
			this.itemMap.put(itemNovo.getId(), itemNovo);
		}
	}

	public void imprime(PrintStream out) {
		out.println("Pedido: " + this.id);
		out.println("Cliente " + this.cliente.getNome());
		out.println("Itens:");
		for (var item : this.itemMap.values()) {
			item.imprime(out, "  ", 60);
		}
		out.println();
	}

}
