package currency;
import java.util.Map;
import java.util.SortedMap;
import java.util.Scanner;
import java.util.TreeMap;

public class CurrencyConverter {
    static final Map<String, Double> exchangeRateUSD = Map.of(
            "EUR", 0.924959,
            "GBP", 0.771044,
            "INR", 84.079635,
            "CNY", 7.141183
            );
    static final Map<String, Double> exchangeRateEUR = Map.of(
            "USD", 1.081431,
            "GBP", 0.833658,
            "INR", 90.923793,
            "CNY", 7.723366
            );
    static final Map<String, Double> exchangeRateGBP = Map.of(
            "USD", 1.297283,
            "EUR", 1.199694,
            "INR", 109.072865,
            "CNY", 9.264304
            );
    static final Map<String, Double> exchangeRateINR = Map.of(
            "USD", 0.011894,
            "EUR", 0.010996,
            "GBP", 0.009162,
            "CNY", 0.084922
    );
    static final Map<String, Double> exchangeRateCNY = Map.of(
            "USD", 0.140051,
            "EUR", 0.129485,
            "GBP", 0.107901,
            "INR", 11.775593
    );
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currencyCode = inputCurrency(scanner);
        double amountOfMoney;
        while(currencyCode != 6) {
            amountOfMoney = inputAmountOfMoney(scanner);
            switch (currencyCode) {
                case 1:
                    convertAndDisplay(exchangeRateUSD, amountOfMoney);
                    break;
                case 2:
                    convertAndDisplay(exchangeRateEUR, amountOfMoney);
                    break;
                case 3:
                    convertAndDisplay(exchangeRateINR, amountOfMoney);
                    break;
                case 4:
                    convertAndDisplay(exchangeRateGBP, amountOfMoney);
                    break;
                case 5:
                    convertAndDisplay(exchangeRateCNY, amountOfMoney);
                    break;
            }
            currencyCode = inputCurrency(scanner);
        }

    }
    public static int inputCurrency(Scanner scanner) {
        int currencyCode = 0;
        System.out.println("Выбор конвертируемой валюты");
        System.out.println("1. Доллар США (USD)\n" +
                "2. Евро (EUR)\n" +
                "3. Индийская рупия (INR)\n" +
                "4. Фунт cтерлингов Соединенного Королевства (GBP)\n" +
                "5. Китайский юань (CNY)\n");
        System.out.println("Для выхода введите 6");
        while(currencyCode == 0) {
            System.out.println("Введите номер валюты от 1 до 5: ");
            try {
                currencyCode = scanner.nextInt();
                if (currencyCode < 1 || currencyCode > 6) {
                   currencyCode = 0;
                }
            }
            catch (Exception InputMismatchException) {
                scanner.nextLine();
            }
        }
        return currencyCode;
    }
    public static double inputAmountOfMoney(Scanner scanner) {
        double amount = 0;
        while (amount <= 0) {
            System.out.println("Введите сумму для конвертации: ");
            try {
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Сумма должна быть положительной!");
                }
            }
            catch (Exception InputMismatchException) {
                scanner.nextLine();
            }
        }
        return amount;
    }
    public static void convertAndDisplay(Map<String, Double> exchangeRate, double amount) {
        SortedMap<String, Double> sortedExchangeRate = new TreeMap<String, Double>(exchangeRate);
        double convertedAmount;
        for(String currency: sortedExchangeRate.keySet()) {
            convertedAmount = sortedExchangeRate.get(currency) * amount;
            System.out.println(currency + ": " + convertedAmount + "\n");
        }
    }
}
