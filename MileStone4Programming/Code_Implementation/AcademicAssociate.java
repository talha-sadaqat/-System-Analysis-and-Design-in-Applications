import java.util.ArrayList;
import java.util.*;
import java.util.Random;


public class AcademicAssociate {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private int id;
    private ArrayList<CourseAttempt> courses;

    public AcademicAssociate(CourseCatalog courseCatalog) { // Hard coded academic associate
        this.id = 1234567;
        this.firstName = "John";
        this.middleName = "Ray";
        this.lastName = "Doe";
        this.email = "JohnRDoe@uoguelph.ca";
        this.courses = getHardCodedCourses(courseCatalog);

        
    
    }

    public AcademicAssociate(String firstName, String middleName,
            String lastName, String email,
            int id, ArrayList<CourseAttempt> courses) {

        this.id = id;
        this.firstName = String.valueOf(firstName);
        this.middleName = String.valueOf(middleName);
        this.lastName = String.valueOf(lastName);
        this.email = String.valueOf(email);
        this.courses = courses;
    }

    public AcademicAssociate(CourseCatalog courseCatalog, AcademicAssociate academicAssociate) {
        String firstName = academicAssociate.getFirstName();
        String middleName = academicAssociate.getMiddleName();
        String lastName = academicAssociate.getLastName();
        String email = academicAssociate.getEmail();
        int id = academicAssociate.getID();
        ArrayList<CourseAttempt> courses = academicAssociate.getCourses();
    }

    public String toString() {
        return ("\nfirstname: " + this.getFirstName() + "\n"
                + "middleName: " + this.getMiddleName() + "\n"
                + "lastName: " + this.getLastName() + "\n"
                + "email: " + this.getEmail() + "\n"
                + "id: " + this.getID() + "\n");
    }

    public String getFirstName() {
        return String.valueOf(this.firstName);
    }

    public String getMiddleName() {
        return String.valueOf(this.middleName);
    }

    public String getLastName() {
        return String.valueOf(this.lastName);
    }

    public String getFullName() {
        return ((this.getHasPHD() ? "Dr. " : "") +
                this.getFirstName() + " " +
                this.getMiddleName() + " " +
                this.getLastName());

    }

    public String getEmail() {
        return String.valueOf(this.email);
    }

    public int getID() {
        return this.id;
    }

    public boolean getHasPHD() {
        return false;
    }

    public ArrayList<CourseAttempt> getCourses() {
        return this.courses;
    }

    public void setFirstName(String firstName) {
        this.firstName = String.valueOf(firstName);
    }

    public void setMiddleName(String middleName) {
        this.middleName = String.valueOf(middleName);
    }

    public void setLastName(String lastName) {
        this.lastName = String.valueOf(lastName);
    }

    public void setEmail(String email) {
        this.email = String.valueOf(email);
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setCourses(ArrayList<CourseAttempt> courses) {
        this.courses = courses;
    }

    // if this doesn't work double check
    public boolean courseExists(CourseAttempt courseAttempt) {
        if (this.getCourses() == null || courseAttempt == null) return false;

        Course course = courseAttempt.getCourseOffering();
        Iterator<CourseAttempt> coursesIterator = this.getCourses().iterator();

        while (coursesIterator.hasNext()) {
            Course currentCourse = coursesIterator.next().getCourseOffering();
            if (currentCourse.equals(course)) return true;
        }

        return false;
    }

    // protected CourseAttempt retrieveAttempt(String courseID, int courseCode) {
    // if (this.courses == null) return null;

    // Iterator<CourseAttempt> coursesIterator = this.getCourses().iterator();

    // while (coursesIterator.hasNext()) {
    // Course currentCourse = coursesIterator.next();
    // if (currentCourse.getCourseOffering().getCourse().equals(courseID,
    // courseCode)) return currentCourse;
    // }

    // return null;
    // }

    // protected void removeAttempt(String courseID, int courseCode) {
    // if (this.getCourses() == null) return;

    // Iterator<CourseAttempt> coursesIterator = this.getCourses().iterator();

    // while (coursesIterator.hasNext()) {
    // int index = coursesIterator.nextIndex();
    // Course currentCourse =
    // coursesIterator.next().getCourseOffering().getCourse();

    // if (currentCourse.equals(courseID, courseCode)) {
    // this.getCourses().remove(index);
    // break;
    // }
    // }
    // }

    public ArrayList<Student> generateTAs(CourseCatalog courseCatalog, int numTas) {

        ArrayList<Student> teachingAssistants = new ArrayList<Student>();
        Random random = new Random();

        for (int i = 0; i < numTas; i++) {
            int randomNum = random.nextInt(50) + 1;
            String firstName = courseCatalog.getFirstName(randomNum);
            String lastName = courseCatalog.getLastName(randomNum);
            String middleName = courseCatalog.getMiddleName(randomNum);
            String email = courseCatalog.generateEmail(firstName, lastName);
            int id = 1000000 + random.nextInt(99);

            AcademicAssociate academicAssociate = new AcademicAssociate(
                firstName, middleName, lastName, email, id, new ArrayList<CourseAttempt>() 
            );

            Student student = new Student(
                academicAssociate, "Bachelor of Computing", "Computer Science", "Mathematics" 
            );

            teachingAssistants.add(student);
        }

        return teachingAssistants;
    }

    public ArrayList<CourseAttempt> getHardCodedCourses(CourseCatalog courseCatalog) {
        ArrayList<CourseAttempt> courses = new ArrayList<CourseAttempt>();

        String[] courseIDs = {
                "CIS", "CIS", "MATH", "MATH", "UNIV",
                "CIS", "CIS", "ECON", "MATH", "STAT",
                "CIS", "CIS", "CIS", "MATH", "MATH",
                "CIS", "CIS",
        };

        int[] courseCodes = {
                1300, 1910, 1160, 1200, 1200,
                2500, 2910, 1050, 1210, 2040,
                2030, 2430, 2520, 2000, 2200,
                2750, 3110,
        };

        String[] semesters = {
                "F2023", "F2023", "F2023", "F2021", "F2021",
                "W2022", "W2022", "W2022", "W2022", "W2022",
                "F2022", "F2022", "F2022", "F2022", "F2022",
                "W2023", "W2023",
        };

        String[] courseProgress = {
                "Planned", "Planned", "Planned", "Completed", "Completed",
                "Completed", "Completed", "Completed", "Completed", "Completed",
                "Completed", "Completed", "Completed", "Completed", "Completed",
                "Registered", "Planned",
        };
        
        for (int i = 0; i < courseIDs.length; i++) {
            ArrayList<Student> teachingAssistants = generateTAs(courseCatalog, 5);
            Course course = courseCatalog.getCourse(courseIDs[i], courseCodes[i]);
            CourseOffering courseOffering = new CourseOffering(course, semesters[i], new ArrayList<String>(), new ArrayList<Student>(), teachingAssistants, teachingAssistants);
            CourseAttempt courseAttempt = new CourseAttempt(courseOffering, "0101", "Student");
            courseAttempt.setCourseProgress(courseProgress[i]);
            courses.add(courseAttempt);
        }

        return courses;
    }


}