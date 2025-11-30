package com.fitnessapp.service;

import com.fitnessapp.model.*;
import com.fitnessapp.repository.InMemoryDataStore;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationEngine {
    private final InMemoryDataStore store;

    public RecommendationEngine(InMemoryDataStore store) {
        this.store = store;
    }

    public List<WorkoutPlan> recommendWorkouts(String userId, int max) {
        User u = store.findUser(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
        List<WorkoutPlan> all = store.allWorkoutPlans();
        return all.stream()
                .sorted(Comparator.comparingInt(p -> p.getDurationMinutes()))
                .filter(p -> matchWorkoutToGoal(u.getGoal(), p))
                .limit(max)
                .collect(Collectors.toList());
    }

    private boolean matchWorkoutToGoal(FitnessGoal goal, WorkoutPlan p) {
        return switch (goal) {
            case LOSE_WEIGHT -> p.getDurationMinutes() >= 20;
            case BUILD_MUSCLE -> p.getTitle().toLowerCase().contains("strength") || p.getDurationMinutes() >= 20;
            case MAINTAIN -> true;
        };
    }

    public List<MealPlan> recommendMeals(String userId, int max) {
        User u = store.findUser(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
        return store.allMealPlans().stream()
                .filter(m -> matchMealToGoal(u.getGoal(), m))
                .limit(max)
                .collect(Collectors.toList());
    }

    private boolean matchMealToGoal(FitnessGoal goal, MealPlan m) {
        return switch (goal) {
            case LOSE_WEIGHT -> m.getCaloriesPerDay() <= 1800;
            case BUILD_MUSCLE -> m.getCaloriesPerDay() >= 2400;
            case MAINTAIN -> m.getCaloriesPerDay() >= 2000 && m.getCaloriesPerDay() <= 2400;
        };
    }
}
