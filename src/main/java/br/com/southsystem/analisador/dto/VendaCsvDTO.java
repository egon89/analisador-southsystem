package br.com.southsystem.analisador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaCsvDTO {

	private Long id;
	private String descricaoItens;
	private String vendedor;
	
}
