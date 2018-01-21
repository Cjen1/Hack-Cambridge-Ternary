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

import com.example.app.ui.R;

import java.util.Arrays;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link DefiniteAllergenFragmentInteractionListener}
 * interface.
 */
public class DefiniteAllergenListFragment extends Fragment {

    public final static String TAG="DefiniteAllergenFragmen";

    private DefiniteAllergenFragmentInteractionListener mListener;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            String[] fooArray = {"foo","bar","bat","baz"};
            recyclerView.setAdapter(new MyDefiniteAllergenFragmentRecyclerViewAdapter( mListener, Arrays.asList(fooArray)));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DefiniteAllergenFragmentInteractionListener) {
            mListener = (DefiniteAllergenFragmentInteractionListener) context;
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
    public interface DefiniteAllergenFragmentInteractionListener {
        void onDeleteButtonClick(final int listPosition);
    }
}
