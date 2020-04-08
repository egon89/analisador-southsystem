package br.com.southsystem.analisador.adapter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.southsystem.analisador.dto.VendaRelatorioDTO;
import br.com.southsystem.analisador.model.Venda;

@Component
public class VendaRelatorioAdapter {

	public VendaRelatorioDTO valueOf(Venda venda) {
		BigDecimal total = venda.getItens().stream()
                .map(item -> item.getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return VendaRelatorioDTO.builder()
				.id(venda.getId())
				.vendedor(venda.getVendedor())
				.total(total)
				.build();
	}
	
	public List<VendaRelatorioDTO> valueOf(List<Venda> vendas) {
		return vendas.stream().map(this::valueOf).collect(Collectors.toList());
	}
	
}
