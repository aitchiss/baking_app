package com.example.android.bakingapp.models;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public enum MeasurementType {

    CUP,
    TBLSP,
    TSP,
    K,
    G,
    OZ,
    UNIT;

    public static MeasurementType getType(String typeName) {
        switch (typeName.toLowerCase()){
            case "cup":
                return CUP;
            case "tblsp":
                return TBLSP;
            case "tsp":
                return TSP;
            case "k":
                return K;
            case "g":
                return G;
            case "oz":
                return OZ;
            case "unit":
                return UNIT;
            default:
                return UNIT;
        }
    }
}
