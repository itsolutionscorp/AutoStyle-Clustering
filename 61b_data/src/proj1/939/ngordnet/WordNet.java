package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* @author: Daiwei Liu */
public class WordNet {
    private Digraph edgeInfo;
    private HashMap<Integer, HashSet<String>> graph;
    private HashMap<String, HashSet<Integer>> nouns;

    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME*/
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        String[] entry, words;
        int index = 0;
        /* Read a line from synset file, add to graph
        * repeat until the graph is complete (without directed edges) */
        graph = new HashMap<Integer, HashSet<String>>();
        nouns = new HashMap<String, HashSet<Integer>>();
        HashSet<String> wordSet;
        HashSet<Integer> indexSet;
        while (synsets.hasNextLine()) {
            entry = synsets.readLine().split(",");
            index = Integer.parseInt(entry[0]);
            wordSet = new HashSet<String>(Arrays.asList(entry[1].split(" ")));
            // create {index -> set_of_word} map
            graph.put(index, wordSet);
            // create {word -> set_of_index} map
            for (String word : wordSet) {
                if (nouns.containsKey(word)) {
                    nouns.get(word).add(index);
                } else {
                    indexSet = new HashSet<Integer>();
                    indexSet.add(index);
                    nouns.put(word, indexSet);
                }
            }
        }
        /* Read a line from hyponym file, add to edgeInfo
         * repeat until edgeInfo is complete */
        edgeInfo = new Digraph(index + 1);
        while (hyponyms.hasNextLine()) {
            entry = hyponyms.readLine().split(",");
            index = Integer.parseInt(entry[0]);
            for (int i = 1; i < entry.length; i += 1) {
                edgeInfo.addEdge(index, Integer.parseInt(entry[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet<String>();
        // Indices of synsets that contain WORD
        Set<Integer> synsetIDs = nouns.get(word);
        // Indices of all descendant synsets of WORD
        Set<Integer> deIDs = GraphHelper.descendants(edgeInfo, synsetIDs);
        // Union the two sets of indices, and add all the words within
        synsetIDs.addAll(deIDs);
        for (Integer i : synsetIDs) {
            result.addAll(graph.get(i));
        }
        return result;
    }
}
