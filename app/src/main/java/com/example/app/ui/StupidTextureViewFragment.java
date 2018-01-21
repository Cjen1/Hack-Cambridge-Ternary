package com.example.app.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.DataCentre;
import com.example.app.allergic.EatEvent;
import com.example.app.camera.Camera;
import com.example.app.ocr.OCRInterface;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StupidTextureViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StupidTextureViewFragment extends Fragment {
    Camera mCamera;

    public StupidTextureViewFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_stupid_texture_view, container, false);

        TextureView textureView = (TextureView)rootView.findViewById(R.id.CameraPreviewTextureView);
        mCamera = new Camera(textureView, this, new DataCentre.saveListener() {
            @Override
            public void callback(String filepath) {
                List<String> textTokens = OCRInterface.analyseLocalToText(filepath);
                EatEvent e = new EatEvent(System.currentTimeMillis(), textTokens);
                DataCentre.addEatEvent(e);
            }
        });
        textureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.takePicture();
            }
        });

        return rootView;
    }

    @Override
    public void onPause(){
        super.onPause();
        mCamera.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera.onResume();
    }
}
