package config;

public class SystemPropertyProvider {
    private static final String BASE_URL = "https://api.nationalize.io/";

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
