package br.com.southsystem.analisador.exception;

public class StrategyNotFound extends RuntimeException {

	private static final long serialVersionUID = -4522349358162110099L;
	
	public StrategyNotFound(String mensagem) {
		super(mensagem);
	}

}
