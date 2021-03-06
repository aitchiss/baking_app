package com.example.android.bakingapp.models;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public class IngredientSpecification {

    private int quantity;
    private MeasurementType measure;
    private String ingredient;

    public IngredientSpecification(int quantity, MeasurementType measure, String ingredient){
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public MeasurementType getMeasure() {
        return measure;
    }
}
