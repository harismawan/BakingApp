package com.harismawan.bakingapp.model;

import android.view.View;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.viewholder.IngredientViewHolder;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import java.util.List;

public class Ingredient extends AbstractFlexibleItem<IngredientViewHolder> {

    public float quantity;
    public String measure;
    public String ingredient;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_ingredient;
    }

    @Override
    public IngredientViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new IngredientViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, IngredientViewHolder holder, int position, List payloads) {
        holder.bind(quantity, measure, ingredient);
    }
}
