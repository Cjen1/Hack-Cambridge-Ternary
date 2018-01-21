package com.example.app.ui.FoodDiaryFragments;

/**
 * Created by sophie on 21/01/18.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import com.example.app.allergic.EatEvent;
import com.example.app.allergic.ReactionEvent;
import com.example.app.ui.R;
import com.example.app.allergic.Event;

import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a String and makes a call to the
 */
public class FoodDiaryListItemsFragment extends RecyclerView.Adapter<FoodDiaryListItemsFragment.ViewHolder> {

    private List<Event> mValues;
    private FoodDiaryListFragment.FoodDiaryListener mCaller;

    public FoodDiaryListItemsFragment( List<Event> values) {
        mValues = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_definite_allergens_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Event e = mValues.get(position);
        Date d = new Date(mValues.get(position).time);
        String dateString = d.toString();

        String eventString = "";

        if (e instanceof EatEvent) {
            EatEvent ee = (EatEvent) e;
            while (ee.ingredients.iterator().hasNext()) {
                String item = ee.ingredients.iterator().next();
                eventString += item;
                if (ee.ingredients.iterator().hasNext())
                    eventString += ", ";
            }
        }
        if (e instanceof ReactionEvent) {
            ReactionEvent re = (ReactionEvent) e;
            eventString = "Reaction occurred";
        }

        if (eventString.equals("")) {
            holder.mButton.setVisibility(View.INVISIBLE);
            holder.mContentView.setVisibility(View.INVISIBLE);
        } else {
            holder.mContentView.setText(dateString + ": " + eventString);

            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCaller.removeEventFromDiary(position);
                }
            });
        }


    }

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
