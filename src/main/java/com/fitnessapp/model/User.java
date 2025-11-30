package com.fitnessapp.model;

public class User {
    private final String id;
    private String name;
    private int age;
    private int heightCm;
    private double weightKg;
    private FitnessGoal goal;

    public User(String id, String name, int age, int heightCm, double weightKg, FitnessGoal goal) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id required");
        this.id = id;
        this.name = name;
        this.age = age;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.goal = goal;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getHeightCm() { return heightCm; }
    public double getWeightKg() { return weightKg; }
    public FitnessGoal getGoal() { return goal; }

    public void setWeightKg(double weightKg) {
        if (weightKg <= 0) throw new IllegalArgumentException("weight must be positive");
        this.weightKg = weightKg;
    }

    public void setGoal(FitnessGoal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s,name=%s,age=%d,height=%dcm,weight=%.1fkg,goal=%s]",
                id, name, age, heightCm, weightKg, goal);
    }
}
