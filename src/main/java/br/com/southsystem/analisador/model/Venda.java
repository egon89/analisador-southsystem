package br.com.southsystem.analisador.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venda implements Serializable {

	private static final long serialVersionUID = -5294825909888400018L;
	
	private Long id;
	private List<Item> itens;
	private Vendedor vendedor;
	
}
