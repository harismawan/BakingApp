package com.harismawan.bakingapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.adapter.RecipeRecyclerAdapter;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.model.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_recipe) RecyclerView recyclerRecipe;

    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));

        DatabaseHelper db = new DatabaseHelper(this);
        Query query = new Query(db);

        if (getResources().getBoolean(R.bool.isTablet)) {
            mGridLayoutManager = new GridLayoutManager(this, 5);
        } else {
            mGridLayoutManager = new GridLayoutManager(this, 2);
        }

        recyclerRecipe.setLayoutManager(mGridLayoutManager);

        initRecyclerView(query.getRecipeList());

        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt(Constants.EXTRA_KEY_CURRENT_POSITION);
            mGridLayoutManager.scrollToPosition(position);
        }
    }

    private void initRecyclerView(ArrayList<Recipe> recipes) {
        RecipeRecyclerAdapter adapter = new RecipeRecyclerAdapter(recipes);
        recyclerRecipe.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.EXTRA_KEY_CURRENT_POSITION, mGridLayoutManager.findFirstVisibleItemPosition());
    }
}
