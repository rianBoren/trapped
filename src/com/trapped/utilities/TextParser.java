package com.trapped.utilities;
import java.util.ArrayList;
import java.util.Map;

public class TextParser {

    /*
     * Takes userInput String and strips whitespace, anything not a letter, "the", and "to"
     * then returns an ArrayList of the remaining words
     * Returns empty list if there is nothing left after scrubbing text, or if the userInput is blank
     */

    public static ArrayList<String> parseText (String userInput) {
        String[] strArr;
        ArrayList<String> parsedArr = new ArrayList<>();
        String stripRegex = "[^A-Za-z]";

//        if (userInput.isEmpty() || userInput == null) {
//            System.out.println("Empty, or null string passed to parser");
//            return parsedArr;
//        }

        strArr = userInput.trim().split(stripRegex);

        for (String s : strArr) {

            // removing empty strings left by split()
            if (s.equals(""));
            // ignoring "the" and "to"
            else if (s.equalsIgnoreCase("the"));
            else if (s.equalsIgnoreCase("to"));
            // add what's left back to the ArrayList
            else {
                parsedArr.add(s);
            }
        }
        return parsedArr;
    }

    /*
     * Returns verb if present in the ArrayList returned after parsing text
     * returns null if there are no recognized verbs from the verbs.json
     */

    public static String getVerb (String userInput) {
        ArrayList<String> parsedArr = parseText(userInput.toLowerCase());

        //keywords from JSON loaded to Map
        Map<String, ArrayList<String>> keywordMap = FileManager.loadJson("verbs.json");

        for (String word: parsedArr) {
            assert keywordMap != null;
            if(keywordMap.containsKey(word)) {  // if user input matches specific keyword no need to iterate Map synonyms
                return word;
            }
            // checking synonyms, then return keyword if there is a match
            for(Map.Entry<String, ArrayList<String>> entry:  keywordMap.entrySet()) {
                if (entry.getValue().contains(word)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    /*
     * accepts userInput string, parses to leave verbs and nouns,
     * then move through parsed array removing the nouns
     * will return an array with verb keywords removed
     */

    public static ArrayList<String> getNouns (String userInput) {
        ArrayList<String> parsedArr = parseText(userInput.toLowerCase());
        ArrayList<String> nouns = new ArrayList<>();
        String word;

        //keywords from JSON as LinkedTreeMap
        Map<String, ArrayList<String>> nounMap = FileManager.loadJson("nouns.json");

        for (String s : parsedArr) {
            word = s;
            assert nounMap != null;
            if (nounMap.containsKey(word)) {
                nouns.add(word);
            }
            for (Map.Entry<String, ArrayList<String>> entry : nounMap.entrySet()) {
                if (entry.getValue().contains(word)) {
                    nouns.add(entry.getKey());
                }
            }
        }
        return nouns;
    }
}