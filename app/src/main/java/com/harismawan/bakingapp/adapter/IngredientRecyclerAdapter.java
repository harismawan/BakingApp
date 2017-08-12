package com.harismawan.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.model.Ingredient;
import com.harismawan.bakingapp.viewholder.IngredientViewHolder;

import java.util.ArrayList;

public class IngredientRecyclerAdapter extends RecyclerView.Adapter<IngredientViewHolder> {

    private ArrayList<Ingredient> ingredients;

    public IngredientRecyclerAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient entry = ingredients.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
