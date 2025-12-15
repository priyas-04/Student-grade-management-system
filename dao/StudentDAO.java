package dao;

import model.Student;
import java.sql.*;

public class StudentDAO {
    private final Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    // Get student details by linked user ID
    public Student getStudentByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM students WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            userId
                    );
                }
            }
        }
        return null; // No student found
    }

    // Register a new student
    public void registerStudent(String name, String email, int userId) throws SQLException {
        // Optional: check if student already exists
        if (getStudentByUserId(userId) != null) {
            throw new SQLException("Student already registered for this user ID: " + userId);
        }

        String sql = "INSERT INTO students (name, email, user_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, userId);
            ps.executeUpdate();
        }
    }
}
