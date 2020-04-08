package br.com.southsystem.analisador.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoProcessadorEnum {

	VENDEDOR("001"),
	CLIENTE("002"),
	VENDA("003");
	
	private String id;
	
}
