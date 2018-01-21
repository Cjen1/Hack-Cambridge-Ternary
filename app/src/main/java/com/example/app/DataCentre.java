package com.example.app;


import com.example.app.allergic.EatEvent;
import com.example.app.allergic.Event;
import com.example.app.allergic.History;
import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListFragment;
import com.example.app.ui.DefiniteAllergyFragments.DefiniteAllergenListItemsFragment;

import java.util.List;

public class DataCentre {
    public static synchronized void addEatEvent(EatEvent event){
        history.events.add(event);
        history.updateAllergenMap();
    }

    public static synchronized void remove(Event e){
        history.events.remove(e);
        history.updateAllergenMap();
    }

    private static History history = new History();

    public abstract class Listener {
        public abstract void callback();
    };
}
