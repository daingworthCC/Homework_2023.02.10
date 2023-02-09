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

    private String inputtext;

    public void addToData(String input) {
        String words[] = input.replaceAll("\\p{Punct}", "").toLowerCase().split(" ");
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

    public static void main(String[] args) {
        Backend be = new Backend();
        be.addToData("My name is ronan");
        System.out.println(be.data);
        System.out.println(be.getText("name"));
    }
}
