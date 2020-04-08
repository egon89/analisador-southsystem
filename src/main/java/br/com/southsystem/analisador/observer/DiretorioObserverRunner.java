package br.com.southsystem.analisador.observer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DiretorioObserverRunner {

	private DiretorioObserver diretorioObserver;
	
	@Autowired
	public DiretorioObserverRunner(DiretorioObserver diretorioObserver) {
		this.diretorioObserver = diretorioObserver;
	}
	
	@Scheduled(fixedDelay = Long.MAX_VALUE)
	public void monitorRunner() throws InterruptedException, IOException {
		log.info("Iniciando diretório observer...");
		diretorioObserver.monitorar();
	}

}
