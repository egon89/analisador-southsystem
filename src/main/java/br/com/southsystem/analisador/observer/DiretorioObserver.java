package br.com.southsystem.analisador.observer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.southsystem.analisador.helper.DiretorioHelper;
import br.com.southsystem.analisador.service.AnaliseArquivoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DiretorioObserver extends Observer {

	@Autowired
	public DiretorioObserver(AnaliseArquivoService analiseService) {
		super(analiseService);
	}

	@Override
	public void monitorar() throws InterruptedException, IOException {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			Path path = Paths.get(DiretorioHelper.getInputFolder());
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			WatchKey key;
			log.info("Monitorando diretório: {}", DiretorioHelper.getInputFolder());
			while ((key = watchService.take()) != null) {
				key.pollEvents().forEach(evento -> {
					log.info("Novo arquivo reconhecido: {}", evento.context());
					Path filePath = (Path) evento.context();
					Path inputFilePath = path.resolve(filePath);
					analise.analisar(inputFilePath);
				});
				key.reset();
			}
		} catch (Exception e) {
			log.error("{}", e.getMessage());
		}
	}

}
