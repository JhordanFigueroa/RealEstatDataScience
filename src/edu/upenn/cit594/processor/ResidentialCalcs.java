package edu.upenn.cit594.processor;

import edu.upenn.cit594.ui.Results;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class ResidentialCalcs {
    CalcAverageContext calcAverageContext = new CalcAverageContext();

    private static TreeMap<Integer, Integer> populationByZip = PopulationByZip.getPopulationByZip();
    private static HashMap<Integer, Long[]> residentialDataByZip;

    static {
        try {
            residentialDataByZip = ResidentialDataByZip.getResidentialDataByZip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMktValuePerCap(int zipCode){
        double numeratorMktValue;
        double denominatorPopulation;
        double mktValuePerCap;

        if(residentialDataByZip.containsKey(zipCode)){
            numeratorMktValue = residentialDataByZip.get(zipCode)[0];
        } else {
            numeratorMktValue = 0;
        }

        if(populationByZip.containsKey(zipCode)){
            denominatorPopulation = populationByZip.get(zipCode);
        } else {
            denominatorPopulation = 0;
        }

        if(denominatorPopulation == 0 || numeratorMktValue == 0){
            mktValuePerCap = 0;
        } else {
            mktValuePerCap = numeratorMktValue / denominatorPopulation;
        }

        Results.printGeneral(mktValuePerCap);
    }

    public void averageResidentialLivableArea(int zipCode){
        calcAverageContext.setCalcAverage(new AverageResidentialLivableArea());
        calcAverageContext.calculateAverage(residentialDataByZip, zipCode);
    }

    public void averageResidentialMarketValue(int zipCode){
        calcAverageContext.setCalcAverage(new AvgResidentialMktValue());
        calcAverageContext.calculateAverage(residentialDataByZip, zipCode);
    }


}
