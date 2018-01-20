package com.example.app.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.app.ui.FoodDiaryFragment;


public class MainActivity extends AppCompatActivity implements FoodDiaryFragment.OnFragmentInteractionListener{

private FoodDiaryFragment mDiaryFragment;
private ViewGroup mView;
private FragmentManager mFragmentManager;

private final static String TAG="sophie.hello_world";

private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        =new BottomNavigationView.OnNavigationItemSelectedListener(){

@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.navigation_food_evaluator:
                return true;
            case R.id.navigation_food_diary:
                Log.i(TAG, "onNavigationItemSelected: beginning transaction");
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                Log.i(TAG, "onNavigationItemSelected: begun transaction");
                ft.replace(R.id.frame_layout, mDiaryFragment);
                Log.i(TAG, "onNavigationItemSelected: Committing tracsaction");
                ft.commit();
                Log.i(TAG, "onNavigationItemSelected: Committed transaction");
                return true;
            case R.id.navigation_profile:
                return true;
            }
        return false;
        }
        };

@Override
protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getFragmentManager();
        mDiaryFragment = new FoodDiaryFragment();


        BottomNavigationView navigation=(BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }

}
