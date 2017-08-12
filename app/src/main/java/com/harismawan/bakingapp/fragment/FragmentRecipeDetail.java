package com.harismawan.bakingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;

public class FragmentRecipeDetail extends Fragment {

    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(Constants.EXTRA_KEY_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail,
                container, false);
//        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
//        tvTitle.setText(item.getTitle());
//        tvBody.setText(item.getBody());
        return view;
    }

    public static FragmentRecipeDetail newInstance(Bundle send) {
        FragmentRecipeDetail fragmentRecipeDetail = new FragmentRecipeDetail();
        fragmentRecipeDetail.setArguments(send);
        return fragmentRecipeDetail;
    }
}
