package edu.upenn.cit594.processor;

import edu.upenn.cit594.ui.Results;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class TotalAreaValueTimesFinesPerCapita {
    private static TreeMap<Integer, Integer> populationByZip = PopulationByZip.getPopulationByZip();
    private static TreeMap<Integer, Long> finesByZip;
    private static HashMap<Integer, Long[]> residentialDataByZip;
    private double finesPerCapita;
    private static double areaValueTimesFinesPerCapita;

    static {
        try {
            finesByZip = FinesByZip.getFinesByZip();
            residentialDataByZip = ResidentialDataByZip.getResidentialDataByZip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TotalAreaValueTimesFinesPerCapita() {
        for(int zipCode : finesByZip.keySet()){
            /* BEGIN POPULATION CHECKS */
            if(populationByZip.get(zipCode) == null || finesByZip.get(zipCode) == null){
                continue;
            }
            double populationForZip = populationByZip.get(zipCode);
            double finesForZip = finesByZip.get(zipCode);

            Long[] propertyDataByZip = residentialDataByZip.get(zipCode);
            long marketValue = propertyDataByZip[0];
            long totalLivableArea = propertyDataByZip[1];


            if(populationForZip <= 0 || finesForZip <= 0){
                continue;
            }

            double pricePerSquareInch = marketValue / totalLivableArea;
            finesPerCapita = calcTotalFinesPerCapita(populationForZip, finesForZip);

            areaValueTimesFinesPerCapita = pricePerSquareInch * finesPerCapita;


            Results.printTotalAreaValueTimesFinesPerCapita(zipCode, areaValueTimesFinesPerCapita);
        }
    }

    public double calcTotalFinesPerCapita(double pop, double fines){
        return fines / pop;
    }

    public static double getAreaValueTimesFinesPerCapita() {
        return areaValueTimesFinesPerCapita;
    }
}
