package br.com.southsystem.analisador.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	private static final long serialVersionUID = 8959035783983687798L;
	
	private Long id;
	private Integer quantidade;
	private BigDecimal preco;
	
}
