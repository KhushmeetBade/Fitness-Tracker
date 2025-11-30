package com.fitnessapp.service;

import com.fitnessapp.model.User;
import com.fitnessapp.model.FitnessGoal;
import com.fitnessapp.repository.InMemoryDataStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationEngineTest {
    @Test
    void testRecommendWorkoutsForUser() {
        InMemoryDataStore store = new InMemoryDataStore();
        User u = new User("u5", "Zoe", 22, 165, 60, FitnessGoal.LOSE_WEIGHT);
        store.saveUser(u);
        RecommendationEngine r = new RecommendationEngine(store);
        var list = r.recommendWorkouts("u5", 5);
        assertFalse(list.isEmpty());
    }

    @Test
    void testRecommendMealsForUser() {
        InMemoryDataStore store = new InMemoryDataStore();
        User u = new User("u6", "Max", 27, 175, 80, FitnessGoal.BUILD_MUSCLE);
        store.saveUser(u);
        RecommendationEngine r = new RecommendationEngine(store);
        var meals = r.recommendMeals("u6", 5);
        assertFalse(meals.isEmpty());
    }
}
