package id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies.service.VolleySingleton;

/**
 * Created by elvirayu on 15/05/2017.
 */

public class TopRatedFragment extends Fragment {

    ArrayList<Result> mList = new ArrayList<>();
    SourceAdapter mAdapter;


    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new SourceAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        downloadDataSources();
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=fdcdbf0470f07fda93c90e98fe6c0a64&language=en-US&page=1";

        GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
                (url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>() {

                    @Override
                    public void onResponse(SourcesResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));

                        mList.addAll(response.results);
                        mAdapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

}
