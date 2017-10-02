package com.zaptrapp.friendswhattowatch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.MainActivity;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.Model.SeriesListInfo;
import com.zaptrapp.friendswhattowatch.R;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant;

import java.util.List;

/**
 * Created by Nishanth on 12-Oct-17.
 */

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private static final String TAG = SeriesAdapter.class.getSimpleName();
    private List<SeriesInfo> mSeries;
    private Context mContext;

    public SeriesAdapter(List<SeriesInfo> mSeries) {
        this.mSeries = mSeries;
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_series_recycler,parent,false);
        return new SeriesViewHolder(view,this,mSeries);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Glide.with(mContext).load(ApiConstant.IMAGE_URL_BASE+mSeries.get(position).poster_path).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        private List<SeriesInfo> series;


        public SeriesViewHolder(View itemView, SeriesAdapter seriesAdapter, List<SeriesInfo> mSeries) {
            super(itemView);
            Log.d(TAG, "SeriesViewHolder: ");
            series = mSeries;
            imageView = itemView.findViewById(R.id.seriesImageRecycler);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            SeriesInfo seriesInfo = series.get(mPosition);
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("seriesClicked",seriesInfo.id);
            view.getContext().startActivity(intent);
        }
    }
}
