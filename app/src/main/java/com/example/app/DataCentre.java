package com.example.app;


import com.example.app.allergic.EatEvent;
import com.example.app.allergic.Event;
import com.example.app.allergic.History;
import com.example.app.test_arrays.TestArrays;
import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment;
import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListItemsFragment;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DataCentre {
    public static synchronized void addEatEvent(EatEvent event){
        history.events.add(event);
        history.updateAllergenMap();
    }

    public static void remove(EatEvent event){
        history.events.remove(event);
        history.updateAllergenMap();
    }

    public static synchronized void remove(Event e){
        history.events.remove(e);
        history.updateAllergenMap();
    }

    public static History history = new History();

    public static List<Event> mFoodDiaryArray;
    public static List<SuggestedAllergen> mSuggestedAllergenArray;
    public static List<String> mDefiniteAllergenArray = new ArrayList<>();

    public abstract class Listener {
        public abstract void callback();
    };

    public static void updateDefinite(){
        //todo read in from new from csv and then write back to ensure changes are made
    }

    public static void updateSuggested(){
        List<SuggestedAllergen> suggestedAllergens = new ArrayList<>();
        for(Map.Entry<String, Double> e : history.allergenMap.entrySet()){
            if(!mDefiniteAllergenArray.contains(e.getKey()))
                suggestedAllergens.add(new SuggestedAllergen(e.getKey(), e.getValue()));
        }

        Collections.sort(suggestedAllergens, new Comparator<SuggestedAllergen>() {
            @Override
            public int compare(SuggestedAllergen suggestedAllergen, SuggestedAllergen t1) {
                    double diff = suggestedAllergen.rating - t1.rating;
                    return diff > 0 ? 1 : diff < 0 ? -1 : 0;
            }
        });

        mSuggestedAllergenArray = suggestedAllergens;
    }

//    TestArrays.generateTestData();
}
