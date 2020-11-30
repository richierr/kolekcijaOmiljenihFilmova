package com.example.kolekcijaomiljenihfilmova.fragments;

import android.app.Activity;

import com.example.kolekcijaomiljenihfilmova.DatabaseHelper;
import com.example.kolekcijaomiljenihfilmova.model.Movie;

import java.util.List;

public class FavsListFragment extends ListFragment {
    @Override
    List<Movie> getListData() {
        getActivity().setTitle("Pregled filmova");
        return DatabaseHelper.getINSTANCE(getContext()).getFavMoviesList();

    }
}