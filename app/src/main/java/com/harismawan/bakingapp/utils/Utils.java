package com.harismawan.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.model.Ingredient;
import com.harismawan.bakingapp.model.Step;
import eu.davidea.flexibleadapter.FlexibleAdapter;

public final class Utils {

    private Utils() {

    }

    public static APIHelper getAPIHelper() {
        return RetrofitClient.getClient(Constants.BASE_URL).create(APIHelper.class);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void initDetailRecyclerView(RecyclerView recyclerList, int id, int type, Query query) {
        if (type == Constants.TYPE_INGREDIENT) {
            FlexibleAdapter<Ingredient> adapter = new FlexibleAdapter<>(query.getIngredientList(id));
            recyclerList.setAdapter(adapter);
        } else {
            FlexibleAdapter<Step> adapter = new FlexibleAdapter<>(query.getStepList(id));
            recyclerList.setAdapter(adapter);
        }
        recyclerList.setVerticalScrollBarEnabled(false);
    }

    public static void pausePlayer() {
        for (SimpleExoPlayer p : Constants.activePlayer) {
            p.setPlayWhenReady(false);
        }
    }

    public static void releasePlayer() {
        for (SimpleExoPlayer p : Constants.activePlayer) {
            p.release();
        }
        Constants.activePlayer.clear();
    }
}
