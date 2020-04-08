package br.com.southsystem.analisador.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioDTO {

	private Long quantidadeCliente;
	private Long quantidadeVendedor;
	private Long vendaMaisCaraId;
	private String piorVendedor;
	
}
