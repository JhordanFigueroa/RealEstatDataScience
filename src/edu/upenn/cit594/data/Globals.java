package edu.upenn.cit594.data;


import edu.upenn.cit594.ui.ErrorMessages;

import java.util.Scanner;

public final class Globals {
    public static Scanner scan = new Scanner(System.in);

    private static String parkingInputFileType;
    private static String parkingInputFile;
    private static String propertyInputFile;
    private static String populationInputFile;
    private static String logFile;

    public Globals(String[] args){
        checkArgs(args);

        this.parkingInputFileType = args[0];
        this.parkingInputFile = args[1];
        this.propertyInputFile = args[2];
        this.populationInputFile = args[3];
        this.logFile = args[4];
    }

    public static void checkArgs(String[] args){
        // check that all 5 required arguments have been input at the CLI
        if (args.length < 5) {
            ErrorMessages.errorInvalidArguments();
            System.exit(0);
        }
    }

    public static String getParkingInputFileType() {
        return parkingInputFileType;
    }

    public static String getParkingInputFile() {
        return parkingInputFile;
    }

    public static String getPropertyInputFile() {
        return propertyInputFile;
    }

    public static String getPopulationInputFile() {
        return populationInputFile;
    }

    public static String getLogFile() {
        return logFile;
    }
}
