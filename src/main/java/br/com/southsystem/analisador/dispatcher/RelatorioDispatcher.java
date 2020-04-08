package br.com.southsystem.analisador.dispatcher;

import br.com.southsystem.analisador.dto.RelatorioDTO;

public interface RelatorioDispatcher {

	void enviar(RelatorioDTO relatorio, DispatcherConfig config);
	
}
