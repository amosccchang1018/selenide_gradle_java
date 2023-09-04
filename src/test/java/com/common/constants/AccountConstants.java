package com.common.constants;

import java.io.IOException;
import java.util.Properties;

public class AccountConstants {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(AccountConstants.class.getClassLoader().getResourceAsStream("account.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String account, String field) {
        return properties.getProperty(account + "." + field);
    }
}
