package br.com.southsystem.analisador.mock;

import br.com.southsystem.analisador.dto.RelatorioDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RelatorioMocker {

	public static final RelatorioDTO DTO = RelatorioDTO.builder().build();
	
}
