package br.com.southsystem.analisador.strategy.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.enumeration.TipoProcessadorEnum;
import br.com.southsystem.analisador.mapper.implementation.VendaArquivoMapper;
import br.com.southsystem.analisador.model.Venda;
import br.com.southsystem.analisador.strategy.Processador;

@Component
public class VendaLinhaProcessador implements Processador {

	private VendaArquivoMapper vendaArquivoMapper;
	
	@Autowired
	public VendaLinhaProcessador(VendaArquivoMapper vendaArquivoMapper) {
		this.vendaArquivoMapper = vendaArquivoMapper;
	}

	@Override
	public void processar(String conteudo, ResumoDTO resumo) {
		Venda venda = vendaArquivoMapper.mapper(conteudo);
		resumo.adicionar(venda);
	}

	@Override
	public String getProcessadorStrategy() {
		return TipoProcessadorEnum.VENDA.getId();
	}

}
