package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UrlReader {
    //Read Properties file
    public static Properties getProperties() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        FileInputStream file = new FileInputStream(userDirectory + "/Config.properties");
        Properties properties = new Properties();
        properties.load(file);
        return properties;
    }
    // this method for first Assignment 1 read complete url here
    public static String getUrl() throws IOException {// product list
        return getProperties().getProperty("baseURL");
    }
}
