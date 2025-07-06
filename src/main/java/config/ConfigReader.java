package config;

// 👉 Kelas ini load file `config.properties` dan sediakan method untuk ambil nilai properti.

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static Properties initProperties() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }
}
