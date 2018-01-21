package com.example.app.ui.DefiniteAllergyFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.test_arrays.TestArrays;
import com.example.app.ui.R;

import java.util.Arrays;

/**
 * A fragment representing a list of Items.
 */
public class DefiniteAllergenListFragment extends Fragment {

    public final static String TAG="DefiniteAllergenFragmen";
    private DefiniteAllergenListFragmentListener mCallback;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DefiniteAllergenListFragment() {

    }

    @SuppressWarnings("unused")
    public static DefiniteAllergenListFragment newInstance() {
        DefiniteAllergenListFragment fragment = new DefiniteAllergenListFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_definite_allergen_fragment_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            LinearLayoutManager g = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(g);
            DefiniteAllergenListItemsFragment d = new DefiniteAllergenListItemsFragment(TestArrays.mDefiniteAllergenArray, mCallback);
            recyclerView.setAdapter(d);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (DefiniteAllergenListFragmentListener) context;

        }  catch (ClassCastException e) {
            Log.e(TAG, "onAttach: Must implement DefiniteAllergenListFragmentListener", e);
            throw new ClassCastException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public interface DefiniteAllergenListFragmentListener {
        void onDefiniteAllergenDelete(int position);
        void onDefiniteAllergenAdd(String item);
    }
}
