package base;

import java.io.IOException;

import static endPoint.EndPoint.productEndURL;
import static helper.UrlReader.getProperties;

public class Base_URL {
    public static String finalUrl;

    //This is Base url read from properties file return the base url
    public static String productBaseUrl() throws IOException {
        return getProperties().getProperty("UrlBase");
    }

    // combine base url and end point store into finalUrl and return it
    public static String giveUrl() throws IOException {
        finalUrl = productBaseUrl() + productEndURL();
        return finalUrl;
    }
}
