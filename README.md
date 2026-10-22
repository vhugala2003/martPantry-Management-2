# Smart Pantry Manager

## Overview
**Smart Pantry Manager** is a fully functional Android application written in Java for Mobile App Development 700. The application helps users reduce food waste by tracking ingredients they currently have at home (their pantry) and suggesting recipes they can cook using strictly those leftover ingredients—no shopping trip required, and no recipe suggested unless the user genuinely already has everything it needs.

---

## Key Features & Functionality
1. **Pantry Management (Full CRUD)**:
   - Add new pantry items with name, quantity, unit, and optional expiry date.
   - View all current ingredients in a dynamic `ListView` backed by SQLite.
   - Edit existing ingredient details or delete items.
   - Input validation on forms (preventing empty names, non-positive quantities, or missing units).
2. **Pre-Loaded Recipe Collection**:
   - Includes 18 pre-seeded recipes (e.g., Scrambled Eggs, Garlic Butter Pasta, Tomato Rice, Pancakes, Peanut Butter Toast, French Toast, etc.) stored in the local SQLite database.
3. **The Strict-Matching Rule (Core Business Logic)**:
   - Evaluates user pantry items against required recipe ingredients.
   - A recipe is suggested **only** if every single required ingredient is present in sufficient quantity.
   - Zero-match feedback screen ("No recipes match your pantry yet - add more ingredients") when no recipes match.
4. **Recipe Detail Screen**:
   - Displays full ingredient breakdown with quantities and units, plus step-by-step preparation instructions.
5. **Settings & Preferences**:
   - Toggle switch for expiring-soon alerts.
   - Unit system selector (Metric / Imperial).
   - About information and app description.

---

## Database Implementation Choice & Justification
- **SQLite (via SQLiteOpenHelper)**:
  - Chosen for local on-device persistence, speed, reliability, and full compatibility with standard Android Architecture.
  - Implements relational tables (`pantry_items`, `recipes`, `recipe_ingredients`) with foreign key constraints and automated seeding of recipes on first launch.
  - Data persists securely across app sessions and restarts.

---

## Technical Architecture
- **Language**: Java 11 / Android SDK.
- **UI Toolkit**: Android Views / XML Layouts (`LinearLayout`, `ScrollView`, `ListView`).
- **Navigation & Intents**: Explicit `Intent` navigation between Activities with extra data passing.
- **Adapters**: Custom `ArrayAdapter` (`PantryAdapter`, `RecipeAdapter`) for dynamic list rendering.

---

## Setup and Running Instructions
1. Open **Android Studio** (Koala or newer).
2. Select **File > Open** and choose the `SmartPantryManager` project root directory.
3. Allow Gradle to sync dependencies.
4. Connect an Android Virtual Device (AVD Emulator running API 24 or higher) or a physical Android device with USB debugging enabled.
5. Click the **Run** (`▶`) button in Android Studio to build and deploy the app.
