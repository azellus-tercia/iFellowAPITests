package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  Класс для про слушивания проперти файла. с возможностью подгрузки из вне
 *  */
public abstract class Configuration {
    private static final String CONFIGURATION_FILE = "/application.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = Configuration.class.getResourceAsStream(CONFIGURATION_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file" + CONFIGURATION_FILE, e);
        }
    }

    public static String getConfigurationValue(String key) {
        return ((System.getProperty(key) == null) ? properties.getProperty(key) : System.getProperty(key));
    }
}
