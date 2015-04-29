package ngordnet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * @author Alexey Grigoryev aleksei.grigoryev@berkeley.edu
 * @since 2015-02-28
 */
public class WordNet {
    private int verticies;
    private Set<String> nouns;
    private HashMap<String, LinkedList<Integer>> wordMap;
    private HashMap<Integer, LinkedList<String>> idMap;
    private Digraph wordGraph;

    /**
     * Constructor for WordNet
     * 
     * @param sysetFilename
     *            : a text file full of synsets
     * @param hyponymFilename
     *            : a text file full of hyponyms
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nouns = new HashSet<String>();
        wordMap = new HashMap<String, LinkedList<Integer>>();
        idMap = new HashMap<Integer, LinkedList<String>>();
        In synset = new In(synsetFilename);
        makeSynsetStructures(synset);
        In hyponym = new In(hyponymFilename);
        wordGraph = new Digraph(verticies);
        makeDiGraph(hyponym);
    }

    /**
     * creates data structures set of nouns, and map of id's to words, and words
     * to id's
     * 
     * @param synset
     *            the In type input which is going to read the noun
     */
    private void makeSynsetStructures(In synset) {
        int id = 0;
        while (synset.hasNextLine()) {
            String[] line = synset.readLine().split(",");
            id = Integer.parseInt(line[0]);
            String[] lineNouns = line[1].split(" ");
            for (int i = 0; i < lineNouns.length; i += 1) {
                nouns.add(lineNouns[i]);
                makeWordMap(lineNouns[i], id);
                makeIdMap(id, lineNouns[i]);
            }
        }
        verticies = id + 1;
    }

    /**
     * creates a directed graph with the numbers
     * 
     * @param hyponym
     *            : the In type text file which has all the hyponyms listed
     */
    private void makeDiGraph(In hyponym) {
        while (hyponym.hasNextLine()) {
            String[] line = hyponym.readLine().split(",");
            int[] ids = new int[line.length];
            for (int i = 0; i < line.length; i += 1) {
                ids[i] = Integer.parseInt(line[i]);
            }
            if (ids.length < 2) {
                return;
            } else {
                for (int i = 1; i < ids.length; i += 1) {
                    wordGraph.addEdge(ids[0], ids[i]);
                }
            }
        }
    }

    /**
     * adds a noun as key, and the id number as the value
     * 
     * @param word
     *            : the string that makes a key type file to parse through
     * @param val
     *            : the id number
     */
    private void makeWordMap(String key, int val) {
        if (!wordMap.containsKey(key)) {
            wordMap.put(key, new LinkedList<Integer>());
        }
        wordMap.get(key).add(val);
    }

    /**
     * adds a noun as key, and the id number as the value
     * 
     * @param word
     *            : the string that makes a key type file to parse through
     * @param val
     *            : the id number
     */
    private void makeIdMap(int key, String val) {
        if (!idMap.containsKey(key)) {
            idMap.put(key, new LinkedList<String>());
        }
        idMap.get(key).add(val);
    }

    /**
     * checks whether the string is a noun
     *
     * @param noun
     *            : passed in for a check
     * @return true if it is a noun, false otherwise
     */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /**
     * Looks up the set of all hyponyms of word and synonyms of word
     * 
     * @param word
     *            : what is being checked
     * @return Set of all hyponyms and synonyms.
     */
    public Set<String> hyponyms(String word) {
        LinkedList<Integer> ids = wordMap.get(word);
        Set<Integer> keys = new TreeSet<Integer>();
        Set<Integer> graphVerticies = new TreeSet<Integer>();
        for (Integer x : ids) {
            keys.add(x);
        }
        graphVerticies = GraphHelper.descendants(wordGraph, keys);
        for (Integer x : graphVerticies) {
            keys.add(x);
        }
        Set<String> hyponymsSet = new TreeSet<String>();
        for (Integer x : keys) {
            LinkedList<String> idVals = idMap.get(x);
            for (String y : idVals) {
                hyponymsSet.add(y);
            }
        }
        return hyponymsSet;
    }

    /**
     * Looks up all the nouns in avaliable
     *
     * @return Set of all the nouns in a given file
     */
    public Set<String> nouns() {
        return wordMap.keySet();
    }
}
