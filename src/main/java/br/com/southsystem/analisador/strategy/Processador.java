package br.com.southsystem.analisador.strategy;

import br.com.southsystem.analisador.dto.ResumoDTO;

public interface Processador {

	void processar(String conteudo, ResumoDTO resumo);
	String getProcessadorStrategy();
	
}
