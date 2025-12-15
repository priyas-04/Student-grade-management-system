package view;
import java.util.Scanner;

public class StudentView {
    Scanner scanner = new Scanner(System.in);

    public void showMenu(String name) {
        System.out.println("===== Welcome, " + name + " =====");
        System.out.println("1. View Profile");
        System.out.println("2. View Courses");
        System.out.println("3. View Grades");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }

    public void displayReport(String report) {
        System.out.println(report);
    }
}
