package com.example.sophie.hello_world;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FooFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FooFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FooFragment extends Fragment {

    final String TAG = "";
    private OnFragmentInteractionListener mListener;
    private ListView mList;
    public FooFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FooFragment.
     */
    public static FooFragment newInstance() {
        FooFragment fragment = new FooFragment();
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
        Log.i(TAG, "onCreateView: Creating view");
        View view = inflater.inflate(R.layout.fragment_foo, container, false);

        Log.i(TAG, "getting list");
        mList = (ListView)view.findViewById(R.id.list);

        Log.i(TAG, "onCreateView: Adding foo");
        ArrayList<String> l = new ArrayList<String>();
        l.add("foo");
        l.add("bar");


        Log.i(TAG, "onCreateView: Adding adapter");
        ListAdapter adapter = new ArrayAdapter<String>(container.getContext(), R.layout.simplerow, l);
        mList.setAdapter(adapter);

        Log.i(TAG, "onCreateView: done");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }
}
