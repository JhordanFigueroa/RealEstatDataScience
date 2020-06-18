package edu.upenn.cit594.processor;

import edu.upenn.cit594.ui.Results;

import java.io.IOException;
import java.util.TreeMap;

public class TotalFinesPerCapita {
    private static TreeMap<Integer, Integer> populationByZip = PopulationByZip.getPopulationByZip();
    private static TreeMap<Integer, Long> finesByZip;
    private static double finesPerCapita;

    static {
        try {
            finesByZip = FinesByZip.getFinesByZip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TotalFinesPerCapita () {
        for(int zipCode : finesByZip.keySet()){
            /* BEGIN POPULATION CHECKS */
            if(populationByZip.get(zipCode) == null){
                continue;
            }
            double populationForZip = populationByZip.get(zipCode);

            if(populationForZip <= 0){
                continue;
            }
            /* END POPULATION CHECKS */

            /* BEGIN FINE CHECKS*/
            if(finesByZip.get(zipCode) == null){
                continue;
            }

            double finesForZip = finesByZip.get(zipCode);

            if(finesForZip <= 0){
                continue;
            }
            /* END FINE CHECKS */

            finesPerCapita = calcTotalFinesPerCapita(populationForZip, finesForZip);

            Results.printTotalFinesPerCapita(zipCode, finesPerCapita);
        }
    }

    public double calcTotalFinesPerCapita(double pop, double fines){
        return fines / pop;
    }

    public double getFinesPerCapita(){
        return finesPerCapita;
    }
}
