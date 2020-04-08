package br.com.southsystem.analisador.mapper;

public interface Mapper<I,O> {

	O mapper(I entrada);
	
}
