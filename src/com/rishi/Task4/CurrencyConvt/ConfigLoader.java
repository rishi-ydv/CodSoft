package com.rishi.Task4.CurrencyConvt;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "config.properties";

    public ConfigLoader() {
    }

    public static String loadApiKey() {
        Properties properties = new Properties();

        try {
            FileInputStream input = new FileInputStream("config.properties");

            String var2;
            try {
                properties.load(input);
                var2 = properties.getProperty("API_KEY");
            } catch (Throwable var5) {
                try {
                    input.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            input.close();
            return var2;
        } catch (IOException var6) {
            System.out.println("Error loading API key: " + var6.getMessage());
            return null;
        }
    }
}
