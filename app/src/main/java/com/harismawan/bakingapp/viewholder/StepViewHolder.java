package com.harismawan.bakingapp.viewholder;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.config.Constants;
import com.squareup.picasso.Picasso;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public class StepViewHolder extends FlexibleViewHolder {

    @BindView(R.id.step_video) SimpleExoPlayerView video;
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
            initPlayer(video);

        }

        if (!image.isEmpty()) {
            this.image.setVisibility(View.VISIBLE);
            Picasso.with(context).load(image).placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_placeholder).into(this.image);
        }

        this.shortDesc.setText(shortDesc);
        this.desc.setText(desc);
    }

    private void initPlayer(String url) {
        video.setVisibility(View.VISIBLE);

        TrackSelector trackSelector = new DefaultTrackSelector();
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        video.setPlayer(player);

        String userAgent = Util.getUserAgent(context, context.getString(R.string.app_name));
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url), new DefaultDataSourceFactory
                (context, userAgent), new DefaultExtractorsFactory(), null, null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(false);
        Constants.activePlayer.add(player);
    }
}
