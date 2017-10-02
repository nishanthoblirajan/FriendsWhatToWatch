package com.zaptrapp.friendswhattowatch.RetroFit;

import com.zaptrapp.friendswhattowatch.Model.EpisodeInfo;
import com.zaptrapp.friendswhattowatch.Model.Movie;
import com.zaptrapp.friendswhattowatch.Model.MovieResponse;
import com.zaptrapp.friendswhattowatch.Model.SeasonInfo;
import com.zaptrapp.friendswhattowatch.Model.SeriesInfo;
import com.zaptrapp.friendswhattowatch.Model.SeriesListInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.apiKey;

/**
 * Created by nishanth on 26/09/17.
 */

public interface ApiInterface {

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
//
    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie?sort_by=popularity.desc")
    Observable<MovieResponse> getMostPopularMovies(@Query("api_key") String apiKey);

    @GET("discover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc")
    Observable<MovieResponse> getRRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie?primary_release_year=2017&sort_by=vote_average.desc")
    Observable<MovieResponse> getBestOf2017Movies(@Query("api_key") String apiKey);


    @GET("tv/popular")
    Observable<SeriesListInfo> getTopRated(@Query("page")int page,@Query("api_key") String apiKey);

    //For receiving information about a tv series with the tvseries id (here: Friends = 1668)
    @GET("tv/{tv_id}")
    Observable<SeriesInfo> getSeriesInfo(@Path("tv_id") int id,@Query("api_key") String apiKey);


    @GET("tv/{tv_id}/season/{season_number}")
    Observable<SeasonInfo> getSeasonInfo(@Path("tv_id") int id, @Path("season_number") int seasonNumber, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Observable<EpisodeInfo> getEpisodeInfo(@Path("tv_id") int id, @Path("season_number") int seasonNumber, @Path("episode_number") int episodeNumber, @Query("api_key") String apiKey);



}
