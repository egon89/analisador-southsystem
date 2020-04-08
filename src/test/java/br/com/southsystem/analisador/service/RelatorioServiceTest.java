package br.com.southsystem.analisador.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.southsystem.analisador.dto.RelatorioDTO;
import br.com.southsystem.analisador.dto.ResumoDTO;
import br.com.southsystem.analisador.mock.ResumoMocker;
import br.com.southsystem.analisador.model.Cliente;
import br.com.southsystem.analisador.model.Vendedor;

@SpringBootTest
class RelatorioServiceTest {

	@Autowired
	@InjectMocks
	private RelatorioService service;
	
	@Test
	void givenResumoComClientes_whenApurar_thenDeveContarQuantidadeDeCliente() {
		ResumoDTO resumo = new ResumoDTO();
		resumo.adicionar(Cliente.builder().cnpj("123").build());
		resumo.adicionar(Cliente.builder().cnpj("456").build());
		resumo.adicionar(Cliente.builder().cnpj("123").build());
		RelatorioDTO relatorio = service.apurar(resumo);
		
		assertEquals(2, relatorio.getQuantidadeCliente());
	}
	
	@Test
	void givenResumoComVendedores_whenApurar_thenDeveContarQuantidadeDeVendedores() {
		ResumoDTO resumo = new ResumoDTO();
		resumo.adicionar(Vendedor.builder().cpf("123").nome("João").build());
		resumo.adicionar(Vendedor.builder().cpf("456").nome("Maria").build());
		resumo.adicionar(Vendedor.builder().cpf("123").nome("João").build());
		
		RelatorioDTO relatorio = service.apurar(resumo);
		
		assertEquals(2, relatorio.getQuantidadeVendedor());
	}
	
	@Test
	void givenResumoComVendas_whenApurar_thenRetornaRelatorio() {
		RelatorioDTO relatorio = service.apurar(ResumoMocker.getResumoPadrao());
		
		assertEquals(2, relatorio.getQuantidadeVendedor());
		assertEquals(2, relatorio.getQuantidadeCliente());
		assertEquals(1, relatorio.getVendaMaisCaraId());
		assertEquals(ResumoMocker.NOME_PIOR_VENDEDOR, relatorio.getPiorVendedor());
	}

}
