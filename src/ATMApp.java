import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ATMApp {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Double> cashCodes = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers(); // Kullanıcı verilerini başlat

        Scanner scanner = new Scanner(System.in);

        System.out.print("Kart Nomresi: ");
        String cardNumber = scanner.nextLine();

        System.out.print("PIN: ");
        String pin = scanner.nextLine();


        if (authenticateUser(cardNumber, pin)) {
            User user = users.get(cardNumber);

            System.out.println("Hoş geldiniz, " + user.getName() + "!");
            System.out.println("Seçenekler: ");
            System.out.println("1. Karttan Karta");
            System.out.println("2. Bakiyeyi Göster");
            System.out.println("3. Nakit Kodu ile Para Çekme");
            System.out.println("4. Döviz Bozdurma");

            System.out.print("Bir secim secin: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    performCardToCard(user);
                    break;
                case 2:
                    showBalance(user);
                    break;
                case 3:
                    performCashByCode(user);
                    break;
                case 4:
                    performExchange(user);
                    break;
                default:
                    System.out.println("Geçersiz secim.");
                    break;
            }
        } else {
            System.out.println("Geçersiz kart numarası veya PIN.");
        }
    }

    private static void performCardToCard(User user) {

        System.out.println(user.getName() );

        System.out.println(  + user.getBalance());
    }

    private static void showBalance(User user) {
        System.out.println("Kullanıcı: " + user.getName());
        System.out.println("Bakiye: " + user.getBalance());
    }

    private static void performCashByCode(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nakit Kodu ile Para Çekme Seçenekleri: ");
        System.out.println("1. Nakit kodu oluştur");
        System.out.println("2. Kod ile tutarı al");

        System.out.print("Bir seçenek seçin: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                generateCashCode(user);
                break;
            case 2:
                getAmountByCode(user);
                break;
            default:
                System.out.println("Geçersiz seçenek.");
                break;
        }
    }

    private static void generateCashCode(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tutarı girin: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        String cashCode = generateUniqueCashCode();
        cashCodes.put(cashCode, amount);

        user.reduceBalance(amount);

        System.out.println("Nakit kodu oluşturuldu: " + cashCode);
        System.out.println("Son bakiye: " + user.getBalance());
    }

    private static void getAmountByCode(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nakit kodunu girin: ");
        String cashCode = scanner.nextLine();

        if (cashCodes.containsKey(cashCode)) {
            double amount = cashCodes.get(cashCode);
            user.topUpBalance(amount);

            System.out.println("Bakiyenize " + amount + " TL eklendi.");
            System.out.println("Son bakiye: " + user.getBalance());
        } else {
            System.out.println("Geçersiz nakit kodu.");
        }
    }

    private static boolean authenticateUser(String cardNumber, String pin) {
        if (users.containsKey(cardNumber)) {
            User user = users.get(cardNumber);
            return user.getPin().equals(pin) && user.getStatus().equals("active");
        }
        return false;
    }

    private static String generateUniqueCashCode() {
        return UUID.randomUUID().toString();
    }

    private static void performExchange(User user) {
        // Döviz bozdurma işlemi
        System.out.println(user.getName() + " için Döviz Bozdurma işlemi yapılıyor.");
    }

    private static void initializeUsers() {
        User user1 = new User("1234567890123456", "1234", "John Doe", "active", 500.0);
        User user2 = new User("9876543210987654", "4321", "Jane Smith", "active", 1000.0);
        User user3 = new User("1111222233334444", "5678", "David Johnson", "blocked", 2500.0);

        users.put(user1.getCardNumber(), user1);
        users.put(user2.getCardNumber(), user2);
        users.put(user3.getCardNumber(), user3);
    }
}

class User {
    private String cardNumber;
    private String pin;
    private String name;
    private String status;
    private double balance;

    public User(String cardNumber, String pin, String name, String status, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.name = name;
        this.status = status;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public double getBalance() {
        return balance;
    }

    public void reduceBalance(double amount) {
        balance -= amount;
    }

    public void topUpBalance(double amount) {
        balance += amount;
    }

}
