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
    loadAllWords();
    findFactorialWords();
    factorialWords.forEach(System.out::println);
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
      for (int j = 0; j < current.length(); j++) {
        String eightCurrentWord = current.substring(0, j) + current.substring(j + 1);

        if (eightLetterWords.contains(eightCurrentWord)) {
          for (int k = 0; k < eightCurrentWord.length(); k++) {
            String sevenCurrentWord = eightCurrentWord.substring(0, k) + eightCurrentWord.substring(k + 1);

            if (sevenLetterWords.contains(sevenCurrentWord)) {
              for (int l = 0; l < sevenCurrentWord.length(); l++) {
                String sixCurrentWord = sevenCurrentWord.substring(0, l) + sevenCurrentWord.substring(l + 1);

                if (sixLetterWords.contains(sixCurrentWord)) {
                  for (int m = 0; m < sixCurrentWord.length(); m++) {
                    String fiveCurrentWord = sixCurrentWord.substring(0, m) + sixCurrentWord.substring(m + 1);

                    if (fiveLetterWords.contains(fiveCurrentWord)) {
                      for (int n = 0; n < fiveCurrentWord.length(); n++) {
                        String fourCurrentWord = fiveCurrentWord.substring(0, n) + fiveCurrentWord.substring(n + 1);

                        if (fourLetterWords.contains(fourCurrentWord)) {
                          for (int o = 0; o < fourCurrentWord.length(); o++) {
                            String threeCurrentWord = fourCurrentWord.substring(0, o)
                                + fourCurrentWord.substring(o + 1);

                            if (threeLetterWords.contains(threeCurrentWord)) {
                              for (int p = 0; p < threeCurrentWord.length(); p++) {
                                String twoCurrentWord = threeCurrentWord.substring(0, p)
                                    + threeCurrentWord.substring(p + 1);

                                if (twoLetterWords.contains(twoCurrentWord)) {
                                  for (int q = 0; q < twoCurrentWord.length(); q++) {
                                    String oneCurrentWord = twoCurrentWord.substring(0, q)
                                        + twoCurrentWord.substring(q + 1);

                                    if (oneLetterWords.contains(oneCurrentWord)) {
                                      factorialWords.add(current);
                                      break;
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}