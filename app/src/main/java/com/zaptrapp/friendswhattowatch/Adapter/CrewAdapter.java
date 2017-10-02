package com.zaptrapp.friendswhattowatch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.EpisodeActivity;
import com.zaptrapp.friendswhattowatch.Model.EpisodeInfo;
import com.zaptrapp.friendswhattowatch.Model.SeasonInfo;
import com.zaptrapp.friendswhattowatch.R;

import java.util.List;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.IMAGE_URL_BASE;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class CrewAdapter  extends RecyclerView.Adapter<CrewAdapter.MyViewHolder> {

    private List<EpisodeInfo.Crew> mCrew;
    private Context mContext;

    public CrewAdapter(List<EpisodeInfo.Crew> crew) {
        mCrew = crew;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_crew_recycler,parent,false);
        return new MyViewHolder(view,this,mCrew);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EpisodeInfo.Crew crew = mCrew.get(position);
        //bind items to view
        if(crew!=null) {
            Glide.with(mContext).load(IMAGE_URL_BASE +crew.profile_path).into(holder.crewProfilePhoto);
            holder.crewName.setText(crew.name);
            holder.crewJob.setText(String.valueOf(crew.job));
        }
//        Glide.with(mContext).load(IMAGE_URL_BASE+mCrew.get(position).still_path).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return mCrew.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView crewProfilePhoto;
        CrewAdapter adapter;
        TextView crewName;
        TextView crewJob;
        private List<EpisodeInfo.Crew> mCrew;


        public MyViewHolder(View itemView, CrewAdapter episodeAdapter, List<EpisodeInfo.Crew> crewList) {
            super(itemView);
            this.adapter = episodeAdapter;
            crewProfilePhoto = itemView.findViewById(R.id.imageViewCrewProfile);
            crewName = itemView.findViewById(R.id.crewName);
            crewJob = itemView.findViewById(R.id.crewJob);
            itemView.setOnClickListener(this);
            mCrew = crewList;
        }


        @Override
        public void onClick(View view) {
            //get the position of the item that was clicked
            int mPosition = getLayoutPosition();
//            EpisodeInfo.Crew crew= mCrew.get(mPosition);
//            Log.d("CrewAdapter", "onClick: "+crew.name);
//            Intent intent = new Intent(view.getContext(), EpisodeActivity.class);
//            view.getContext().startActivity(intent);
        }
    }


}