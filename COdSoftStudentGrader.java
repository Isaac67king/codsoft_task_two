import java.util.*;
public class COdSoftStudentGrader {
    //create scanner object
    static Scanner scan = new Scanner(System.in);

    //initialize some global variables
    static int subjectSIze = 0;
    static double total = 0.0;
    static int validSubjects = 0;
    static double average = 0.0;

    //main method
    public static void main(String[] args) {
        System.out.println("\n*********************   Welcome to Student Grading    ***********************");
        System.out.print("\nPlease enter the number of subjects: ");
        String subjectNO = scan.nextLine();
        System.out.println();
        //invoke digit checker function
        if (isAllDigit(subjectNO)) {
            int number= Integer.parseInt(subjectNO);
            if(number> 0)
            subjectSIze = number;
            else{
                System.out.println("\t@ Invalid Subject Number, your input is negative number!\n");

                String[] arg = {};
                main(arg);
            }
        }
        else if(!isAllDigit(subjectNO)){
            String[] arg = {};
            System.out.println("Invalid Subject Number!");
            main(arg);
        }

        //declare double and single arrays for storing inputs
        String[][] subjectDetail = new String[subjectSIze][3];
        double[] subjectScore = new double[subjectSIze];

        //prompt the user to enter subject detail through invoked method
        subjectDetails(subjectDetail, subjectScore);

        //option page
        while (true){
            System.out.println("Please enter your choice from the following options:");
            System.out.println("\tA. show me the total score");
            System.out.println("\tB. show me the average score");
            System.out.println("\tC. show me all subject details");
            System.out.println("\tD. show me subject and grade alone");
            System.out.println("\tE. exit the program");
            System.out.print("Select an option: ");
            char option = scan.nextLine().toUpperCase().trim().charAt(0);
            System.out.println();
            if (option == 'A') {
                System.out.println("Total score: " + total);
                System.out.println("________________________________________");
                System.out.println();
            }
            else if (option == 'B') {
                System.out.printf("Average score: %.2f\n", average);
                System.out.println("________________________________________");
                System.out.println();
            }
            else if (option == 'C') {
                System.out.println("________________________________________");
                System.out.printf("%10s | %10s | %10s\n", "subject", "score", "Grade");
                subjcetDelailsPrinter(subjectDetail, subjectScore);
                System.out.println("________________________________________");
                System.out.println();
            }
            else if (option == 'D') {
                System.out.println("________________________________________");
                System.out.printf("%10s | %10s\n", "subject", "Grade");
                System.out.println("________________________________________");
                subjectAndGradePrinter(subjectDetail, subjectScore);
                System.out.println("________________________________________");
                System.out.println();
            }
            else if (option == 'E') {
                System.out.println("Goodbye Bro!");
                System.exit(0);
            }
            else{
                System.out.println("Invalid option");
            }
        }
    }

    //subject details method
    public static void subjectDetails(String[][] subjectDetail, double[] subjectScore) {
        String score = "";
        System.out.println("Please enter the subject detail");
      for(int i = 0; i < subjectDetail.length; i++) {
          System.out.print("Subject Name: ");
          String subject = scan.nextLine();
          if(isTheSubjectUnique(subjectDetail, subject)) {
              subjectDetail[i][0] =subject;
              System.out.print("Subject score (out of 100): ");
              score = scan.nextLine();
          }
          else if(!isTheSubjectUnique(subjectDetail, subject)) {
              System.out.println("\n\t Attention!!! Subject is repeated! please change the subject.\n");
              i--;
          }
          //digit validity check, and storing the values
          if(isAllDigit(score) && !score.contains("-")) {
              subjectDetail[i][1] = score;
              subjectScore[i] = Double.parseDouble(score);
              total = total + Double.parseDouble(score);
              validSubjects++;
          }
          else if(!(isAllDigit(score)) || score.contains("-")) {
              System.out.println("Invalid score either your score should be digit or should be positive \n");
          }
      }
      average += total/validSubjects;
    }

    // show subject details
    public static void subjcetDelailsPrinter(String[][] subjectDetail, double[] subjectScore) {
        for(int i = 0; i < subjectDetail.length; i++) {
            if(subjectScore[i] > average) {
                subjectDetail[i][2] = "A";
            }else if(subjectScore[i] == average) {
                subjectDetail[i][2] = "B";
            } else{
                subjectDetail[i][2] = "C";
            }
            for(int j = 0; j < subjectDetail[i].length; j++) {
                System.out.printf("%10s |",subjectDetail[i][j]);
            }
            System.out.println();
        }

    }
    //display subject and grade only
    public static void subjectAndGradePrinter(String[][] subjectDetail, double[] subjectScore) {
        for(int i = 0; i < subjectDetail.length; i++) {
            if(subjectScore[i] > average) {
                subjectDetail[i][2] = "A";
            }
            else if(subjectScore[i] == average) {
                subjectDetail[i][2] = "B";
            }
            else{
                subjectDetail[i][2] = "C";
            }
            for(int j = 0; j < subjectDetail[i].length; j++) {
                if(j ==1)
                    continue;
                System.out.printf("%10s |",subjectDetail[i][j]);
            }
            System.out.println();
        }
    }

    //the following function will check is the input is digit or not
    public static boolean isAllDigit(String string){
        for(int i = 0; i < string.length(); i++){
            if(!Character.isDigit(string.charAt(i))){
                return false;
            }
        }
        return true;
    }
    //check is there a repetition between subject name
    public static  boolean isTheSubjectUnique(String[][] subjectDetail, String subject) {
        for(int i = 0; i < subjectDetail.length; i++) {
            if( subject.equals(subjectDetail[i][0])){
                return false;
            }
        }
        return true;
    }

}
