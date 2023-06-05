package postAllProduct;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Reader {
    public static Properties getProperties() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        FileInputStream file = new FileInputStream(userDirectory + "/Config.properties");
        Properties properties = new Properties();
        properties.load(file);
        return properties;
    }


    public static String getUrl() throws IOException {
        return getProperties().getProperty("baseURL");
    }
}
