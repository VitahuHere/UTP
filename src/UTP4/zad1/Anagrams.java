/**
 * @author Vu Cong Minh S25206
 */

package UTP4.zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Anagrams {
    ArrayList<String> words;
    public Anagrams(String allWords) {
        this.words = new ArrayList<>();
        try {
            this.words = Files.readAllLines(
                    Paths.get(allWords))
                    .stream()
                    .flatMap(line -> Arrays.stream(line.split(" "))
                    ).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ArrayList<String>> getSortedByAnQty() {
        String word;
        ArrayList<String> words = new ArrayList<>(this.words);
        ArrayList<ArrayList<String>> anagrams = new ArrayList<>();
        while (!words.isEmpty()) {
            word = words.get(0);
            ArrayList<String> anagram = new ArrayList<>();
            anagram.add(word);
            words.remove(word);
            for (int i = 0; i < words.size(); i++) {
                if (isAnagram(word, words.get(i))) {
                    anagram.add(words.get(i));
                    words.remove(i);
                    i--;
                }
            }
            anagrams.add(anagram);
        }
        anagrams.sort((o1, o2) -> o2.size() - o1.size());
        return anagrams;
    }

    private boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();
        Arrays.sort(word1Array);
        Arrays.sort(word2Array);
        return Arrays.equals(word1Array, word2Array);
    }

    public String getAnagramsFor(String word) {
        ArrayList<String> anagrams = new ArrayList<>();
        for (String w : words) {
            if (isAnagram(word, w) && !word.equals(w)) {
                anagrams.add(w);
            }
        }
        return word + ": " + anagrams;
    }
}  
