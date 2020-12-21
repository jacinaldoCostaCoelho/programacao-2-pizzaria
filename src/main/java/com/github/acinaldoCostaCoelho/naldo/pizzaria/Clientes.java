package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.ArquivoVazioException;
import com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions.ClienteNaoLocalizadoPeloIdException;

public class Clientes {

	// :: Singleton

	public static final Clientes INSTANCIA = new Clientes();

	private Clientes() {
		// Nada a fazer aqui
	}

	// :: Instância

	private Map<Integer, Cliente> clientePorIdMap = new HashMap<>();
	private Map<String, Cliente> clientePorIdStrMap = new HashMap<>();
	private Map<String, Cliente> clientePorNomeMap = new HashMap<>();
	private Map<String, Cliente> clientePorTelefoneMap = new HashMap<>();
	private Map<String, Cliente> clientePorCpfMap = new HashMap<>();
	private Map<String, Cliente> clientePorEmailMap = new HashMap<>();

	public void popular(Path file) throws IOException {
		var linhasIt = Files.readAllLines(file).iterator();

		if (linhasIt.hasNext()) {
			var quantidadeDeClientes = Integer.valueOf(linhasIt.next());
			for (int i = 0; i < quantidadeDeClientes; i++) {
				var id = Integer.valueOf(linhasIt.next());
				var nome = linhasIt.next();
				var email = linhasIt.next();
				var telefone = linhasIt.next();
				var cpf = linhasIt.next();
				var localEntrega = Enderecos.INSTANCIA.getPorId(Integer.valueOf(linhasIt.next()));
				var cliente = new Cliente(id, nome, email, telefone, cpf, localEntrega);

				clientePorIdMap.put(cliente.getId(), cliente);
				clientePorNomeMap.put(cliente.getNome(), cliente);
				clientePorTelefoneMap.put(cliente.getTelefone(), cliente);
				clientePorCpfMap.put(cliente.getCpf(), cliente);
				clientePorEmailMap.put(cliente.getEmail(), cliente);
				clientePorIdStrMap.put(cliente.getId().toString(), cliente);
			}
		} else {
			throw new ArquivoVazioException(file);
		}
	}

	public Cliente getPorId(Object chave) {
		Cliente cliente = null;

		if (chave instanceof Integer) {
			cliente = clientePorIdMap.get(chave);
		} else if (chave instanceof String) {
			cliente = clientePorCpfMap.get(chave);
			if (cliente == null) {
				cliente = clientePorTelefoneMap.get(chave);
				if (cliente == null) {
					cliente = clientePorNomeMap.get(chave);
					if (cliente == null) {
						cliente = clientePorEmailMap.get(chave);
						if (cliente == null) {
							cliente = clientePorIdStrMap.get(chave);
						}
					}
				}
			}
		}

		if (cliente == null) {
			throw new ClienteNaoLocalizadoPeloIdException(chave);
		}
		return cliente;
	}

}
