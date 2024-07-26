package com.automation.highrewards.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader(String configFilePath) {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Config file not found: " + configFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + configFilePath);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

