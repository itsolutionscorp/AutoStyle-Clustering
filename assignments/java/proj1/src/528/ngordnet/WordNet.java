package ngordnet;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Map<Integer, Set<String>> synsetsMap = new TreeMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> reverseMap = new TreeMap<String, Set<Integer>>();
    private Map<String, Set<String>> hyponymsMap = new TreeMap<String, Set<String>>();
    private Digraph net;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);
        String line;  // One line of synFile (Or part of line interrupted by a " ")
        String[] entries; // List of ID, word, and definition
        int id;  // Numerical ID of given synset
        Set<String> synonyms; 
        String[] synsets;
        TreeSet<Integer> idSet;
        while (synFile.hasNextLine()) {
            synonyms = new TreeSet<String>();
            line = synFile.readLine();
            entries = line.split(",");
            id = Integer.parseInt(entries[0]);
            synsets = entries[1].split(" ");
            for (String synset : synsets) {
                idSet =  new TreeSet<Integer>();
                idSet.add(id);
                if (reverseMap.containsKey(synset)) {
                    reverseMap.get(synset).add(id);
                } else {
                    reverseMap.put(synset, idSet);
                }
                synonyms.add(synset);
            }
            synsetsMap.put(id, synonyms);
        }
        net = new Digraph(synsetsMap.size());
        while (hypFile.hasNextLine()) {
            String[] hypVals = hypFile.readLine().split(",");
            id = Integer.parseInt(hypVals[0]);
            for (int i = 1; i < hypVals.length; i++) {
                net.addEdge(id, Integer.parseInt(hypVals[i]));
            }
        }
        Set<Integer> ids;
        Set<String> hyponyms;
        for (String key : reverseMap.keySet()) {
            hyponyms = new TreeSet<String>();
            ids = reverseMap.get(key);
            for (int reachable : GraphHelper.descendants(net, ids)) {
                for (String synonym : synsetsMap.get(reachable)) {
                    hyponyms.add(synonym);
                }
            }
            hyponymsMap.put(key, hyponyms);
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return reverseMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return reverseMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (hyponymsMap.containsKey(word)) {
            return hyponymsMap.get(word);
        } else {
            return new TreeSet<String>();
        }
    }

}
