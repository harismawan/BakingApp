package com.harismawan.bakingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.utils.Utils;

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.EXTRA_KEY_CURRENT_POSITION, mLinearLayoutManager.findFirstVisibleItemPosition());
    }
}
