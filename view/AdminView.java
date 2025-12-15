package view;
import java.util.Scanner;

public class AdminView {
    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("===== Welcome Admin =====");
        System.out.println("1. Register New Student");
        System.out.println("2. Add Course");
        System.out.println("3. Assign Grades");
        System.out.println("4. View Student Reports");
        System.out.println("5. Top Performers");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
    }
}