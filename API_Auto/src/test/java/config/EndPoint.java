package config;

import java.io.IOException;

public class EndPoint extends UrlReader {
    //This is Base url read from properties file return the base url
    public static String productBaseUrl() throws IOException {
        return getProperties().getProperty("UrlBase");
    }

    //This is End url read from properties file and return the end url
    public static String productEndURL() throws IOException {
        return getProperties().getProperty("endUrl");
    }
}
