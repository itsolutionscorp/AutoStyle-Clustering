package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph synset;
    private HashMap<String, Set<Integer>> toID;
    private HashMap<Integer, ArrayList<String>> toNoun;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets;
        In hyponyms;
        synsets = new In(synsetFilename);
        hyponyms = new In(hyponymFilename);
        toNoun = new HashMap<Integer, ArrayList<String>>();
        toID = new HashMap<String, Set<Integer>>();
        while (synsets.hasNextLine()) {
            String[] input = synsets.readLine().split(",");
            String[] words = input[1].split(" ");
            int id = Integer.parseInt(input[0]);
            ArrayList<String> nouns = new ArrayList<String>();
            for (String word : words) {
                nouns.add(word);
                if (toID.get(word) == null) {
                    Set<Integer> newtoID = new HashSet<Integer>();
                    newtoID.add(id);
                    toID.put(word, newtoID);
                } else {
                    toID.get(word).add(id);
                }
            }
            toNoun.put(id, nouns);
        }
        synset = new Digraph(toID.size());
        while (hyponyms.hasNextLine()) {
            String[] input = hyponyms.readLine().split(",");
            for (int i = 1; i < input.length; i++) {
                synset.addEdge(Integer.parseInt(input[0]),
                        Integer.parseInt(input[i]));
            }
        }

    }

    public Set<String> hyponyms(String word) {
        Set<String> hyps = new TreeSet<String>();
        for (int i : GraphHelper.descendants(synset, toID.get(word))) {
            hyps.addAll(toNoun.get(i));
        }
        return hyps;
    }

    public boolean isNoun(String noun) {
        for (String word : toID.keySet()) {
            if (word.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (ArrayList<String> words : toNoun.values()) {
            for (String word : words) {
                nouns.add(word);
            }
        }
        return nouns;
    }
}
