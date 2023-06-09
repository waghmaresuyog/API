package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UrlReader {

    //Read Properties file from Config.properties file
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/java/config/Config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String baseUrl() {
        String commonUrl = getProperties().getProperty("UrlBase");
        return commonUrl;
    }
}