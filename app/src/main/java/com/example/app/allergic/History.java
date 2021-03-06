package com.example.app.allergic;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class History {
    //todo ensure no synchronisation errors occur here
    public  LinkedList<Event> events = new LinkedList<>();
    public Map<String, Double> allergenMap = new HashMap<>();

    public void updateAllergenMap(){
        allergenMap = getLatestAllergens();
    }

    private double clamp(double val) {return val < 0 ? 0 : val > 1 ? 1 : val; }
    NormalDistribution normal = new NormalDistribution(120, 30);
    public Map<String, Double> getLatestAllergens(){
        Map<String, Double> workingAllergenMap = new HashMap<>();

        for(int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            if(event.type == EventType.CHECKPOINT){
                CheckpointEvent checkpointEvent = (CheckpointEvent)event;
                workingAllergenMap = checkpointEvent.allergens;
            }
            else if(event.type == EventType.EAT){
                EatEvent eatEvent = (EatEvent)event;
                for(String food : eatEvent.ingredients){
                    if(workingAllergenMap.containsKey(food)){
                        workingAllergenMap.put(food, workingAllergenMap.get(food) / 2);
                    } else {
                        workingAllergenMap.put(food, 0.5);
                    }
                }
            }
            else if(event.type == EventType.REACTION){
                for(int j = 0; j < i; j++) {
                    Event previous = events.get(j);
                    if (previous.type == EventType.EAT) {
                        if (hoursBetween(previous.time, event.time) < 12) {
                            for (String food : ((EatEvent) previous).ingredients) {
                                Double value = workingAllergenMap.get(food);
                                if (workingAllergenMap.get(food) != 0.5)
                                    workingAllergenMap.put(food, value * 2);
                            }
                        }

                        double timeDiff = minutesBetween(previous.time, event.time);

                        double reactionProb = normal.probability(timeDiff - 30, timeDiff + 30);

                        double normalising = 0.1;//may need to change.. :)
                        for(String food : ((EatEvent)previous).ingredients){
                            double prob = workingAllergenMap.containsKey(food) ? workingAllergenMap.get(food) : 0.5;
                            workingAllergenMap.put(food, clamp(reactionProb * prob / normalising));
                        }
                    }
                }
            }
        }

        return workingAllergenMap;
    }

    private double hoursBetween(long A, long B){
        return Math.abs(A - B) / (3600.0 * 1000);
    }

    private double minutesBetween(long A, long B){
        return Math.abs(A - B) / (60.0 * 1000);
    }
}
