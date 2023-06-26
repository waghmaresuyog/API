package endPoint;

import java.io.IOException;

import static endPoint.ServiceEndPoint.productEndURL;
import static configReader.UrlReader.getProperties;

public class ServiceUrl {
    //This is Base url read from properties file return the base url

    public static String productBaseUrl()  {
        String commonUrl;
        try {
            commonUrl= getProperties().getProperty("UrlBase");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return commonUrl;
    }

    public static String getFinalUrl;

    public static String getUrl() {
        getFinalUrl = productBaseUrl() + productEndURL();
        return getFinalUrl;
    }

    public static String finalUrl;

    // combine base url and end point store into finalUrl and return it
    public static String giveUrl() {
        finalUrl = productBaseUrl() + productEndURL();
        return finalUrl;
    }
}
