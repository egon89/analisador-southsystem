package br.com.southsystem.analisador.strategy.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.enumeration.TipoProcessadorEnum;
import br.com.southsystem.analisador.mapper.implementation.ClienteArquivoMapper;
import br.com.southsystem.analisador.model.Cliente;
import br.com.southsystem.analisador.strategy.Processador;

@Component
public class ClienteLinhaProcessador implements Processador {

	private ClienteArquivoMapper clienteArquivoMapper;

	@Autowired
	public ClienteLinhaProcessador(ClienteArquivoMapper mapper) {
		this.clienteArquivoMapper = mapper;
	}

	@Override
	public void processar(String conteudo, ResumoDTO resumo) {
		Cliente cliente = clienteArquivoMapper.mapper(conteudo);
		resumo.adicionar(cliente);
	}

	@Override
	public String getProcessadorStrategy() {
		return TipoProcessadorEnum.CLIENTE.getId();
	}

}
