package br.com.southsystem.analisador.mapper.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.southsystem.analisador.dto.VendaCsvDTO;
import br.com.southsystem.analisador.exception.MapperException;
import br.com.southsystem.analisador.helper.CsvHelper;
import br.com.southsystem.analisador.mapper.Mapper;
import br.com.southsystem.analisador.model.Item;
import br.com.southsystem.analisador.model.Venda;
import br.com.southsystem.analisador.model.Vendedor;

@Component
public class VendaArquivoMapper implements Mapper<String, Venda> {

	private ItemListArquivoMapper itemListArquivoMapper;
	
	@Autowired
	public VendaArquivoMapper(ItemListArquivoMapper itemListArquivoMapper) {
		this.itemListArquivoMapper = itemListArquivoMapper;
	}

	@Override
	public Venda mapper(String entrada) {
		if (StringUtils.isEmpty(entrada)) {
			throw new IllegalArgumentException();
		}
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
		CsvSchema schema = CsvSchema.builder()
				.addColumn("id")
				.addColumn("descricaoItens")
				.addColumn("vendedor")
				.setColumnSeparator(CsvHelper.DELIMITADOR_CSV)
				.build();
		try {
			VendaCsvDTO dto = csvMapper.readerFor(VendaCsvDTO.class).with(schema).readValue(entrada.substring(4));
			List<Item> itens = itemListArquivoMapper.mapper(dto.getDescricaoItens());
			return Venda.builder()
				.id(dto.getId())
				.vendedor(Vendedor.builder().nome(dto.getVendedor()).build())
				.itens(itens)
				.build();
		} catch (Exception e) {
			throw new MapperException("Linha de venda: "+ e.getMessage());
		}
	}

}
