package com.harismawan.bakingapp.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.harismawan.bakingapp.R;
import com.squareup.picasso.Picasso;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public class StepViewHolder extends FlexibleViewHolder {

    @BindView(R.id.step_video) VideoView video;
    @BindView(R.id.step_image) ImageView image;
    @BindView(R.id.step_short) TextView shortDesc;
    @BindView(R.id.step_desc) TextView desc;

    private Context context;

    public StepViewHolder(View itemView, FlexibleAdapter adapter) {
        super(itemView, adapter);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void bind(String video, String image, String shortDesc, String desc) {
        if (!video.isEmpty()) {

        }

        if (!image.isEmpty()) {
            Picasso.with(context).load(image).placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_placeholder).into(this.image);
        }

        this.shortDesc.setText(shortDesc);
        this.desc.setText(desc);
    }
}
