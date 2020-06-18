package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.ui.ErrorMessages;

import java.io.File;

/**
 * This class will be used to handle errors for the file arguments input into the program
 */

public final class IOErrorHandling {

    public IOErrorHandling(String[] args) {
        String parkingFileInputFormat = args[0];
        String parkingFileName = args[1];
        // get the file type of the parking file (get all values after the period in the file name)
        String parkingFileNameFileType = parkingFileName.substring(parkingFileName.indexOf(".") + 1, parkingFileName.length());

        // check that the parking file input is either csv or json (case-sensitive)
        boolean parkingFileCSV = (parkingFileInputFormat.equals("csv"));
        boolean parkingFileJSON = (parkingFileInputFormat.equals("json"));

        // check the correct Parking File input format was specified. Exit if incorrect.
        if(!(parkingFileCSV || parkingFileJSON)){
            ErrorMessages.incorrectParkingFileFormat(parkingFileInputFormat);
            System.exit(0);
        }

        // check the Parking file format matches the format input (e.g. if the user specifies the file is json, the file should be json)
        if(!(parkingFileInputFormat.equals(parkingFileNameFileType))){
            ErrorMessages.parkingFileMismatch(parkingFileInputFormat, parkingFileNameFileType);
            System.exit(0);
        }

        // check that system is able to access the Parking, Property and Population files
        // file names are provided as CLI arguments for args[1] thru args[3]
        // the below 'for' loop checks that each of these files is able to be read successfully
        for(int i = 1; i <= 3; i++){
            File file = new File(args[i]);
            if(!file.canRead()){
                ErrorMessages.unsuccessfulFileRead(args[i]);
                System.exit(0);
            }
        }
    }
}