package com.example.app.ui.SuggestedAllergenFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.ui.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link SuggestedAllergenFragmentInteractionListner}
 * interface.
 */
public class SuggestedAllergenFragment extends Fragment {

    public final static String TAG="SuggestedAllergenFragme";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SuggestedAllergenFragment() {

    }

    @SuppressWarnings("unused")
    public static SuggestedAllergenFragment newInstance() {
        SuggestedAllergenFragment fragment = new SuggestedAllergenFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: Creating View");
        View view = inflater.inflate(R.layout.fragment_suggested_allergen_item_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            LinearLayoutManager g = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(g);
            Log.i(TAG, "onCreateView: Setting adaptor");
            recyclerView.setAdapter(new SuggestedAllergenRecycleViewAdaptor());
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface SuggestedAllergenFragmentInteractionListner {
        void onDeleteButtonClick(final int listPosition);
    }
}
