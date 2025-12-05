//This is the InMemoryDataStoreTest Test

package com.fitnessapp.repository;

import com.fitnessapp.model.ActivityRecord;
import com.fitnessapp.model.ActivityType;
import com.fitnessapp.model.User;
import com.fitnessapp.model.FitnessGoal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDataStoreTest {
    @Test
    void testUserSaveAndFind() {
        InMemoryDataStore store = new InMemoryDataStore();
        User u = new User("t1", "Test", 25, 170, 65.0, FitnessGoal.MAINTAIN);
        store.saveUser(u);
        assertTrue(store.findUser("t1").isPresent());
        assertEquals("Test", store.findUser("t1").get().getName());
    }

    @Test
    void testAddActivityAndQuery() {
        InMemoryDataStore store = new InMemoryDataStore();
        String uid = "u2";
        store.saveUser(new User(uid, "A", 20, 160, 60, FitnessGoal.MAINTAIN));
        ActivityRecord ar = new ActivityRecord(LocalDate.now(), ActivityType.WALK, 30, 3);
        store.addActivity(uid, ar);
        List<ActivityRecord> list = store.getActivitiesForUser(uid);
        assertEquals(1, list.size());
        assertEquals(ar, list.get(0));
    }
}
