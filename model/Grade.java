package model;

public class Grade {
    
    private int id;
    private int studentId;
    private int courseId;
    private int marks;
    private String grade;

    public Grade(int id, int studentId, int courseId, int marks, String grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }
    
    public int getMarks() {
        return marks;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
