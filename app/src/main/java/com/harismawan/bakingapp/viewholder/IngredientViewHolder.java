package com.harismawan.bakingapp.viewholder;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public class IngredientViewHolder extends FlexibleViewHolder {

    @BindView(R.id.ingredient_name) TextView name;
    @BindView(R.id.ingredient_quantity) TextView quantity;

    public IngredientViewHolder(View itemView, FlexibleAdapter adapter) {
        super(itemView, adapter);
        ButterKnife.bind(this, itemView);
    }

    public void bind(float quantity, String measure, String ingredient) {
        name.setText(ingredient);
        this.quantity.setText("Quantity : " + quantity + " " + measure);
    }
}
