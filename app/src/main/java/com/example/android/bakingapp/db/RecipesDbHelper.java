package com.example.android.bakingapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public class RecipesDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 2;

    public RecipesDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_RECIPES_TABLE = "CREATE TABLE " + RecipesContract.RecipesEntry.TABLE_NAME + " (" +
                RecipesContract.RecipesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecipesContract.RecipesEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL, " +
                RecipesContract.RecipesEntry.COLUMN_RECIPE_NAME + " TEXT, " +
                RecipesContract.RecipesEntry.COLUMN_RECIPE_INGREDIENTS + " TEXT, " +
                RecipesContract.RecipesEntry.COLUMN_RECIPE_STEPS + " TEXT, " +
                RecipesContract.RecipesEntry.COLUMN_RECIPE_SERVINGS + " INTEGER, " +
                RecipesContract.RecipesEntry.COLUMN_RECIPE_IMAGE + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_RECIPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipesContract.RecipesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
