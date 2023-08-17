import java.util.*;
import java.time.LocalDate;
import java.time.Month;

public class TextUI {
    
    public void startUI(CourseCatalog courseCatalog) {
        
        Scanner userInput = new Scanner(System.in);
        String currentSemester = getCurrentSemester();
        Scanner scanner = new Scanner(System.in);

        while (true) { // Runs while user doesn't enter a valid role

            printLoginPrompt(currentSemester);
            String userRole = userInput.nextLine().toUpperCase();

            if (userRole.equals("S")) { // Logged in as Student

                // Creates a new Student with hard-coded information
                AcademicAssociate academicAssociate = new AcademicAssociate(courseCatalog);
                Student student = new Student(courseCatalog, academicAssociate);
    
                printWelcomeMessage(student);
                int menuOption;
    
                while (true) {
                    printMenuStudent(); // Main menu for student
                    
                    try {
                        menuOption = scanner.nextInt();
                        scanner.nextLine();
    
                        if (menuOption == 1) { // Add a planned course to the course schedule
                            printAddPlannedCoursePrompt();
                            String fullCourseCode = scanner.nextLine().toUpperCase().trim();
                            
                            CourseOffering courseOffering = courseCatalog.getCourseOfferings().get(fullCourseCode);
                            
                            if (courseOffering == null) {
                                printInvalidCourse();
                                continue;
                            }

                            printSectionPrompt();
                            String section = scanner.nextLine();

                            if (courseOffering.getSections().contains(section) == false) {
                                printInvalidSection();
                                continue;
                            }

                            CourseAttempt courseAttempt = new CourseAttempt(courseOffering, section, "Student");
                            boolean result = student.planCourse(courseAttempt);
    
                            if (result == false) System.out.println("\nError Occured! Back to main menu!\n");
                            else System.out.println("\nCourse Planned Successfully! Back to main menu!\n");
                        }
    
                        if (menuOption == 2) { // Remove a planned course from the course schedule
                            printRemovePlannedCourseStudent();
                            String fullCourseCode = scanner.nextLine().toUpperCase().trim();
                            boolean result = student.removePlannedCourse(fullCourseCode, courseCatalog);
    
                            if (result == false) System.out.println("\nError Occured! Back to main menu!\n");
                            else System.out.println("\nPlanned Course Removed Successfully! Back to main menu!\n");
                        }
    
                        if (menuOption == 3) { // Register for a planned course to course schedule
                            printRegisterPlannedCourseStudent();
                            String fullCourseCode = scanner.nextLine().toUpperCase().trim();
                            boolean result = student.registerCourse(fullCourseCode, courseCatalog);
    
                            if (result == false) System.out.println("\nError Occured! Back to main menu!\n");
                            else System.out.println("\nCourse Registered Successfully! Back to main menu!\n");
                        }
    
                        if (menuOption == 4) { // Drop a registered course from the course schedule
                            printDropRegisteredCourseStudent();
                            String fullCourseCode = scanner.nextLine().toUpperCase().trim();
                            boolean result = student.dropCourse(fullCourseCode, courseCatalog);

                            if (result == false) System.out.println("\nError Occured! Back to main menu!\n");
                            else System.out.println("\nCourse Dropped Successfully! Back to main menu!\n");
                        }
    
                        if (menuOption == 5) { // View Future Courses
                            viewFutureCourses(student, currentSemester);
                        }
                    
                        if (menuOption == 6) { // View Current Courses
                            viewCurrentCourses(student, currentSemester);
                        }
    
                        if (menuOption == 7) { // View Past Courses
                            viewPastCourses(student, currentSemester);
                        }
    
                        if (menuOption == 8) { // View TA Positions Available
                            printTAPositions(courseCatalog);
                        }
    
                    } catch (Exception e) { // This includes if the user enters "Q" or "Quit"
                        System.out.println("Quitting Program!");
                        scanner.close();
                        return;
                    }
                }
    
            } else if (userRole.equals("P")) { // Logged in as Professor
    
                AcademicAssociate academicAssociate = new AcademicAssociate(courseCatalog);
                Professor professor = new Professor(academicAssociate, true);
                
                printWelcomeMessage(professor);
                int menuOption;

                while (true) {
                    printMenuProfessor();

                    try {
                        menuOption = scanner.nextInt();
                        scanner.nextLine();

                        if (menuOption == 1) { // View Future Courses
                            viewFutureCourses(professor, currentSemester);
                        }
                    
                        if (menuOption == 2) { // View Current Courses
                            viewCurrentCourses(professor, currentSemester);
                        }
    
                        if (menuOption == 3) { // View Past Courses
                            viewPastCourses(professor, currentSemester);
                        }

                        if (menuOption == 4) { // Review TA Applications
                            printReviewTAApplicationsPrompt(professor, currentSemester);
                            String fullCourseCode = scanner.nextLine().toUpperCase().trim();
                            CourseOffering courseOffering = courseCatalog.getCourseOfferings().get(fullCourseCode);

                            if (courseOffering == null) {
                                printInvalidCourse();
                                continue;
                            }

                            printEnterStudentToBecomeTAPrompt();
                            int studentID = scanner.nextInt();
                            Student student = courseOffering.retrieveTAApplicant(studentID);

                            if (student == null) {
                                printInvalidStudentID();
                                continue;
                            }

                            boolean result = professor.approveTAApplicant(courseOffering, student);

                            if (result == false) System.out.println("\nError Occured! Back to main menu!\n");
                            else System.out.println("\nTA Approved Successfully! Back to main menu!\n");
                        }

                        if (menuOption == 5) { // View class list for a course
                            printViewClassListPrompt();
                            String fullCourseCode = scanner.nextLine().toUpperCase().trim();
                            viewClassList(courseCatalog, fullCourseCode);
                        }
                    } catch (Exception e) {
                        System.out.println("Quitting Program!");
                        scanner.close();
                        return;    
                    }    
                }
            } else {
                printInvalidUserRole();
            }
        }
    }

    private void printInvalidUserRole() {
        System.out.println("\nYou have entered an invalid login! Please try again!\n\n");
        System.out.println("Enter S to log in as a Student");
        System.out.println("Enter P to log in as a Professor\n");
        System.out.print("Enter Your Choice Here: ");
    }

    // Retrieve the current semester for the academic year
    private String getCurrentSemester() {
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        int currentYear = currentdate.getYear();

        if (currentMonth.getValue() >= 9 && currentMonth.getValue() <= 12) {
            return "F" + Integer.toString(currentYear);
        } else if (currentMonth.getValue() >= 1 && currentMonth.getValue() <= 4) {
            return "W" + Integer.toString(currentYear);
        } else {
            return "S" + Integer.toString(currentYear);
        }
    }

    // Login Menu
    public void printLoginPrompt(String currentSemester) {
        System.out.println("\nCurrent Semester: " + currentSemester);
        System.out.println("\nWelcome to Course Scheduler!\n");
        System.out.println("Enter S to log in as a Student");
        System.out.println("Enter P to log in as a Professor\n");
        System.out.print("Enter Your Choice Here: ");
    }

    // Welcome Message
    public void printWelcomeMessage(AcademicAssociate academicAssociate) {
        System.out.println("\nWelcome to " + academicAssociate.getClass().toString().replace("class", "").trim() + " Course Scheduling!");
        System.out.println("Currently signed in as " + academicAssociate.getFullName() + "\n");
        System.out.println("Enter Q or Quit to exit Course Scheduler\n");
        System.out.println("What would you like to do today?");
    }

    // Student Menu
    public void printMenuStudent() {
        System.out.println("1. Add a planned course to the course schedule");
        System.out.println("2. Remove a planned course from the course schedule");
        System.out.println("3. Register for a planned course to course schedule");
        System.out.println("4. Drop a registered course from the course schedule");
        System.out.println("5. View Future Courses");
        System.out.println("6. View Current Courses");
        System.out.println("7. View Past Courses");
        System.out.println("8. View TA positions available");
        System.out.print("Enter Your Choice Here: ");
    }

    // Professor Menu
    public void printMenuProfessor() {
        System.out.println("1. View your courses to teach next semester");
        System.out.println("2. View the courses you are currently teaching");
        System.out.println("3. View past taught courses");
        System.out.println("4. Review TA Applications");
        System.out.println("5. View the class list for a course\n");
    }

    /* STUDENT PRINT METHODS */
    public void printAddPlannedCoursePrompt() {
        System.out.println("\nWhich course would you like to plan for?\n");
        System.out.println("Please enter the course code and ID (i.e CIS1300):\n");
    }

    public void printSectionPrompt() {
        System.out.println("\nWhich section would you like to enroll in?");
        System.out.print("Enter section here: ");
    }

    public void printCoursePlannedSuccessfullyMessage() {
        System.out.println(
                "You have successfully planned (Course)(Section) for the (Semester) semester, and your planned schedule has been updated accordingly");
        System.out.println("Returning to main menu...");
    }

    public void printAlreadyPlannedCourse() {
        System.out.println(
                "You already have (Course) in your planned schedule, please choose another course you would like to plan\n");
    }

    public void printInvalidCourse() {
        System.out.println("The course entered does not exist, please enter a valid course\n");
    }

    public void printInvalidSection() {
        System.out.println("The section entered was invalid, please enter a valid section\n");
    }

    // method print for remove a planned course
    public void printRemovePlannedCourseStudent() {
        System.out.println("Which planned course would you like to remove from your schedule?");
        System.out.println("Please enter the course code and ID (i.e CIS1300)");
    }

    public void printRegisterPlannedCourseStudent() {
        System.out.println("Which planned course would you like to register for?");
        System.out.println("Please enter the course code and ID (i.e CIS1300)");   
    }

    public void printDropRegisteredCourseStudent() {
        System.out.println("Which registered course would you like to drop?");
        System.out.println("Please enter the course code and ID (i.e CIS1300)"); 
    }

    public void printPlannedCourses(Student student) {
        System.out.println("Courses you currently have planned");

        Iterator<CourseAttempt> coursesIterator = student.getCourses().iterator();

        while (coursesIterator.hasNext()) {
            CourseAttempt courseAttempt = coursesIterator.next();
            System.out.println(courseAttempt.toString());
        }

        // print out planned courses
        // user enters course
        // if valid course
        // confirmation
        System.out.println("Are you sure you want to remove (Course)(Section) from your planned courses? Yes or No");
        // if user enters yes
        System.out.println(
                "You have successfully removed (Course)(Section) from your planned courses, and your section has been updated accordingly");
        System.out.println("Returning to main menu...");
        // else if course entered is not in planned schedule
        System.out.println(
                "You have entered a course that has not been planned. Please enter a course from the list of planned courses");
        // else user has no planned courses
        System.out.println("You have no planned courses");
    }

    // method print for register for a planned course
    public void registerPlannedCourseStudent(Student student) {
        // if user has registered courses
        System.out.println("Here is a list of the registered courses you have selected for this semester");
        System.out.println("Courses which are currently registered for the upcoming semester:");
        // print courses they are registered for
        System.out.print("(Course)");

        // if course valid
        // if course not completed in past
        // if the course is not in their registered courses
        System.out.println("Which section would you like to enroll in?");
        System.out.print("(Section)");
        // if section valid
        System.out.println(
                "You have successfully registered for (Course)*(Section) for the Fall 2021 Semester, andyour scedule has been updates accordingly");
        System.out.println("Returning to the main menu...");
        // else
        System.out.println("You have entered an invalid section, Please select an available section:");
        System.out.print("(Section)");
        // else
        System.out.println("Sorry, this course cannot be registered, you are already registered for this course");
        // else
        System.out.println("Course has already been completed in the past!");

        // else
        System.out.println("Which section would you like to enroll in?");
        System.out.print("(Section)");
        // if section valid
        System.out.println(
                "You have successfully registered for (Course)*(Section) for the Fall 2021 Semester, andyour scedule has been updates accordingly");
        System.out.println("Returning to the main menu...");
        // else
        System.out.println("You have entered an invalid section, Please select an available section:");
        System.out.print("(Section)");

    }

    // method print for view future courses
    public void viewFutureCourses(AcademicAssociate academicAssociate, String currentSemester) {

        List<CourseAttempt> courses = academicAssociate.getCourses();
        List<CourseAttempt> futureCourses = new ArrayList<>();

        for (CourseAttempt course : courses) {
            if (course.getCourseOffering().isFutureCourse(currentSemester)) {
                futureCourses.add(course);
            }
        }

        if (futureCourses.size() <= 0) {
            System.out.println("You have not currently planned/registered for any future courses.");
            return;
        }

        System.out.println("\nViewing Future Courses:\n");

        for (CourseAttempt course : futureCourses) {
            System.out.println(course.toString() + "\n");
        }
    }

    // method print for view current courses
    public void viewCurrentCourses(AcademicAssociate academicAssociate, String currentSemester) {

        List<CourseAttempt> courses = academicAssociate.getCourses();
        List<CourseAttempt> currentCourses = new ArrayList<>();

        for (CourseAttempt course : courses) {
            if (course.getCourseOffering().getSemester().equals(currentSemester)) {
                currentCourses.add(course);
            }
        }

        if (currentCourses.size() <= 0) {
            System.out.println("You have not currently planned/registered for any courses this semester.");
            return;
        }

        System.out.println("\nViewing Current Semester:\n");

        for (CourseAttempt course : currentCourses) {
            System.out.println(course.toString() + "\n");
        }
    }

    // method print for view past courses
    public void viewPastCourses(AcademicAssociate academicAssociate, String currentSemester) {

        List<CourseAttempt> courses = academicAssociate.getCourses();
        List<CourseAttempt> pastCourses = new ArrayList<>();

        for (CourseAttempt course : courses) {
            if (course.getCourseOffering().isPastCourse(currentSemester)) {
                pastCourses.add(course);
            }
        }

        if (pastCourses.size() <= 0) {
            System.out.println("You did not plan or register for any past courses.");
            return;
        }

        System.out.println("\nViewing Past Courses:\n");

        for (CourseAttempt course : pastCourses) {
            System.out.println(course.toString() + "\n");
        }
    }

    public void viewClassList(CourseCatalog courseCatalog, String fullCourseCode) {
        CourseOffering courseOffering = courseCatalog.getCourseOfferings().get(fullCourseCode);
        System.out.println("CLASS LIST FOR (" + fullCourseCode + ") - " + courseOffering.getCourseName() + ":\n\n");

        Iterator<Student> studentIterator = courseOffering.getStudents().iterator();

        while (studentIterator.hasNext()) {
            System.out.println(studentIterator.next().toString());
        }
    }

    public void printViewClassListPrompt() {
        System.out.println("\nWhich couse would you like to view the classlist for?");
        System.out.println("Please enter the course code and ID (i.e CIS1300)\n"); 

    }

    // method print for view ta positions vailable
    public void viewTAPositionssAvailableStudent(Student student, CourseCatalog courseCatalog) {
        // if available TA positions
        printTAPositions(courseCatalog);
        System.out.println("Returning to main menu ...");
    }

    public void printApplyAsTAPrompt() {
        System.out.println("Which course would you like to apply for a TA position for Fall 2023?");
    }

    public void printViewTAPositions() {
        System.out.println("For Fall 2023 TA positions are available for the following courses:");
    }

    public void printTAPositions(CourseCatalog courseCatalog) {
        HashMap<String, CourseOffering> courseOfferings = courseCatalog.getCourseOfferings();

        for (String key : courseOfferings.keySet()) {
            CourseOffering courseOffering = courseOfferings.get(key);

            System.out.println(courseOffering.toString());
            System.out.println("current number of TAs: " + courseOffering.getTeachingAssistants().size());
            System.out.println("current number of TA applications: " + courseOffering.getTeachingAssistantApplications().size() + "\n");
        }
    }

    public void printReviewTAApplicationsPrompt(Professor professor, String currentSemester) {
        System.out.println("Current courses:\n");
        viewCurrentCourses(professor, currentSemester);

        System.out.println("\nEnter the course you would like to view the TA application list for (i.e CIS1300)");
        System.out.print("Enter the course here: ");
    }

    public void printEnterStudentToBecomeTAPrompt() {
        System.out.println("\nEnter the id of the student you would like make a TA for the course (i.e 1234567)");
        System.out.print("Enter the id here: ");
    }

    public void printInvalidStudentID() {
        System.out.println("\nThe student id you have entered does not exist in the class!\n");
    }
}
