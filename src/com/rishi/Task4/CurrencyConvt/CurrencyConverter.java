package com.rishi.Task4.CurrencyConvt;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_KEY = ConfigLoader.loadApiKey();
    private static final String API_URL;

    public CurrencyConverter() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean convertMore = true;
        System.out.println("Welcome to the Currency Converter!");

        while(convertMore) {
            System.out.print("Enter the base currency (e.g., INR): ");
            String baseCurrency = scanner.nextLine().toUpperCase();
            System.out.print("Enter the target currency (e.g., NPR): ");
            String targetCurrency = scanner.nextLine().toUpperCase();
            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            try {
                double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
                double convertedAmount = amount * exchangeRate;
                System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
            } catch (Exception var11) {
                System.out.println("Error fetching the exchange rate. Please try again.");
            }

            System.out.print("Do you want to convert another currency? (yes/no): ");
            scanner.nextLine();
            String userResponse = scanner.nextLine().toLowerCase();
            convertMore = userResponse.equals("yes");
        }

        System.out.println("Thank you for using the Currency Converter! Goodbye!");
        scanner.close();
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + baseCurrency + "/" + targetCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Failed to fetch exchange rate.");
        } else {
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder inline = new StringBuilder();

            while(scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            scanner.close();
            JSONObject jsonResponse = new JSONObject(inline.toString());
            return jsonResponse.getDouble("conversion_rate");
        }
    }

    static {
        API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";
    }
}