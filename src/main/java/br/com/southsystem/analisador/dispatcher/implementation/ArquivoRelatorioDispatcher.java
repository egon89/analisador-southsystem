package br.com.southsystem.analisador.dispatcher.implementation;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.southsystem.analisador.dispatcher.DispatcherConfig;
import br.com.southsystem.analisador.dispatcher.RelatorioDispatcher;
import br.com.southsystem.analisador.dto.RelatorioDTO;
import br.com.southsystem.analisador.helper.CsvHelper;
import br.com.southsystem.analisador.helper.DiretorioHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArquivoRelatorioDispatcher implements RelatorioDispatcher {

	@Override
	public void enviar(RelatorioDTO relatorio, DispatcherConfig config) {
		String arquivoOutputPath = DiretorioHelper.getOutputFolder().concat(config.getNomeArquivoOutput());
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
		CsvSchema schema = csvMapper.schemaFor(RelatorioDTO.class).withHeader()
				.withColumnSeparator(CsvHelper.DELIMITADOR_CSV_OUTPUT);
		try {
			Files.write(Paths.get(arquivoOutputPath),
					csvMapper.writer(schema).writeValueAsBytes(relatorio));
			log.info("Relatório enviado: {}", arquivoOutputPath);
		} catch (Exception e) {
			log.error("Problema ao enviar o relatório: {}", relatorio);
		}

	}
	
}
