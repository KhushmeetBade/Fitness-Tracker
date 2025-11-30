package com.fitnessapp.model;

import java.util.List;

public class MealPlan {
    private final String id;
    private final String title;
    private final List<String> meals;
    private final int caloriesPerDay;

    public MealPlan(String id, String title, List<String> meals, int caloriesPerDay) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id required");
        this.id = id; this.title = title; this.meals = meals; this.caloriesPerDay = caloriesPerDay;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public List<String> getMeals() { return meals; }
    public int getCaloriesPerDay() { return caloriesPerDay; }

    @Override
    public String toString() {
        return String.format("MealPlan[%s] %s (cal/day=%d)", id, title, caloriesPerDay);
    }
}
