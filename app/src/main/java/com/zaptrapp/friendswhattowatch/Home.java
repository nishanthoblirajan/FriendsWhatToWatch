package com.zaptrapp.friendswhattowatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zaptrapp.friendswhattowatch.Adapter.SeasonAdapter;
import com.zaptrapp.friendswhattowatch.Adapter.SeriesAdapter;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.Model.SeriesListInfo;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiClient;
import com.zaptrapp.friendswhattowatch.RetroFit.ApiInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.zaptrapp.friendswhattowatch.RetroFit.ApiConstant.API_KEY;

public class Home extends AppCompatActivity {
    
    private static final String TAG = Home.class.getSimpleName();
    

    private RecyclerView homeRecyclerView;
    private SeriesAdapter seriesAdapter;
    ApiInterface apiInterface;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeRecyclerView = (RecyclerView) findViewById(R.id.home_recycler_view);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        homeRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        getList(page);
    }

    private void getList(int page) {
        Observable<SeriesListInfo> seriesListInfoObservable = apiInterface.getTopRated(page,API_KEY);
        seriesListInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SeriesListInfo>() {
                    @Override
                    public void onNext(@NonNull SeriesListInfo seriesListInfo) {
                        seriesAdapter = new SeriesAdapter(seriesListInfo.results);
                        homeRecyclerView.setAdapter(seriesAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void nextPage(View view) {
        page++;
        getList(page);
    }

    public void previousPage(View view) {
        if(page ==1){
            return;
        }else {
            page--;
            getList(page);
        }
    }
}
