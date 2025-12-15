import controller.AdminController;
import controller.LoginController;
import controller.StudentController;
import model.User;
import util.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            LoginController loginController = new LoginController(conn);
            
            while (true) {
                User user = loginController.login();

                if (user == null) {
                    System.out.println("Invalid credentials. Try again.\n");
                    continue;
                }

                if (user.getRole().equalsIgnoreCase("admin")) {
                    AdminController adminController = new AdminController(conn);
                    adminController.handleAdminMenu();
                } else if (user.getRole().equalsIgnoreCase("student")) {
                    StudentController studentController = new StudentController(conn, user);
                    studentController.handleStudentMenu();
                } else {
                    System.out.println("Unknown role.");
                }
            }

        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
    }
}