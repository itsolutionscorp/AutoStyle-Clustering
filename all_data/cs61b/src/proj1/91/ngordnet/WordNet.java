package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

public class WordNet {
    private Set<String> nouns;
    private TreeMap<Integer, ArrayList<String>> words;
    private TreeMap<Integer, ArrayList<Integer>> hyponymWords;

    public WordNet(String synsets, String hyponyms) {
        nouns = new HashSet<String>();
        words = new TreeMap<Integer, ArrayList<String>>();
        In in = new In(synsets);
        
        while (in.hasNextLine()) {
            String delimiters = "[,]+";
            String line = in.readLine();
            String[] data = line.split(delimiters);
            String[] inputWords = data[1].split("[ ]+");

            ArrayList<String> singleWords = new ArrayList<String>();

            for (String string : inputWords) {
                singleWords.add(string);
            }

            words.put(Integer.parseInt(data[0]), singleWords);

            for (String word : singleWords) {
                if (!nouns.contains(word)) {
                    nouns.add(word);
                }
            }
        }

        in = new In(hyponyms);
        hyponymWords = new TreeMap<Integer, ArrayList<Integer>>();
        while (in.hasNextLine()) {
            String delimiters = "[,]+";
            String line = in.readLine();
            String[] data = line.split(delimiters);

            Integer id = Integer.parseInt(data[0]);

            ArrayList<Integer> hyponymSet = new ArrayList<Integer>(1);

            for (int i = 1; i < data.length; i += 1) {
                hyponymSet.add(Integer.parseInt(data[i]));
            }

            if (hyponymWords.get(id) != null) {
                for (Integer hypo : hyponymSet) {
                    hyponymWords.get(id).add(hypo);
                }
            } else {
                hyponymWords.put(id, hyponymSet);
            }

        }
    }

    public boolean isNoun(String word) {
        //if word is in nouns, then return true
        return nouns.contains(word);
    }

    public Set<String> nouns() {
        Set<String> toReturn = new HashSet<String>();
        for (String word : nouns) {
            toReturn.add(word);
        }
        return toReturn;
    }

    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        Integer id = null;

        if (isNoun(word)) {
            for (Map.Entry<Integer, ArrayList<String>> entry : words.entrySet()) {
                if (entry.getValue().contains(word)) {
                    id = entry.getKey();

                    for (String input : getHyponyms(id)) {
                        if (!toReturn.contains(input)) {
                            toReturn.add(input);
                        }
                    }
                }
            }

            for (String toAdd : getHyponyms(id)) {
                if (word != null) {
                    toReturn.add(toAdd);   
                }
            }
            return toReturn;

        }
        return null;
    }

    private ArrayList<String> getHyponyms(Integer number) {
        ArrayList<String> list = new ArrayList<String>(1);

        for (String word : words.get(number)) {
            list.add(word);
        }

        if (hyponymWords.get(number) != null) {
            for (Integer hyponym : hyponymWords.get(number)) {
                for (String hypo : getHyponyms(hyponym)) {
                    list.add(hypo);
                }              
            }
        }
        return list;
    }
}
