package endPoint;

import helper.UrlReader;

import java.io.IOException;

public class EndPoint extends UrlReader {
    //This is End url read from properties file and return the end url
    public static String productEndURL() throws IOException {
        return getProperties().getProperty("endUrl");
    }
}
