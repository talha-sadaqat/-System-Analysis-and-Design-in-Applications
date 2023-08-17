import java.util.ArrayList;
import java.util.*;

public class Student extends AcademicAssociate {

    private String degree;
    private String major;
    private String minor;

    public Student(CourseCatalog courseCatalog, AcademicAssociate academicAssociate) { // HARD CODED STUDENT
        super (
            academicAssociate.getFirstName(), academicAssociate.getMiddleName(), 
            academicAssociate.getLastName(), academicAssociate.getEmail(), 
            academicAssociate.getID(), academicAssociate.getCourses()
        );

        // Majors and Minors
        String major = "Computer Science";
        String minor = "Mathematics";

        this.degree = "Bachelor of Computing";
        this.major = major;
        this.minor = minor;
    }

    public Student(AcademicAssociate academicAssociate, String degree, String major, String minor) {
        super (
            academicAssociate.getFirstName(), academicAssociate.getMiddleName(), 
            academicAssociate.getLastName(), academicAssociate.getEmail(), 
            academicAssociate.getID(), academicAssociate.getCourses()
        );

        this.degree = String.valueOf(degree);
        this.major = String.valueOf(major);
        this.minor = String.valueOf(minor);
    }

    public String toString() {
        return (
            "firstname: " + this.getFirstName() + "\n"
            + "middleName: " + this.getMiddleName() + "\n"
            + "lastName: " + this.getLastName() + "\n"
            + "email: " + this.getEmail() + "\n"
            + "id: " + this.getID() + "\n"
            + "degree:" + this.getDegree() + "\n" 
            + "major:" + this.getMajor() + "\n" 
            + "minor:" + this.getMinor() + "\n"
        );
    }

    public String getDegree() {
        return String.valueOf(this.degree);
    }

    public String getMajor() {
        return String.valueOf(this.major);
    }

    public String getMinor() {
        return String.valueOf(this.minor);
    }

    public void setDegree(String degree) {
        this.degree = String.valueOf(degree);
    }

    public void setMajor(String major) {
        this.major = String.valueOf(major);
    }

    public void setMinors(String minor) {
        this.minor = String.valueOf(minor);
    }
    
    // Returns true if a course can be planned
    public boolean planCourse(CourseAttempt courseAttempt) {
        if (courseAttempt == null) return false;
       
        ArrayList<CourseAttempt> courses = this.getCourses();
        if (courses == null) courses = new ArrayList<>();

        if (this.courseExists(courseAttempt) == true) return false;
        courseAttempt.setCourseProgress("Planned");

        courses.add(courseAttempt);
        return true;
    }

    // Returns true if a planned course can be removed
    public boolean removePlannedCourse(String fullCourseCode, CourseCatalog courseCatalog) {
        ArrayList<CourseAttempt> courses = this.getCourses();
        if (courses == null) return false;

        Iterator<CourseAttempt> coursesIterator = courses.iterator();
        int index = 0;

        while (coursesIterator.hasNext()) {
            CourseAttempt courseAttempt = coursesIterator.next();
            
            if (courseAttempt.getCourseOffering().equals(courseCatalog, fullCourseCode) == true) {
                
                if (courseAttempt.getCourseProgress().equals("Planned") == false) {
                    return false;
                }

                courses.remove(index);
                return true;
            }

            index++;
        }

        return false;
    }

    // Returns True if a course can be registered
    public boolean registerCourse(String fullCourseCode, CourseCatalog courseCatalog) {       
        ArrayList<CourseAttempt> courses = this.getCourses();
        if (courses == null) return false;

        Iterator<CourseAttempt> coursesIterator = courses.iterator();

        while (coursesIterator.hasNext()) {
            CourseAttempt courseAttempt = coursesIterator.next();
            
            if (courseAttempt.getCourseOffering().equals(courseCatalog, fullCourseCode) == true) {
                
                if (courseAttempt.getCourseProgress().equals("Planned") == false) {
                    return false;
                }

                courseAttempt.setCourseProgress("Registered");
                return courseCatalog.getCourseOfferings().get(fullCourseCode).addStudent(this);
            }
        }

        return false;
    }

    // Returns True if a course can be dropped
    public boolean dropCourse(String fullCourseCode, CourseCatalog courseCatalog) {
        ArrayList<CourseAttempt> courses = this.getCourses();
        if (courses == null) return false;

        Iterator<CourseAttempt> coursesIterator = courses.iterator();
        int index = 0;

        while (coursesIterator.hasNext()) {
            CourseAttempt courseAttempt = coursesIterator.next();
            
            if (courseAttempt.getCourseOffering().equals(courseCatalog, fullCourseCode) == true) {
                
                if (courseAttempt.getCourseProgress().equals("Registered") == false) {
                    return false;
                }

                courses.remove(index);
                return courseCatalog.getCourseOfferings().get(fullCourseCode).removeStudent(this);
            }

            index++;
        }

        return false;
    }
}
