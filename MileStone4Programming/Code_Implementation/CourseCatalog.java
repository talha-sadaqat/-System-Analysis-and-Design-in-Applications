import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.*;

public class CourseCatalog {
    
    private final HashMap<String, CourseOffering> courseOfferings;

    // Retrieves courses for the given semester
    public CourseCatalog(String semester) {
        this.courseOfferings = getCourseCatalogOfferings(semester);
    }

    public String toString() {
        String offerings = "";
        HashMap<String, CourseOffering> courseOfferings = this.getCourseOfferings();
        
        for (String key : courseOfferings.keySet()) {
            offerings += courseOfferings.get(key).toString() + "\n";
        }
        
        return offerings;
    }

    public HashMap<String, CourseOffering> getCourseCatalogOfferings(String semester) {
        
        HashMap<String, CourseOffering> courseOfferings = new HashMap<String, CourseOffering>();

        int[] cisCourseCodes = getCisCourseCodes();
        String[] cisCourseNames = getCisCourseNames();

        int[] mathCourseCodes = getMathCourseCodes();
        String[] mathCourseNames = getMathCourseNames();
        
        String[] otherCourseNames = getOtherCourseNames();
        int[] otherCourseCodes = getOtherCourseCodes();
        String[] otherCourseIDs = getOtherCourseIDs();

        HashMap<Integer, Student> studentsMap = getStudents();

        for (int i = 0; i < cisCourseCodes.length; i++) { 
            ArrayList<String> sections = new ArrayList<String>();
            ArrayList<Student> students = new ArrayList<Student>();
            
            sections.add("0101");
            sections.add("0102");
            sections.add("0201");
            sections.add("0202");

            Random random = new Random();
            int numStudents = random.nextInt(5);

            for (int j = 0; j < numStudents; j++) {
                int id = 1000000 + random.nextInt(99);
                Student student = studentsMap.get(id);
                students.add(student);
            }

            Course course = new Course("CIS", cisCourseCodes[i], cisCourseNames[i]);
            CourseOffering courseOffering = new CourseOffering(course, semester, sections, students, new ArrayList<Student>(), new ArrayList<Student>());
            
            // Maps Course IDs to their offering
            courseOfferings.put(getFullCourseID(course), courseOffering);
        
            Iterator<Student> studentIterator = students.iterator();

            while (studentIterator.hasNext()) {
                String section = sections.get(random.nextInt(sections.size() - 1));
                CourseAttempt courseAttempt = new CourseAttempt(courseOffering, section, "Student");

                Student student = studentIterator.next();
                //student.registerCourse(courseAttempt.getCourseOffering().getCourseID(), courseAttempt.getCourseOffering().getCourseCode());
            }
        }

        for (int i = 0; i < mathCourseCodes.length; i++) { 
            ArrayList<String> sections = new ArrayList<String>();
            ArrayList<Student> students = new ArrayList<Student>();
            
            sections.add("0101");
            sections.add("0102");
            sections.add("0201");
            sections.add("0202");

            Random random = new Random();
            int numStudents = random.nextInt(20);

            for (int j = 0; j < numStudents; j++) {
                int id = 1000000 + random.nextInt(99);
                Student student = studentsMap.get(id);
                students.add(student);
            }

            Course course = new Course("MATH", mathCourseCodes[i], mathCourseNames[i]);
            CourseOffering courseOffering = new CourseOffering(course, semester, sections, students, new ArrayList<Student>(), new ArrayList<Student>());
            
            // Maps Course IDs to their offering
            courseOfferings.put(getFullCourseID(course), courseOffering);
            
            Iterator<Student> studentIterator = students.iterator();

            while (studentIterator.hasNext()) {
                String section = sections.get(random.nextInt(sections.size() - 1));
                CourseAttempt courseAttempt = new CourseAttempt(courseOffering, section, "Student");

                Student student = studentIterator.next();
                //student.registerCourse(courseAttempt.getCourseOffering().getCourseID(), courseAttempt.getCourseOffering().getCourseCode());
            }
        }

        for (int i = 0; i < otherCourseCodes.length; i++) { 
            ArrayList<String> sections = new ArrayList<String>();
            ArrayList<Student> students = new ArrayList<Student>();
            
            sections.add("0101");
            sections.add("0102");
            sections.add("0201");
            sections.add("0202");

            Random random = new Random();
            int numStudents = random.nextInt(20);

            for (int j = 0; j < numStudents; j++) {
                int id = 1000000 + random.nextInt(99);
                Student student = studentsMap.get(id);
                students.add(student);
            }

            Course course = new Course(otherCourseIDs[i], otherCourseCodes[i], otherCourseNames[i]);
            CourseOffering courseOffering = new CourseOffering(course, semester, sections, students, new ArrayList<Student>(), new ArrayList<Student>());
            
            // Maps Course IDs to their offering
            courseOfferings.put(getFullCourseID(course), courseOffering);
            Iterator<Student> studentIterator = students.iterator();

            while (studentIterator.hasNext()) {
                
                String section = sections.get(random.nextInt(sections.size() - 1));
                CourseAttempt courseAttempt = new CourseAttempt(courseOffering, section, "Student");

                Student student = studentIterator.next();
                //student.registerCourse(courseAttempt.getCourseOffering().getCourseID(), courseAttempt.getCourseOffering().getCourseCode());
            }
        }
        return courseOfferings;
    }

    public HashMap<String,CourseOffering> getCourseOfferings() {
        return this.courseOfferings;
    }

    private int[] getCisCourseCodes() {
        return new int[] {
                1050, 1200, 1250, 1300, 1500, 1910, 2030,
                2170, 2250, 2430, 2500, 2520, 2750, 2910,
                3050, 3090, 3110, 3120, 3130, 3150, 3190,
                3210, 3250, 3260, 3490, 3530, 3700, 3750,
                3760, 4010, 4020, 4030, 4050, 4150, 4250,
                4300, 4450, 4500, 4510, 4520, 4650, 4720,
                4780, 4800, 4820, 4900, 4910,
        };
    }

    private String[] getCisCourseNames() {
        return new String[] {
                "Web Design and Development",
                "Introduction to Computing",
                "Software Design I",
                "Programming",
                "Introduction to Programming",
                "Discrete Structures in Computing I",
                "Structure and Application of Microcomputers",
                "User Interface Design",
                "Software Design II",
                "Object Oriented Programming",
                "Intermediate Programming",
                "Data Structures",
                "Software Systems Development and Integration",
                "Discrete Structures in Computing II",
                "Systems Programming",
                "Parallel Programming",
                "Operating Systems I",
                "Digital Systems I",
                "System Modeling and Simulation",
                "Theory of Computation",
                "Software for Legacy Systems",
                "Computer Networks",
                "Software Design III",
                "Software Design IV",
                "The Analysis and Design of Computer Algorithms",
                "Data Base Systems and Concepts",
                "Introduction to Intelligent Systems",
                "System Analysis and Design in Applications",
                "Software Engineering",
                "Cloud Computing",
                "Data Science",
                "Mobile Computing",
                "Digital Systems II",
                "Software Reliability and Testing",
                "Software Design V",
                "Human Computer Interaction",
                "Special Topics in Information Science",
                "Special Topics in Computing Science",
                "Computer Security Foundations",
                "Introduction to Cryptography",
                "Compilers",
                "Image Processing and Vision",
                "Computational Intelligence",
                "Computer Graphics",
                "Game Programming",
                "Computer Science Project",
                "Computer Science Thesis",
        };
    }

    private int[] getMathCourseCodes() {
        return new int[] {
                1030, 1080, 1090, 1160, 1200, 1210, 2000,
                2130, 2200, 2210, 2270, 3100, 3130, 3160,
                3200, 3240, 3260, 3510, 4050, 4060, 4150,
                4200, 4240, 4270, 4310, 4440, 4600,
        };
    }

    private String[] getMathCourseNames() {
        return new String[] {
                "Business Mathematics",
                "Elements of Calculus I",
                "Elements of Calculus II",
                "Linear Algebra I",
                "Calculus I",
                "Calculus II",
                "Proofs, Sets, and Numbers",
                "Numerical Methods",
                "Advanced Calculus I",
                "Advanced Calculus II",
                "Applied Differential Equations",
                "Differential Equations II",
                "Abstract Algebra",
                "Linear Algebra II",
                "Real Analysis",
                "Operations Research",
                "Complex Analysis",
                "Biomathematics",
                "Topics in Mathematics I",
                "Topics in Mathematics II",
                "Topics in Mathematics III",
                "Advanced Analysis",
                "Advanced Topics in Modeling and Optimization",
                "Partial Differential Equations",
                "Combinatorics and Graph Theory",
                "Case Studies in Mathematics and Statistics",
                "Advanced Research Project in Mathematics",
        };
    }

    private String[] getOtherCourseIDs() {
        return new String[] { "UNIV", "ECON", "STAT" };
    }

    private int[] getOtherCourseCodes() {
        return new int[] { 1200, 1050, 2040 };
    }

    private String[] getOtherCourseNames() {
        return new String[] {
                "First Year Seminar",
                "Introductory Microeconomics",
                "Statistics I"
        };
    }

    // Generates students with random information
    public HashMap<Integer, Student> getStudents() {
        HashMap<Integer, Student> students = new HashMap<Integer, Student>();
        
        for (int i = 0; i < 100; i++) {
            int studentID = 1000000 + i;
            String firstName = getFirstName(i);
            String middleName = getMiddleName(i);
            String lastName = getLastName(i);
            String email = generateEmail(firstName, lastName);

            AcademicAssociate academicAssociate = new AcademicAssociate(
                firstName, middleName, lastName, email, studentID, new ArrayList<CourseAttempt>() 
            );

            String degree = "Bachelor of Computing";
            String major = "Computer Science";
            String minor = "Mathematics";

            Student student = new Student(academicAssociate, degree, major, minor);
            students.put(studentID, student);
        }

        return students;
    }

    public String getFullCourseID(Course course) {
        return course.getCourseID() + course.getCourseCode();
    }

    public String getFullCourseID(String courseID, int courseCode) {
        return courseID + courseCode;
    }

    public String getCourseName(String courseID, int courseCode) {
        String fullCourseID = getFullCourseID(courseID, courseCode);
        return this.getCourseOfferings().get(fullCourseID).getCourseName();
    }

    public Course getCourse(String courseID, int courseCode) {
        String fullCourseID = getFullCourseID(courseID, courseCode);
        CourseOffering courseOffering = this.getCourseOfferings().get(fullCourseID);
        
        String tempCourseID = courseOffering.getCourseID();
        int tempCourseCode = courseOffering.getCourseCode();
        String courseName = courseOffering.getCourseName();

        Course course = new Course(tempCourseID, tempCourseCode, courseName);
        return course;
    }

    public String getFirstName(int index) {
        String[] firstNames = { "Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Gina",
                "Harry", "Isabel", "Jack", "Katie", "Liam", "Megan", "Nate", "Olivia", "Patrick", "Quinn", "Rachel",
                "Sam", "Tom", "Una", "Victoria", "Will", "Xander", "Yvonne", "Zach", "Amelia", "Ben", "Cathy", "Daniel",
                "Ella", "Felix", "Grace", "Henry", "Ivy", "Jacob", "Kate", "Luke", "Mary", "Noah", "Oliver", "Penelope",
                "Quincy", "Rose", "Sophia", "Toby", "Una", "Violet", "Wesley", "Xavier", "Yara", "Zoe", "Abigail",
                "Bryan", "Carla", "David", "Emma", "Fred", "Gloria", "Haley", "Isaac", "Jenna", "Kevin", "Lila",
                "Marcus", "Nina", "Owen", "Paige", "Quentin", "Rebecca", "Steven", "Tara", "Uma", "Vance", "Wendy",
                "Xandra", "Yasmine", "Zara", "Adam", "Brooke", "Cameron", "Danielle", "Ellie", "Finn", "Georgia",
                "Hannah", "Ian", "Jasmine", "Kyle", "Lena", "Mia", "Nathan", "Olivia", "Peter",
                "Quinn", "Rachel", "Samantha", "Tina", "Uriel", "Victoria", "William", "Ximena", "Yvette", "Zander"
        };
        
        return firstNames[index];
    }
    
    public String getLastName(int index) {
        String[] lastNames = { "Anderson", "Brown", "Carter", "Davis", "Evans", "Franklin", "Garcia", "Hernandez",
                "Irving", "Jones", "Kim", "Lopez", "Martinez", "Nelson", "Owens", "Patel", "Quinn", "Robinson", "Smith",
                "Taylor", "Upton", "Valdez", "Walker", "Xu", "Yoo", "Zhang", "Adams", "Baker", "Chang", "Diaz",
                "Edwards", "Foster", "Gonzalez", "Harris", "Ingram", "Johnson", "Khan", "Lee", "Mitchell", "Nguyen",
                "Ortega", "Perez", "Quintero", "Ramirez", "Sanchez", "Thompson", "Usher", "Vargas", "Williams", "Xiong",
                "Yates", "Zimmerman", "Allen", "Bennett", "Clark", "Duncan", "Ellis", "Flores", "Gibson", "Henderson",
                "Ibrahim", "Jackson", "Kumar", "Lewis", "Moore", "Norton", "O'Brien", "Parker", "Quesada", "Rodriguez",
                "Simpson", "Turner", "Underwood", "Vasquez", "Wilson", "Xavier", "Young", "Zamora", "Armstrong",
                "Black", "Cruz", "Davies", "Ellison", "Freeman", "Gomez", "Hill", "Isaacs", "Johnson", "Keller",
                "Lawson", "Miller", "Nash", "O'Connor", "Pierce", "Quayle", "Reynolds", "Sandoval", "Tanner", "Ullman",
                "Villanueva", "White", "Xiao", "Yoder", "Zane" };

        return lastNames[index];
    }

    public String getMiddleName(int index) {
        Random random = new Random();
        String[] middleNames = { "Arthur", "Bram", "Denver", "Carlen", "Suzan", "Reeve", "Verena", "Ryder",
                "Clark", "Rhett", "Varian", "Breean", "John", "Mes", "Marc", "Seo"};

        String middleName = middleNames[random.nextInt(middleNames.length)];
        return middleName;
    }

    public String generateEmail(String firstName, String lastName) {
        return firstName + lastName + "@uoguelph.ca";
    }

   
}
