package com.example.app.allergic;

import java.time.Instant;

public class ReactionEvent extends Event{
    public ReactionEvent(Instant Time){
        this.type = EventType.REACTION;
        this.time = Time;
    }
}
