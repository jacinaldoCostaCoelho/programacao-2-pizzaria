package com.github.acinaldoCostaCoelho.naldo.pizzaria;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

	public static void main(String[] args) throws Exception {
		var app = new Main();
		try {
			app.trabalhoPath = Paths.get("").toAbsolutePath();
			app.preparar();
			app.rodar();
		} finally {
			app.liberar();
		}
	}

	// :: Configuracao

	Path trabalhoPath;

	// :: Injeção

	private Enderecos enderecos = Enderecos.INSTANCIA;
	private Clientes clientes = Clientes.INSTANCIA;
	private Cardapio cardapio = Cardapio.INSTANCIA;
	private Compras compras = Compras.INSTANCIA;

	void preparar() throws Exception {
		enderecos.popular(trabalhoPath.resolve("enderecos.txt"));
		clientes.popular(trabalhoPath.resolve("clientes.txt"));
		cardapio.popular(trabalhoPath.resolve("cardapio.txt"));
	}

	void liberar() throws Exception {
		// Nada a fazer para esse programa
	}

	void rodar() throws Exception {
		try (//
				var outStream = Files.newOutputStream(trabalhoPath.resolve("output.txt"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
				var out = new PrintStream(outStream, true, StandardCharsets.UTF_8);
		//
		) {
			cardapio.imprimeCardapio(out);

			compras.simular(trabalhoPath.resolve("simulacao-pedidos.txt"), out);

			compras.imprimeRelatorioPedidosConfirmados(out);
			compras.imprimeRelatorioPedidosCancelados(out);
		}
	}

}
