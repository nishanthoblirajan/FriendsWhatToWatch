package com.zaptrapp.friendswhattowatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.zaptrapp.friendswhattowatch.Adapter.EpisodeAdapter;
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

import static android.R.attr.finishOnCloseSystemDialogs;
import static android.R.attr.width;
import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.API_KEY;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    SeasonAdapter mSeasonAdapter;
    RecyclerView mRecyclerView;

    //RxJava 2
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();

    }

        ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int) (metrics.density * 160f);

        int width1 = (500 * 160) / densityDpi;
        Log.d(TAG, "500px in dp for this device " + width1 + "");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.d(TAG, "width " + width + "");
        Log.d(TAG, "height " + height + "");


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSeries);
        Log.d(TAG, "onCreate: no. of columns "+Utils.calculateNoOfColumns(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Utils.calculateNoOfColumns(this)));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Observable<SeriesInfo> seriesInfoObservable = apiInterface.getSeriesInfo(1668, API_KEY);
        seriesInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SeriesInfo>() {
                    @Override
                    public void onNext(@NonNull SeriesInfo seriesInfo) {
                        List<SeriesInfo.Seasons> seasonsList = seriesInfo.seasons;
                        mSeasonAdapter = new SeasonAdapter(seasonsList);
                        mRecyclerView.setAdapter(mSeasonAdapter);
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

    }

    public void showRandomEpisode(View view) {
        final int seasonNumber = Utils.randomNumber(1,10);
        Log.d(TAG, "showRandomEpisode: season "+seasonNumber);
        Observable<SeasonInfo> seasonInfoObservable = apiInterface.getSeasonInfo(1668,seasonNumber,API_KEY);
        seasonInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SeasonInfo>() {
                    @Override
                    public void onNext(@NonNull SeasonInfo seasonInfo) {
                        int episodeSize = seasonInfo.episodes.size();
                        Log.d(TAG, "showRandomEpisode: episodeSize "+episodeSize);
                        int episodeNumber = Utils.randomNumber(1,episodeSize);
                        Log.d(TAG, "showRandomEpisode: episodeNumber "+episodeNumber);
                        Log.d("EpisodeAdapter", "onClick: "+episodeNumber);
                        SeasonInfo.Episodes episodeToShow = seasonInfo.episodes.get(episodeNumber);
                        Intent intent = new Intent(getApplicationContext(), EpisodeActivity.class);
                        intent.putExtra("episodeClicked",episodeToShow);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("rxjava2 retrofit2","Error Occured "+e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
