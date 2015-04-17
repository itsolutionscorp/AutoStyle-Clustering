package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;

// Author: Franklin Rice

public class WordNet {

    private Set<String> nouns = new TreeSet<String>();
    private int nounsFill = 0;
    private Digraph dg;
    private int count = 0;
    private HashMap wordToNumber;
    private HashMap numberToWord;
    private HashMap definitions;


    public WordNet(String synsetFile, String hyponymFile) {

        In synsets = new In(synsetFile);
        In hyponyms = new In(hyponymFile);
        wordToNumber = new HashMap<String, TreeSet<Integer>>();
        numberToWord = new HashMap<Integer, HashSet<String>>();
        definitions = new HashMap<String, String>();

        while (synsets.hasNextLine()) {
            String item = synsets.readLine();
            String[] keys = item.split(",");
            for (String synonym : keys[1].split(" ")) {
                if (!numberToWord.containsKey(Integer.parseInt(keys[0]))) {
                    numberToWord.put(Integer.parseInt(keys[0]), new HashSet<String>());
                }
                ((HashSet<String>) numberToWord.get(Integer.parseInt(keys[0]))).add(synonym);
                if (!wordToNumber.containsKey(synonym)) {
                    wordToNumber.put(synonym, new TreeSet<Integer>());
                }
                ((TreeSet<Integer>) wordToNumber.get(synonym)).add(Integer.parseInt(keys[0]));
                definitions.put(synonym, keys[2]);
                addNoun(synonym);
                count += 1;
            }
            count = Integer.parseInt(keys[0]);
            count += 1;
        }

        dg = new Digraph(count);
        while (hyponyms.hasNextLine()) {
            String line = hyponyms.readLine();
            String[] numbers = line.split(",");
            Integer hyper = Integer.parseInt(numbers[0]);
            for (int i = 0; i < numbers.length; i += 1) {
                dg.addEdge(hyper, Integer.parseInt(numbers[i]));
            }

        }

    }

    private void addNoun(String word) {
        if (!nouns.contains(word)) {
            nouns.add(word);
        }
    }


    public boolean isNoun(String noun) {
        if (wordToNumber.containsKey(noun)) {
            return true;
        }
        return false;
    }


    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        if (!wordToNumber.containsKey(word)) {
            return new TreeSet<String>();
        }
        TreeSet<String> hypoWords = new TreeSet<String>();
        for (Integer ind : ((TreeSet<Integer>) wordToNumber.get(word))) {
            Set<Integer> set = new TreeSet<Integer>();
            set.add(ind);
            TreeSet<Integer> hypoNumbers = (TreeSet<Integer>) GraphHelper.descendants(dg, set);
            for (Integer i : hypoNumbers) {
                for (String str : ((HashSet<String>) numberToWord.get(i))) {
                    hypoWords.add(str);
                }

            }
        }
        return hypoWords;
    }
}
