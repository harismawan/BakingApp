package com.harismawan.bakingapp.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.squareup.picasso.Picasso;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public class RecipeViewHolder extends FlexibleViewHolder implements View.OnClickListener {

    @BindView(R.id.container) CardView container;
    @BindView(R.id.recipe_image) ImageView image;
    @BindView(R.id.recipe_name) TextView name;
    @BindView(R.id.recipe_servings) TextView servings;

    public RecipeViewHolder(View itemView, FlexibleAdapter adapter) {
        super(itemView, adapter);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String imageUrl, String name, int servings) {
        if (!imageUrl.isEmpty()) {
            Picasso.with(image.getContext()).load(imageUrl).placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_placeholder).into(image);
        }
        this.name.setText(name);
        this.servings.setText("Servings : " + servings);
    }
}
