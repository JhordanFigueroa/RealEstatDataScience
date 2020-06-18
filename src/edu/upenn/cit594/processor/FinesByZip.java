package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Globals;
import edu.upenn.cit594.data.ParkingObj;
import edu.upenn.cit594.datamanagement.ParkingCSVInput;
import edu.upenn.cit594.datamanagement.ParkingJSONInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/***
 * The purpose of this class is to calculate the Total Fines data.
 * These totals are grouped by zip code. The data structure used is a TreeMap, where the key (integer) is the zip code,
 * and the values are the total fine amounts for each respective zip code.
 ***/

public class FinesByZip {
    public static final String PARKING_FILE_TYPE = Globals.getParkingInputFileType();
    public static final boolean PARKING_FILE_TYPE_JSON = PARKING_FILE_TYPE.equals("json");
    public static final String PARKING_FILE_NAME = Globals.getParkingInputFile();

    public static TreeMap<Integer, Long> finesByZip = new TreeMap<>();

    public static TreeMap<Integer, Long> getTotalFinesByZip(ArrayList<ParkingObj> parkingObjs){
        for(ParkingObj p : parkingObjs){
            int currentZip = p.getZipCode();
            long currentFine = p.getFine();
            String currentState = p.getState();

            if(!currentState.toUpperCase().equals("PA")){
                continue;
            }

            if(finesByZip.containsKey(currentZip)){
                long increaseFine = finesByZip.get(currentZip) + currentFine;
                finesByZip.put(currentZip, increaseFine);
            } else {
                finesByZip.put(currentZip, currentFine);
            }
        }
        return finesByZip;
    }


    public static void processParkingData() throws IOException {
        if(PARKING_FILE_TYPE_JSON){
            getTotalFinesByZip(new ParkingJSONInput(PARKING_FILE_NAME).getAllParkingData());
        } else {
            getTotalFinesByZip(new ParkingCSVInput(PARKING_FILE_NAME).getParkingObjs());
        }
    }

    public void printFinesByZip(TreeMap<Integer, Long> finesByZip){
        for(int i : finesByZip.keySet()){
            System.out.println("Zip Code: " + i + " + Total Fines: " + finesByZip.get(i));
        }
    }

    public static TreeMap<Integer, Long> getFinesByZip() throws IOException {
        if((finesByZip.isEmpty() || finesByZip == null)) {
            processParkingData();
        }

        return finesByZip;
    }
}
