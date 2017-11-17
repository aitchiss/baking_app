package com.example.android.bakingapp.models;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public class Recipe {

    private int id;
    private  String name;
    private RecipeStep[] steps;
    private IngredientSpecification[] ingredients;
    private int servings;
    private String image;

    public Recipe(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecipeStep[] getSteps() {
        return steps;
    }

    public void setSteps(RecipeStep[] steps) {
        this.steps = steps;
    }

    public IngredientSpecification[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientSpecification[] ingredients) {
        this.ingredients = ingredients;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
