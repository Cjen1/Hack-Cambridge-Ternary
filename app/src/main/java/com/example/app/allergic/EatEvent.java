package com.example.app.allergic;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class EatEvent extends Event{
    public List<String> ingredients;
    public EatEvent(Instant Time){
        this.type = EventType.EAT;
        this.time = Time;
    }

    public EatEvent(Instant Time, List<String> ingredients){
        this.ingredients = ingredients;
        this.time = Time;
    }
}
