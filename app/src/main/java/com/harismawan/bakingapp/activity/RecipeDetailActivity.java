package com.harismawan.bakingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.utils.Utils;

public class RecipeDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_list) RecyclerView recyclerList;

    private int id;
    private int type;
    private Query query;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(getString(R.string.app_name));
        }

        Bundle recv = getIntent().getExtras();
        id = recv.getInt(Constants.EXTRA_KEY_ID);
        type = recv.getInt(Constants.EXTRA_KEY_TYPE);


        DatabaseHelper db = new DatabaseHelper(this);
        query = new Query(db);

        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerList.setLayoutManager(mLinearLayoutManager);

        Utils.initDetailRecyclerView(recyclerList, id, type, query);

        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt(Constants.EXTRA_KEY_CURRENT_POSITION);
            mLinearLayoutManager.scrollToPosition(position);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.EXTRA_KEY_CURRENT_POSITION, mLinearLayoutManager.findFirstVisibleItemPosition());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
