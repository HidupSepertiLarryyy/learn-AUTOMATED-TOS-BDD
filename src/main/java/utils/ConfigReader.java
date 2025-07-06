package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ✅ ConfigReader ➜ Load data dari config.properties
 * - Centralize URL, credentials, base paths dll
 */
public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            // ✅ Path ke config.properties ➜ di folder resources
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load config.properties file", e);
        }
    }

    /**
     * ✅ Method untuk ambil value by key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
