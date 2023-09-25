
import java.util.Scanner;
import java.util.Arrays;

class Main {
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        //arrays for the courses where 15 is maximum number of courses and their code and 2 is their respective name
        String[][] courses = new String[16][2];
        //array for evaluations where 15 is the maximum number of courses and 3 are the teacher, content and examination evaluations
        String[][] evaluation = new String[64][2];
        //switch statement that handles the input returned from the menu() method
        while(true){
            switch(menu()){
                case "1":
                    AddCourse(courses);
                    break;
                case "2":
                    AddScore(evaluation, courses);
                    break;
                case "3":
                    PrintSummary(evaluation, courses);
                    break;
                case "4":
                    DeleteCourse(evaluation, courses);
                    break;
                case "5":
                    printCourses(courses);
                    break;
                case "q":
                    System.out.print("Terminating program");
                    System.exit(0);
                    break;
                default:
                    System.out.print("\n Enter one of the options above \n");
                    break;
            }
        }
    }
    public static String menu(){
        //displays the menu and handles ending the program
        System.out.print("\n 1. Register new course");
        System.out.print("\n 2. Register evaluation score");
        System.out.print("\n 3. Print evaluation summary for a course");
        System.out.print("\n 4. Delete course");
        System.out.print("\n 5. Print course list");
        System.out.print("\n q. End program");
        System.out.print("\n enter your option: ");
        String choice = Input();
        return choice;
    }
    public static String Input(){
        //this method will take input for the rest of the program, for example for course code and description aswell as the evaluation input
        String input = userInput.nextLine();
        return input;
    }
    public static int InputInt(){
        int input = 0;
        while(!userInput.hasNextInt()){
            System.out.println("Enter a number");
            userInput.next();
        }
        input = userInput.nextInt();
        while(input < 1 || input > 5){
            System.out.print("Number must be from 1 to 5 \n");
            input = userInput.nextInt();
        }
        return input;
    }

    public static String[][] AddCourse(String[][]courses){
        // registers a new course
        System.out.print(" Enter code in format A0000A (letter, 4 numbers (0-9), letter)");
        System.out.print("\n Enter course code: ");
        String courseCode;
        courseCode = Input().toUpperCase();
        while(!courseCode.matches("\\w\\d\\d\\d\\d\\w")){
            System.out.print("Patter does not match, try again \n");
            courseCode = Input().toUpperCase();
        }
        System.out.print("\n Enter course name: ");
        String courseName;
        courseName = Input();

//for loop that sets the code and name of the course in the array
        for(int i = 0; i < courses.length; i++){
            if(i == courses.length -1){
                System.out.print("You cannot register more than " + i + " courses");
            } else if(courses[i][0] == null){
                courses[i][0] = courseCode;
                courses[i][1] = courseName;
                break;
            }
        }
        return courses;
    }

    public static String[][] AddScore(String[][] evaluations, String[][]courses){
        //registers evaluation scores and check if course already exists
        System.out.print("Enter course code: ");
        String code = Input().toUpperCase();
        int index = -1;
        while(index == -1){
            for(int i = 0; i < courses.length; i++){
                if(courses[i][0].equals(code)){
                    index = i;
                    break;
                }
                if(index == -1){
                    System.out.println("Course does not exist, try again");
                    code = Input().toUpperCase();
                    break;
                }
            }
        }
        //variables
        String teacher;
        String contents;
        String examination;
        //sets the above variables and stores them in the array
        System.out.println("Enter evaluation score for Teacher (1-5)");
        teacher = Input().toUpperCase();
        System.out.println("Enter evaluation score for Contents (1-5)");
        contents = Input().toUpperCase();
        System.out.println("Enter evaluation score for Examination (1-5)");
        examination = Input().toUpperCase();

        // Find the next available row to store the new scores
        int row = 0;
        while (evaluations[index][row] != null) {
            row++;
            if (row >= evaluations[index].length) {
                System.out.println("Maximum evaluations reached for this course.");
                return evaluations;
            }
        }

        // Store the scores in the evaluations array
        evaluations[index][row] = teacher + " " + contents + " " + examination;

        return evaluations;
    }

    public static void PrintSummary(String[][] evaluation, String[][]courses) {
        //prints the evaluation summary of the course after checking that course code is valid
        System.out.print("Enter course code: ");
        String code = Input().toUpperCase();
        int index = -1;
        while (index == -1) {
            for (int i = 0; i < courses.length; i++) {
                if (courses[i][0].equals(code)) {
                    index = i;
                    break;
                }
                if (index == -1) {
                    System.out.println("Course does not exist, try again");
                    code = Input().toUpperCase();
                    break;
                }
            }
        }
        System.out.println("Teacher   Contents   Examination");
        for (int i = 0; i < evaluation[index].length; i++) {
            if (evaluation[index][i] != null) {
                String[] scores = evaluation[index][i].split(" ");
                System.out.println(scores[0] + "         " + scores[1] + "         " + scores[2]);

            }
        }
    }
    public static void DeleteCourse(String[][] evaluation, String[][] courses) {
        System.out.print("Enter course code to delete: ");
        String code = Input().toUpperCase();
        int index = -1;
        while (index == -1) {
            for (int i = 0; i < courses.length; i++) {
                if (courses[i][0].equals(code)) {
                    index = i;
                    courses[i][0] = null;
                    courses[i][1] = null;
                    break;
                }
                for(int j = 0; i < evaluation.length; i++){
                    if(evaluation[i][0].equals(code)){
                        evaluation[i][0] = null;
                        evaluation[i][1] = null;
                        break;

                    }
                }
            }
        }
        System.out.print("Course deleted succesfully");
    }
    public static void printCourses(String[][] courses) {
        // Print all courses
        for (String[] course : courses) {
            if (course != null) {
                System.out.println(course);
            }
        }

    }
}