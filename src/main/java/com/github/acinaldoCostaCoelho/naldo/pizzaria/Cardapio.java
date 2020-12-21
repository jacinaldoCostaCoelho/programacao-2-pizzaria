package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.ArquivoVazioException;
import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.PizzaNaoLocalizadaPeloIdException;
import com.github.acinaldoCostaCoelho.naldo.pizzaria.utils.StringUtils;

public class Cardapio {

	// :: Singleton

	public static final Cardapio INSTANCIA = new Cardapio();

	private Cardapio() {
		// Nada a fazer aqui
	}

	// :: Instância

	private Map<Integer, Pizza> pizzaMap = new LinkedHashMap<>();

	public void popular(Path file) throws IOException {
		var linhasIt = Files.readAllLines(file).iterator();

		if (linhasIt.hasNext()) {
			var tamanhos = new ArrayList<TamanhoPizza>();
			var pizzaList = new ArrayList<Pizza>();

			var quantidadeDeTamanhos = Integer.valueOf(linhasIt.next());

			for (int i = 0; i < quantidadeDeTamanhos; i++) {
				var nome = linhasIt.next();
				var qtdeFatias = Integer.valueOf(linhasIt.next());
				tamanhos.add(new TamanhoPizza(nome, qtdeFatias));
			}

			var quantidadeDePizzas = Integer.valueOf(linhasIt.next());
			for (int i = 0; i < quantidadeDePizzas; i++) {
				var id = Integer.valueOf(linhasIt.next());
				var nome = linhasIt.next();

				var quantidadeDeIngredientes = Integer.valueOf(linhasIt.next());
				var ingredienteList = new ArrayList<String>();
				for (int j = 0; j < quantidadeDeIngredientes; j++) {
					ingredienteList.add(linhasIt.next());
				}

				var ingredientes = StringUtils.juntar(ingredienteList, ", ", " e ");

				var valorMap = new HashMap<TamanhoPizza, Double>();
				for (int j = 0; j < tamanhos.size(); j++) {
					Double valor = Double.valueOf(linhasIt.next());
					valorMap.put(tamanhos.get(j), valor);
				}

				pizzaList.add(new Pizza(id, nome, ingredientes, valorMap));
			}

			pizzaList.sort((a, b) -> String.CASE_INSENSITIVE_ORDER.compare(a.getNome(), b.getNome()));

			for (var pizza : pizzaList) {
				pizzaMap.put(pizza.getId(), pizza);
			}
		} else {
			throw new ArquivoVazioException(file);
		}
	}

	public Pizza getPorId(Integer id) {
		var pizza = pizzaMap.get(id);
		if (pizza == null) {
			throw new PizzaNaoLocalizadaPeloIdException(id);
		}
		return pizza;
	}

	public void imprimeCardapio(PrintStream out) {
		out.println("*** Cardário ****");
		out.println();

		for (var pizza : pizzaMap.values()) {
			pizza.imprime(out);
			out.println();
		}
	}

}
