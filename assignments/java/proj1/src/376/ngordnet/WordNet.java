package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *  @author Zhongtian Wang
 */
public class WordNet {
    /* Store the hyponyms relationships. */
    private Digraph hyponyms;
    /* Store the synsets. */
    private HashMap<Integer, HashSet<String>> synsets;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Read synsets
        synsets = new HashMap<Integer, HashSet<String>>();

        In scanner = new In(synsetFilename);
        while (scanner.hasNextLine()) {
            String[] line = scanner.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            HashSet<String> synset = new HashSet<String>(Arrays.asList(line[1].split(" ")));
            synsets.put(id, synset);
        }
        scanner.close();

        // Read hyponyms
        hyponyms = new Digraph(synsets.size());

        scanner = new In(hyponymFilename);
        while (scanner.hasNextLine()) {
            String[] line = scanner.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                hyponyms.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }
        }
        scanner.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (HashSet<String> hs : synsets.values()) {
            if (hs.contains(noun)) { 
                return true; 
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounsSet = new HashSet<String>();

        for (HashSet<String> hs : synsets.values()) {
            for (String element : hs) { 
                nounsSet.add(element); 
            }
        }

        return nounsSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymsSet = new HashSet<String>();

        // Get all keys that contains the word
        Set<Integer> keys = new TreeSet<Integer>();
        for (Map.Entry<Integer, HashSet<String>> es : synsets.entrySet()) {
            if (es.getValue().contains(word)) { 
                keys.add(es.getKey()); 
            }
        }

        // Add set that corresponding to key to hyponymsSet
        for (Integer key : keys) {
            for (String element : synsets.get(key)) { 
                hyponymsSet.add(element); 
            }
        }

        // Add sets that is the hyponyms to key to hyponymsSet
        for (Integer hyponymKeys : GraphHelper.descendants(hyponyms, keys)) {
            for (String element : synsets.get(hyponymKeys)) {
                hyponymsSet.add(element);
            }
        }

        return hyponymsSet;
    }
}
