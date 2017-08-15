package com.harismawan.bakingapp.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public class DetailListViewHolder extends FlexibleViewHolder {

    @BindView(R.id.detail_name) TextView name;
    @BindView(R.id.detail_desc) TextView desc;

    private Context context;

    public DetailListViewHolder(View itemView, FlexibleAdapter adapter) {
        super(itemView, adapter);
        ButterKnife.bind(this, itemView);
        context = name.getContext();
    }

    public void bind(String entry, int position) {
        if (position == 0) {
            name.setText(context.getString(R.string.ingredient));
        } else if (position == 1) {
            name.setText(context.getString(R.string.introduction));
        } else {
            name.setText(context.getString(R.string.step) + " " + (position - 1));
        }
        desc.setText(entry);
    }
}
