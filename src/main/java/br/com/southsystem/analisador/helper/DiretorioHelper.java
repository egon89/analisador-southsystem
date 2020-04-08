package br.com.southsystem.analisador.helper;

import java.io.File;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DiretorioHelper {

	public static final String HOME = System.getProperty("user.home").concat(File.separator);
	public static final String DATA_PATH = HOME.concat("data").concat(File.separator);
	
	public static String getHomeFolder() {
		return HOME;
	}
	
	public static String getInputFolder() {
		return DATA_PATH.concat("in").concat(File.separator);
	}
	
	public static String getOutputFolder() {
		return DATA_PATH.concat("out").concat(File.separator);
	}
	
}
