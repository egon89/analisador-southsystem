package br.com.southsystem.analisador.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ArquivoHelper {

	public static final String EXTENSAO_ARQUIVO_INPUT = ".dat";
	public static final String EXTENSAO_ARQUIVO_OUTPUT = ".done.dat";
	
	public static String finalizarNomeArquivo(String nomeArquivo) {
		try {
			int lastIndexOf = nomeArquivo.lastIndexOf('.');
			return nomeArquivo.substring(0, lastIndexOf).concat(EXTENSAO_ARQUIVO_OUTPUT);
		} catch (RuntimeException e) {
			return null;
		}
	}
	
}
