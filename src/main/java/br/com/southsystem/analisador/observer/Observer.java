package br.com.southsystem.analisador.observer;

import br.com.southsystem.analisador.service.AnalisadorService;

public abstract class Observer {

	protected AnalisadorService analise;

	public Observer(AnalisadorService analise) {
		this.analise = analise;
	}

	public abstract void monitorar() throws Exception;
	
}
