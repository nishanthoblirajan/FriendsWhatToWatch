package com.zaptrapp.friendswhattowatch.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.Model.EpisodeInfo;
import com.zaptrapp.friendswhattowatch.R;

import java.util.List;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.IMAGE_URL_BASE;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class GuestStarAdapter extends RecyclerView.Adapter<GuestStarAdapter.MyViewHolder> {

    private List<EpisodeInfo.Guest_stars> mGuest_stars;
    private Context mContext;

    public GuestStarAdapter(List<EpisodeInfo.Guest_stars> guest_stars) {
        mGuest_stars = guest_stars;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_crew_recycler,parent,false);
        return new MyViewHolder(view,this,mGuest_stars);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EpisodeInfo.Guest_stars guest_stars = mGuest_stars.get(position);
        //bind items to view
        if(guest_stars!=null) {
            Glide.with(mContext).load(IMAGE_URL_BASE +guest_stars.profile_path).into(holder.guest_starsProfilePhoto);
            holder.guest_starsJob.setText(guest_stars.character);
            holder.guest_starsName.setText(String.valueOf(guest_stars.name));
        }
//        Glide.with(mContext).load(IMAGE_URL_BASE+mGuest_stars.get(position).still_path).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return mGuest_stars.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView guest_starsProfilePhoto;
        GuestStarAdapter adapter;
        TextView guest_starsName;
        TextView guest_starsJob;
        private List<EpisodeInfo.Guest_stars> mGuest_stars;


        public MyViewHolder(View itemView, GuestStarAdapter episodeAdapter, List<EpisodeInfo.Guest_stars> guest_starsList) {
            super(itemView);
            this.adapter = episodeAdapter;
            guest_starsProfilePhoto = itemView.findViewById(R.id.imageViewCrewProfile);
            guest_starsName = itemView.findViewById(R.id.crewName);
            guest_starsJob = itemView.findViewById(R.id.crewJob);
            itemView.setOnClickListener(this);
            mGuest_stars = guest_starsList;
        }


        @Override
        public void onClick(View view) {
            //get the position of the item that was clicked
            int mPosition = getLayoutPosition();
//            EpisodeInfo.Guest_stars guest_stars= mGuest_stars.get(mPosition);
//            Log.d("Guest_starsAdapter", "onClick: "+guest_stars.name);
//            Intent intent = new Intent(view.getContext(), EpisodeActivity.class);
//            view.getContext().startActivity(intent);
        }
    }


}