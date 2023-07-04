package base;

import static config.UrlReader.productBaseUrl;
import static base.EndPointServiceUrl.productEndURL;

public class FinalServiceUrl {
    // combine base url and end point store into finalUrl and return it
    public static String getFinalUrl() {
        String FinalUrl = productBaseUrl() + productEndURL();
        return FinalUrl;
    }
}