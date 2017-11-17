package com.example.android.bakingapp;

import android.database.Cursor;
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
import com.example.android.bakingapp.utilities.RecipeJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static int RECIPES_LOADER_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRecipeData();
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
                    Log.d("results", String.valueOf(recipes.length));
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
