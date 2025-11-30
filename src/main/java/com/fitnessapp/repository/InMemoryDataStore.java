package com.fitnessapp.repository;

import com.fitnessapp.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDataStore {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, List<ActivityRecord>> activities = new ConcurrentHashMap<>();
    private final Map<String, WorkoutPlan> workoutPlans = new ConcurrentHashMap<>();
    private final Map<String, MealPlan> mealPlans = new ConcurrentHashMap<>();

    public InMemoryDataStore() {
        seedDefaults();
    }

    private void seedDefaults() {
        WorkoutPlan runBeginner = new WorkoutPlan("wp1", "Run - Beginner",
                List.of("Warmup 5min", "Run 20min easy", "Cool down 5min"), 30);
        WorkoutPlan strengthShort = new WorkoutPlan("wp2", "Strength - 20min",
                List.of("Pushups 3x8", "Squats 3x10", "Plank 3x30s"), 20);
        workoutPlans.put(runBeginner.getId(), runBeginner);
        workoutPlans.put(strengthShort.getId(), strengthShort);

        MealPlan loseWeight = new MealPlan("mp1", "Low Cal Balanced", List.of("Oatmeal", "Salad + chicken", "Veg + rice"), 1600);
        MealPlan buildMuscle = new MealPlan("mp2", "High Protein", List.of("Eggs + oats", "Chicken + quinoa", "Cottage cheese + nuts"), 2600);
        mealPlans.put(loseWeight.getId(), loseWeight);
        mealPlans.put(buildMuscle.getId(), buildMuscle);
    }

    public void saveUser(User user) {
        users.put(user.getId(), user);
    }

    public Optional<User> findUser(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public void addActivity(String userId, ActivityRecord record) {
        activities.computeIfAbsent(userId, k -> Collections.synchronizedList(new ArrayList<>())).add(record);
    }

    public List<ActivityRecord> getActivitiesForUser(String userId) {
        return activities.getOrDefault(userId, List.of()).stream().collect(Collectors.toList());
    }

    public List<ActivityRecord> getActivitiesForUserBetween(String userId, LocalDate start, LocalDate endInclusive) {
        return getActivitiesForUser(userId).stream()
                .filter(a -> !a.getDate().isBefore(start) && !a.getDate().isAfter(endInclusive))
                .collect(Collectors.toList());
    }

    public List<WorkoutPlan> allWorkoutPlans() { return new ArrayList<>(workoutPlans.values()); }
    public List<MealPlan> allMealPlans() { return new ArrayList<>(mealPlans.values()); }

    public Optional<WorkoutPlan> getWorkoutById(String id) { return Optional.ofNullable(workoutPlans.get(id)); }
    public Optional<MealPlan> getMealById(String id) { return Optional.ofNullable(mealPlans.get(id)); }
}
