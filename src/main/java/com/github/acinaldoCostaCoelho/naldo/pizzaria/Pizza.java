package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.PrintStream;
import java.util.Map;

import com.github.acinaldoCostaCoelho.naldo.pizzaria.utils.NumberUtils;

public class Pizza {

	private Integer id;

	private String nome;

	private String ingredientes;

	private Map<TamanhoPizza, Double> valorMap;

	public Pizza(Integer id, String nome, String ingredientes, Map<TamanhoPizza, Double> valorMap) {
		this.id = id;
		this.nome = nome;
		this.ingredientes = ingredientes;
		this.valorMap = valorMap;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public Map<TamanhoPizza, Double> getValorMap() {
		return valorMap;
	}

	public TamanhoPizza getTamanhoPorNome(String nome) {
		for (var item : valorMap.entrySet()) {
			if (item.getKey().getNome().equalsIgnoreCase(nome)) {
				return item.getKey();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nome=" + nome + ", ingredientes=" + ingredientes + ", valorMap=" + valorMap + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingredientes == null) ? 0 : ingredientes.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valorMap == null) ? 0 : valorMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingredientes == null) {
			if (other.ingredientes != null)
				return false;
		} else if (!ingredientes.equals(other.ingredientes))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valorMap == null) {
			if (other.valorMap != null)
				return false;
		} else if (!valorMap.equals(other.valorMap))
			return false;
		return true;
	}

	public void imprime(PrintStream out) {
		out.print(id);
		out.print(" - ");
		out.print(nome);
		out.print(" -");

		int i = 0;
		for (var item : valorMap.entrySet()) {
			out.print(i == 0 ? " " : "; ");
			out.print(item.getKey().getNome() + "(" + item.getKey().getQtdeFatias() + " Fatias) por R$ " + NumberUtils.formataComoDinheiro(item.getValue()));
			i++;
		}
		out.println();

		out.print("         Ingredientes: ");
		out.println(ingredientes);
	}

}
