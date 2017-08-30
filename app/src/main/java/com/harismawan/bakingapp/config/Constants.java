package com.harismawan.bakingapp.config;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.harismawan.bakingapp.model.Ingredient;

import java.util.ArrayList;

public final class Constants {

    private Constants() {

    }

    public static ArrayList<Ingredient> widget = new ArrayList<>();

    public static ArrayList<SimpleExoPlayer> activePlayer = new ArrayList<>();

    public static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public final static String EXTRA_KEY_CURRENT_POSITION = "current_position";
    public final static String EXTRA_KEY_ID = "id";
    public final static String EXTRA_KEY_TYPE = "type";

    public final static int TYPE_INGREDIENT = 0;
    public final static int TYPE_STEP = 1;
}
