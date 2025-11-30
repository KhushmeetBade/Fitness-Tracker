package com.fitnessapp.service;

import com.fitnessapp.model.ActivityRecord;
import com.fitnessapp.model.ActivityType;
import com.fitnessapp.model.User;
import com.fitnessapp.model.FitnessGoal;
import com.fitnessapp.repository.InMemoryDataStore;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FitnessServiceTest {
    @Test
    void testAddActivityAndWeeklySummary() {
        InMemoryDataStore store = new InMemoryDataStore();
        User u = new User("u3", "Sam", 28, 180, 75, FitnessGoal.BUILD_MUSCLE);
        store.saveUser(u);
        FitnessService s = new FitnessService(store);
        ActivityRecord a1 = new ActivityRecord(LocalDate.now(), ActivityType.WEIGHT_TRAIN, 40, 7);
        s.addActivity("u3", a1);

        String summary = s.getWeeklySummary("u3");
        assertTrue(summary.contains("activities=1"));
    }

    @Test
    void testAvgDailyCalories() {
        InMemoryDataStore store = new InMemoryDataStore();
        User u = new User("u4", "Lee", 35, 170, 80, FitnessGoal.MAINTAIN);
        store.saveUser(u);
        FitnessService s = new FitnessService(store);
        s.addActivity("u4", new ActivityRecord(LocalDate.now(), ActivityType.RUN, 30, 6));
        double avg = s.avgDailyCalories("u4", 7);
        assertTrue(avg >= 0.0);
    }
}
