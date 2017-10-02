package com.zaptrapp.friendswhattowatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.Adapter.EpisodeAdapter;
import com.zaptrapp.friendswhattowatch.Model.SeasonInfo;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiClient;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.API_KEY;

public class SeasonActivity extends AppCompatActivity {


    ApiInterface apiInterface;
    EpisodeAdapter mEpisodeAdapter;
    RecyclerView mRecyclerView;
    public static final String TAG = "SeasonActivity";
    int seriesId = 1668;
    private ImageView imageViewSeasonActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSeasons);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        seriesId = getIntent().getIntExtra("seriesClicked", 1668);
        initView();
        getEpisodeList();
    }


    private void getEpisodeList() {
        SeriesInfo.Seasons receivedSeason = getIntent().getParcelableExtra("seasonClicked");
        Log.d("dataToView", receivedSeason.toString());
        if (receivedSeason != null) {
            Log.d(TAG, "dataToView: " + receivedSeason.season_number);

            Observable<SeasonInfo> seasonInfoObservable = apiInterface.getSeasonInfo(seriesId, receivedSeason.season_number, API_KEY);
            seasonInfoObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<SeasonInfo>() {
                        @Override
                        public void onNext(@NonNull SeasonInfo seasonInfo) {
                            List<SeasonInfo.Episodes> episodesList = seasonInfo.episodes;
                            mEpisodeAdapter = new EpisodeAdapter(episodesList, seriesId);
                            mRecyclerView.setAdapter(mEpisodeAdapter);
                            Log.d(TAG, "onNext: " + episodesList.get(0).name);


                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("rxjava2 retrofit2", "Error Occured " + e.getLocalizedMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void initView() {
//        imageViewSeasonActivity = (ImageView) findViewById(R.id.imageViewSeasonActivity);
//        Glide.with(this).load(ApiConstant.IMAGE_URL_ORIGINAL_BASE+receivedSeason.poster_path).into(imageViewSeasonActivity);
    }
}
