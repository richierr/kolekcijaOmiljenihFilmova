package com.example.kolekcijaomiljenihfilmova.fragments;

import com.example.kolekcijaomiljenihfilmova.model.Movie;

import java.util.List;

public class TechDetailsFragment extends ListFragment {

    @Override
    List<Movie> getListData() {
        getActivity().setTitle("Tehnicki detalji");
        return null;
    }
}
