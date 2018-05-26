package com.lern.baby.seti.bilans.DataModel;


public class DataObject {

    private String QuadNumber;
    private String GasKind;
    private String QuadCapacity;
    private String QuadPressure;



    public DataObject(String QN, String GK, String QC, String QP) {
        this.QuadNumber = QN;
        this.GasKind = GK;
        this.QuadCapacity = QC;
        this.QuadPressure = QP;

    }


    public String getQuadNumber() {
        return QuadNumber;
    }

    public String getGasKind() {
        return GasKind;
    }

    public String getQuadCapacity() {
        return QuadCapacity;
    }

    public String getQuadPressure() {
        return QuadPressure;
    }

}
