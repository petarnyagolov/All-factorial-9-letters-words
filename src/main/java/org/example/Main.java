package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Property of Petar Nyagolov
 * Date: 20.2.2025 г. Time: 16:09
 *
 * @author petar
 */
public class Main {
  private static Set<String> oneLetterWords = new HashSet<>(Arrays.asList("I", "A"));
  private static Set<String> twoLetterWords = new HashSet<>();
  private static Set<String> threeLetterWords = new HashSet<>();
  private static Set<String> fourLetterWords = new HashSet<>();
  private static Set<String> fiveLetterWords = new HashSet<>();
  private static Set<String> sixLetterWords = new HashSet<>();
  private static Set<String> sevenLetterWords = new HashSet<>();
  private static Set<String> eightLetterWords = new HashSet<>();
  private static List<String> nineLetterWords = new ArrayList<>();
  private static Set<String> factorialWords = new HashSet<>();

  public static void main(String[] args) throws IOException {
    // start time
    long startTime = System.currentTimeMillis();
    loadAllWords();
    findFactorialWords();
    factorialWords.forEach(System.out::println);
    // end time
    long endTime = System.currentTimeMillis();
    System.out.println("Execution time: " + (endTime - startTime) + "ms");
    System.out.println("Factorial words count: " + factorialWords.size());
  }

  public static void loadAllWords() throws IOException {
    URL wordsUrl = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");

    try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
      reader
          .lines()
          .skip(2)
          .filter(a -> a.length() <= 9)
          .forEach(Main::categorizeWord);
    }
  }

  private static void categorizeWord(String word) {
    switch (word.length()) {
      case 2:
        twoLetterWords.add(word);
        break;
      case 3:
        threeLetterWords.add(word);
        break;
      case 4:
        fourLetterWords.add(word);
        break;
      case 5:
        fiveLetterWords.add(word);
        break;
      case 6:
        sixLetterWords.add(word);
        break;
      case 7:
        sevenLetterWords.add(word);
        break;
      case 8:
        eightLetterWords.add(word);
        break;
      case 9:
        nineLetterWords.add(word);
        break;
      default:
        break;
    }
  }

  private static void findFactorialWords() {
    for (String current : nineLetterWords) {
      if (isFactorialWord(current, 9)) {
        factorialWords.add(current);
      }
    }
  }

  private static boolean isFactorialWord(String word, int length) {
    if (length == 1) {
      return oneLetterWords.contains(word);
    }

    boolean isValid = false;
    switch (length) {
      case 2:
        isValid = twoLetterWords.contains(word);
        break;
      case 3:
        isValid = threeLetterWords.contains(word);
        break;
      case 4:
        isValid = fourLetterWords.contains(word);
        break;
      case 5:
        isValid = fiveLetterWords.contains(word);
        break;
      case 6:
        isValid = sixLetterWords.contains(word);
        break;
      case 7:
        isValid = sevenLetterWords.contains(word);
        break;
      case 8:
        isValid = eightLetterWords.contains(word);
        break;
      case 9:
        isValid = nineLetterWords.contains(word);
        break;
      default:
        return false;
    }
    if (!isValid) {
      return false;
    }

    for (int i = 0; i < word.length(); i++) {
      String subWord = word.substring(0, i) + word.substring(i + 1);
      if (isFactorialWord(subWord, length - 1)) {
        return true;
      }
    }
    return false;
  }
}