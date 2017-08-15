package com.harismawan.bakingapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.harismawan.bakingapp.model.Ingredient;
import com.harismawan.bakingapp.model.Recipe;
import com.harismawan.bakingapp.model.Step;

import java.util.ArrayList;

public class Query {

    private DatabaseHelper dbHelper;

    public Query(DatabaseHelper db) {
        this.dbHelper = db;
    }

    public void cache(ArrayList<Recipe> recipes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        deleteCache(db);

        for (Recipe recipe : recipes) {
            ContentValues rValues = new ContentValues();
            rValues.put(DatabaseHelper.COLUMN_ID, recipe.id);
            rValues.put(DatabaseHelper.COLUMN_NAME, recipe.name);
            rValues.put(DatabaseHelper.COLUMN_IMAGE, recipe.image);
            rValues.put(DatabaseHelper.COLUMN_SERVINGS, recipe.servings);

            db.insert(DatabaseHelper.TABLE_RECIPE, null, rValues);

            for (Ingredient ingredient : recipe.ingredients) {
                ContentValues iValues = new ContentValues();
                iValues.put(DatabaseHelper.COLUMN_QUANTITY, ingredient.quantity);
                iValues.put(DatabaseHelper.COLUMN_MEASURE, ingredient.measure);
                iValues.put(DatabaseHelper.COLUMN_INGREDIENT, ingredient.ingredient);
                iValues.put(DatabaseHelper.COLUMN_RECIPE_ID, recipe.id);
                db.insert(DatabaseHelper.TABLE_INGREDIENT, null, iValues);
            }

            for (Step step : recipe.steps) {
                ContentValues sValues = new ContentValues();
                sValues.put(DatabaseHelper.COLUMN_ID, step.id);
                sValues.put(DatabaseHelper.COLUMN_SHORT_DESC, step.shortDescription);
                sValues.put(DatabaseHelper.COLUMN_DESC, step.description);
                sValues.put(DatabaseHelper.COLUMN_VIDEO_URL, step.videoURL);
                sValues.put(DatabaseHelper.COLUMN_THUMBNAIL_URL, step.thumbnailURL);
                sValues.put(DatabaseHelper.COLUMN_RECIPE_ID, recipe.id);
                db.insert(DatabaseHelper.TABLE_STEP, null, sValues);
            }
        }
    }

    private void deleteCache(SQLiteDatabase db) {
        db.delete(DatabaseHelper.TABLE_RECIPE, null, null);
        db.delete(DatabaseHelper.TABLE_INGREDIENT, null, null);
        db.delete(DatabaseHelper.TABLE_STEP, null, null);
    }

    public ArrayList<Recipe> getRecipeList() {
        ArrayList<Recipe> list = new ArrayList<>();
        Cursor mCursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM " +
                DatabaseHelper.TABLE_RECIPE, null);

        if (mCursor.moveToFirst()) {
            do {
                Recipe entry = new Recipe();
                entry.id = mCursor.getInt(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                entry.name = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                entry.image = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE));
                entry.servings = mCursor.getInt(mCursor.getColumnIndex(DatabaseHelper.COLUMN_SERVINGS));
                list.add(entry);
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        return list;
    }

    public ArrayList<Ingredient> getIngredientList(int recipeId) {
        ArrayList<Ingredient> list = new ArrayList<>();
        Cursor mCursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_INGREDIENT
                + " WHERE " + DatabaseHelper.COLUMN_RECIPE_ID + " = ?", new String[]{Integer.toString(recipeId)});

        if (mCursor.moveToFirst()) {
            do {
                Ingredient entry = new Ingredient();
                entry.quantity = mCursor.getFloat(mCursor.getColumnIndex(DatabaseHelper.COLUMN_QUANTITY));
                entry.ingredient = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_INGREDIENT));
                entry.measure = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_MEASURE));
                list.add(entry);
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        return list;
    }

    public ArrayList<Step> getStepList(int recipeId) {
        ArrayList<Step> list = new ArrayList<>();
        Cursor mCursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_STEP
                + " WHERE " + DatabaseHelper.COLUMN_RECIPE_ID + " = ?", new String[]{Integer.toString(recipeId)});

        if (mCursor.moveToFirst()) {
            do {
                Step entry = new Step();
                entry.id = mCursor.getInt(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                entry.shortDescription = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_SHORT_DESC));
                entry.description = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_DESC));
                entry.thumbnailURL = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_THUMBNAIL_URL));
                entry.videoURL = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_VIDEO_URL));
                list.add(entry);
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        return list;
    }
}
