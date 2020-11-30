package com.example.kolekcijaomiljenihfilmova.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.kolekcijaomiljenihfilmova.R;
import com.example.kolekcijaomiljenihfilmova.web.RESTService;

import java.util.HashMap;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieFragment extends Fragment {
    String key;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Search movie");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        key = sharedPreferences.getString("key", "7aa7bfb5");


        View view=inflater.inflate(R.layout.search_movie_fragment,container,false);

        EditText editText=view.findViewById(R.id.editTextId);
        Button button=view.findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

                searchMovies(editText.getText().toString());
            }
        });
        return view;

    }
    private void searchMovies(String title){
        HashMap<String, String> query = new HashMap<>();
        query.put("apikey", key);
        query.put("s", title);

        Call<Result> call = RESTService.apiInterface().getMoviesByTitle(query);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() == 200) {

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
