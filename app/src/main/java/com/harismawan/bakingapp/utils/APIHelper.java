package com.harismawan.bakingapp.utils;

import com.harismawan.bakingapp.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.ArrayList;

public interface APIHelper {

    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipes();

}