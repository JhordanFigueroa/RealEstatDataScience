package edu.upenn.cit594.ui;

public class ErrorMessages {
    // error message to be displayed when a user inputs the incorrect number of CLI arguments
    public static void errorInvalidArguments(){
        System.out.println("ERROR: INVALID NUMBER OF ARGS");
        System.out.println("YOU MUST ENTER AT LEAST 5 ARGUMENTS OF THE FOLLOWING:" +
                "\n 1st: Parking Violations input file Format, either csv or json (case-sensitive)" +
                "\n 2nd: Parking Violations input file Name" +
                "\n 3rd: Property Values Input File" +
                "\n 4th: Population Input File" +
                "\n 5th: Log File Name");
    }

    // error message to be displayed when a file passed as a CLI argument cannot be read
    public static void unsuccessfulFileRead(String arg){
        System.out.println("The following file cannot be read: " + arg);
        System.out.println("Please check your inputs and re-run the program.");
    }

    // error message to be displayed when the specified parking file format in the CLI is incorrect
    public static void incorrectParkingFileFormat(String parkingFileInputFormat){
        System.out.println("The Parking file must either be input as 'json' or 'csv' (case-sensitive).");
        System.out.println("Your input of '" + parkingFileInputFormat + "' is incorrect.\nPlease update your input and run the program again.");
    }

    // error message to be displayed with the parking file format doesn't match the parking file type
    public static void parkingFileMismatch(String parkingFileInputFormat, String parkingFileNameFileType){
        System.out.println("The parking file type does not match the input format specified.");
        System.out.println("Value entered as the Parking File Input Format: " + parkingFileInputFormat);
        System.out.println("File format of the Parking File: " + parkingFileNameFileType);
    }

    // error message to be displayed with the user does not input an integer in the range of 0 to 6
    public static void incorrectMainMenuInput(int userMenuSelection){
        System.out.println();
        System.out.println("Your selection of " + userMenuSelection + " is incorrect. Please review the menu options and" +
                " try again. Note that your input must be an integer value between 0 and 6, inclusive.");
        System.out.println();
    }

    public static void incorrectMainMenuInput(){
        System.out.println();
        System.out.println("Incorrect input. Your input must be an integer value between 0 and 6.");
        System.out.println();
    }

    public static void incorrectZipInput(){
        System.out.println();
        System.out.println("Incorrect input. Please input a 5 digit zip consisting only of integers.");
    }

    public static void incorrectLogFileInput(){
        System.out.println();
        System.out.println("The log file specified in the CLI arguments is incorrect. Please check your input and " +
                "run the program again.");
    }

    public static void incorrectLogFileAccess(){
        System.out.println();
        System.out.println("The log file was not able to be accessed. Please check your input and the log file (if it exists)" +
                " and run the program again.");
    }

    public static void incorrectRangeInput(){
        System.out.println("You must input an integer between 0 and 6, inclusive.");
        System.out.println();

    }
}
