package br.com.southsystem.analisador.mock;

import java.nio.file.Path;

import br.com.southsystem.analisador.dispatcher.DispatcherConfig;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DispatcherConfigMocker {

	public static final DispatcherConfig ENTIDADE = DispatcherConfig.builder().build();
	
	public static final DispatcherConfig getEntidadeCom(Path path) {
		return DispatcherConfig.builder().path(path).build();
	}
	
}
