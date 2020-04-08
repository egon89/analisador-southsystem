package br.com.southsystem.analisador.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor implements Serializable {

	private static final long serialVersionUID = 1251794789637223725L;
	
	private String cpf;
	
	@EqualsAndHashCode.Exclude
	private String nome;
	
	@EqualsAndHashCode.Exclude
	private BigDecimal salario;
	
}
