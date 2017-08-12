package com.harismawan.bakingapp.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.viewholder.DetailListViewHolder;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.utils.DrawableUtils;

import java.util.List;

public class ListDetail extends AbstractFlexibleItem<DetailListViewHolder> {

    private Context context;
    private String text;

    public ListDetail(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_detail_list;
    }

    @Override
    public DetailListViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new DetailListViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, DetailListViewHolder holder, int position, List payloads) {
        Drawable drawable = DrawableUtils.getSelectableBackgroundCompat(
                Color.WHITE, Color.parseColor(context.getString(R.color.colorSelected)),
                DrawableUtils.getColorControlHighlight(context));
        DrawableUtils.setBackgroundCompat(holder.itemView, drawable);

        holder.bind(text, position);
    }


}
