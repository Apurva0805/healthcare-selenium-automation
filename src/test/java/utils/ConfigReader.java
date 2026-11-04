package utils;


import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     ConfigReader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "config.properties file not found");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to load configuration", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Missing configuration property: " + key);
        }

        return value.trim();
    }
}

