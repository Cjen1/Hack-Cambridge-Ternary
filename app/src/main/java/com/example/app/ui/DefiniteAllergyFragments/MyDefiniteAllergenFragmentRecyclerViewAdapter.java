package com.example.app.ui.DefiniteAllergyFragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment.DefiniteAllergenFragmentInteractionListener;
import com.example.app.ui.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a String and makes a call to the
 * specified {@link DefiniteAllergenFragmentInteractionListener}.
 */
public class MyDefiniteAllergenFragmentRecyclerViewAdapter extends RecyclerView.Adapter<MyDefiniteAllergenFragmentRecyclerViewAdapter.ViewHolder> {

    private List<String> mValues;
    private final DefiniteAllergenFragmentInteractionListener mListener;

    public MyDefiniteAllergenFragmentRecyclerViewAdapter(DefiniteAllergenFragmentInteractionListener listener, List<String> values) {
        mValues = values;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_definite_allergen_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mContentView.setText(mValues.get(position));

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onDeleteButtonClick(position);

                    mValues.remove(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final Button mButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mButton = (Button) view.findViewById(R.id.delete_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
