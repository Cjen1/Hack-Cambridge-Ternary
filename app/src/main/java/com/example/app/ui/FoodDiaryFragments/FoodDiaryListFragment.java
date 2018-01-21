package com.example.app.ui.FoodDiaryFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app.DataCentre;
import com.example.app.allergic.ReactionEvent;
import com.example.app.test_arrays.TestArrays;
import com.example.app.ui.R;
import com.example.app.allergic.Event;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodDiaryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodDiaryListFragment extends Fragment {

    final String TAG = "";
    private RecyclerView mList;
    private FoodDiaryListener mCallback;
    private Button mAddReactionButton;
    private Button mAddFoodButton;

    public FoodDiaryListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FoodDiaryListFragment.
     */
    public static FoodDiaryListFragment newInstance() {
        FoodDiaryListFragment fragment = new FoodDiaryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_food_diary, container, false);

        View buttonView = view.findViewById(R.id.foodDiaryRootLayout).findViewById(R.id.foodDiarySecondaryLayout);
        mAddFoodButton = (Button)buttonView.findViewById(R.id.add_food);
        mAddReactionButton = (Button)buttonView.findViewById(R.id.add_reaction);

        mAddFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mAddReactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.addEventToDiary(new ReactionEvent(new Date().getTime()));
            }
        });

        if (view.findViewById(R.id.list) instanceof RecyclerView) {
            mList = (RecyclerView) view.findViewById(R.id.list);
            Context context = view.getContext();
            LinearLayoutManager g = new LinearLayoutManager(context);
            mList.setLayoutManager(g);
            FoodDiaryListItemsFragment adapter = new FoodDiaryListItemsFragment(DataCentre.history.events);
            mList.setAdapter(adapter);


        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FoodDiaryListener) {
            mCallback = (FoodDiaryListener)context;
        }
        else {
            Exception e = new ClassCastException();
            Log.e(TAG, "onAttach: FoodDiaryListFragment needs to implement foodDiaryListener", e);
            throw new ClassCastException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface FoodDiaryListener {
        void addEventToDiary(Event e);
        void removeEventFromDiary(int position);
    }

}
