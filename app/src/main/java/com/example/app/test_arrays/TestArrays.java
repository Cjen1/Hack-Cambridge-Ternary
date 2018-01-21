package com.example.app.test_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.app.DataCentre;
import com.example.app.allergic.EatEvent;
import com.example.app.allergic.ReactionEvent;
import com.example.app.ui.SuggestedAllergenFragments.SuggestedAllergen;

/**
 * Created by sophie on 21/01/18.
 */

public class TestArrays {

    public static void generateTestData(){
        String[] foodDiary = {"cheese", "eggs", "biscuits"};
        String[] definiteAllergens = {"oranges", "pineapple", "chocolate"};

        ArrayList<String> definiteAllergiesAL = new ArrayList<>(Arrays.asList(definiteAllergens));
        ArrayList<SuggestedAllergen> suggestedAllergensAL = new ArrayList<>();
        suggestedAllergensAL.add(new SuggestedAllergen("peaches", 0.25));
        suggestedAllergensAL.add(new SuggestedAllergen("cheese", 0.75));

        EatEvent foodDiaryEvent = new EatEvent(System.currentTimeMillis(), Arrays.asList(foodDiary));

        DataCentre.mDefiniteAllergenArray = definiteAllergiesAL;
        DataCentre.mSuggestedAllergenArray = suggestedAllergensAL;
        DataCentre.history.events.add(foodDiaryEvent);
        DataCentre.history.events.add(new ReactionEvent(System.currentTimeMillis()));
    }
}
