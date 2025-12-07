// Updating ActivityRecord Test file
package com.fitnessapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class ActivityRecord {
    private final LocalDate date;
    private final ActivityType type;
    private final int minutes;
    private final int intensity; // 1-10

    public ActivityRecord(LocalDate date, ActivityType type, int minutes, int intensity) {
        if (date == null) throw new IllegalArgumentException("date required");
        if (minutes <= 0) throw new IllegalArgumentException("minutes positive");
        if (intensity < 1 || intensity > 10) throw new IllegalArgumentException("intensity 1-10");
        this.date = date;
        this.type = type;
        this.minutes = minutes;
        this.intensity = intensity;
    }

    public LocalDate getDate() { return date; }
    public ActivityType getType() { return type; }
    public int getMinutes() { return minutes; }
    public int getIntensity() { return intensity; }

    public double estimateCalories() {
        double base = switch (type) {
            case RUN -> 10.0;
            case CYCLE -> 7.5;
            case WEIGHT_TRAIN -> 6.0;
            default -> 5.0;
        };
        double intensityMultiplier = 0.5 + intensity / 10.0;
        return minutes * base * intensityMultiplier;
    }

    @Override
    public String toString() {
        return String.format("%s %s %dmin intensity=%d calories=%.1f",
                date, type, minutes, intensity, estimateCalories());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityRecord that = (ActivityRecord) o;
        return minutes == that.minutes && intensity == that.intensity && date.equals(that.date) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type, minutes, intensity);
    }
}
