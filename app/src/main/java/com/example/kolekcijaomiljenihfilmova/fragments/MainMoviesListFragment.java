package com.example.kolekcijaomiljenihfilmova.fragments;

import com.example.kolekcijaomiljenihfilmova.DatabaseHelper;
import com.example.kolekcijaomiljenihfilmova.model.Movie;

import java.util.List;

public class MainMoviesListFragment extends ListFragment {
    @Override
    List<Movie> getListData() {
        getActivity().setTitle("Dodavanje filma");

        return DatabaseHelper.getINSTANCE(getContext()).getMovieList();
    }
}
