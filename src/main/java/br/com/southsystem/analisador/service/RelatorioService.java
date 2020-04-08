package br.com.southsystem.analisador.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.analisador.adapter.VendaRelatorioAdapter;
import br.com.southsystem.analisador.dto.RelatorioDTO;
import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.dto.VendaRelatorioDTO;
import br.com.southsystem.analisador.model.Cliente;
import br.com.southsystem.analisador.model.Vendedor;

@Service
public class RelatorioService {

	private VendaRelatorioAdapter vendaRelatorioAdapter;
	
	@Autowired
	public RelatorioService(VendaRelatorioAdapter vendaRelatorioAdapter) {
		this.vendaRelatorioAdapter = vendaRelatorioAdapter;
	}

	public RelatorioDTO apurar(ResumoDTO resumo) {
		long quantidadeCliente = contabilizarClientes(resumo.getClientes());
		long quantidadeVendedor = contabilizarVendedores(resumo.getVendedores());
		List<VendaRelatorioDTO> vendas = vendaRelatorioAdapter.valueOf(resumo.getVendas());
		Map<String, BigDecimal> vendasPorVendedorMap = buildVendasPorVendedorMap(vendas);
		Optional<VendaRelatorioDTO> vendaMaisCara = getVendaMaisCara(vendas);
		Long vendaMaisCaraId = 0L;
		if (vendaMaisCara.isPresent()) {
			vendaMaisCaraId = vendaMaisCara.get().getId();
		}
		
		return RelatorioDTO.builder()
				.quantidadeCliente(quantidadeCliente)
				.quantidadeVendedor(quantidadeVendedor)
				.vendaMaisCaraId(vendaMaisCaraId)
				.piorVendedor(getNomePiorVendedor(vendasPorVendedorMap))
				.build();
	}
	
	private long contabilizarVendedores(Set<Vendedor> vendedores) {
		return vendedores.stream().count();
	}

	private long contabilizarClientes(Set<Cliente> clientes) {
		return clientes.stream().count();
	}
	
	private Optional<VendaRelatorioDTO> getVendaMaisCara(List<VendaRelatorioDTO> vendas) {
		return vendas.stream().max(Comparator.comparing(VendaRelatorioDTO::getTotal));
	}

	private Map<String, BigDecimal> buildVendasPorVendedorMap(List<VendaRelatorioDTO> vendas) {
		return vendas.stream().collect(Collectors
				.toMap(venda -> venda.getVendedor().getNome(), venda -> venda.getTotal(), (v1, v2) -> v1.add(v2)));
	}
	
	private String getNomePiorVendedor(Map<String, BigDecimal> vendasPorVendedorMap) {
		String nomePiorVendedor = null;
		Optional<Entry<String, BigDecimal>> piorVendedor = vendasPorVendedorMap.entrySet().stream()
				.min(Comparator.comparing(Entry::getValue));
		if (piorVendedor.isPresent()) {
			nomePiorVendedor = piorVendedor.get().getKey();
		}
		return nomePiorVendedor;
	}
	
}
