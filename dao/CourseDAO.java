package dao;

import model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCourse(String name, String code) throws SQLException {
        String sql = "INSERT INTO courses (name, code) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, code);
        ps.executeUpdate();
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Course course = new Course(rs.getInt("id"), rs.getString("name"), rs.getString("code"));
            courses.add(course);
        }
        return courses;
    }
}