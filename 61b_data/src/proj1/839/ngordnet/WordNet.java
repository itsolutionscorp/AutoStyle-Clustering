package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private HashMap<Integer, TreeSet<String>> synsets;
    private HashMap<String, TreeSet<Integer>> nouns;
    private Digraph hyponymGraph;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        synsets = new HashMap<Integer, TreeSet<String>>();
        nouns = new HashMap<String, TreeSet<Integer>>();
        while (synsetFile.hasNextLine()) {
            String[] line = synsetFile.readLine().split(",");
            TreeSet<String> synset = new TreeSet<String>();
            for (String word: line[1].split(" ")) {
                synset.add(word);
                // Integer.valueOf returns an Integer object
                if (!nouns.containsKey(word)) {
                    nouns.put(word, new TreeSet<Integer>());
                }
                nouns.get(word).add(Integer.valueOf(line[0]));
            }
            synsets.put(Integer.valueOf(line[0]), synset);
        }
        In hyponymFile = new In(hyponymFilename);
        hyponymGraph = new Digraph(synsets.size());
        while (hyponymFile.hasNextLine()) {
            String[] line = hyponymFile.readLine().split(",");
            // Integer.parseInt returns a primitive int
            int word = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                hyponymGraph.addEdge(word, Integer.parseInt(line[i]));
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
        if (!nouns.containsKey(word)) {
            return null;
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(hyponymGraph, nouns.get(word));
        TreeSet<String> hyponymWords = new TreeSet<String>();
        for (Integer synset: nouns.get(word)) {
            for (String synonym: synsets.get(synset)) {
                hyponymWords.add(synonym);
            }
        }
        for (Integer wordID: hyponymIDs) {
            for (String hyponym: synsets.get(wordID)) {
                hyponymWords.add(hyponym);
            }
        }
        return hyponymWords;
    }
}
