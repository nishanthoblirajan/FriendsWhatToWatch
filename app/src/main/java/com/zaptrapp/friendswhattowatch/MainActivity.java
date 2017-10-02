package com.zaptrapp.friendswhattowatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zaptrapp.friendswhattowatch.Adapter.SeasonAdapter;
import com.zaptrapp.friendswhattowatch.Model.SeasonInfo;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiClient;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiInterface;
import com.zaptrapp.friendswhattowatch.Utility.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.API_KEY;
import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.IMAGE_URL_BASE;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    SeasonAdapter mSeasonAdapter;
    RecyclerView mRecyclerView;

    //RxJava 2
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private RecyclerView recyclerViewSeries;

    private int seriesId = 1668;



    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();

    }

    ApiInterface apiInterface;
    int season_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        seriesId = getIntent().getIntExtra("seriesClicked",1668);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSeries);
        Log.d(TAG, "onCreate: no. of columns " + Utils.calculateNoOfColumns(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Utils.calculateNoOfColumns(this)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Observable<SeriesInfo> seriesInfoObservable = apiInterface.getSeriesInfo(seriesId, API_KEY);
        seriesInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SeriesInfo>() {
                    @Override
                    public void onNext(@NonNull SeriesInfo seriesInfo) {
                        List<SeriesInfo.Seasons> seasonsList = seriesInfo.seasons;
                        season_count = seasonsList.size();
                        mSeasonAdapter = new SeasonAdapter(seasonsList,seriesId);
                        mRecyclerView.setAdapter(mSeasonAdapter);
//                        Glide.with(getApplicationContext()).load(IMAGE_URL_BASE + seriesInfo.backdrop_path).into(appBarImage);
//                        Glide.with(getApplicationContext()).load("http://www.epicgeekdom.com/wordpress/wp-content/uploads/2014/02/friends-tv-show-wallpapers-1280x1024.jpg").into(appBarImage);

                        Log.d(TAG, "onNext: " + seasonsList.get(0).season_number);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("rxjava2 retrofit2", "Error Occured");

                    }

                    @Override
                    public void onComplete() {

                    }
                });



//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void showRandomEpisode(View view) {
        final int seasonNumber = Utils.randomNumber(1, season_count);
        Log.d(TAG, "showRandomEpisode: season " + seasonNumber);
        Observable<SeasonInfo> seasonInfoObservable = apiInterface.getSeasonInfo(seriesId, seasonNumber, API_KEY);
        seasonInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SeasonInfo>() {
                    @Override
                    public void onNext(@NonNull SeasonInfo seasonInfo) {
                        int episodeSize = seasonInfo.episodes.size();
                        Log.d(TAG, "showRandomEpisode: episodeSize " + episodeSize);
                        int episodeNumber = Utils.randomNumber(1, episodeSize);
                        Log.d(TAG, "showRandomEpisode: episodeNumber " + episodeNumber);
                        Log.d("EpisodeAdapter", "onClick: " + episodeNumber);
                        SeasonInfo.Episodes episodeToShow = seasonInfo.episodes.get(episodeNumber);
                        Intent intent = new Intent(getApplicationContext(), EpisodeActivity.class);
                        intent.putExtra("seriesClicked",seriesId);
                        intent.putExtra("episodeClicked", episodeToShow);
                        startActivity(intent);

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

    private void initView() {
        recyclerViewSeries = (RecyclerView) findViewById(R.id.recyclerViewSeries);
    }
}
