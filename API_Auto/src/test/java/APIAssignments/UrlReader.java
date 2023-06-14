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

    public static String getUrl() throws IOException {
        return getProperties().getProperty("baseURL");
    }

       @BeforeTest
     public void getLoggerDisplay() {
       PropertyConfigurator.configure("log4j2.properties");
     }
}
