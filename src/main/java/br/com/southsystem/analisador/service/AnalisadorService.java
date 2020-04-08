package br.com.southsystem.analisador.service;

import br.com.southsystem.analisador.dispatcher.RelatorioDispatcher;
import br.com.southsystem.analisador.leitor.Leitor;

public abstract class AnalisadorService<T> {

	protected Leitor leitor;
	protected RelatorioDispatcher expeditor;
	
	public AnalisadorService(Leitor leitor, RelatorioDispatcher expeditor) {
		this.leitor = leitor;
		this.expeditor = expeditor;
	}
	
	public abstract void analisar(T dados);
	
}
