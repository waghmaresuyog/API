package helper;

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
}