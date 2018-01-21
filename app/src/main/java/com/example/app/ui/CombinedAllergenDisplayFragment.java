package com.example.app.ui;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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


    DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener mCallback;
    private Button mAllergenAddButton;

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
        final View v = inflater.inflate(R.layout.fragment_combined_allergen_display, container, false);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        DefiniteAllergenListFragment d = new DefiniteAllergenListFragment();
        SuggestedAllergenFragment s = new SuggestedAllergenFragment();

        
        ft.replace(R.id.definiteAllergenFragmet, d);
        ft.replace(R.id.suggestedAllergenFragment, s);

        Log.i(TAG, "onCreateView: Committing transaction");
        ft.commit();

        Log.i(TAG, "onCreateView: Transaction committed");

        mAllergenAddButton = v.findViewById(R.id.add_definite_allergen);

        mAllergenAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Enter Allergen Here");

                // Set up the input
                final EditText input = new EditText(v.getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        mCallback.onDefiniteAllergenAdd(m_Text);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        return v;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener)
            mCallback = (DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener)context;
        else
            throw new ClassCastException("Context needs to implement DefiniteAllergenlistener");

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
