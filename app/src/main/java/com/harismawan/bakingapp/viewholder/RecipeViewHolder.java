package com.harismawan.bakingapp.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.activity.ListDetailActivity;
import com.harismawan.bakingapp.config.Constants;
import com.harismawan.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.container) CardView container;
    @BindView(R.id.recipe_image) ImageView image;
    @BindView(R.id.recipe_name) TextView name;
    @BindView(R.id.recipe_servings) TextView servings;

    private Recipe recipe;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        container.setOnClickListener(this);
    }

    public void bind(Recipe entry) {
        this.recipe = entry;
        container.setOnClickListener(this);

        if (!recipe.image.equals("")) {
            Picasso.with(image.getContext()).load(recipe.image).placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_placeholder).into(image);
        }
        name.setText(recipe.name);
        servings.setText("Servings : " + recipe.servings);
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent change = new Intent(context, ListDetailActivity.class);
        change.putExtra(Constants.EXTRA_KEY_ID, recipe.id);
        context.startActivity(change);
    }
}
