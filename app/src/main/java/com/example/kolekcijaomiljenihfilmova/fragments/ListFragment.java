package com.example.kolekcijaomiljenihfilmova.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kolekcijaomiljenihfilmova.R;
import com.example.kolekcijaomiljenihfilmova.adapters.ListAdapter;
import com.example.kolekcijaomiljenihfilmova.model.Movie;

import java.util.List;

public abstract class ListFragment extends Fragment {

    boolean isMainList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        OnMovieItemClickInteface mListener=(OnMovieItemClickInteface)getActivity();
        View view=inflater.inflate(R.layout.fragment_list,container,false);

        RecyclerView recyclerView=view.findViewById(R.id.listRecyclerView);

        recyclerView.setHasFixedSize(true);
        if(this instanceof MainMoviesListFragment){
            isMainList=true;
        }else{
            isMainList=false;
        }


        ListAdapter listAdapter=new ListAdapter(getListData(), mListener,isMainList);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));

        recyclerView.setLayoutManager(layoutManager);
        return view;
    }


    public interface OnMovieItemClickInteface{

        void onMovieItemClick(int index);
    }


    abstract List<Movie> getListData();

}
