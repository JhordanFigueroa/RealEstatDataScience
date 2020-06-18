package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Globals;
import edu.upenn.cit594.data.PropertyObj;
import edu.upenn.cit594.datamanagement.PropertyCSVInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/***
 * The purpose of this class is to calculate the Total Market Value and Total Livable area (from the properties) data.
 * These totals are grouped by zip code. The data structures used is a TreeMap, where the key (integer) is the zip code,
 * and the values are a long[] array containing the totalMarketValue and totalLivableArea.
 ***/

public class ResidentialDataByZip {
    private static String PROPERTY_CSV_FILE = Globals.getPropertyInputFile();
    private static HashMap<Integer, Long[]> residentialDataByZip = new HashMap<>();

    public static HashMap<Integer, Long[]> getTotalResidentialDataByZip(ArrayList<PropertyObj> propertyObjs){
        if(!(residentialDataByZip == null || residentialDataByZip.isEmpty())){
            return residentialDataByZip;
        }

        for(PropertyObj prop : propertyObjs){
            int currentZip = prop.getZipCode();
            long currentMarketValue = prop.getMarketValue();
            long currentLivableArea = prop.getTotalLivableArea();
            Long[] residentialData = new Long[3];

            if(residentialDataByZip.containsKey(currentZip)){
                long increaseMktValue = currentMarketValue + residentialDataByZip.get(currentZip)[0];
                long increaseLivableArea = currentLivableArea + residentialDataByZip.get(currentZip)[1];
                long increaseCount =  residentialDataByZip.get(currentZip)[2] + 1;

                residentialData[0] = increaseMktValue;
                residentialData[1] = increaseLivableArea;
                residentialData[2] = increaseCount;

                residentialDataByZip.put(currentZip, residentialData);

            } else {
                residentialData[0] = currentMarketValue;
                residentialData[1] = currentLivableArea;
                residentialData[2] = Long.valueOf(1);

                residentialDataByZip.put(currentZip, residentialData);
            }
        }

//        printResidentialDataByZip(residentialDataByZip);

        return residentialDataByZip;
    }

    public static HashMap<Integer, Long[]> getResidentialDataByZip() throws IOException {
        if((residentialDataByZip.isEmpty() || residentialDataByZip == null)) {
            processResidentialData();
        }
        return residentialDataByZip;
    }

    public static void processResidentialData() throws IOException {
        getTotalResidentialDataByZip(new PropertyCSVInput(PROPERTY_CSV_FILE).getPropertyObjs());
    }

    public void printResidentialDataByZip(){
        for(int i : residentialDataByZip.keySet()){
            System.out.println("Zip: " + i);
            System.out.println("Total Market Value: " + residentialDataByZip.get(i)[0]);
            System.out.println("Total Livable Area: " + residentialDataByZip.get(i)[1]);
            System.out.println("Total Residences in Zip: " + residentialDataByZip.get(i)[2]);
            System.out.println();
        }
    }
}
