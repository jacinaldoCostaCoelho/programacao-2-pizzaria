package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.PrintStream;

import com.github.acinaldoCostaCoelho.naldo.pizzaria.utils.NumberUtils;

public class PedidoItem {

	private String id;

	private Pizza pizza;

	private TamanhoPizza tamanho;

	private int quantidade;

	public PedidoItem(Pizza pizza, TamanhoPizza tamanho, int quantidade) {
		this.id = pizza.getId() + ":" + tamanho.getNome();
		this.pizza = pizza;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
	}

	public String getId() {
		return id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public TamanhoPizza getTamanho() {
		return tamanho;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void imprime(PrintStream out, String ident, int width) {
		var sb = new StringBuilder();
		sb.append(pizza.getNome());
		sb.append(" / ");
		sb.append(tamanho.getNome());
		sb.append(" / ");
		if (quantidade > 0) {
			sb.append(quantidade);
		} else {
			sb.append("Cancelado");
		}
		for (int i = sb.length(); i < width; i++) {
			sb.append(' ');
		}
		sb.append("R$ ");
		sb.append(NumberUtils.formataComoDinheiro(pizza.getValorMap().get(tamanho)));
		
		out.print(ident);
		out.println(sb.toString());
	}

}
