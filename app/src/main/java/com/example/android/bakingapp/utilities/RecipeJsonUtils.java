package com.example.android.bakingapp.utilities;

import android.util.Log;

import com.example.android.bakingapp.models.IngredientSpecification;
import com.example.android.bakingapp.models.MeasurementType;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.RecipeStep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by suzanneaitchison on 17/11/2017.
 */

public class RecipeJsonUtils {

    public static Recipe[] convertJsonToRecipes(String jsonRecipesString) throws JSONException{
//        Keys to JSON data
        final String RECIPE_ID = "id";
        final String RECIPE_NAME = "name";
        final String INGREDIENTS = "ingredients";
        final String STEPS = "steps";
        final String SERVINGS = "servings";
        final String IMAGE = "image";

        JSONArray jsonRecipes = new JSONArray(jsonRecipesString);
        Recipe[] recipes = new Recipe[jsonRecipes.length()];

        for (int i = 0; i < jsonRecipes.length(); i++){
            JSONObject jsonRecipe = jsonRecipes.getJSONObject(i);

            int id = jsonRecipe.getInt(RECIPE_ID);
            String name = jsonRecipe.getString(RECIPE_NAME);
            int servings = jsonRecipe.getInt(SERVINGS);
            String image = jsonRecipe.getString(IMAGE);

            IngredientSpecification[] ingredients = convertJsonToIngredientSpecs(jsonRecipe.getString(INGREDIENTS));
            RecipeStep[] steps = convertJsonToRecipeSteps(jsonRecipe.getString(STEPS));

            Recipe recipe = new Recipe(id, name);
            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);
            recipe.setImage(image);
            recipe.setServings(servings);

            recipes[i] = recipe;
        }
        return recipes;
    }

    private static IngredientSpecification[] convertJsonToIngredientSpecs(String jsonIngredientsString) throws JSONException{
//        Keys to JSON ingredient data
        final String QUANTITY = "quantity";
        final String MEASURE = "measure";
        final String INGREDIENT = "ingredient";

        JSONArray jsonIngredients = new JSONArray(jsonIngredientsString);
        IngredientSpecification[] ingredients = new IngredientSpecification[jsonIngredients.length()];

        for (int i = 0; i < jsonIngredients.length(); i++){
            JSONObject jsonIngredientSpec = jsonIngredients.getJSONObject(i);

            int quantity = jsonIngredientSpec.getInt(QUANTITY);
            MeasurementType measurement = MeasurementType.getType(jsonIngredientSpec.getString(MEASURE));
            String ingredient = jsonIngredientSpec.getString(INGREDIENT);

            IngredientSpecification ingredientSpecification = new IngredientSpecification(quantity, measurement, ingredient);
            ingredients[i] = ingredientSpecification;
        }
        return ingredients;
    }

    private static RecipeStep[] convertJsonToRecipeSteps(String jsonRecipeStepsString) throws JSONException{
//        Keys to JSON recipe step data
        final String ID = "id";
        final String SHORT_DESC = "shortDescription";
        final String DESCRIPTION = "description";
        final String VIDEO_URL = "videoURL";
        final String THUMBAIL_URL = "thumbnailURL";

        JSONArray jsonRecipeSteps = new JSONArray(jsonRecipeStepsString);
        RecipeStep[] recipeSteps = new RecipeStep[jsonRecipeSteps.length()];

        for (int i = 0; i < jsonRecipeSteps.length(); i++){
            JSONObject jsonRecipeStep = jsonRecipeSteps.getJSONObject(i);

            int id = jsonRecipeStep.getInt(ID);
            String shortDescription = jsonRecipeStep.getString(SHORT_DESC);
            String description = jsonRecipeStep.getString(DESCRIPTION);
            String videoURL = jsonRecipeStep.getString(VIDEO_URL);
            String thumnailURL = jsonRecipeStep.getString(THUMBAIL_URL);

            RecipeStep step = new RecipeStep(id, shortDescription, description, videoURL, thumnailURL);
            recipeSteps[i] = step;
        }
        return recipeSteps;
    }
}
