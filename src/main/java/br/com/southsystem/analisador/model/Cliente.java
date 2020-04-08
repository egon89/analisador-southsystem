package br.com.southsystem.analisador.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1193946659262058269L;
	
	private String cnpj;
	
	@EqualsAndHashCode.Exclude
	private String nome;
	
	@EqualsAndHashCode.Exclude
	private String area;
	
}
