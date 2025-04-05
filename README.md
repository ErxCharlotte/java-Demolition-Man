# 💣 Demolition Man

A retro-style arcade game prototype written in Java using the Processing library.  

---

## 🎮 Gameplay

You control **Bomb Guy** and must:
- Destroy breakable walls using bombs
- Avoid enemies and explosions
- Reach the goal tile to win each level

Features include:
- Map loading from text files
- Animated player and enemies
- Bombs with timed explosions and sprite effects
- Multiple levels and a game-over/win system

---

## 🔧 Tech Stack

- Java 8+
- [Processing](https://processing.org/) graphics library
- Gradle build system
- Runs at 60 FPS in 480x480 window

---

## ▶️ Run Instructions

```bash
gradle build
gradle run
Make sure to include all assets and config.json in the /resources directory.

🧪 Testing
Unit tests should be added under src/test/
Code coverage via JaCoCo recommended (target ≥ 90%)

📄 Notes
Lives, level timer, and level progression defined in config.json

All maps must be valid (surrounded by solid walls, contain start + goal)

Game ends on 0 lives or timeout
