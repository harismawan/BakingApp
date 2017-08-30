package com.harismawan.bakingapp.fragment;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.utils.Utils;
import com.harismawan.bakingapp.widget.IngredientAppWidget;

public class FragmentRecipeDetail extends Fragment {

    @BindView(R.id.recycler_list) RecyclerView recyclerList;

    private int id;
    private int type;
    private Query query;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle receive = getArguments();
        id = receive.getInt(Constants.EXTRA_KEY_ID);
        type = receive.getInt(Constants.EXTRA_KEY_TYPE);

        if (type == Constants.TYPE_INGREDIENT)
            setHasOptionsMenu(true);
    }

    public static FragmentRecipeDetail newInstance(Bundle send) {
        FragmentRecipeDetail fragmentRecipeDetail = new FragmentRecipeDetail();
        fragmentRecipeDetail.setArguments(send);
        return fragmentRecipeDetail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHelper db = new DatabaseHelper(getContext());
        query = new Query(db);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(mLinearLayoutManager);

        Utils.initDetailRecyclerView(recyclerList, id, type, query);

        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt(Constants.EXTRA_KEY_CURRENT_POSITION);
            mLinearLayoutManager.scrollToPosition(position);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.pausePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.releasePlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.EXTRA_KEY_CURRENT_POSITION, mLinearLayoutManager.findFirstVisibleItemPosition());
    }

    private void updateWidget() {
        query.setWidgetData(query.getIngredientList(id));
        AppWidgetManager manager = AppWidgetManager.getInstance(getContext());
        int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(getContext(), IngredientAppWidget.class));
        manager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);

        Toast.makeText(getContext(), getString(R.string.saved), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (type == Constants.TYPE_INGREDIENT) {
            inflater.inflate(R.menu.menu_ingredient, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_widget:
                updateWidget();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
