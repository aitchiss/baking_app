package com.example.android.bakingapp.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public class RecipesContract {

    public static final String AUTHORITY = "com.example.android.bakingapp";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_RECIPES = "recipes";
    public static final String PATH_INGREDIENTS = "ingredients";

    public static final class RecipesEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();
        public static final String TABLE_NAME = "recipes";

        public static final String COLUMN_RECIPE_ID = "recipeId";
        public static final String COLUMN_RECIPE_NAME = "recipeName";
        public static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
        public static final String COLUMN_RECIPE_STEPS = "steps";

    }
}
