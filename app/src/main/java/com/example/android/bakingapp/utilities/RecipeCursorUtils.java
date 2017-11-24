package com.example.android.bakingapp.utilities;

import android.database.Cursor;
import android.util.Log;

import com.example.android.bakingapp.db.RecipesContract;
import com.example.android.bakingapp.models.Recipe;

/**
 * Created by suzanneaitchison on 24/11/2017.
 */

public class RecipeCursorUtils {

    public static Recipe[] getRecipesFromCursor(Cursor cursor){
        Recipe[] recipes = new Recipe[cursor.getCount()];
        while (cursor.moveToNext()){

            int columnId = cursor.getColumnIndex(RecipesContract.RecipesEntry.COLUMN_RECIPE_ID);
            int id = cursor.getInt(columnId);

            int columnName = cursor.getColumnIndex(RecipesContract.RecipesEntry.COLUMN_RECIPE_NAME);
            String name = cursor.getString(columnName);
            Recipe recipe = new Recipe(id, name);

            recipes[cursor.getPosition()] = recipe;
            Log.d("recipe: ", recipe.getName());
        }

        return recipes;
    }
}
