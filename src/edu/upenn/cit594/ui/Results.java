package edu.upenn.cit594.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Results {
    private static DecimalFormat df = new DecimalFormat("##.####");
    private static DecimalFormat dn = new DecimalFormat("##.#");

    public static void printTotalPopulation(long totalPop){
        System.out.println((int) totalPop);
    }

    public static void printTotalFinesPerCapita(double zip, double value){
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println((int) zip + " " + df.format(value));
    }

    public static void printTotalAreaValueTimesFinesPerCapita (double zip, double value) {
        dn.setRoundingMode(RoundingMode.DOWN);
        System.out.println((int) zip + " " + dn.format(value));
    }

    public static void printGeneral(double value){
        System.out.println((int) value);
    }

    public static void printGeneral(long value){
        System.out.println((int) value);
    }
}