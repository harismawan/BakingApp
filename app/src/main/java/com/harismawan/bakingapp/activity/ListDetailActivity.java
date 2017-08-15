package com.harismawan.bakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.fragment.FragmentRecipeDetail;
import com.harismawan.bakingapp.model.ListDetail;
import com.harismawan.bakingapp.model.Step;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.SelectableAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

import java.util.ArrayList;

public class ListDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_list) RecyclerView recyclerList;
    @Nullable @BindView(R.id.fragment_container) FrameLayout fragmentContainer;

    private int id;
    private Query query;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean isTwoPane = false;
    private int activatedPosition;
    private FlexibleAdapter<IFlexible> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(getString(R.string.app_name));
        }

        id = getIntent().getExtras().getInt(Constants.EXTRA_KEY_ID);

        DatabaseHelper db = new DatabaseHelper(this);
        query = new Query(db);

        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerList.setLayoutManager(mLinearLayoutManager);

        determinePaneLayout();

        initRecyclerView(getFlexibleList());

        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt(Constants.EXTRA_KEY_CURRENT_POSITION);
            mLinearLayoutManager.scrollToPosition(position);
        }
    }

    private void determinePaneLayout() {
        if (fragmentContainer != null) {
            isTwoPane = true;
        }
    }

    private ArrayList<IFlexible> getFlexibleList() {
        ArrayList<IFlexible> flexible = new ArrayList<>();
        flexible.add(new ListDetail(this, getString(R.string.recipe_ingredient)));

        ArrayList<Step> steps = query.getStepList(id);
        for (Step step : steps) {
            flexible.add(new ListDetail(this, step.shortDescription));
        }

        return flexible;
    }

    private void initRecyclerView(ArrayList<IFlexible> item) {
        adapter = new FlexibleAdapter<>(item);
        adapter.addListener(new FlexibleAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(int position) {
                Bundle send = new Bundle();
                send.putInt(Constants.EXTRA_KEY_ID, id);
                if (position == 0) {
                    send.putInt(Constants.EXTRA_KEY_TYPE, Constants.TYPE_INGREDIENT);
                } else {
                    send.putInt(Constants.EXTRA_KEY_TYPE, Constants.TYPE_STEP);
                }

                if (isTwoPane) {
                    adapter.setMode(SelectableAdapter.Mode.SINGLE);
                    FragmentRecipeDetail fragmentRecipeDetail = FragmentRecipeDetail.newInstance(send);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragmentRecipeDetail);
                    ft.commit();

                    if (position != activatedPosition) setActivatedPosition(position);
                    return true;
                } else {
                    adapter.setMode(SelectableAdapter.Mode.IDLE);
                    Intent intent = new Intent(ListDetailActivity.this, RecipeDetailActivity.class);
                    intent.putExtras(send);
                    startActivity(intent);

                    return false;
                }
            }
        });

        recyclerList.setAdapter(adapter);
        recyclerList.setVerticalScrollBarEnabled(false);
    }

    private void setActivatedPosition(int position) {
        activatedPosition = position;
        adapter.toggleSelection(position);
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
