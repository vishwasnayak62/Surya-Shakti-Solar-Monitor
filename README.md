# ☀️ Surya-Shakti Solar Monitor

An Android application developed using GenAI concepts to help users monitor and analyze solar energy generation and consumption in smart homes.

The application provides users with insights into their daily solar power usage, electricity savings, grid dependency, and smart energy suggestions based on weather conditions.

---

# 📌 Problem Statement

Many households in rural and semi-urban areas are installing rooftop solar panels. However, users often do not understand how much energy they are generating versus consuming.

Without a simple monitoring tool, users cannot optimize electricity usage effectively.

Surya-Shakti helps users:
- Track solar generation and energy consumption
- Monitor savings and environmental impact
- Get smart suggestions for appliance usage
- Understand solar vs grid dependency

---

# 🚀 Features

- ☀️ Solar Energy Generation Tracking
- ⚡ Electricity Consumption Monitoring
- 📊 Solar vs Grid Circular Progress Dashboard
- 💰 Daily Electricity Savings Calculation
- 🌱 CO₂ Reduction Monitoring
- 🌤 Weather-based Smart Suggestions
- 🔋 Grid Status Detection
- 📝 Add Daily Solar Data
- 📱 Modern Dark Theme UI

---

# 🛠 Tech Stack

- Android Studio
- Java / Kotlin
- XML UI Design
- Room Database
- Material UI Components

---

## Live Demo
https://appetize.io/app/b_f5rit4bkudmlny5uvr3is4574i?device=pixel7&osVersion=13.0&toolbar=true

# 📂 Project Structure

```bash
Surya-Shakti/
│
├── app/
│
├── src/
│   ├── androidTest/
│   │
│   └── main/
│       ├── java/com/example/suryashakti/
│       │
│       ├── data/
│       │   ├── SolarDao.kt
│       │   ├── SolarDatabase.kt
│       │   └── SolarLog.kt
│       │
│       ├── navigation/
│       │   └── AppNavigation.kt
│       │
│       ├── ui/theme/
│       │   ├── Color.kt
│       │   ├── Theme.kt
│       │   └── Type.kt
│       │
│       ├── components/
│       │   └── DashboardCard.kt
│       │
│       ├── screens/
│       │   ├── AddDataScreen.kt
│       │   ├── DashboardScreen.kt
│       │   └── HistoryScreen.kt
│       │
│       └── MainActivity.kt
│
└── README.md
```
