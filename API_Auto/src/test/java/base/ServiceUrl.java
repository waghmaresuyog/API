package base;

import java.io.IOException;

import static endPoint.EndPoint.productEndURL;
import static helper.UrlReader.getProperties;

public class ServiceUrl {
    //This is Base url read from properties file return the base url
    public static String productBaseUrl() throws IOException {
        return getProperties().getProperty("UrlBase");
    }

    public static String finalUrl;

    // combine base url and end point store into finalUrl and return it
    public static String giveUrl() throws IOException {
        finalUrl = productBaseUrl() + productEndURL();
        return finalUrl;
    }


}
