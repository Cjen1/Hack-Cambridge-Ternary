package com.example.app.ui.DefiniteAllergyFragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import com.example.app.ui.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a String and makes a call to the
 */
public class DefiniteAllergenListItemsFragment extends RecyclerView.Adapter<DefiniteAllergenListItemsFragment.ViewHolder> {

    private List<String> mValues;
    private DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener mCallback;


    public DefiniteAllergenListItemsFragment(List<String> values, DefiniteAllergenListFragment.DefiniteAllergenListFragmentListener callback) {
        mValues = values;
        mCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_definite_allergens_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mContentView.setText(mValues.get(position));
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
