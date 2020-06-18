package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.ParkingObj;
import edu.upenn.cit594.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ParkingJSONInput {
    protected String filename;

    public ParkingJSONInput (String filename) {
        this.filename = filename;
    }

    public ArrayList<ParkingObj> getAllParkingData() {
        ArrayList<ParkingObj> parkingData = new ArrayList<>();
        if (filename.isEmpty()) {
            System.out.println("Parking JSON File failed to be read\n");
            return parkingData;
        }

        JSONParser parser = new JSONParser();
        try {
            Logger.getInstance().printToLog(String.valueOf(System.currentTimeMillis()) + " " + filename);
            JSONArray ParkingObjs = (JSONArray) parser.parse(new FileReader(filename));
            Iterator iter = ParkingObjs.iterator();

            while (iter.hasNext()) {
                JSONObject parkingObjJSONObject = (JSONObject) iter.next();

                String stringZipCode = (String) parkingObjJSONObject.get("zip_code");
                Long stringFine = (Long) parkingObjJSONObject.get("fine");
                String state = (String) parkingObjJSONObject.get("state");

                int zipCode = -Integer.MAX_VALUE;
                long fine = - Long.MAX_VALUE;

                if(!(stringZipCode.isEmpty() || stringZipCode == null)){
                    zipCode = Integer.parseInt(stringZipCode);
                } else {
                    continue;
                }

                if(stringFine != null){
                    fine = stringFine;
                } else {
                    continue;
                }

                if(state == null || state == ""){
                    continue;
                }

                ParkingObj parkingObj = new ParkingObj(zipCode, fine, state);

                parkingData.add(parkingObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

////        Testing for correct output
//        for (ParkingObj p : parkingData) {
//            System.out.println(p.getZipCode() + " " + p.getFine() + " " + p.getState());
//        }
        return parkingData;
    }
}
