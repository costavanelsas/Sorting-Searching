package nl.hva.ict.ss.textsearch;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Costa van Elsas
 * a source of english letter frequenqy https://en.wikipedia.org/wiki/Letter_frequency
 */
public class LanguageDetector {
    private String content;

    private HashMap<String, Integer> charMap;
    private HashMap<String, Double> englishDistributionMap;
    private HashMap<String, Double> vowelDistributionMap;

    private int amountOfChars = 0;

    private boolean isEnglishText = false;

    /**
     * Constructor LanguageDetector
     * @param input
     */
    public LanguageDetector(InputStream input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\\Z");

        content = scanner.next();

        charMap = new HashMap<>();
        vowelDistributionMap = new HashMap<>();
        englishDistributionMap = new HashMap<>();

        setPercentageOfVowel();
        Analyze();
    }

    /**
     * Check if the text is english or not
     * @return boolean
     */
    public boolean isEnglishText() {
        return isEnglishText;
    }

    /**
     * set the percentage of all the vowels found on wikipedia
     */
    private void setPercentageOfVowel() {
        englishDistributionMap.clear();

        // percentages found on wikipedia
        englishDistributionMap.put("a", 8.167);
        englishDistributionMap.put("e", 12.702);
        englishDistributionMap.put("o", 7.507);
        englishDistributionMap.put("u", 2.758);
        englishDistributionMap.put("i", 6.966);
    }

    /**
     * Start analysing the given text
     */
    private void Analyze() {
        createCharacterMap();
        makeVowelDistributionMap();

        // range in what the percentage can deviate.
        double rangeOfVariety = 2.5;

        isEnglishText = detectEnglishText(rangeOfVariety);
    }

    /**
     * TODO:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    private void createCharacterMap() {
        //regular expression to get all the letters of the alphabet
        String pattern = "([A-Z]|[a-z])";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(content);

        while (matcher.find())
        {
            String charFound = matcher.group(0).toLowerCase();
            if (charMap.containsKey(charFound)) {
                int amount = charMap.get(charFound);
                charMap.replace(charFound, ++amount);
            } else {
                charMap.put(charFound, 1);
            }
        }
    }

    /**
     * Filling the vowelDestributionMap (can only be accomplished is charMap is full)
     * Filling the englishDistributionMap.
     * @param range
     * @return boolean isEnglishText
     */
    private boolean detectEnglishText(double range) {
        for (Map.Entry<String, Double> entry : vowelDistributionMap.entrySet()) {
            String key = entry.getKey();

            double percentage = entry.getValue();
            double statisticalPercentage = englishDistributionMap.get(key);

            // when one vowel doesn't correspond with the range
            if (!(percentage - range < statisticalPercentage && percentage + range > statisticalPercentage)) {
                return false;
            }
        }
        return true;
    }

    /**
     * calculate the percentage of the given character
     * @param string
     * @return percentage
     */
    private double getPercentage(String string) {
        if (string == null || string.isEmpty() || string.length() > 1) {
            return -1;
        }

        int amount = charMap.get(string);
        double percentage = ((double) amount / (double) amountOfChars) * 100.0f;

        return percentage;
    }

    /**
     * distribute the vowels in a map
     */
    private void makeVowelDistributionMap() {
        String[] vowels = {"a", "e", "o", "u", "i"};
        amountOfChars = 0;

        for (Map.Entry<String, Integer> entry : charMap.entrySet()) {
            amountOfChars += entry.getValue();
        }

        for (Map.Entry<String, Integer> entry : charMap.entrySet()) {
            String key = entry.getKey();

            for (int i = 0; i < vowels.length; i++) {
                if (vowels[i].equalsIgnoreCase(key)) {
                    vowelDistributionMap.put(key, getPercentage(key));
                }
            }
        }
    }
}
