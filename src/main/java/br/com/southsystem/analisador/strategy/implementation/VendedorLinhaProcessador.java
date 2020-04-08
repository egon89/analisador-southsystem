package br.com.southsystem.analisador.strategy.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.enumeration.TipoProcessadorEnum;
import br.com.southsystem.analisador.mapper.implementation.VendedorArquivoMapper;
import br.com.southsystem.analisador.model.Vendedor;
import br.com.southsystem.analisador.strategy.Processador;

@Component
public class VendedorLinhaProcessador implements Processador {

	private VendedorArquivoMapper vendedorArquivoMapper;
	
	@Autowired
	public VendedorLinhaProcessador(VendedorArquivoMapper vendedorArquivoMapper) {
		this.vendedorArquivoMapper = vendedorArquivoMapper;
	}

	@Override
	public void processar(String conteudo, ResumoDTO resumo) {
		Vendedor vendedor = vendedorArquivoMapper.mapper(conteudo);
		resumo.adicionar(vendedor);
	}

	@Override
	public String getProcessadorStrategy() {
		return TipoProcessadorEnum.VENDEDOR.getId();
	}

}
