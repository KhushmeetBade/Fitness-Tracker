package com.fitnessapp.model;

import java.util.List;
import java.util.StringJoiner;

public class WorkoutPlan {
    private final String id;
    private final String title;
    private final List<String> steps;
    private final int durationMinutes;

    public WorkoutPlan(String id, String title, List<String> steps, int durationMinutes) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id required");
        this.id = id; this.title = title; this.steps = steps; this.durationMinutes = durationMinutes;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public List<String> getSteps() { return steps; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        steps.forEach(sj::add);
        return String.format("Plan[%s] %s (%dmin) steps=%s", id, title, durationMinutes, sj.toString());
    }
}
