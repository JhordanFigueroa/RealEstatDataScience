package edu.upenn.cit594.processor;

import java.util.HashMap;

public class CalcAverageContext {
    private CalcAverage calcAverage;

    public void setCalcAverage(CalcAverage calcAverage){
        this.calcAverage = calcAverage;
    }

    public void calculateAverage(HashMap<Integer, Long[]> inputMap, int zipCode){
        calcAverage.calculateAverage(inputMap, zipCode);
    }
}
