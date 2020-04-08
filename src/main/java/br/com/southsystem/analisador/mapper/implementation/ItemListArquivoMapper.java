package br.com.southsystem.analisador.mapper.implementation;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.southsystem.analisador.exception.MapperException;
import br.com.southsystem.analisador.helper.CsvHelper;
import br.com.southsystem.analisador.mapper.Mapper;
import br.com.southsystem.analisador.model.Item;

@Component
public class ItemListArquivoMapper implements Mapper<String, List<Item>> {

	private ItemArquivoMapper itemArquivoMapper;
	
	@Autowired
	public ItemListArquivoMapper(ItemArquivoMapper itemArquivoMapper) {
		this.itemArquivoMapper = itemArquivoMapper;
	}

	@Override
	public List<Item> mapper(String entrada) {
		if (StringUtils.isEmpty(entrada)) {
			throw new IllegalArgumentException();
		}
		List<Item> itens = new ArrayList<>();
		StringReader reader = new StringReader(entrada.replace("[", "").replace("]", ""));
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(CsvHelper.DELIMITADOR_CSV_ITEM_LISTA);
		try {
			MappingIterator<String> linhas = csvMapper.readerFor(String.class).with(schema).readValues(reader);
			linhas.forEachRemaining(linha -> itens.add(itemArquivoMapper.mapper(linha)));
		} catch (Exception e) {
			throw new MapperException("Erro ao ler a lista de itens da venda");
		}
		
		return itens;
	}

}
