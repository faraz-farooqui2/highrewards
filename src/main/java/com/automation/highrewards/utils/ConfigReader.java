package com.automation.highrewards.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    // Static block to load properties file
    static {
        String configFilePath = "config.properties"; // Default path
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(configFilePath)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Config file not found: " + configFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + configFilePath, e);
        }
    }

    // Method to get property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
