package com.example.app.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StupidTextureViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StupidTextureViewFragment extends Fragment {


    public StupidTextureViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StupidTextureViewFragment.
     */
    public static StupidTextureViewFragment newInstance() {
        StupidTextureViewFragment fragment = new StupidTextureViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stupid_texture_view, container, false);
    }

}
