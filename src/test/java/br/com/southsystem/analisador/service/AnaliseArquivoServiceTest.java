package br.com.southsystem.analisador.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.southsystem.analisador.dispatcher.implementation.ArquivoRelatorioDispatcher;
import br.com.southsystem.analisador.leitor.implementation.LeitorArquivo;
import br.com.southsystem.analisador.mock.DispatcherConfigMocker;
import br.com.southsystem.analisador.mock.RelatorioMocker;
import br.com.southsystem.analisador.mock.ResumoMocker;

@ExtendWith(MockitoExtension.class)
class AnaliseArquivoServiceTest {

	@InjectMocks
	private AnaliseArquivoService service;
	
	@Mock
	private LeitorArquivo leitor;
	
	@Mock
	private ArquivoRelatorioDispatcher expeditor;
	
	@Mock
	private RelatorioService relatorioService;
	
	@Test
	void givenPathValido_whenAnalisar_thenChamaEnviarRelatorio() {
		when(leitor.ler(any())).thenReturn(ResumoMocker.DTO);
		when(relatorioService.apurar(any())).thenReturn(RelatorioMocker.DTO);
		Path path = Paths.get("arquivo.dat");
		service.analisar(path);
		
		verify(expeditor, atLeastOnce()).enviar(RelatorioMocker.DTO, DispatcherConfigMocker.getEntidadeCom(path));
	}
	
	@Test
	void givenPathInvalido_whenAnalisar_thenNaoChamaEnviarRelatorio() {
		Path path = Paths.get("arquivo.txt");
		service.analisar(path);
		
		verify(expeditor, never()).enviar(RelatorioMocker.DTO, DispatcherConfigMocker.getEntidadeCom(path));
	}

}
