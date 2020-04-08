package br.com.southsystem.analisador.dispatcher;

import java.nio.file.Path;
import java.util.Objects;

import br.com.southsystem.analisador.helper.ArquivoHelper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DispatcherConfig {

	private Path path;
	
	public String getNomeArquivoOutput() {
		if (Objects.isNull(path)) {
			return null;
		}
		return ArquivoHelper.finalizarNomeArquivo(this.getPath().getFileName().toString());
	}
	
}
