package com.example.app.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment;
import com.example.app.ui.FoodDiaryFragments.FoodDiaryListFragment;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.example.app.test_arrays.TestArrays;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergen;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergenFragment;

public class MainActivity extends AppCompatActivity implements SuggestedAllergenFragment.SuggestedAllergenFragmentInteractionListener, DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener{

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

        String[] foodDiary = {"cheese", "eggs", "biscuits"};
        String[] definiteAllergens = {"oranges", "pineapple", "chocolate"};

        ArrayList<String> foodDiaryAL = new ArrayList<>(Arrays.asList(foodDiary));
        ArrayList<String> definiteAllergiesAL = new ArrayList<>(Arrays.asList(definiteAllergens));
        ArrayList<SuggestedAllergen> suggestedAllergensAL = new ArrayList<>();
        suggestedAllergensAL.add(new SuggestedAllergen("peaches", 0.25));
        suggestedAllergensAL.add(new SuggestedAllergen("cheese", 0.75));

        TestArrays.mFoodDiaryArray = foodDiaryAL;
        TestArrays.mDefiniteAllergenArray = definiteAllergiesAL;
        TestArrays.mSuggestedAllergenArray = suggestedAllergensAL;

        mFragmentManager = getFragmentManager();
        mDiaryFragment = new FoodDiaryListFragment();
        mCameraFragment = new StupidTextureViewFragment();
        mCombinedAllergenFragment = new CombinedAllergenDisplayFragment();

        BottomNavigationView navigation=(BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }

        @Override
        public void onSuggestedAllergenAddRequest(String allergen) {

            TestArrays.mDefiniteAllergenArray.add(allergen);
            while (TestArrays.mSuggestedAllergenArray.iterator().hasNext()) {
                SuggestedAllergen item = TestArrays.mSuggestedAllergenArray.iterator().next();
                if (item.allergen.equals(allergen)) {
                    TestArrays.mSuggestedAllergenArray.remove(item);
                }
            }

            refreshCombinedAllergenFragment();


        }

        @Override
        public void onSuggestedAllergenDeleteRequest(int position) {
            TestArrays.mSuggestedAllergenArray.remove(position);
            refreshCombinedAllergenFragment();
        }

        @Override
        public void onDefiniteAllergenDelete(int position) {
            TestArrays.mDefiniteAllergenArray.remove(position);
            refreshCombinedAllergenFragment();
    }

    @Override
    public void onDefiniteAllergenAdd(String allergen) {
    //pass
    }

        private void refreshCombinedAllergenFragment() {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.frame_layout, mCombinedAllergenFragment);
            ft.commit();
        }

}
