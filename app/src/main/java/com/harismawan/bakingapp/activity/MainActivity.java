package com.harismawan.bakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.model.Recipe;
import eu.davidea.flexibleadapter.FlexibleAdapter;

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

    private void initRecyclerView(final ArrayList<Recipe> recipes) {
        FlexibleAdapter<Recipe> adapter = new FlexibleAdapter<>(recipes);
        adapter.addListener(new FlexibleAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(int position) {
                Intent change = new Intent(MainActivity.this, ListDetailActivity.class);
                change.putExtra(Constants.EXTRA_KEY_ID, recipes.get(position).id);
                startActivity(change);
                return false;
            }
        });
        recyclerRecipe.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.EXTRA_KEY_CURRENT_POSITION, mGridLayoutManager.findFirstVisibleItemPosition());
    }
}
