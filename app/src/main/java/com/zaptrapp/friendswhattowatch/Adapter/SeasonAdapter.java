package com.zaptrapp.friendswhattowatch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.R;
import com.zaptrapp.friendswhattowatch.SeasonActivity;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_movie_recycler,parent,false);
        return new MyViewHolder(view,this,mSeasons);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(mSeasons.get(position).season_number<1){
            return;
        }else {
            //bind items to view
            Glide.with(mContext).load(IMAGE_URL_BASE + mSeasons.get(position).poster_path).into(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return mSeasons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        SeasonAdapter adapter;
        private List<SeriesInfo.Seasons> mSeasons;


        public MyViewHolder(View itemView, SeasonAdapter seasonAdapter, List<SeriesInfo.Seasons> seasons) {
            super(itemView);
            this.adapter = seasonAdapter;
            imageView = itemView.findViewById(R.id.imageViewRecycler);
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

