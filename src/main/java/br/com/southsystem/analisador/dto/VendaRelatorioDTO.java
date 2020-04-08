package br.com.southsystem.analisador.dto;

import java.math.BigDecimal;

import br.com.southsystem.analisador.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaRelatorioDTO {

	private Long id;
	private BigDecimal total;
	private Vendedor vendedor;
	
}
