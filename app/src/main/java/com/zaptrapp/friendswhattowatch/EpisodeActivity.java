package com.zaptrapp.friendswhattowatch;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.Adapter.CrewAdapter;
import com.zaptrapp.friendswhattowatch.Adapter.GuestStarAdapter;
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
    private TextView episodeReference;
    private AppBarLayout appbar;
    private ImageView appBarImage;
    private Toolbar toolbar;
    private RecyclerView crewRecyclerView;
    private CrewAdapter crewAdapter;
    private GuestStarAdapter guestStarAdapter;
    private RecyclerView guestStarRecyclerView;
    private LinearLayout guestStartLayout;
    private LinearLayout crewLayout;

    int seriesId = 1668;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        seriesId = getIntent().getIntExtra("seriesClicked",1668);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        initView();

        crewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        guestStarRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dataToView();

    }

    private void initView() {
        episodeName = (TextView) findViewById(R.id.episodeName);
        episodeOverview = (TextView) findViewById(R.id.episodeOverview);
        episodeReference = (TextView) findViewById(R.id.episodeReference);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appBarImage = (ImageView) findViewById(R.id.app_bar_image);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        crewRecyclerView = (RecyclerView) findViewById(R.id.crewRecyclerView);
        guestStarRecyclerView = (RecyclerView) findViewById(R.id.guestStarRecyclerView);
        guestStartLayout = (LinearLayout) findViewById(R.id.guestStartLayout);
        crewLayout = (LinearLayout) findViewById(R.id.crewLayout);
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
        Observable<EpisodeInfo> episodeInfoObservable = apiInterface.getEpisodeInfo(seriesId, receivedEpisode.season_number, receivedEpisode.episode_number, API_KEY);
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
                        Glide.with(getApplicationContext()).load(IMAGE_URL_BASE + episodeInfo.still_path).into(appBarImage);

                        if (!(episodeInfo.crew.size()==0)) {
                            //setting up crew adapter
                            crewAdapter = new CrewAdapter(episodeInfo.crew);
                            crewRecyclerView.setAdapter(crewAdapter);
                        } else {
                            crewLayout.setVisibility(View.GONE);
                        }
                        if (!(episodeInfo.guest_stars.size()==0)) {
                            //setting up guest stars adapter
                            guestStarAdapter = new GuestStarAdapter(episodeInfo.guest_stars);
                            guestStarRecyclerView.setAdapter(guestStarAdapter);
                        } else {
                            guestStartLayout.setVisibility(View.GONE);
                        }

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
