package com.example.kolekcijaomiljenihfilmova.fragments;

import com.example.kolekcijaomiljenihfilmova.model.Movie;

import java.util.List;

public class ParticipantsDetails extends ListFragment {
    @Override
    List<Movie> getListData() {
        getActivity().setTitle("Lista ucesnika");
        return null;
    }
}
