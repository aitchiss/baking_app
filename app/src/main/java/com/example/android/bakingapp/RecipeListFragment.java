package com.example.android.bakingapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.db.RecipesContract;
import com.example.android.bakingapp.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by suzanneaitchison on 24/11/2017.
 */

public class RecipeListFragment extends Fragment {

    @BindView(R.id.recipe_card_constraint_layout)
    ConstraintLayout mRecipeCardLayout;

    @BindView(R.id.fragment_recipe_title)
    TextView mRecipeTextView;

    Recipe[] mRecipes;

    public RecipeListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, rootView);
        mRecipeTextView.setText("testing");
        return rootView;
    }
}
