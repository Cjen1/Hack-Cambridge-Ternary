package com.example.app.allergic;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class CheckpointEvent extends Event{
    public CheckpointEvent(Instant Time){
        this.type = EventType.CHECKPOINT;
        this.time = Time;
    }
    public Map<String, Double> allergens;
}
