import java.util.Scanner;

public class CurrencyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Kart balansını daxil edin (AZN): ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Valyutanı daxil edin (USD): ");
        String currency = scanner.nextLine();


        System.out.print("Əsas məbləği daxil edin (USD): ");
        double amount = scanner.nextDouble();


        double commission = amount * 0.01;
        amount += commission;


        if (currency.equalsIgnoreCase("USD")) {
            double aznAmount = amount * getCurrentExchangeRate();
            if (aznAmount <= balance) {
                balance -= aznAmount;
                System.out.printf("%.2f USD, %.2f AZN olaraq dəyişdirildi. Yeni balans: %.2f AZN\n", amount, aznAmount, balance);
            } else {
                System.out.println("Kifayət qədər balans yoxdur.");
            }
        } else if (currency.equalsIgnoreCase("AZN")) {
            double usdAmount = amount / getCurrentExchangeRate();
            if (usdAmount <= balance) {
                balance -= usdAmount;
                System.out.printf("%.2f AZN, %.2f USD olaraq dəyişdirildi. Yeni balans: %.2f AZN\n", amount, usdAmount, balance);
            } else {
                System.out.println("Kifayət qədər balans yoxdur.");
            }
        } else {
            System.out.println("Düzgün valyuta daxil etməlisiniz (USD və ya AZN).");
        }
    }


    public static double getCurrentExchangeRate() {
        return 1.7;
    }
}
