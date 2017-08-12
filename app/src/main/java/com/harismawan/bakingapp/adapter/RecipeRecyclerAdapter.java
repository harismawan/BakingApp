package com.harismawan.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.model.Recipe;
import com.harismawan.bakingapp.viewholder.RecipeViewHolder;

import java.util.ArrayList;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private ArrayList<Recipe> recipes;

    public RecipeRecyclerAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe entry = recipes.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
