package com.example.android.bakingapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.bakingapp.db.RecipesContract;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.utilities.NetworkUtils;
import com.example.android.bakingapp.utilities.RecipeCursorUtils;
import com.example.android.bakingapp.utilities.RecipeJsonUtils;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static int RECIPES_LOADER_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRecipeData();

        RecipeListFragment recipeListFragment = new RecipeListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_list_container, recipeListFragment)
                .commit();
    }


    private void loadRecipeData(){
        if (NetworkUtils.isOnlineOrConnecting(this)){
//            mLoadingBar.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = getSupportLoaderManager();
            Loader loader = loaderManager.getLoader(RECIPES_LOADER_ID);
            if (loader == null){
                loaderManager.initLoader(RECIPES_LOADER_ID, null, this);
            } else {
                loaderManager.restartLoader(RECIPES_LOADER_ID, null, this);
            }
        } else {
//            showErrorView();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Override
            public Cursor loadInBackground() {
                URL url = NetworkUtils.buildUrl();
                Cursor results = null;
                try{
                    String recipeResults = NetworkUtils.getResponseFromHttpUrl(url);
                    Recipe[] recipes = RecipeJsonUtils.convertJsonToRecipes(recipeResults);
                    int insertedRows = 0;
//                    TODO clear the db before this? or check if each one is already in there

                    for (int i = 0; i < recipes.length; i++){
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(RecipesContract.RecipesEntry.COLUMN_RECIPE_ID, recipes[i].getId());
                        contentValues.put(RecipesContract.RecipesEntry.COLUMN_RECIPE_NAME, recipes[i].getName());
                        contentValues.put(RecipesContract.RecipesEntry.COLUMN_RECIPE_IMAGE, recipes[i].getImage());
                        contentValues.put(RecipesContract.RecipesEntry.COLUMN_RECIPE_SERVINGS, recipes[i].getServings());
                        contentValues.put(RecipesContract.RecipesEntry.COLUMN_RECIPE_INGREDIENTS, RecipeJsonUtils.convertObjectArrayToJsonString(recipes[i].getIngredients()));
                        contentValues.put(RecipesContract.RecipesEntry.COLUMN_RECIPE_STEPS, RecipeJsonUtils.convertObjectArrayToJsonString(recipes[i].getSteps()));

                        Uri returnUri = getContentResolver().insert(RecipesContract.RecipesEntry.CONTENT_URI, contentValues);
                        if (returnUri != null){
                            insertedRows++;
                        }
                    }

                    results = getContentResolver().query(RecipesContract.RecipesEntry.CONTENT_URI,null, null, null, null);
                    Log.d("cursor returns: ", String.valueOf(results.getCount()));
                    Recipe[] newRecipes = RecipeCursorUtils.getRecipesFromCursor(results);

                } catch (Exception e){
                    e.printStackTrace();
                }
                return results;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
