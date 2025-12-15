package controller;

import dao.*;
import model.*;
import view.AdminView;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private Connection conn;
    private AdminView adminView;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private GradeDAO gradeDAO;
    private Scanner scanner;

    public AdminController(Connection conn) {
        this.conn = conn;
        this.adminView = new AdminView();
        this.studentDAO = new StudentDAO(conn);
        this.courseDAO = new CourseDAO(conn);
        this.gradeDAO = new GradeDAO(conn);
        this.scanner = new Scanner(System.in);
    }

    public void handleAdminMenu() {
        while (true) {
            adminView.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> addCourse();
                case 3 -> assignGrades();
                case 4 -> System.out.println("Feature to view student reports (to be added)");
                case 5 -> System.out.println("Feature to view top performers (to be added)");
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void registerStudent() {
        try {
            System.out.print("Enter student username: ");
            String username = scanner.nextLine();
            System.out.print("Enter student password: ");
            String password = scanner.nextLine();

            UserDAO userDAO = new UserDAO(conn);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, "student");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) userId = rs.getInt(1);

            System.out.print("Enter full name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            studentDAO.registerStudent(name, email, userId);
            System.out.println("Student registered successfully!");
        } catch (SQLException e) {
            System.out.println("Error registering student: " + e.getMessage());
        }
    }

    private void addCourse() {
        try {
            System.out.print("Enter course name: ");
            String name = scanner.nextLine();
            System.out.print("Enter course code: ");
            String code = scanner.nextLine();
            courseDAO.addCourse(name, code);
            System.out.println("Course added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private void assignGrades() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = scanner.nextInt();
            System.out.print("Enter course ID: ");
            int courseId = scanner.nextInt();
            System.out.print("Enter marks: ");
            int marks = scanner.nextInt();
            scanner.nextLine(); 

            String grade;
            if (marks >= 90) grade = "A+";
            else if (marks >= 80) grade = "A";
            else if (marks >= 70) grade = "B";
            else if (marks >= 60) grade = "C";
            else grade = "F";

            gradeDAO.assignGrade(studentId, courseId, marks, grade);
            System.out.println("Grade assigned successfully!");
        } catch (SQLException e) {
            System.out.println("Error assigning grade: " + e.getMessage());
        }
    }
}