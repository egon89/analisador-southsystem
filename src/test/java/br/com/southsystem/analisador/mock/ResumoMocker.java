package br.com.southsystem.analisador.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.model.Cliente;
import br.com.southsystem.analisador.model.Item;
import br.com.southsystem.analisador.model.Venda;
import br.com.southsystem.analisador.model.Vendedor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResumoMocker {

	public static final String NOME_VENDEDOR_JOAO = "JOAO";
	public static final String NOME_VENDEDOR_PEDRO = "PEDRO";
	public static final String NOME_PIOR_VENDEDOR = NOME_VENDEDOR_JOAO;
	public static final ResumoDTO DTO = new ResumoDTO();
	
	public static final ResumoDTO getResumoPadrao() {
		ResumoDTO resumo = new ResumoDTO();
		Vendedor pedro = Vendedor.builder().cpf("123").nome(NOME_VENDEDOR_PEDRO).build();
		Vendedor joao = Vendedor.builder().cpf("456").nome(NOME_VENDEDOR_JOAO).build();
		Cliente maria = Cliente.builder().cnpj("102030").nome("Maria").build();
		Cliente rita= Cliente.builder().cnpj("405060").nome("Rita").build();
		Cliente ritaDuplicada= Cliente.builder().cnpj("405060").nome("Rita").build();
		List<Item> itens1 =  new ArrayList<>();
		itens1.add(Item.builder().id(1L).quantidade(1).preco(new BigDecimal(50.01)).build());
		itens1.add(Item.builder().id(2L).quantidade(2).preco(new BigDecimal(49.99)).build());
		List<Item> itens2 =  new ArrayList<>();
		itens2.add(Item.builder().id(3L).quantidade(2).preco(new BigDecimal(10)).build());
		itens2.add(Item.builder().id(4L).quantidade(3).preco(new BigDecimal(9.99)).build());
		resumo.adicionar(pedro);
		resumo.adicionar(joao);
		resumo.adicionar(maria);
		resumo.adicionar(rita);
		resumo.adicionar(ritaDuplicada);
		resumo.adicionar(Venda.builder().id(1L).itens(itens1).vendedor(pedro).build());
		resumo.adicionar(Venda.builder().id(2L).itens(itens2).vendedor(joao).build());
		
		return resumo;
	}
	
}
