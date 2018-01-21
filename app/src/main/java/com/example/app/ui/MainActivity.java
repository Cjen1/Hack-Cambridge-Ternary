package com.example.app.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment.DefiniteAllergenFragmentInteractionListener;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DefiniteAllergenFragmentInteractionListener{

private FoodDiaryFragment mDiaryFragment;
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
                return true;
            }
        return false;
        }
        };

@Override
public void onDeleteButtonClick(int position) {
    definiteAllergens.remove(position);
}

@Override
protected void onCreate(Bundle savedInstanceState){

    Log.i(TAG, "onCreate: Started app");

        super.onCreate(savedInstanceState);

    Log.i(TAG, "onCreate: Initialised super");
        setContentView(R.layout.activity_main);

        definiteAllergens = new ArrayList<String>();
        definiteAllergens.add("food");
        definiteAllergens.add("baz");

    Log.i(TAG, "onCreate: Created allergy list");

    if (savedInstanceState == null)
        savedInstanceState = new Bundle();
        savedInstanceState.putStringArrayList("definite_allergens", new ArrayList(definiteAllergens));

        mFragmentManager = getFragmentManager();

    Log.i(TAG, "onCreate: Creating food diary fragment");
        mDiaryFragment = new FoodDiaryFragment();
        mCameraFragment = new StupidTextureViewFragment();
        mCombinedAllergenFragment = new CombinedAllergenDisplayFragment();

        BottomNavigationView navigation=(BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }

}
