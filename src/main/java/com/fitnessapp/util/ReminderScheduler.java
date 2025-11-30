package com.fitnessapp.util;

import java.util.Timer;
import java.util.TimerTask;

public class ReminderScheduler {
    public static void scheduleDailyReminder(Runnable r, long secondsInterval) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try { r.run(); } catch (Throwable t) { t.printStackTrace(); }
            }
        }, secondsInterval * 1000, secondsInterval * 1000);
    }
}
