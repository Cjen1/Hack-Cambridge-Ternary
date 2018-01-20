package com.example.app.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sophie.hello_world.R;

public class MainActivity extends AppCompatActivity{

private TextView mTextMessage;

private final static String TAG="sophie.hello_world";

private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        =new BottomNavigationView.OnNavigationItemSelectedListener(){

@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.navigation_food_evaluator:
                Log.i(TAG, "onNavigationItemSelected: Selected navigation item");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.camera_view, Camera2BasicFragment.newInstance())
                            .commit();
                return true;
            case R.id.navigation_food_diary:
                mTextMessage.setText(R.string.title_dashboard);
                return true;
            case R.id.navigation_profile:
                mTextMessage.setText("Profile");
                return true;
            }
        return false;
        }
        };

@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage=(TextView)findViewById(R.id.message);
        BottomNavigationView navigation=(BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

        }
