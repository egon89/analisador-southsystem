package br.com.southsystem.analisador.leitor.implementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.exception.StrategyNotFound;
import br.com.southsystem.analisador.leitor.Leitor;
import br.com.southsystem.analisador.strategy.Processador;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeitorArquivo implements Leitor<Path> {

	private List<Processador> processadores;

	@Autowired
	public LeitorArquivo(List<Processador> processadores) {
		this.processadores = processadores;
	}

	@Override
	public ResumoDTO ler(Path dados) {
		ResumoDTO resumo = new ResumoDTO();
		try {
			Files.lines(dados).forEach(linha -> {
				getStrategy(linha).processar(linha, resumo);
			});
		} catch (IOException e) {
			log.error("{}", e.getMessage());
		}
		return resumo;
	}
	
	private Processador getStrategy(String linha) {
		Optional<Processador> processadorOpt = processadores.stream()
				.filter(p -> linha.startsWith(p.getProcessadorStrategy()))
				.findFirst();
		return processadorOpt
				.orElseThrow(() -> new StrategyNotFound("Nenhuma estratégia de leitura foi encontrada para a linha: " + linha));
	}

}
