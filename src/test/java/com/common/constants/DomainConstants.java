package com.constants;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DomainConstants {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = DomainConstants.class.getClassLoader().getResourceAsStream("env.properties");
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load properties", ex);
        }
    }

    public static String getStage() {
        return properties.getProperty("stage");
    }
}
