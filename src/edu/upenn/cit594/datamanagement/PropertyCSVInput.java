package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.PropertyObj;
import edu.upenn.cit594.logging.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PropertyCSVInput {
    private ArrayList<PropertyObj> propertyObjs;
    private BufferedReader reader;

    public PropertyCSVInput(String fileName) throws IOException {
        try {
            Logger.getInstance().printToLog(System.currentTimeMillis() + " " + fileName);
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e){
            System.out.println("The file '" + fileName + "' could not be found. Please check your inputs and try again.");
            System.exit(0);
        }
        String currentLine;
        propertyObjs = new ArrayList<>();
        while((currentLine = reader.readLine())!= null){
            // create a String array by splitting each line from the CSV using comma as a delimiter
            // note that the regex handles the case where values are passed in surrounded by quotes with commas included as part of the data
            String[] currentLineArray = currentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if(currentLineArray[34].equals("market_value")){
                continue;
            }

            if(currentLineArray.length <= 77){
                // create string variables for the data fields
                // these will be used for handling nulls and improperly formatted data
                String mktValue = currentLineArray[34];
                String totalLivable = currentLineArray[64];
                String zip = currentLineArray[72].trim();

                // create data field variables to be passed to the PropertyObj upon creation
                long marketValue;
                long totalLivableArea;
                int zipCode;

                // handle Market Value data field. if the data in the field is invalid, skip to next iteration of the loop.
                if(mktValue == null || mktValue.length() == 0){
                    continue;
                } else {
                    marketValue = (long) Double.parseDouble(mktValue);
                }

                // handle Total Livable Area data field. if the data in the field is invalid, skip to next iteration of the loop.
                if(totalLivable == null || totalLivable.length() == 0){
                    continue;
                } else {
                    totalLivableArea = (long) Double.parseDouble(totalLivable);
                }


                // handle zip code data field. if the data in the field is invalid, skip to next iteration of the loop.
                if(Pattern.compile("\\d{5}").matcher(zip).find()) {
                    zipCode = Integer.parseInt(zip.substring(0, 5));
                } else {
                    continue;
                }

                // create a property object using the "cleaned" fields from above
                PropertyObj propertyObj = new PropertyObj(marketValue, totalLivableArea, zipCode);
                propertyObjs.add(propertyObj);
            }
        }
    }

    public ArrayList<PropertyObj> getPropertyObjs(){
        return propertyObjs;
    }
}