package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Globals;
import edu.upenn.cit594.data.PopulationObj;
import edu.upenn.cit594.datamanagement.PopulationTXTInput;

import java.util.ArrayList;
import java.util.TreeMap;

/***
 * The purpose of this class is to calculate the Total Population data.
 * These totals are grouped by zip code. The data structure used is a TreeMap, where the key (integer) is the zip code,
 * and the values are the total population amounts for each respective zip code.
 ***/

public class PopulationByZip {
    private static final String POPULATION_FILENAME = Globals.getPopulationInputFile();
    private static int totalPop = 0;
    private static TreeMap<Integer, Integer> populationByZip = new TreeMap<>();


    public static TreeMap<Integer, Integer> getTotalPopByZip (ArrayList<PopulationObj> popData) {
        for(PopulationObj pop : popData){
            int currentZip = pop.getZipCode();
            int currentPop = pop.getPopulation();
            totalPop += currentPop;

            if(populationByZip.containsKey(currentZip)){
                int increaseZipPop = populationByZip.get(currentZip) + currentPop;
                populationByZip.put(currentZip, increaseZipPop);

            } else {
                populationByZip.put(currentZip, currentPop);
            }
        }

        return populationByZip;
    }

    public int getTotalPop(){
        processPopData();

        return this.totalPop;
    }

    public static TreeMap<Integer, Integer> getPopulationByZip(){
        if((populationByZip.isEmpty() || populationByZip == null)) {
            processPopData();
        }
        return populationByZip;
    }

    public static void printPopByZip(TreeMap<Integer, Integer> populationByZip){
        for(int i : populationByZip.keySet()){
            System.out.println("Zip: " + i + " ; Pop: " + populationByZip.get(i));
        }
    }

    public static void processPopData(){
        getTotalPopByZip(new PopulationTXTInput(POPULATION_FILENAME).getPopData());
    }
}
