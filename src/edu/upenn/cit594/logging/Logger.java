package edu.upenn.cit594.logging;

import edu.upenn.cit594.data.Globals;
import edu.upenn.cit594.ui.ErrorMessages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    // create a PrintWriter object
    PrintWriter out;
    // create a private variable to store the instance of the Logger (Singleton instance)
    private static Logger loggerInstance;

    //1. private constructor
    private Logger(String filename) throws IOException {
        boolean fileNameTest = filename.matches("(([a-zA-Z0-9\\\\s_\\\\\\\\.\\\\-\\\\(\\\\):])+(.txt)$)");

        if(!fileNameTest){
            ErrorMessages.incorrectLogFileInput();
            System.exit(0);
        }

        try {
            out = new PrintWriter(new FileWriter(filename, true));;
        } catch(Exception e) {
            ErrorMessages.incorrectLogFileAccess();
        }
    }


    // Singleton Accessor Method
    public static Logger getInstance() throws IOException {
        if (loggerInstance == null) {
            loggerInstance = new Logger(Globals.getLogFile());
        }
        return loggerInstance;
    }

    public void printToLog(String msg) {
        out.println(msg);
        out.flush();
    }
}
