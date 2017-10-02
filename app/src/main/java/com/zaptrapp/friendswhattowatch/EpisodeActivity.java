package com.zaptrapp.friendswhattowatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.Model.EpisodeInfo;
import com.zaptrapp.friendswhattowatch.Model.SeasonInfo;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiClient;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.API_KEY;
import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.IMAGE_URL_BASE;

public class EpisodeActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    private TextView episodeName;
    private TextView episodeOverview;
    private ImageView episodePhoto;
    private TextView episodeReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        initView();
        dataToView();

    }

    private void initView() {
        episodeName = (TextView) findViewById(R.id.episodeName);
        episodeOverview = (TextView) findViewById(R.id.episodeOverview);
        episodePhoto = (ImageView) findViewById(R.id.episodePhoto);
        episodeReference = (TextView) findViewById(R.id.episodeReference);
    }

    public static final String TAG = "EpisodeActivity";

    private void dataToView() {
        SeasonInfo.Episodes receivedEpisode = getIntent().getParcelableExtra("episodeClicked");
//        Log.d("dataToView", receivedEpisode.toString());
        if (receivedEpisode != null) {
            showEpisode(receivedEpisode);
        } else {
            Log.d(TAG, "dataToView: Received episode is null");
        }
    }

    private void showEpisode(SeasonInfo.Episodes receivedEpisode) {
        Observable<EpisodeInfo> episodeInfoObservable = apiInterface.getEpisodeInfo(1668, receivedEpisode.season_number, receivedEpisode.episode_number, API_KEY);
        Log.d(TAG, "dataToView: " + episodeInfoObservable.toString());
        Log.d(TAG, "dataToView: id " + receivedEpisode.id);
        Log.d(TAG, "dataToView: season " + receivedEpisode.season_number);
        Log.d(TAG, "dataToView: episode " + receivedEpisode.episode_number);
        episodeInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<EpisodeInfo>() {
                    @Override
                    public void onNext(@NonNull EpisodeInfo episodeInfo) {
                        Log.d(TAG, "onNext: " + episodeInfo.name);
                        episodeName.setText(episodeInfo.name);
                        episodeOverview.setText(episodeInfo.overview);
                        episodeReference.setText(episodeInfo.season_number + "x" + episodeInfo.episode_number);
                        Glide.with(getApplicationContext()).load(IMAGE_URL_BASE + episodeInfo.still_path).into(episodePhoto);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
