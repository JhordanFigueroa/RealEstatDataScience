package edu.upenn.cit594;

import edu.upenn.cit594.data.Globals;
import edu.upenn.cit594.datamanagement.IOErrorHandling;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Handler;

import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException {
        // store the CLI args as static properties in the StoredArgs class, which is a final class
        new Globals(args);

        // create logger, log time, and log arguments from CLI
        Logger.getInstance().printToLog(String.valueOf(System.currentTimeMillis() + " " +
                Globals.getParkingInputFileType() + " " +
                Globals.getParkingInputFile() + " " +
                Globals.getPopulationInputFile() + " " +
                Globals.getPropertyInputFile() + " " +
                Globals.getLogFile()));

        // run IO Error Handling
        new IOErrorHandling(args);

        // start the program 19154
        new Handler();
    }
}
