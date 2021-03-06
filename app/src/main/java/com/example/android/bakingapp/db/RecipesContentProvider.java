package com.example.android.bakingapp.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by suzanneaitchison on 14/11/2017.
 */

public class RecipesContentProvider extends ContentProvider {

    private RecipesDbHelper mRecipesDbHelper;
    private static final int RECIPES = 100;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(RecipesContract.AUTHORITY, RecipesContract.PATH_RECIPES, RECIPES);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mRecipesDbHelper = new RecipesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] columns, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy) {
        final SQLiteDatabase db = mRecipesDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor recipes;
        switch(match){
            case RECIPES:
                recipes = db.query(RecipesContract.RecipesEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, orderBy);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }

        return recipes;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mRecipesDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch(match){
            case RECIPES:
                long id = db.insert(RecipesContract.RecipesEntry.TABLE_NAME, null, contentValues);
                if (id > 0){
                    returnUri = ContentUris.withAppendedId(RecipesContract.RecipesEntry.CONTENT_URI, id);
                } else {
                    returnUri = null;
                    throw new UnsupportedOperationException("Unknown Uri: " + uri);
                }
                break;
            default:
                returnUri = null;
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
