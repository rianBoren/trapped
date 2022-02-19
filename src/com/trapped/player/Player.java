package com.trapped.player;


import com.trapped.utilities.Puzzle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {
    public static String location = "bed";
    private Puzzle puzzle = Puzzle.getInstance();
    private Inventory inventory = Inventory.getInstance();
    // Player Singleton
    private static final Player PLAYER = new Player();

    private Player() {}

    public static Player getInstance() {
        return PLAYER;
    }

    // solve puzzle
    public void solvePuzzle(String loc) {
        location = loc;
        puzzle.generatePuzzle(loc);

        if ("Y".equals(puzzle.getPuzzleExist())) {
            if ("riddles".equals(puzzle.getPuzzleType())) {
                puzzle.checkRiddle(inventory.invList, loc);
            }
        }
    }

    public Map<String, String> solveUseTool(String item) {
        return puzzle.useTool(location, item);
    }

    public Map<String, String> moveDirection(String direction) {
        Map<String, String> result = new HashMap<>();

        Map<String, Object> furniture = puzzle.MAP.get(location);
        String newlocation = (String) furniture.get(direction);
        location = newlocation;
        Map<String, Object> new_furniture = puzzle.MAP.get(newlocation);

        String furniture_desc = (String) new_furniture.get("furniture_desc");
        String furniture_picture = (String) new_furniture.get("furniture_picture");

        result.put("description", furniture_desc);
        result.put("picture", furniture_picture);
        return result;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static String getLocation() {
        return location;
    }
}