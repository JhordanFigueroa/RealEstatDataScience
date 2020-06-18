package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.ParkingObj;
import edu.upenn.cit594.logging.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingCSVInput {
    private String fileName;
    private String fileType;
    private ArrayList<ParkingObj> parkingObjs;
    private BufferedReader reader;

    public ParkingCSVInput(String fileName) throws IOException {
        try {
            Logger.getInstance().printToLog(String.valueOf(System.currentTimeMillis()) + " " + fileName);
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e){
            System.out.println("The file '" + fileName + "' could not be found. Please check your inputs and try again.");
            System.exit(0);
        }
        int count = 0;

        String currentLine = null;
        parkingObjs = new ArrayList<>();
        while((currentLine = reader.readLine()) != null){
            // create a String array by splitting each line from the CSV using comma as a delimiter
            // note that the regex handles the case where values are passed in surrounded by quotes with commas included as part of the data
            String[] currentLineArray = currentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            /* we assume there are 7 values for each Parking record; however, since there are null values, want to
            convert the array to a List, and then to an ArrayList so that size doesn't limit the functionality.
            This is done since there could be null values in the Zip Code field */
            List<String> fixedLengthArray = Arrays.asList(currentLineArray);
            ArrayList<String> currentLineArrList = new ArrayList<>(fixedLengthArray);

            // handle the case where the ZIP Code field is null
            if(currentLineArrList.size() == 6){
                currentLineArrList.add(null);
            }
            count++;

            /* get values from Parking CSV file as strings -- will use conditional statements below to handle nulls and
            to convert to the correct data type*/
            String stringFine = currentLineArrList.get(1);
            String state = currentLineArrList.get(4);
            String stringZipCode = currentLineArrList.get(6);

            /* set default values for fine and zipCode -- if unable to parse to correct data type, can filter out records
            with these numbers */
            long fine;
            int zipCode;

            if(!(stringFine.isEmpty() || stringFine == null)){
                fine = Long.parseLong(stringFine);
            } else {
                continue;
            }

            if(state.equals(null) || state.equals("") || state.isEmpty()){
                continue;
            }

            if(!(stringZipCode == null || stringZipCode.isEmpty())){
                zipCode = Integer.parseInt(stringZipCode);
            } else {
                continue;
            }

            ParkingObj parkingObj = new ParkingObj(zipCode, fine, state);
            parkingObjs.add(parkingObj);
        }
    }

    public ArrayList<ParkingObj> getParkingObjs(){
        return parkingObjs;
    }
}