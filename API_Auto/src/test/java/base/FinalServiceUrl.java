package base;

import static config.UrlReader.baseUrl;

public class FinalServiceUrl extends EndPointServiceUrl {
    // combine base url and end point store into finalUrl and return it
    public static String getFinalUrl() {
        String FinalUrl = baseUrl() + productEndURL();
        return FinalUrl;
    }

    public static String getBrandsFinalUrl() {
        String FinalUrl = baseUrl() + brandEndUrl();
        return FinalUrl;
    }
}