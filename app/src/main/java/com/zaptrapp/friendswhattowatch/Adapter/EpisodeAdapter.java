package com.zaptrapp.friendswhattowatch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zaptrapp.friendswhattowatch.EpisodeActivity;
import com.zaptrapp.friendswhattowatch.Model.SeasonInfo;
import com.zaptrapp.friendswhattowatch.R;

import java.util.List;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.IMAGE_URL_BASE;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.MyViewHolder> {

    private List<SeasonInfo.Episodes> mEpisodes;
    private Context mContext;

    public EpisodeAdapter(List<SeasonInfo.Episodes> episodes) {
        mEpisodes = episodes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_episodes_recycler,parent,false);
        return new MyViewHolder(view,this,mEpisodes);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SeasonInfo.Episodes episode = mEpisodes.get(position);
        //bind items to view
        if(episode!=null) {
            Glide.with(mContext).load(IMAGE_URL_BASE +episode.still_path).into(holder.episodeImage);
            holder.episodeName.setText(episode.name);
            holder.episodeNumber.setText(String.valueOf(episode.episode_number));
            holder.episodeOverview.setText(episode.overview);
        }
//        Glide.with(mContext).load(IMAGE_URL_BASE+mEpisodes.get(position).still_path).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return mEpisodes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView episodeImage;
        EpisodeAdapter adapter;
        TextView episodeName;
        TextView episodeOverview;
        TextView episodeNumber;
        private List<SeasonInfo.Episodes> mEpisodes;


        public MyViewHolder(View itemView, EpisodeAdapter episodeAdapter, List<SeasonInfo.Episodes> episodes) {
            super(itemView);
            this.adapter = episodeAdapter;
            episodeImage = itemView.findViewById(R.id.imageViewEpisodeRecycler);
            episodeName = itemView.findViewById(R.id.episodeName);
            episodeNumber = itemView.findViewById(R.id.episodeNumber);
            episodeOverview = itemView.findViewById(R.id.episodeOverview);
            itemView.setOnClickListener(this);
            mEpisodes = episodes;
        }


        @Override
        public void onClick(View view) {
            //get the position of the item that was clicked
            int mPosition = getLayoutPosition();
            SeasonInfo.Episodes episode= mEpisodes.get(mPosition);
            Log.d("EpisodeAdapter", "onClick: "+episode.name);
            Intent intent = new Intent(view.getContext(), EpisodeActivity.class);
            intent.putExtra("episodeClicked",episode);
            view.getContext().startActivity(intent);
        }
    }


}


