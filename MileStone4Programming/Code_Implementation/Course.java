public class Course {

    private String courseID;
    private int courseCode;
    private String courseName;

    public Course(String courseID, int courseCode, String courseName) {
        this.courseID = String.valueOf(courseID);
        this.courseCode = courseCode;
        this.courseName = String.valueOf(courseName);
    }

    public Course(Course course) {
        this.courseID = course.getCourseID();
        this.courseCode = course.getCourseCode();
        this.courseName = course.getCourseName();
    }

    public String toString() {
        return (
            "courseID: " + this.getCourseID() + "\n" +
            "courseCode: " + this.getCourseCode() + "\n" +
            "courseName: " + this.getCourseName() + "\n"
        );
    }

    public String getCourseID() {
        return String.valueOf(this.courseID);
    }

    public int getCourseCode() {
        return this.courseCode;
    }

    public String getCourseName() {
        return String.valueOf(this.courseName);
    }

    public boolean equals(Course course) {
        if (this.getCourseID().equals(course.getCourseID()) == false) {
            return false;
        }

        if (this.getCourseCode() != course.getCourseCode()) {
            return false;
        }

        return true;
    }

    public boolean equals(CourseCatalog courseCatalog, String fullCourseCode) {
        String currentFullCourseCode = courseCatalog.getFullCourseID(this.getCourseID(), this.getCourseCode());
        
        if (currentFullCourseCode.equals(fullCourseCode) == false) {
            return false;
        }

        if (this.getCourseCode() != courseCode) {
            return false;
        }

        return true;
    }
}
