package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try{
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String get(String key){
        String value = properties.getProperty(key);
        if (value == null)
            throw new RuntimeException("Key does not present in the config.properties file, Key -> :"+ key);
        return value;
    }

    public static int getInt(String key){
        return Integer.parseInt(key);
    }

    public static boolean getBoolean(String key){
        return Boolean.parseBoolean(key);
    }

}
