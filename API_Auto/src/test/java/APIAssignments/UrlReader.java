package APIAssignments;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UrlReader {

    public static Properties getProperties() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        FileInputStream file = new FileInputStream(userDirectory + "/Config.properties");
        Properties properties = new Properties();
        properties.load(file);
        return properties;
    }

    public static String getUrl() throws IOException {// product list
        return getProperties().getProperty("baseURL");
    }
    public  static String postUrl() throws IOException {
        return getProperties().getProperty("endUrl");
    }
    public static String base() throws IOException {
        return getProperties().getProperty("commonURL");
    }
}
