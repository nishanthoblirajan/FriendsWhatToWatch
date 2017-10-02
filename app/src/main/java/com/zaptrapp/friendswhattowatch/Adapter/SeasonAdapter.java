package com.zaptrapp.friendswhattowatch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.R;
import com.zaptrapp.friendswhattowatch.SeasonActivity;
import com.zaptrapp.friendswhattowatch.Utility.Utils;

import org.w3c.dom.Text;

import java.util.List;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.IMAGE_URL_BASE;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.MyViewHolder> {

    private List<SeriesInfo.Seasons> mSeasons;
    private Context mContext;

    public SeasonAdapter(List<SeriesInfo.Seasons> seasons) {
        mSeasons = seasons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_seasons_recycler,parent,false);
        return new MyViewHolder(view,this,mSeasons);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            //bind items to view
            Glide.with(mContext).load(IMAGE_URL_BASE + mSeasons.get(position).poster_path).into(holder.imageView);
            holder.title.setText("Season "+mSeasons.get(position).season_number);
            String seasonDetailsText = "Air Date: "+ Utils.convertTMDBDate(mSeasons.get(position).air_date)+"\n"+
                    "Episodes: "+mSeasons.get(position).episode_count;
            holder.details.setText(seasonDetailsText);

    }


    @Override
    public int getItemCount() {
        return mSeasons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title;
        TextView details;
        SeasonAdapter adapter;
        private List<SeriesInfo.Seasons> mSeasons;


        public MyViewHolder(View itemView, SeasonAdapter seasonAdapter, List<SeriesInfo.Seasons> seasons) {
            super(itemView);
            this.adapter = seasonAdapter;
            imageView = itemView.findViewById(R.id.imageViewRecycler);
            title = itemView.findViewById(R.id.seasonTitle);
            details = itemView.findViewById(R.id.seasonDetails);
            itemView.setOnClickListener(this);
            mSeasons = seasons;
        }


        @Override
        public void onClick(View view) {
            //get the position of the item that was clicked
            int mPosition = getLayoutPosition();
            SeriesInfo.Seasons season = mSeasons.get(mPosition);
            Intent intent = new Intent(view.getContext(), SeasonActivity.class);
            intent.putExtra("seasonClicked",season);
            view.getContext().startActivity(intent);
        }
    }


}

