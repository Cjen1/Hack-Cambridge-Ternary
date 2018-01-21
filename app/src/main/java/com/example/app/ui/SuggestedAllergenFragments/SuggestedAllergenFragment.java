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

import com.example.app.DataCentre;
import com.example.app.ui.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link SuggestedAllergenFragmentInteractionListener}
 * interface.
 */
public class SuggestedAllergenFragment extends Fragment {

    public final static String TAG="SuggestedAllergenFragme";
    private RecyclerView mRecyclerView;
    private SuggestedAllergenFragmentInteractionListener mCallback;


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

        View view = inflater.inflate(R.layout.fragment_suggested_allergen_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;

            LinearLayoutManager g = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(g);

            mRecyclerView.setAdapter(new SuggestedAllergenRecycleViewAdaptor(DataCentre.mSuggestedAllergenArray, mCallback));
        }
        Log.i(TAG, "onCreateView: Returning view");
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SuggestedAllergenFragmentInteractionListener) {
            mCallback = (SuggestedAllergenFragmentInteractionListener)context;
            Log.i(TAG, "onAttach: Successfully attached callback");
        }
        else {
            Log.e(TAG, "onAttach: SuggestedAllergenFragmentInteractionListener not implemented", new ClassCastException());
            throw new ClassCastException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
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
    public interface SuggestedAllergenFragmentInteractionListener {
        void onSuggestedAllergenDeleteRequest(final int listPosition);
        void onSuggestedAllergenAddRequest(final String allergen, final int listPosition);
    }
}
