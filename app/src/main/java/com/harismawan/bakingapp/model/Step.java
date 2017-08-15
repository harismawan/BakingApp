package com.harismawan.bakingapp.model;

import android.view.View;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.viewholder.StepViewHolder;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import java.util.List;

public class Step extends AbstractFlexibleItem<StepViewHolder> {

    public int id;
    public String shortDescription;
    public String description;
    public String videoURL;
    public String thumbnailURL;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_step;
    }

    @Override
    public StepViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new StepViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, StepViewHolder holder, int position, List payloads) {
        holder.bind(videoURL, thumbnailURL, shortDescription, description);
    }
}
