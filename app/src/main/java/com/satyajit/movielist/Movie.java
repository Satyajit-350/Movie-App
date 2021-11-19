package com.satyajit.movielist;

public class Movie {
    private String mOverView;
    private String mMovieName;
    private String mPoster;
    private String mRating;

    public Movie(String overview, String poster, String moviename, String rating){
        mMovieName = moviename;
        mPoster = poster;
        mOverView = overview;
        mRating = rating;
    }

    public String getmOverView() {
        return mOverView;
    }

    public String getmMovieName() {
        return mMovieName;
    }

    public String getmPoster() {
        return mPoster;
    }

    public String getmRating() {
        return mRating;
    }
}
