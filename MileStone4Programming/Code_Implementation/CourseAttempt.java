
public class CourseAttempt {

    private String section;
    private String userRole;
    private String courseProgress;
    private CourseOffering courseOffering;

    public CourseAttempt(CourseOffering courseOffering, String section, String userRole) {
        this.courseProgress = "Planned"; // Future, Past, Current
        this.userRole = String.valueOf(userRole);
        this.courseOffering = courseOffering;
        this.section = section;
    }

    public String getSection() {
        return String.valueOf(this.section);
    }

    public String getUserRole() {
        return String.valueOf(this.userRole);
    }

    public String getCourseProgress() {
        return String.valueOf(this.courseProgress);
    }

    public CourseOffering getCourseOffering() {
        return this.courseOffering;
    }

    public void setCourseProgress(String courseProgress) {
        this.courseProgress = String.valueOf(courseProgress);
    }

    public void setSection(String section) {
        this.section = String.valueOf(section);
    }

    public boolean matchProgress(String progress) {
        if (this.courseProgress == null)
            return false;
        return this.courseProgress.equals(progress);
    }

    public String toString() {
        return courseOffering.toString() 
               + "Section: " + this.getSection() + "\n"
               + "Progress (" + this.getUserRole() + "): " + this.getCourseProgress();
    }

}
