import java.util.ArrayList;
//import java.util.*;

public class CourseOffering extends Course {

    private String semester;
    private ArrayList<String> sections;
    private ArrayList<Student> students;
    private ArrayList<Student> teachingAssistantApplications;
    private ArrayList<Student> teachingAssistants;

    public CourseOffering(String courseID, int courseCode, String courseName, 
                          String semester, ArrayList<String> sections, ArrayList<Student> students) {

        super(courseID, courseCode, courseName);
        this.semester = String.valueOf(semester);
        this.sections = sections;
        this.students = students;
        this.teachingAssistantApplications = new ArrayList<Student>();
        this.teachingAssistants = new ArrayList<Student>();
    }

    public CourseOffering(Course course, String semester,
                        ArrayList<String> sections, ArrayList<Student> students,
                        ArrayList<Student> teachingAssistantApplications,
                        ArrayList<Student> teachingAssistants) {

        super(course.getCourseID(), course.getCourseCode(), course.getCourseName());
        this.semester = String.valueOf(semester);
        this.sections = sections;
        this.students = students;
        this.teachingAssistantApplications = teachingAssistantApplications;
        this.teachingAssistants = teachingAssistants;
    }

    public CourseOffering(String courseID, int courseCode, String courseName, String semester,
                          ArrayList<String> sections, ArrayList<Student> students,
                          ArrayList<Student> teachingAssistantApplications,
                          ArrayList<Student> teachingAssistants) {

        super(courseID, courseCode, courseName);
        this.semester = String.valueOf(semester);
        this.sections = sections;
        this.students = students;
        this.teachingAssistantApplications = teachingAssistantApplications;
        this.teachingAssistants = teachingAssistants;
    }

    public CourseOffering(String courseID, int courseCode, String courseName, String semester) {
        super(courseID, courseCode, courseName);
        this.semester = String.valueOf(semester);
        this.sections = null;
        this.students = null;
        this.teachingAssistantApplications = null;
        this.teachingAssistants = null;
    }

    public String toString() {
        String output = "";

        output += (
            "courseID: " + this.getCourseID() + "\n" +
            "courseCode: " + this.getCourseCode() + "\n" +
            "courseName: " + this.getCourseName() + "\n" +
            "semester: " + this.getSemester() + "\n"
        );

        /*Iterator<Student> studentIterator = this.getStudents().iterator();

        while (studentIterator.hasNext()) {
            output += studentIterator.next().toString();
        }*/

        return output;
    }

    public String getSemester() {
        return String.valueOf(this.semester);
    }
    
    // Returns the list of sections
    public ArrayList<String> getSections() {
        return this.sections;
    }

    // Returns a list of students
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public ArrayList<Student> getTeachingAssistantApplications() {
        return this.teachingAssistantApplications;
    }

    public ArrayList<Student> getTeachingAssistants() {
        return this.teachingAssistants;
    }

    public void setSections(ArrayList<String> sections) {
        this.sections = sections;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    public void setTeachingAssistantApplications(ArrayList<Student> teachingAssistantApplications) {
        this.teachingAssistantApplications = teachingAssistantApplications;
    }

    public void setTeachingAssistants(ArrayList<Student> teachingAssistants) {
        this.teachingAssistants = teachingAssistants;
    }

    public boolean studentInClass(int id) {
        ArrayList<Student> students = this.getStudents();
        if (students == null) return false;

        return students.stream().anyMatch(current -> current.getID() == id);
    }
    
    public boolean teachingAssistantApplicationInClass(int id) {
        ArrayList<Student> TA_Applications = this.getTeachingAssistantApplications();
        if (TA_Applications == null) return false;

        return TA_Applications.stream().anyMatch(current -> current.getID() == id);
    }
    
    public boolean teachingAssistantInClass(int id) {
        ArrayList<Student> TAs = this.getTeachingAssistants();
        if (TAs == null) return false;

        return TAs.stream().anyMatch(current -> current.getID() == id);
    }

    public boolean addStudent(Student student) {
        if (student == null) return false;
        if (this.getStudents() == null) this.setStudents(new ArrayList<Student>());

        int studentID = student.getID();

        if (this.studentInClass(studentID) == true) return false;
        else this.students.add(student);

        return true;
    }

    public boolean removeStudent(Student student) {
        if (student == null) return false;
        if (this.getStudents() == null) return false;

        this.getStudents().remove(student);
        return true;
    }

    public boolean addTeachingAssistantApplication(Student newTAApplication) {
        if (newTAApplication == null) return false;
        
        if (this.getTeachingAssistantApplications() == null) {
            this.setTeachingAssistantApplications(new ArrayList<Student>());
        }

        int studentID = newTAApplication.getID();
        if (this.teachingAssistantApplicationInClass(studentID)) return false;

        this.getTeachingAssistantApplications().add(newTAApplication);

        return true;
    }

    public boolean addTeachingAssistant(Student newTA) {
        if (newTA == null) return false;

        if (this.getTeachingAssistants() == null) {
            this.setTeachingAssistants(new ArrayList<Student>());
        }

        int studentID = newTA.getID();
        if (this.teachingAssistantApplicationInClass(studentID) == false) return false;
        if (this.teachingAssistantInClass(studentID)) return false;

        this.removeTAApplication(studentID);
        this.getTeachingAssistants().add(newTA);

        return true;
    }

    public boolean isFutureCourse(String currentSemester) {
        String thisYear = this.getSemester().replace("F", "").trim();
        thisYear = thisYear.replace("W", "").trim();
        thisYear = thisYear.replace("S", "").trim();

        String currentYear = currentSemester.replace("F", "").trim();
        currentYear = currentYear.replace("W", "").trim();
        currentYear = currentYear.replace("S", "").trim();

        int thisYearNum = Integer.parseInt(thisYear);
        int currentYearNum = Integer.parseInt(currentYear);

        char thisSemesterChar = this.getSemester().charAt(0);
        char currentSemesterChar = currentSemester.charAt(0);

        if (thisYearNum > currentYearNum) return true;
        if (thisYearNum < currentYearNum) return false;

        if (thisSemesterChar == 'F' && (currentSemesterChar == 'S' || currentSemesterChar == 'W')) return true;
        if (thisSemesterChar == 'S' && currentSemesterChar == 'W') return true;
        
        return false;
    }

    public boolean isPastCourse(String currentSemester) {
        String thisYear = this.getSemester().replace("F", "").trim();
        thisYear = thisYear.replace("W", "").trim();
        thisYear = thisYear.replace("S", "").trim();

        String currentYear = currentSemester.replace("F", "").trim();
        currentYear = currentYear.replace("W", "").trim();
        currentYear = currentYear.replace("S", "").trim();

        int thisYearNum = Integer.parseInt(thisYear);
        int currentYearNum = Integer.parseInt(currentYear);

        char thisSemesterChar = this.getSemester().charAt(0);
        char currentSemesterChar = currentSemester.charAt(0);

        if (thisYearNum > currentYearNum) return false;
        if (thisYearNum < currentYearNum) return true;

        if (thisSemesterChar == 'F' && (currentSemesterChar == 'S' || currentSemesterChar == 'W')) return false;
        if (thisSemesterChar == 'S' && currentSemesterChar == 'W') return false;
        
        if (this.getSemester().equals(currentSemester)) return false;
        return true;
    }

    public Student retrieveTAApplicant(int studentID) {
        ArrayList<Student> teachingAssistantApplications = this.getTeachingAssistantApplications();

        for (Student student : teachingAssistantApplications) {
            if (student.getID() == studentID) {
                return student;
            }
        }

        return null;
    }

    private void removeTAApplication(int studentID) {
        if (this.getTeachingAssistantApplications() == null) return;

        Student taApp = this.getTeachingAssistantApplications().stream().filter(current ->
                current.getID() == studentID).findFirst().orElse(null);

        if (taApp == null) return;

        this.getTeachingAssistantApplications().remove(taApp);
    }
}
