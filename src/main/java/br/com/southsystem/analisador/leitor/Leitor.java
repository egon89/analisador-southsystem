package br.com.southsystem.analisador.leitor;

import br.com.southsystem.analisador.dto.ResumoDTO;

public interface Leitor<T> {

	ResumoDTO ler(T dados);
	
}
