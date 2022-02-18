package com.trapped.player;


import com.trapped.utilities.Puzzle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Serializable {
    private int attemptsLeft = 3;
    private String verb;
    private List<String> nouns = new ArrayList<>();
    public static String location = "bed";
    private Puzzle puzzle = Puzzle.getInstance();
    private Inventory inventory = Inventory.getInstance();
    // Player Singleton
    private static final Player PLAYER = new Player();

    private void Player() {}

    public static Player getInstance() {
        return PLAYER;
    }

//    public boolean inspectItem(String loc) {
//        //furniture
//        Prompts.ClearConsole();
////        if (loc.equals("inventory")) {
////            inventory.checkInv();
////            new_command();
////        }
//        if (Puzzle.MAP.containsKey(loc)) {
//            location = loc;
//            Map<String, Object> furniture = Puzzle.MAP.get(loc);
//
////            String furniture_desc = (String) furniture.get("furniture_desc");
////            String furniture_picture = (String) furniture.get("furniture_picture");
//            if (furniture.get("furniture_items") != null) {
//                ArrayList<String> furniture_items = (ArrayList<String>) furniture.get("furniture_items");
//                if (!furniture_items.isEmpty()) {
//                    if (inventory.getInvList().contains(furniture_items.get(0))) {
////                        FileManager.getResource(furniture_picture);
//                        System.out.println("Inspecting...\nNo items found here in " + loc);
//                        return false;
////                        solvePuzzle(something);
//
//                    } else if (!inventory.getInvList().contains(furniture_items.get(0))) {
////                        FileManager.getResource(furniture_picture);
//
//                        System.out.println("Inspecting...\nYou found: " + RED + furniture_items.get(0) + RESET);
////                        inventory.pickUpItem(loc, Puzzle.MAP);
//                        return true;
//
////                        solvePuzzle(something);
//                    }
//                }
//            }
//        }
//        //item
//        else if (Puzzle.MAP.get(location).get("furniture_items") != null) {
//            ArrayList<String> furniture_items = (ArrayList<String>) Puzzle.MAP.get(location).get("furniture_items");
//
//            if (furniture_items.contains(loc)) {
//                System.out.println("It's just a " + loc);
////                new_command();
//            } else if (inventory.getInvList().contains(loc)) {
//                System.out.println("It's just a " + loc);
////                new_command();
//            } else {
//                System.out.println("Sorry, I don't understand your input, please enter again. ");
//                FileManager.getResource("commands.txt");
////                playerInput();
//            }
//        } else {
//            System.out.println("Sorry, I don't understand your input, please enter again. ");
//            FileManager.getResource("commands.txt");
////            playerInput();
//        }
//        return false;
//    }

    public boolean inspectItem(String loc) {
        // does the location exist?
        if (!Puzzle.MAP.containsKey(loc)){
            return false;
        } else {
            // get current furniture object data
            Map<String, Object> furniture = Puzzle.MAP.get(loc);
            ArrayList<String> furniture_items = (ArrayList<String>) furniture.get("furniture_items");

            // are there no items?
            if (furniture_items.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }

    // return array list in format of ["Y", "use tool"] to determine type of popup needed
    public Map<String, String> inspectPuzzle(String loc){

        Map<String, String> response = new HashMap<>();
        // does a puzzle exist? if so...
        if ("Y".equals(Puzzle.MAP.get(loc).get("puzzle_exist"))){
            response.put("puzzle_exists", "Y");
            if ("riddles".equals(puzzle.getPuzzleType())) {
//                puzzle.checkRiddle(inventory.invList, loc);
                response.put("puzzle_type", "riddle");
            } else if ("use tool".equals(puzzle.getPuzzleType())) {
//                puzzle.useTool(inventory.invList, loc);
                response.put("puzzle_type", "use tool");
            } else if ("final".equals(puzzle.getPuzzleType())) {
//                puzzle.finalPuzzle();
                response.put("puzzle_type", "final");
            }
            return response;
        } else {
            return response;
        }
    }

    public static void quitGame() {
        System.out.println("Quitting the Game. See you next time.");
        System.exit(0);
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

    // Called when "help" is input
    public String gameMenu() {
        String returnText = "You are at " + location;
        return returnText;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public List<String> getNouns() {
        return nouns;
    }

    public void setNouns(List<String> nouns) {
        this.nouns = nouns;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }


    public static String getLocation() {
        return location;
    }

}