package com.example.app.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.app.DataCentre;
import com.example.app.allergic.Event;
import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment;
import com.example.app.ui.FoodDiaryFragments.FoodDiaryListFragment;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.example.app.test_arrays.TestArrays;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergen;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergenFragment;

public class MainActivity extends AppCompatActivity implements
        SuggestedAllergenFragment.SuggestedAllergenFragmentInteractionListener,
        DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener,
        FoodDiaryListFragment.FoodDiaryListener {

    private FoodDiaryListFragment mDiaryFragment;
    private FragmentManager mFragmentManager;
    private StupidTextureViewFragment mCameraFragment;
    private CombinedAllergenDisplayFragment mCombinedAllergenFragment;

    private final static String TAG="sophie.hello_world";

private List<String> definiteAllergens;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        =new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){
            FragmentTransaction ft;
            switch(item.getItemId()){
                case R.id.navigation_food_evaluator:
                    ft = mFragmentManager.beginTransaction();
                    ft.replace(R.id.frame_layout, mCameraFragment);
                    ft.commit();
                    return true;
                case R.id.navigation_food_diary:
                    ft = mFragmentManager.beginTransaction();
                    ft.replace(R.id.frame_layout, mDiaryFragment);
                    ft.commit();
                    return true;
                case R.id.navigation_profile:
                    ft = mFragmentManager.beginTransaction();
                    ft.replace(R.id.frame_layout, mCombinedAllergenFragment);
                    ft.commit();
                    Log.i(TAG, "onNavigationItemSelected: Finished wrangling navigation profile");
                    return true;
                }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TestArrays.generateTestData();

        DataCentre.updateDefinite();
        DataCentre.updateSuggested();

        mFragmentManager = getFragmentManager();
        mDiaryFragment = new FoodDiaryListFragment();
        mCameraFragment = new StupidTextureViewFragment();
        mCombinedAllergenFragment = new CombinedAllergenDisplayFragment();

        BottomNavigationView navigation= findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public void onSuggestedAllergenAddRequest(String allergen, int position) {
        DataCentre.mDefiniteAllergenArray.add(allergen);
        DataCentre.mSuggestedAllergenArray.remove(position);
        DataCentre.history.updateAllergenMap();
        refreshCombinedAllergenFragment();
    }

    @Override
    public void onSuggestedAllergenDeleteRequest(int position) {
        DataCentre.mSuggestedAllergenArray.remove(position);
        refreshCombinedAllergenFragment();
    }

    @Override
    public void onDefiniteAllergenDelete(int position) {
        DataCentre.mDefiniteAllergenArray.remove(position);
        refreshCombinedAllergenFragment();
    }

    @Override
    public void onDefiniteAllergenAdd(String allergen) {
        DataCentre.mDefiniteAllergenArray.add(allergen);
        refreshCombinedAllergenFragment();
    }

    private void refreshCombinedAllergenFragment() {
        CombinedAllergenDisplayFragment combined = new CombinedAllergenDisplayFragment();
        mCombinedAllergenFragment = combined;

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, combined);

        ft.commit();
    }

    private void refreshFoodDiaryFragment() {
        FoodDiaryListFragment fd = new FoodDiaryListFragment();
        mDiaryFragment = fd;

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fd);
        ft.commit();
    }

    @Override
    public void addEventToDiary(Event e) {

        DataCentre.history.events.add(e);
        DataCentre.history.updateAllergenMap();
        refreshFoodDiaryFragment();
    }

    @Override
    public void removeEventFromDiary(int position) {
        DataCentre.remove(DataCentre.history.events.get(position));
        DataCentre.history.updateAllergenMap();
        refreshFoodDiaryFragment();
    }

}
