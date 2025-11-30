# Fitness Tracker (COMP3520 Group Project)

Java console-based fitness & wellness tracker.

## Features
- Personalized user profiles
- Activity logging and weekly summary
- Recommendation engine for workouts & meal plans
- Unit tests (JUnit 5)
- CI via GitHub Actions with simulated deploy step

## Run
Prereqs: Java 17, Maven.

Install:
```bash
brew install temurin17
brew install maven
```

Run tests:
```bash
mvn clean test
```

Run app:
```bash
mvn -q -Dexec.args="com.fitnessapp.App" exec:java -Dexec.mainClass="com.fitnessapp.App"
```

## CI
See `.github/workflows/ci-cd.yml` — runs `mvn verify` and prints a simulated deployment message.

## Group
List group members and student IDs here.
