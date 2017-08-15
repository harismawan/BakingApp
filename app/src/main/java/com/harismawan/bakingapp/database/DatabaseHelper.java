package com.harismawan.bakingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "baking_app";
    private static final int DB_VERSION = 2;

    static final String TABLE_RECIPE = "recipe";
    static final String TABLE_INGREDIENT = "ingredient";
    static final String TABLE_STEP = "step";

    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_IMAGE = "image";
    static final String COLUMN_SERVINGS = "servings";
    static final String COLUMN_QUANTITY = "quantity";
    static final String COLUMN_MEASURE = "measure";
    static final String COLUMN_INGREDIENT = "ingredient";
    static final String COLUMN_SHORT_DESC = "short_desc";
    static final String COLUMN_DESC = "description";
    static final String COLUMN_VIDEO_URL = "video_url";
    static final String COLUMN_THUMBNAIL_URL = "thumbnail_url";
    static final String COLUMN_RECIPE_ID = "recipe_id";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RECIPE + " ("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_NAME + " VARCHAR NOT NULL, "
                + COLUMN_IMAGE + " VARCHAR NOT NULL, "
                + COLUMN_SERVINGS + " INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_INGREDIENT + " ("
                + COLUMN_QUANTITY + " FLOAT NOT NULL, "
                + COLUMN_MEASURE + " VARCHAR NOT NULL, "
                + COLUMN_INGREDIENT + " VARCHAR NOT NULL, "
                + COLUMN_RECIPE_ID + " INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STEP + " ("
                + COLUMN_ID + " INTEGER NOT NULL, "
                + COLUMN_SHORT_DESC + " VARCHAR NOT NULL, "
                + COLUMN_DESC + " VARCHAR NOT NULL, "
                + COLUMN_VIDEO_URL + " VARCHAR NOT NULL, "
                + COLUMN_THUMBNAIL_URL + " VARCHAR NOT NULL, "
                + COLUMN_RECIPE_ID + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STEP);
        onCreate(db);
    }
}
