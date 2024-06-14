CSUS College of Engineering and Computer Science
Department of Computer Science
CSc 133 – Object-Oriented Computer Graphics Programming
Spring 2021
Dr. Muyan

---

## Program Description
This assignment involves developing a class hierarchy and control structure for a simple video game called ThePath. The goal is to control an ant navigating a path, avoiding collisions with spiders, and maintaining its food level. The program is designed using UML and implemented in Codename One (CN1) in a text-based simulation with keyboard input commands.

---

## Program Structure

### Main Components
1. **Game:** Encapsulates the main game, manages flow control, enforces rules, and displays game state.
2. **GameWorld:** Contains game objects and state variables.
3. **Starter:** The entry point that constructs an instance of the Game class.

### Class Hierarchy
- **GameObject (Abstract)**
  - Attributes: `size`, `location`, `color`
- **Fixed (Abstract)** extends GameObject
  - Concrete Types: `Flag`, `FoodStation`
- **Movable (Abstract)** extends GameObject
  - Attributes: `heading`, `speed`
  - Concrete Types: `Ant`, `Spider`
  - **Steerable Interface:** Implemented by steerable movable objects (e.g., `Ant`)

### Class Definitions

#### Game
- Extends from `Form` (com.codename1.ui package)
- Manages game initialization, control flow, and display

#### GameWorld
- Holds game objects
- Methods: `init()`, `addObject()`, `removeObject()`, `getObject()`, etc.

#### Starter
- Initializes the game
- Constructs an instance of the Game class

---

## Game World Objects

### Attributes for All Objects
- `size`: Integer, cannot change once created.
- `location`: `Point` (float values), center of the object within the 1000 x 1000 world.
- `color`: Integer, set at creation.

### Fixed Objects
- **Flag:** Attributes include `sequenceNumber`. Cannot change location or color once created.
- **FoodStation:** Attributes include `capacity`, proportional to size. Cannot change location once created.

### Movable Objects
- **Ant:** Attributes include `maximumSpeed`, `foodLevel`, `foodConsumptionRate`, `healthLevel`, `lastFlagReached`. Implements `ISteerable`.
- **Spider:** Random heading changes. Speed and heading initialized randomly.

---

## Game Play

### Commands
- **Movement & Control:**
  - `a`: Accelerate ant
  - `b`: Brake ant
  - `l`: Turn ant left
  - `r`: Turn ant right
  - `1-9`: Collide with flag `x`
  - `f`: Collide with a food station
  - `g`: Collide with a spider
  - `t`: Game clock tick
  - `d`: Display game state
  - `m`: Display map
  - `x`: Exit (requires confirmation with `y` or `n`)

### State Values
- `livesRemaining`
- `currentClockTime`
- `lastFlagReached`
- `foodLevel`
- `healthLevel`

---
