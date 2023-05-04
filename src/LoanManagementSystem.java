import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoanManagementSystem {
    private static Map<String, String> loanApplications = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tam adınızı girin: ");
        String fullName = scanner.nextLine();

        System.out.print("Doğum tarihinizi girin: ");
        String birthDate = scanner.nextLine();

        System.out.print("Kredi miktarını girin: ");
        double loanAmount = scanner.nextDouble();

        System.out.print("Kredi süresini girin (ay cinsinden): ");
        int loanMonths = scanner.nextInt();
        scanner.nextLine();


        String selectedBank = selectBank(loanAmount);


        if (!isApplicationPresent(fullName, birthDate, selectedBank)) {

            addLoanApplication(fullName, birthDate, selectedBank);
            System.out.println("Başvurunuz onaylandı.");
        } else {
            throw new RuntimeException("Bu başvuru zaten mevcut. Hata!");
        }
    }

    private static String selectBank(double loanAmount) {
        String selectedBank = "";

        if (loanAmount < 500) {
            System.out.println("Önerilen bankalar: VTB Bank, Bank Respublika, Express Bank");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bir banka seçin: ");
            selectedBank = scanner.nextLine();
        } else if (loanAmount >= 500 && loanAmount <= 1000) {
            System.out.println("Önerilen bankalar: Tabita Bank, Azerturk Bank, Xalq Bank");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bir banka seçin: ");
            selectedBank = scanner.nextLine();
        } else if (loanAmount > 1000 && loanAmount <= 2000) {
            System.out.println("Önerilen bankalar: Kapital Bank, Pasha Bank, Ibar Bank");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bir banka seçin: ");
            selectedBank = scanner.nextLine();
        } else if (loanAmount > 2000 && loanAmount <= 3000) {
            System.out.println("Önerilen bankalar: Kapital Bank, Pasha Bank, Ibar Bank");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bir banka seçin: ");
            selectedBank = scanner.nextLine();
        } else {
            System.out.println("Geçersiz kredi miktarı.");
        }

        return selectedBank;
    }

    private static boolean isApplicationPresent(String fullName, String birthDate, String selectedBank) {
        String applicationKey = fullName + "-" + birthDate + "-" + selectedBank;
        return loanApplications.containsKey(applicationKey);
    }

    private static void addLoanApplication(String fullName, String birthDate, String selectedBank) {
        String applicationKey = fullName + "-" + birthDate + "-" + selectedBank;
        loanApplications.put(applicationKey, selectedBank);
    }
}

}
