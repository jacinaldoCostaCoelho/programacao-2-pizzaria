package com.github.acinaldoCostaCoelho.naldo.pizzaria.utils;

import java.util.List;

public class StringUtils {

	public static String juntar(List<String> list, String separador1, String separador2) {
		switch (list.size()) {
		case 0:
			return "";
		case 1:
			return list.get(0);
		case 2:
			return list.get(0) + separador2 + list.get(1);
		default:
			var sb = new StringBuilder();
			sb.append(list.get(0));

			int i = 1, iUltimo = list.size() - 1;
			while (i < iUltimo) {
				sb.append(separador1);
				sb.append(list.get(i));
				i++;
			}
			sb.append(separador2);
			sb.append(list.get(i));
			return sb.toString();
		}
	}

}
