package edu.upenn.cit594.data;

public class PopulationObj {
    private int zipCode;
    private int population;

    public PopulationObj(Integer zipCode, Integer population) {
        this.zipCode = zipCode;
        this.population = population;
    }

    //Getters and Setters
    public int getZipCode() {
        return zipCode;
    }

    public int getPopulation() {
        return population;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
