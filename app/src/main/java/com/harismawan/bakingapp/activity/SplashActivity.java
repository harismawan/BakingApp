package com.harismawan.bakingapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.model.Recipe;
import com.harismawan.bakingapp.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.root) LinearLayout root;
    @BindView(R.id.progress) ProgressBar progress;

    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        DatabaseHelper db = new DatabaseHelper(this);
        query = new Query(db);

        if (Utils.isConnected(this)) {
            loadData();
        } else {
            showSnackbar(R.string.no_connection);
        }
    }

    private boolean checkCache() {
        return query.getRecipeList().size() != 0;
    }

    private void startMainActivity() {
        Intent change = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(change);
        finish();
    }

    private void showSnackbar(int resId) {
        progress.setVisibility(View.GONE);

        Snackbar snackbar = Snackbar.make(root, resId, Snackbar.LENGTH_INDEFINITE);
        if (checkCache()) {
            snackbar.setActionTextColor(Color.WHITE).setAction(R.string.button_proceed, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startMainActivity();
                }
            });
        }
        snackbar.show();
    }

    private void loadData() {
        Utils.getAPIHelper().getRecipes().enqueue(new Callback<ArrayList<Recipe>>() {

            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                Log.d("response", response.toString());
                DatabaseHelper db = new DatabaseHelper(SplashActivity.this);
                Query query = new Query(db);
                query.cache(response.body());

                startMainActivity();
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.d("error", t.toString());
                showSnackbar(R.string.fail_connect_api);
            }
        });
    }
}
