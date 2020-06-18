package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.PopulationObj;
import edu.upenn.cit594.logging.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PopulationTXTInput {
    protected String filename;
    private static ArrayList<PopulationObj> popData = new ArrayList<>();

    public PopulationTXTInput (String filename) {
        this.filename = filename;
        if(popData.isEmpty()){

           getAllPopulation();
        } else {
            getPopData();
        }
    }

    public ArrayList<PopulationObj> getAllPopulation() {
        if (filename.isEmpty()) {
            System.out.println("Population File is empty");
            return popData;
        }
        try {
            Logger.getInstance().printToLog(System.currentTimeMillis() + " " + filename);
            Scanner scanner = new Scanner(new FileReader(filename));

            while (scanner.hasNextLine()) {
                String eachRow = scanner.nextLine();
                if (eachRow == null) {
                    break;
                }
                else {
                    String[] rowSplit = eachRow.split(" ");
                    int populationZipCode = Integer.parseInt(rowSplit[0]);
                    int population = Integer.parseInt(rowSplit[1]);

                    PopulationObj popObj = new PopulationObj(populationZipCode, population);
                    popData.add(popObj);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return popData;
    }

    public ArrayList<PopulationObj> getPopData(){
        if(popData.isEmpty()){
            getAllPopulation();
        }
        return this.popData;
    }

}