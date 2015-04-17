package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * WordNet is a way to organize words with reference to each other. Words in a
 * WordNet can be part of a synonym set (synset), or hyponyms to other words.
 * 
 * @author Nathan Braun
 * 
 */
public class WordNet {
    /** A Set of all the nouns in this Digraph. **/
    private HashSet<String> nouns;

    /** A hyponym ID that corresponds to a list of words. **/
    private HashMap<Integer, ArrayList<String>> idToWords;

    /** A single word that can correspond to multiple ids. **/
    private HashMap<String, ArrayList<Integer>> wordToIds;

    /** An int based digraph that represents the hyponym connections **/
    private Digraph wordGraph;

    /**
     * Creates a WordNet that inputs words from sysnetFilename, and connect
     * word's hyponyms based on hyponymFilename.
     * 
     * @param sysnetFilename the filename of the ID's and words
     * @param hyponymFilename the filename of the hyponyms
     */
    public WordNet(String sysnetFilename, String hyponymFilename) {
        nouns = new HashSet<String>();
        idToWords = new HashMap<Integer, ArrayList<String>>();
        wordToIds = new HashMap<String, ArrayList<Integer>>();
        int maxVertex = 0;

        In sysIn = new In(sysnetFilename);
        In hypIn = new In(hyponymFilename);
        if (!sysIn.exists() || !hypIn.exists()) {
            return;
        }

        String[] sysLines = sysIn.readAllLines();
        String[] hypLines = hypIn.readAllLines();

        /*
         * Parse through all of the words and connect the worddToIds and
         * idToWords.
         */
        for (String line : sysLines) {
            String[] commas = line.split(",");
            try {
                int id = Integer.parseInt(commas[0]);
                String words = commas[1];

                if (id > maxVertex) {
                    maxVertex = id;
                }
                if (!idToWords.containsKey(id)) {
                    idToWords.put(id, new ArrayList<String>());
                }

                for (String word : words.split(" ")) {
                    idToWords.get(id).add(word);
                    nouns.add(word);

                    if (!wordToIds.containsKey(word)) {
                        wordToIds.put(word, new ArrayList<Integer>());
                    }
                    wordToIds.get(word).add(id);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        /*
         * Connect the graph using values from the hyponymFile, using the map to
         * coordinate the vertices with the noun sets.
         */
        wordGraph = new Digraph(maxVertex + 2);
        for (String line : hypLines) {
            String[] commas = line.split(",");
            if (commas.length <= 1) {
                continue;
            }
            for (int i = 1; i < commas.length; i++) {
                try {
                    wordGraph.addEdge(Integer.parseInt(commas[0]),
                            Integer.parseInt(commas[i]));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

    }

    /**
     * Checks if a noun is contained within this WordNet.
     * 
     * @param noun the noun to check
     * @return true if the noun is in this WordNet.
     */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /**
     * @return the set of all of the nouns in this WordNet.
     */
    public Set<String> nouns() {
        return nouns;
    }

    /**
     * Returns a set of all of the hyponyms corresponding to a particular word.
     * If the word belongs to multiple synsets, then it will return all of the
     * hyponyms for each individual synset.
     * 
     * @param word the word to search for.
     * @return a set of all of the hyponyms.
     */
    public Set<String> hyponyms(String word) {
        if (word == null) {
            return null;
        }
        Set<String> wordSet = new TreeSet<String>();
        if (!wordToIds.containsKey(word)) {
            return wordSet;
        }

        Set<Integer> setOfIds = new TreeSet<Integer>(wordToIds.get(word));
        Set<Integer> neighbors = GraphHelper.descendants(wordGraph, setOfIds);
        for (Integer neighborId : neighbors) {
            ArrayList<String> neighborWords = idToWords.get(neighborId);
            if (neighborWords == null) {
                continue;
            }

            for (String neighborWord : neighborWords) {
                wordSet.add(neighborWord);
            }
        }
        return wordSet;
    }
}
