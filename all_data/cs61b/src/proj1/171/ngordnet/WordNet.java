package ngordnet;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    // map from a synset id to an array of nouns in that synset
    private Map<Integer, Set<String>> synsets;
    // map from a word to a list of synset ids associated with it
    private Map<String, Set<Integer>> ids;
    // digraph connecting all hyponyms and hypernyms
    private Digraph graph;
    // set of all words
    private Set<String> nouns;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        // initialize instance variables
        nouns = new HashSet<String>();
        ids = new HashMap<String, Set<Integer>>();
        synsets = new HashMap<Integer, Set<String>>();
        
        // read synset file
        In in = new In(synsetFilename);
        String[] allSynsets = in.readAllLines();
        
        // load data from file
        for (String synset : allSynsets) {
            String[] parts = synset.split(",");
            Set<String> s = new HashSet<String>();
            int id = Integer.parseInt(parts[0]);
            for (String noun : parts[1].split(" ")) {
                s.add(noun);
                if (!nouns.contains(noun)) {
                    nouns.add(noun);
                    Set<Integer> t = new HashSet<Integer>();
                    t.add(id);
                    ids.put(noun, t);
                } else {
                    ids.get(noun).add(id);
                }
            }
            synsets.put(id, s);
        }
        
        // initializes a digraph with a number of vertices equal
        // to the number of synsets loaded
        graph = new Digraph(synsets.size());
        
        // read hyponym file
        in = new In(hyponymFilename);
        String[] edges = in.readAllLines();
        
        // construct digraph from hyponym file
        for (String edge : edges) {
            String[] parts = edge.split(",", 2);
            int hypernym = Integer.parseInt(parts[0]);
            String[] hyponyms = parts[1].split(",");
            for (String hyponym : hyponyms) {
                graph.addEdge(hypernym, Integer.parseInt(hyponym));
            }
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }
    
    /* Returns a set of all synonyms and hyponyms given synset ids */
    private Set<String> hypsym(Set<Integer> synsetIds, Set<Integer> traversed) {
        Set<String> s = new HashSet<String>();
        
        // get all synonyms
        for (int i : synsetIds) {
            s.addAll(synsets.get(i));
        }
        
        // add all hyponyms
        for (int i : GraphHelper.descendants(graph, synsetIds)) {
            // don't add the same elements twice
            if (!traversed.contains(i)) {
                traversed.add(i);
                Set<Integer> a = new HashSet<Integer>();
                a.add(i);
                s.addAll(hypsym(a, traversed));
            }
        }
        
        return s;
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        // get all ids related to the word
        Set<Integer> keys = ids.get(word);
        return hypsym(keys, new HashSet<Integer>());
    }
}
