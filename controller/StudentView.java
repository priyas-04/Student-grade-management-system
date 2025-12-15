package view;

public class StudentView {

    public void showMenu(String name) {
        System.out.println("\n===== Welcome, " + name + " =====");
        System.out.println("1. View Profile");
        System.out.println("2. View Courses");
        System.out.println("3. View Grades");
        System.out.println("4. Logout");
    }

    public void showProfile(model.Student student) {
        System.out.println("\n--- Student Profile ---");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Department: " + student.getDepartment());
    }

    public void showCourses(java.util.List<model.Course> courses) {
        System.out.println("\n--- Enrolled Courses ---");
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (model.Course c : courses) {
                System.out.println(c.getCourseId() + " - " + c.getCourseName());
            }
        }
    }

    public void showGrades(java.util.List<model.Grade> grades) {
        System.out.println("\n--- Grades ---");
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            for (model.Grade g : grades) {
                System.out.println("Course: " + g.getCourseName() + " | Marks: " + g.getMarks() + " | Grade: " + g.getGrade());
            }
        }
    }
}
