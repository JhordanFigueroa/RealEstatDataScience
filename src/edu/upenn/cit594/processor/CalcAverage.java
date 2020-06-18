package edu.upenn.cit594.processor;

import edu.upenn.cit594.ui.Results;

import java.util.HashMap;

public interface CalcAverage {
    void calculateAverage(HashMap<Integer, Long[]> inputMap, int zipCode);
}

class AvgResidentialMktValue implements CalcAverage {
    @Override
    public void calculateAverage(HashMap<Integer, Long[]> inputMap, int zipCode) {
        long totalMarketValue = inputMap.get(zipCode)[0];
        long totalResidences = inputMap.get(zipCode)[2];
        Results.printGeneral(totalMarketValue / totalResidences);
    }
}

class AverageResidentialLivableArea implements  CalcAverage {
    @Override
    public void  calculateAverage(HashMap<Integer, Long[]> inputMap, int zipCode) {
        long totalLivableArea = inputMap.get(zipCode)[1];
        long totalResidences = inputMap.get(zipCode)[2];
        Results.printGeneral(totalLivableArea / totalResidences);
    }
}