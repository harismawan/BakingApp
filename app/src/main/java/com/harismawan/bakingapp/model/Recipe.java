package com.harismawan.bakingapp.model;

import android.view.View;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.viewholder.RecipeViewHolder;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import java.util.ArrayList;
import java.util.List;

public class Recipe extends AbstractFlexibleItem<RecipeViewHolder> {

    public int id;
    public String name;
    public ArrayList<Ingredient> ingredients;
    public ArrayList<Step> steps;
    public String image;
    public int servings;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_recipe;
    }

    @Override
    public RecipeViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new RecipeViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecipeViewHolder holder, int position, List payloads) {
        holder.bind(image, name, servings);
    }
}
