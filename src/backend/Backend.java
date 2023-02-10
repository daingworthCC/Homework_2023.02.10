package backend;

import java.util.HashMap;
import java.util.Map;

public class Backend {
    /**
     * Level 1 = word
     * Level 2 = point before the word (including the word) where words appear
     * Level 3 = word featured at previous point
     * Level 4 = number of times the word is featured at previous point
     */
    HashMap<String, HashMap<Integer, HashMap<String, Integer>>> data = new HashMap<>();

    /**
     * Level 1 = number of words
     * Level 2 = words used
     * Level 3 = word featured at next point
     * Level 4 = number of times that occurrence has happened
     */
    HashMap<Integer, HashMap<String, HashMap<String, Integer>>> data2 = new HashMap<>();

    private String inputtext;

    public void addToData(String input) {
        String words[] = toNormalWordArray(input);
        for (int i = 0; i < words.length; i++) {
            if (i == 0);
            else if(data.get(words[i - 1]) == null) {
                data.put(words[i - 1], new HashMap<>());
                data.get(words[i - 1]).put(1, new HashMap<>());
                data.get(words[i - 1]).get(1).put(words[i - 1], 0);
            } if (i == 0);
            else if (data.get(words[i - 1]) == null) {
                data.put(words[i - 1], new HashMap<>());
                data.get(words[i - 1]).put(0, new HashMap<>());
                data.get(words[i - 1]).get(0).put(words[i], 0);
            } for (int j = i; j > 0; j--) {
                if(i == 0);
                else if (data.get(words[i - 1]).get(i - j) == null) {
                    data.get(words[i - 1]).put(i - j, new HashMap<>());
                    data.get(words[i - 1]).get(i - j).put(words[j], 1);
                } else if (data.get(words[i - 1]).get(i - j).get(words[j]) == null)
                    data.get(words[i - 1]).get(i - j).put(words[j], 1);
                else
                    data.get(words[i - 1]).get(i - j).put(words[j], data.get(words[i - 1]).get(i - j).get(words[j]) + 1);
            }
        }
    }

    public String getText(String input) {
        String output = "";
        int outputOccurences = 0;
        int breadth = 0;
        String words[] = input.replaceAll("\\p{Punct}", "").toLowerCase().split(" ");
        int i = words.length - 1;
        //for (int i = words.length - 1; i >= 0; i--) {
            System.out.println();
            if (data.get(words[i]) == null) return "data.get(words[i]) is null";
            if (data.get(words[i]).get(0) == null) return "data.get(words[i]).get(0) is null";
            for (Map.Entry<String, Integer> hashMap : data.get(words[i]).get(0).entrySet()) {
                if (hashMap.getValue() > outputOccurences) {
                    output = hashMap.getKey();
                    outputOccurences = hashMap.getValue();
                }
                breadth++;
            }
        //}
        return output;
    }

    public void addToData2(String input) {
        String[] words = toNormalWordArray(input);
        for (int i = 0; i < words.length; i++) {
            if (data2.get(i) == null) data2.put(i, new HashMap<>());
            for (int j = words.length - 2; j >= i; j--) {
                String wordsInRange = wordsBtwnPoints(words,j - i, j);
                if (data2.get(i).get(wordsInRange) == null) {
                    data2.get(i).put(wordsInRange, new HashMap<>());
                    data2.get(i).get(wordsInRange).put(words[j + 1], 0);
                }
                if (data2.get(i).get(wordsInRange).get(words[j + 1]) == null) {
                    data2.get(i).get(wordsInRange).put(words[j + 1], 0);
                }
                data2.get(i).get(wordsInRange).put(words[j + 1], data2.get(i).get(wordsInRange).get(words[j + 1]) + 1);
            }
        }
    }

    public String getText2(String input) {
        String output = "";
        String[] words = toNormalWordArray(input);
        int outputOccurrences = 0;
        for (int i = 0; i < words.length; i++) {
            if (data2.get(i) == null) break;
            else {
                String wordsInQuestion = wordsBtwnPoints(words, words.length - i - 1, words.length - 1);
                if (data2.get(i).get(wordsInQuestion) == null);
                else for (Map.Entry<String, Integer> hashMap : data2.get(i).get(wordsInQuestion).entrySet()) {
                    int casesOccurrences = hashMap.getValue() * (i + 1);
                    if(casesOccurrences > outputOccurrences) {
                        output = hashMap.getKey();
                        outputOccurrences = casesOccurrences;
                    }
                }
            }
        }
        return output;
    }

    private String wordsBtwnPoints(String[] words, int i, int j) {
        String output = "";
        for (int k = i; k <= j; k++) {
            output += words[k];
            output += " ";
        }
        return output;
    }

    String[] toNormalWordArray(String input) {
        return input.replaceAll("\\p{Punct}", "").toLowerCase().split(" ");
    }

    public static void main(String[] args) {
        Backend be = new Backend();
        be.addToData2("I came I saw I came I saw I came I conquered");
        System.out.println(be.getText2("I came I"));
        /*be.addToData("My name is ronan");
        System.out.println(be.data);
        System.out.println(be.getText("name"));*/
    }
}
