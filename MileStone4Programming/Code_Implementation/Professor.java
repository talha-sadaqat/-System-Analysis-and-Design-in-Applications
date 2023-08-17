import java.util.ArrayList;

public class Professor extends AcademicAssociate {

    private boolean hasPHD;

    public Professor(String firstName, String middleName,
                     String lastName, String email,
                     int id, ArrayList<CourseAttempt> courses, boolean hasPHD) {
        
        super(firstName, middleName, lastName, email, id, courses);
        this.hasPHD = hasPHD;
    }

    public Professor(AcademicAssociate academicAssociate, boolean hasPHD) {
        super (
            academicAssociate.getFirstName(), academicAssociate.getMiddleName(), 
            academicAssociate.getLastName(), academicAssociate.getEmail(), 
            academicAssociate.getID(), academicAssociate.getCourses()
        );

        this.hasPHD = hasPHD;
    }

    public boolean getHasPHD() {
        return this.hasPHD;
    }

    public void setHasPHD(boolean hasPHD) {
        this.hasPHD = hasPHD;
    }

    public boolean approveTAApplicant(CourseOffering courseOffering, Student newTA) {
        ArrayList<Student> teachingAssistantApplications =  courseOffering.getTeachingAssistantApplications();

        for (Student student : teachingAssistantApplications) {
            if (student.getID() == newTA.getID()) {
                teachingAssistantApplications.remove(student);
                courseOffering.getTeachingAssistants().add(student);
            }
        }

        return false;
    }
}
