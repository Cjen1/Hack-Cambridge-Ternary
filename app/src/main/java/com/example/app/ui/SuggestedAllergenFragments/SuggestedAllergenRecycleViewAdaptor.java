package com.example.app.ui.SuggestedAllergenFragments;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.ui.R;

import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergenFragment.SuggestedAllergenFragmentInteractionListener;

import java.util.List;

import android.widget.Button;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SuggestedAllergen} and makes a call to the
 * specified {@link SuggestedAllergenFragmentInteractionListener}.
 */
public class SuggestedAllergenRecycleViewAdaptor extends RecyclerView.Adapter<SuggestedAllergenRecycleViewAdaptor.ViewHolder> {

    private List<SuggestedAllergen> mValues;
    private SuggestedAllergenFragmentInteractionListener mCallback;

    public SuggestedAllergenRecycleViewAdaptor(List<SuggestedAllergen> al, SuggestedAllergenFragmentInteractionListener callback) {
        mValues = al;
        mCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_suggested_allergen_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mAllergenView.setText(mValues.get(position).allergen);

        int redness = (int)((double)0xFF * (1.0 - holder.mItem.rating));
        int greenness = (int)((double)0xFF * holder.mItem.rating);

        int opacity = 0xFF;

        holder.mRatingView.setText("O");
        holder.mRatingView.setTextColor(Color.argb(opacity, redness, greenness, 0));

/*
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                if (mCallback != null)
                    mCallback.onSuggestedAllergenDeleteRequest(position);

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAllergenView;
        public final TextView mRatingView;
        public final Button mButton;
        public SuggestedAllergen mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAllergenView = (TextView) view.findViewById(R.id.allergen);
            mRatingView = (TextView) view.findViewById(R.id.t_light);
            mButton = (Button) view.findViewById(R.id.delete_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAllergenView.getText() + "'";
        }
    }
}
