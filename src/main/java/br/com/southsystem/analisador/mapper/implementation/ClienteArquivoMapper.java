package br.com.southsystem.analisador.mapper.implementation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.southsystem.analisador.exception.MapperException;
import br.com.southsystem.analisador.helper.CsvHelper;
import br.com.southsystem.analisador.mapper.Mapper;
import br.com.southsystem.analisador.model.Cliente;

@Component
public class ClienteArquivoMapper implements Mapper<String, Cliente> {

	@Override
	public Cliente mapper(String entrada) {
		if (StringUtils.isEmpty(entrada)) {
			throw new IllegalArgumentException();
		}
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
		CsvSchema schema = csvMapper.schemaFor(Cliente.class).withoutHeader()
				.withColumnSeparator(CsvHelper.DELIMITADOR_CSV);
		try {
			return csvMapper.readerFor(Cliente.class).with(schema).readValue(entrada.substring(4));
		} catch (Exception e) {
			throw new MapperException("Erro ao ler a linha do cliente");
		}
	}

}
