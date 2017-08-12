package com.harismawan.bakingapp.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.model.Ingredient;

public class IngredientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.container) CardView container;
    @BindView(R.id.ingredient_name) TextView name;
    @BindView(R.id.ingredient_quantity) TextView quantity;

    private Ingredient ingredient;

    public IngredientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        container.setOnClickListener(this);
    }

    public void bind(Ingredient entry) {
        this.ingredient = entry;
        container.setOnClickListener(this);

        name.setText(ingredient.ingredient);
        quantity.setText("Quantity : " + ingredient.quantity + " " + ingredient.measure);
    }

    @Override
    public void onClick(View view) {

    }
}
