package com.github.acinaldoCostaCoelho.naldo.pizzaria.exceptions;

import java.io.IOException;
import java.nio.file.Path;

public class ArquivoVazioException extends IOException {

	private static final long serialVersionUID = 6368850329404581157L;

	public ArquivoVazioException(Path path) {
		super("Arquivo vazio em " + path);
	}

}
