package endPoint;

import static endPoint.ServiceEndPointUrl.productEndURL;
import static configReader.UrlReader.getProperties;

public class ServiceUrl {

    //This is Base url read from properties file return the base url
    public static String productBaseUrl() {
        String commonUrl = getProperties().getProperty("UrlBase");
        return commonUrl;
    }

    // combine base url and end point store into finalUrl and return it
    public static String url() {
        String getFinalUrl = productBaseUrl() + productEndURL();
        return getFinalUrl;
    }
}