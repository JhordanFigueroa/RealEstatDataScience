package edu.upenn.cit594.data;

public class PropertyObj {
    private long marketValue;
    private long totalLivableArea;
    private int zipCode;

    public PropertyObj(long marketValue, long totalLivableArea, int zipCode){
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
        this.zipCode = zipCode;
    }

    public long getMarketValue() {
        return marketValue;
    }

    public long getTotalLivableArea() {
        return totalLivableArea;
    }

    public int getZipCode() {
        return zipCode;
    }
}
