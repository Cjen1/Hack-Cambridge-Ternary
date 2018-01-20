package com.example.app.ui.DefiniteAllergyFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.ui.R;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link DefiniteAllergenFragmentInteractionListner}
 * interface.
 */
public class DefiniteAllergenFragmentFragment extends Fragment {
    
    public final static String TAG="DefiniteAllergenFragmen";

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final int mColumnCount = 2;
    private DefiniteAllergenFragmentInteractionListner mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DefiniteAllergenFragmentFragment() {
    }

    @SuppressWarnings("unused")
    public static DefiniteAllergenFragmentFragment newInstance() {
        DefiniteAllergenFragmentFragment fragment = new DefiniteAllergenFragmentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: Creating view");
        View view = inflater.inflate(R.layout.fragment_definiteallergenfragment_list, container, false);

        Log.i(TAG, "onCreateView: Getting instance state arraylist");
        List<String> definiteAllergens = getArguments().getStringArrayList("definite_allergens");

        // Set the adapter
        Log.i(TAG, "onCreateView: Fiddling with view");
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            GridLayoutManager g = new GridLayoutManager(context, mColumnCount);
            recyclerView.setLayoutManager(g);

            Log.i(TAG, "onCreateView: Setting adaptor");
            recyclerView.setAdapter(new MyDefiniteAllergenFragmentRecyclerViewAdapter(definiteAllergens, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DefiniteAllergenFragmentInteractionListner) {
            mListener = (DefiniteAllergenFragmentInteractionListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface DefiniteAllergenFragmentInteractionListner {
        void onDeleteButtonClick(final int listPosition);
    }
}
