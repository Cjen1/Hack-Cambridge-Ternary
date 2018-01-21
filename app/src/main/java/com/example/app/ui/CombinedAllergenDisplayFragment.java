package com.example.app.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergen;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergenFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CombinedAllergenDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CombinedAllergenDisplayFragment extends Fragment  {
    static final String TAG="combindallergen";


    public CombinedAllergenDisplayFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CombinedAllergenDisplayFragment.
     */
    public static CombinedAllergenDisplayFragment newInstance() {
        CombinedAllergenDisplayFragment fragment = new CombinedAllergenDisplayFragment();


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
        View v = inflater.inflate(R.layout.fragment_combined_allergen_display, container, false);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        DefiniteAllergenListFragment d = new DefiniteAllergenListFragment();
        SuggestedAllergenFragment s = new SuggestedAllergenFragment();

        
        ft.replace(R.id.definiteAllergenFragmet, d);
        ft.replace(R.id.suggestedAllergenFragment, s);

        Log.i(TAG, "onCreateView: Committing transaction");
        ft.commit();

        Log.i(TAG, "onCreateView: Transaction committed");

        return v;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
