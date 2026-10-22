package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_PANTRY = "pantry_items";
    public static final String TABLE_RECIPES = "recipes";
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pantry_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT NOT NULL, " +
                "expiry_date TEXT DEFAULT '')");

        db.execSQL("CREATE TABLE recipes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "instructions TEXT NOT NULL)");

        db.execSQL("CREATE TABLE recipe_ingredients (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "recipe_id INTEGER NOT NULL, " +
                "ingredient_name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT NOT NULL, " +
                "FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE)");

        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        onCreate(db);
    }

    private void seedRecipes(SQLiteDatabase db) {
        addRecipeWithIngredients(db, "Scrambled Eggs",
                "Whisk eggs, milk, and a pinch of salt. Melt butter in a skillet over medium heat. Pour in egg mixture and stir frequently until soft curds form. Serve warm.",
                new String[]{"Eggs", "Butter", "Milk"},
                new double[]{2.0, 1.0, 2.0},
                new String[]{"pieces", "tbsp", "tbsp"});

        addRecipeWithIngredients(db, "Simple Omelette",
                "Beat eggs and milk together. Pour into a hot greased pan. Sprinkle cheese in the center, fold over, and cook until golden brown.",
                new String[]{"Eggs", "Cheese", "Milk"},
                new double[]{3.0, 50.0, 1.0},
                new String[]{"pieces", "g", "tbsp"});

        addRecipeWithIngredients(db, "Garlic Butter Pasta",
                "Boil pasta in salted water until al dente. Melt butter in a pan, sauté minced garlic until fragrant, and toss with drained pasta.",
                new String[]{"Pasta", "Garlic", "Butter", "Salt"},
                new double[]{100.0, 2.0, 2.0, 1.0},
                new String[]{"g", "cloves", "tbsp", "tsp"});

        addRecipeWithIngredients(db, "Tomato Rice",
                "Sauté chopped onion, garlic, and diced tomatoes in oil. Add washed rice and water. Cover and simmer on low heat until rice is tender.",
                new String[]{"Rice", "Tomato", "Onion", "Garlic", "Oil"},
                new double[]{1.0, 2.0, 1.0, 1.0, 1.0},
                new String[]{"cup", "pieces", "piece", "cloves", "tbsp"});

        addRecipeWithIngredients(db, "Pancakes",
                "Mix flour, sugar, milk, and egg into a smooth batter. Cook ladlefuls on a greased hot skillet until bubbles form, flip and brown.",
                new String[]{"Flour", "Milk", "Egg", "Sugar", "Butter"},
                new double[]{1.0, 1.0, 1.0, 2.0, 1.0},
                new String[]{"cup", "cup", "piece", "tbsp", "tbsp"});

        addRecipeWithIngredients(db, "Peanut Butter Toast",
                "Toast bread slices until golden brown. Spread an even layer of peanut butter on each slice and serve.",
                new String[]{"Bread", "Peanut Butter"},
                new double[]{2.0, 2.0},
                new String[]{"slices", "tbsp"});

        addRecipeWithIngredients(db, "Fried Egg Sandwich",
                "Fry egg in butter until white is set and yolk is cooked to preference. Place between toasted bread slices.",
                new String[]{"Bread", "Egg", "Butter"},
                new double[]{2.0, 1.0, 1.0},
                new String[]{"slices", "piece", "tbsp"});

        addRecipeWithIngredients(db, "Simple Fried Rice",
                "Heat oil in a pan, sauté diced onion and garlic. Add cooked rice and soy sauce, stir fry on high heat until hot and well mixed.",
                new String[]{"Rice", "Onion", "Garlic", "Oil", "Soy Sauce"},
                new double[]{2.0, 1.0, 1.0, 1.0, 1.0},
                new String[]{"cups", "piece", "cloves", "tbsp", "tbsp"});

        addRecipeWithIngredients(db, "Basic Oatmeal",
                "Combine oats and milk in a pot. Bring to a boil, then simmer for 5 minutes stirring occasionally. Stir in sugar before serving.",
                new String[]{"Oats", "Milk", "Sugar"},
                new double[]{1.0, 1.5, 1.0},
                new String[]{"cup", "cups", "tbsp"});

        addRecipeWithIngredients(db, "Cheese Quesadilla",
                "Place cheese between two tortillas. Cook in a skillet with butter over medium heat until tortilla is crispy and cheese is melted.",
                new String[]{"Tortilla", "Cheese", "Butter"},
                new double[]{2.0, 100.0, 1.0},
                new String[]{"pieces", "g", "tbsp"});

        addRecipeWithIngredients(db, "Boiled Egg Salad",
                "Boil eggs for 10 minutes, peel and chop. Mix with mayonnaise and salt in a bowl. Serve on toast or crackers.",
                new String[]{"Eggs", "Mayonnaise", "Salt"},
                new double[]{3.0, 2.0, 0.5},
                new String[]{"pieces", "tbsp", "tsp"});

        addRecipeWithIngredients(db, "Garlic Fried Rice",
                "Sauté minced garlic in oil until golden and crispy. Add cooked rice and salt, toss well until heated through.",
                new String[]{"Rice", "Garlic", "Oil", "Salt"},
                new double[]{2.0, 4.0, 2.0, 0.5},
                new String[]{"cups", "cloves", "tbsp", "tsp"});

        addRecipeWithIngredients(db, "French Toast",
                "Whisk egg and milk together. Dip bread slices in the mixture. Cook in a buttered skillet until golden brown on both sides.",
                new String[]{"Bread", "Egg", "Milk", "Butter"},
                new double[]{2.0, 1.0, 2.0, 1.0},
                new String[]{"slices", "piece", "tbsp", "tbsp"});

        addRecipeWithIngredients(db, "Milkshake",
                "Combine milk, ice cream, and sugar in a blender. Blend until smooth and creamy. Pour into a glass and serve.",
                new String[]{"Milk", "Sugar", "Ice Cream"},
                new double[]{1.0, 1.0, 2.0},
                new String[]{"cup", "tbsp", "scoops"});

        addRecipeWithIngredients(db, "Butter Rice",
                "Cook rice until done. Stir in butter and salt while hot until melted and evenly distributed.",
                new String[]{"Rice", "Butter", "Salt"},
                new double[]{1.0, 2.0, 0.5},
                new String[]{"cup", "tbsp", "tsp"});

        addRecipeWithIngredients(db, "Cinnamon Sugar Toast",
                "Toast bread and spread butter. Mix sugar and cinnamon, then sprinkle generously over the buttered toast.",
                new String[]{"Bread", "Butter", "Sugar", "Cinnamon"},
                new double[]{2.0, 1.0, 1.0, 0.5},
                new String[]{"slices", "tbsp", "tbsp", "tsp"});

        addRecipeWithIngredients(db, "Black Coffee",
                "Boil water and brew coffee using your preferred method. Serve hot in a mug.",
                new String[]{"Coffee", "Water"},
                new double[]{2.0, 1.0},
                new String[]{"tbsp", "cup"});

        addRecipeWithIngredients(db, "Steamed Rice",
                "Combine rice and water in a pot, bring to a boil, cover, reduce heat and simmer for 15 minutes until water is absorbed.",
                new String[]{"Rice", "Water"},
                new double[]{1.0, 2.0},
                new String[]{"cup", "cups"});
    }

    private void addRecipeWithIngredients(SQLiteDatabase db, String name, String instructions,
                                          String[] ingNames, double[] quantities, String[] units) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("instructions", instructions);
        long recipeId = db.insert(TABLE_RECIPES, null, cv);

        for (int i = 0; i < ingNames.length; i++) {
            ContentValues icv = new ContentValues();
            icv.put("recipe_id", recipeId);
            icv.put("ingredient_name", ingNames[i]);
            icv.put("quantity", quantities[i]);
            icv.put("unit", units[i]);
            db.insert(TABLE_RECIPE_INGREDIENTS, null, icv);
        }
    }

    public long addIngredient(String name, double quantity, String unit, String expiryDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name.trim());
        values.put("quantity", quantity);
        values.put("unit", unit.trim());
        values.put("expiry_date", expiryDate == null ? "" : expiryDate.trim());
        return db.insert(TABLE_PANTRY, null, values);
    }

    public ArrayList<PantryItem> getAllIngredients() {
        ArrayList<PantryItem> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.query(TABLE_PANTRY, null, null, null, null, null, "name ASC")) {
            while (cursor.moveToNext()) {
                items.add(new PantryItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getString(cursor.getColumnIndexOrThrow("unit")),
                        cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"))
                ));
            }
        }
        return items;
    }

    public int updateIngredient(int id, String name, double quantity, String unit, String expiryDate) {
        ContentValues values = new ContentValues();
        values.put("name", name.trim());
        values.put("quantity", quantity);
        values.put("unit", unit.trim());
        values.put("expiry_date", expiryDate == null ? "" : expiryDate.trim());
        return getWritableDatabase().update(TABLE_PANTRY, values, "id = ?", new String[]{String.valueOf(id)});
    }

    public int deleteIngredient(int id) {
        return getWritableDatabase().delete(TABLE_PANTRY, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.query(TABLE_RECIPES, null, null, null, null, null, "name ASC")) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String instructions = cursor.getString(cursor.getColumnIndexOrThrow("instructions"));
                List<RecipeIngredient> ingredients = getRecipeIngredients(db, id);
                recipes.add(new Recipe(id, name, instructions, ingredients));
            }
        }
        return recipes;
    }

    private List<RecipeIngredient> getRecipeIngredients(SQLiteDatabase db, int recipeId) {
        List<RecipeIngredient> list = new ArrayList<>();
        try (Cursor cursor = db.query(TABLE_RECIPE_INGREDIENTS, null, "recipe_id = ?", new String[]{String.valueOf(recipeId)}, null, null, null)) {
            while (cursor.moveToNext()) {
                list.add(new RecipeIngredient(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ingredient_name")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getString(cursor.getColumnIndexOrThrow("unit"))
                ));
            }
        }
        return list;
    }

    public List<Recipe> getSuggestedRecipes() {
        List<Recipe> allRecipes = getAllRecipes();
        ArrayList<PantryItem> pantryItems = getAllIngredients();
        List<Recipe> suggested = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            boolean canMake = true;
            for (RecipeIngredient req : recipe.getIngredients()) {
                boolean foundMatch = false;
                for (PantryItem pantry : pantryItems) {
                    if (isNameMatch(pantry.getName(), req.getName())) {
                        // Check quantity if units match or are compatible
                        if (isUnitCompatible(pantry.getUnit(), req.getUnit())) {
                            if (pantry.getQuantity() >= req.getQuantity()) {
                                foundMatch = true;
                                break;
                            }
                        } else {
                            // If units differ but name matches, check if pantry quantity is at least required quantity as a fallback
                            if (pantry.getQuantity() >= req.getQuantity()) {
                                foundMatch = true;
                                break;
                            }
                        }
                    }
                }
                if (!foundMatch) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) {
                suggested.add(recipe);
            }
        }
        return suggested;
    }

    private boolean isNameMatch(String pantryName, String reqName) {
        if (pantryName == null || reqName == null) return false;
        String p = pantryName.trim().toLowerCase();
        String r = reqName.trim().toLowerCase();
        if (p.equals(r)) return true;
        // Handle basic singular/plural
        if (p.equals(r + "s") || r.equals(p + "s")) return true;
        if (p.equals(r + "es") || r.equals(p + "es")) return true;
        if (p.contains(r) || r.contains(p)) {
            return Math.abs(p.length() - r.length()) <= 2;
        }
        return false;
    }

    private boolean isUnitCompatible(String pantryUnit, String reqUnit) {
        if (pantryUnit == null || reqUnit == null) return true;
        String p = pantryUnit.trim().toLowerCase();
        String r = reqUnit.trim().toLowerCase();
        if (p.equals(r)) return true;
        if (p.isEmpty() || r.isEmpty()) return true;
        // Grams / kg / ml / cups compatibility
        if ((p.contains("g") && r.contains("g")) || (p.contains("cup") && r.contains("cup")) ||
            (p.contains("tbsp") && r.contains("tbsp")) || (p.contains("tsp") && r.contains("tsp")) ||
            (p.contains("slice") && r.contains("slice")) || (p.contains("piece") && r.contains("piece"))) {
            return true;
        }
        return false;
    }
}
