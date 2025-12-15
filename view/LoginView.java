package view;
import java.util.Scanner;

public class LoginView {
    Scanner scanner = new Scanner(System.in);

    public String[] getLoginCredentials() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }
}