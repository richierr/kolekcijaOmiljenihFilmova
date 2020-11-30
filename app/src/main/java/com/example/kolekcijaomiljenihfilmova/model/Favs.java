package com.example.kolekcijaomiljenihfilmova.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Favs.TABLE_NAME)
public class Favs {

    static final String TABLE_NAME ="favs";
    private static final String ID="id";
    private static final String RATING="rating";
    public static final String MOVIE_ID="movie_id";


    @DatabaseField(columnName =ID,generatedId = true)
    private int id;

    @DatabaseField(columnName = RATING)
    private double rating;


    @DatabaseField(foreign = true,foreignAutoRefresh = true,unique = true)
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Favs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Favs(int id, double rating,Movie movie) {
        this.id = id;
        this.movie=movie;
        this.rating = rating;
    }
}
