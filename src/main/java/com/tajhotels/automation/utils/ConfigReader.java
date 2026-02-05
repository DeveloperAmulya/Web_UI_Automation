package com.tajhotels.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties props = new Properties();

    private ConfigReader() {}

    static {
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found in src/test/resources");
            }

            props.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    public static String get(String key) {
        // Priority: Maven/System property overrides config.properties
        String sysVal = System.getProperty(key);
        if (sysVal != null && !sysVal.trim().isEmpty()) {
            return sysVal.trim();
        }
        return props.getProperty(key);
    }
}