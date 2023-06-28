package configReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UrlReader {
    //Read Properties file from Config.properties file
    public static Properties getProperties() {
        String userDirectory = System.getProperty("user.dir");
        FileInputStream file = null;
        Properties properties = new Properties();
        try {
            file = new FileInputStream(userDirectory + "/Config.properties");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}