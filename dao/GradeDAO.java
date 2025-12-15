package dao;

import model.Grade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private Connection conn;

    public GradeDAO(Connection conn) {
        this.conn = conn;
    }

    public void assignGrade(int studentId, int courseId, int marks, String grade) throws SQLException {
        String sql = "INSERT INTO grades (student_id, course_id, marks, grade) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentId);
        ps.setInt(2, courseId);
        ps.setInt(3, marks);
        ps.setString(4, grade);
        ps.executeUpdate();
    }

    public List<Grade> getGradesByStudentId(int studentId) throws SQLException {
        List<Grade> list = new ArrayList<>();
        String sql = "SELECT * FROM grades WHERE student_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Grade grade = new Grade(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getInt("marks"),
                rs.getString("grade")
            );
            list.add(grade);
        }
        return list;
    }
}