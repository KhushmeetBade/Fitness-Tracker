package com.fitnessapp.service;

import com.fitnessapp.model.ActivityRecord;
import com.fitnessapp.model.User;
import com.fitnessapp.repository.InMemoryDataStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class FitnessService {
    private final InMemoryDataStore store;

    public FitnessService(InMemoryDataStore store) {
        this.store = store;
    }

    public void addActivity(String userId, ActivityRecord record) {
        Optional<User> u = store.findUser(userId);
        if (u.isEmpty()) throw new IllegalArgumentException("user not found");
        store.addActivity(userId, record);
    }

    public String getWeeklySummary(String userId) {
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minus(6, ChronoUnit.DAYS);
        List<ActivityRecord> list = store.getActivitiesForUserBetween(userId, weekStart, today);
        if (list.isEmpty()) return "No activity logged in the last 7 days.";

        DoubleSummaryStatistics stats = list.stream().mapToDouble(ActivityRecord::estimateCalories).summaryStatistics();
        int totalMinutes = list.stream().mapToInt(ActivityRecord::getMinutes).sum();

        return String.format("Weekly summary (%s to %s): activities=%d, minutes=%d, calories=%.1f",
                weekStart, today, list.size(), totalMinutes, stats.getSum());
    }

    public double avgDailyCalories(String userId, int days) {
        if (days <= 0) throw new IllegalArgumentException("days>0");
        LocalDate today = LocalDate.now();
        LocalDate start = today.minus(days - 1, ChronoUnit.DAYS);
        List<ActivityRecord> list = store.getActivitiesForUserBetween(userId, start, today);
        double total = list.stream().mapToDouble(ActivityRecord::estimateCalories).sum();
        return total / days;
    }

    public void updateUserWeight(String userId, double newWeightKg) {
        User user = store.findUser(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
        user.setWeightKg(newWeightKg);
        store.saveUser(user);
    }
}
