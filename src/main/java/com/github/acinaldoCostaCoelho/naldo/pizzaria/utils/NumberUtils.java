package com.github.acinaldoCostaCoelho.naldo.pizzaria.utils;

import java.text.DecimalFormat;

public class NumberUtils {

	public static String formataComoDinheiro(Double value) {
		if (value != null) {
			return new DecimalFormat("###,###.00").format(value);
		} else {
			return "";
		}
	}

}
