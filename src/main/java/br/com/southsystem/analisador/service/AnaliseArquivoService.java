package br.com.southsystem.analisador.service;

import java.nio.file.Path;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.southsystem.analisador.dispatcher.DispatcherConfig;
import br.com.southsystem.analisador.dispatcher.implementation.ArquivoRelatorioDispatcher;
import br.com.southsystem.analisador.dto.RelatorioDTO;
import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.helper.ArquivoHelper;
import br.com.southsystem.analisador.leitor.implementation.LeitorArquivo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnaliseArquivoService extends AnalisadorService<Path> {
	
	private RelatorioService relatorioService;
	
	@Autowired
	public AnaliseArquivoService(@Qualifier("leitorArquivo") LeitorArquivo leitor,
			@Qualifier("arquivoRelatorioDispatcher") ArquivoRelatorioDispatcher expeditor,
			RelatorioService relatorioService) {
		super(leitor, expeditor);
		this.relatorioService = relatorioService;
	}
	
	@Override
	public void analisar(Path dados) {
		if (!isEntradaValida(dados)) {
			log.error("Entrada de dados inválida!");
			return;
		}
		log.info("Analisando {}", dados);
		ResumoDTO resumo = leitor.ler(dados);
		RelatorioDTO relatorio = relatorioService.apurar(resumo);
		log.info("Relatório: {}", relatorio);
		expeditor.enviar(relatorio, DispatcherConfig.builder().path(dados).build());
	}

	private boolean isEntradaValida(Path dados) {
		return Objects.nonNull(dados) && dados.toString().endsWith(ArquivoHelper.EXTENSAO_ARQUIVO_INPUT);
	}
	
}
