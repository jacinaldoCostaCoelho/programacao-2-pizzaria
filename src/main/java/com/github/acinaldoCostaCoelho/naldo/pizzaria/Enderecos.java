package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.ArquivoVazioException;
import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.EnderecoNaoLocalizadoPeloIdException;

public class Enderecos {
	// :: Singleton

	public static final Enderecos INSTANCIA = new Enderecos();

	private Enderecos() {
		// Nada a fazer aqui
	}

	// :: Instância

	private Map<Integer, Endereco> map = new HashMap<>();

	public void popular(Path file) throws IOException {
		var linhasIt = Files.readAllLines(file).iterator();

		if (linhasIt.hasNext()) {
			var quantidadeDeEnderecos = Integer.valueOf(linhasIt.next());
			for (int i = 0; i < quantidadeDeEnderecos; i++) {
				var id = Integer.valueOf(linhasIt.next());
				var rua = linhasIt.next();
				var bairro = linhasIt.next();
				var numero = Integer.valueOf(linhasIt.next());
				map.put(id, new Endereco(id, rua, bairro, numero));
			}
		} else {
			throw new ArquivoVazioException(file);
		}
	}

	public Endereco getPorId(Integer id) {
		var endereco = map.get(id);
		if (endereco == null) {
			throw new EnderecoNaoLocalizadoPeloIdException(id);
		}
		return endereco;
	}
}
