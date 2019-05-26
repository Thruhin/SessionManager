package com.session.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.session.resources.Constants;

public class Configuration {
	
	private static Properties configProperties = getConfigProperties(Constants.CONFIG_FILE);
	
	
	public static Properties getConfigProperties(String propertyFileName) {
		if (configProperties == null) {
			configProperties = new Properties();
			try (InputStream resourceStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(propertyFileName)) {
				configProperties.load(resourceStream);
			} catch (IOException e) {
			}
		}
		return configProperties;
	}
	
	
	public static String getProperty(String key, String defaultValue){
		return configProperties.getProperty(key, defaultValue);
	}
	
	public static String getProperty(String key){
		return getProperty(key, "");
	}

}
