package com.example.app.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergenFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CombinedAllergenDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CombinedAllergenDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CombinedAllergenDisplayFragment extends Fragment {


    private DefiniteAllergenListFragment mDefiniteFragment;
    private SuggestedAllergenFragment mSuggestedFragment;

    public CombinedAllergenDisplayFragment() {
        mSuggestedFragment = new SuggestedAllergenFragment();
        mDefiniteFragment = new DefiniteAllergenListFragment();
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

        ft.replace(R.id.definiteAllergenFragmet, mDefiniteFragment);
        ft.replace(R.id.suggestedAllergenFragment, mSuggestedFragment);

        ft.commit();

        return v;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }
}
