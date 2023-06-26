package endPoint;

import java.io.IOException;

import static endPoint.ServiceEndPoint.productEndURL;
import static configReader.UrlReader.getProperties;

public class ServiceUrl {
    //This is Base url read from properties file return the base url

    public static String productBaseUrl() throws IOException {

        return getProperties().getProperty("UrlBase");
    }

    public static String getFinalUrl;

    public static String getUrl() {
        try {
            getFinalUrl = productBaseUrl() + productEndURL();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getFinalUrl;
    }

    public static String finalUrl;

    // combine base url and end point store into finalUrl and return it
    public static String giveUrl() {
        try {
            finalUrl = productBaseUrl() + productEndURL();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalUrl;
    }
}
