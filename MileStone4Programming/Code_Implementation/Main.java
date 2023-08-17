public class Main {
    public static void main(String[] args) {
        // Starts up the User Interface for Professor/Student
        CourseCatalog courseCatalog = new CourseCatalog("W2023");
        (new TextUI()).startUI(courseCatalog);
    }
}