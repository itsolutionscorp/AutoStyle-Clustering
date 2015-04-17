package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, HashSet<String>> hm;
    private HashSet<String> set;
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        hm = new HashMap<Integer, HashSet<String>>();
        In synFile = new In(synsetFilename);
        In hypnFile = new In(hyponymFilename);

        String a = null;
        String delimiter = ",";
        while (synFile.hasNextLine()) {
            set = new HashSet<String>();
            a = synFile.readLine();
            for (String s : a.split(delimiter)[1].split("\\s+")) {
                set.add(s);
            }
            hm.put(Integer.parseInt(a.split(delimiter)[0]), set);
        }

        // Now the digraph
        String key = null;
        String[] test = null;
        g = new Digraph(hm.size());
        while (hypnFile.hasNextLine()) {
            test = hypnFile.readLine().split(delimiter);
            key = test[0];
            for (String s : test) {
                if (!s.equals(key)) {
                    g.addEdge(Integer.parseInt(key), Integer.parseInt(s));
                }
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (HashSet<String> value : hm.values()) {
            if (value.contains(noun)) {
                return true;
            }
        }
        return false;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> combined = new HashSet<String>();
        for (HashSet<String> value : hm.values()) {
            combined.addAll(value);
        }

        return combined;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> keys = new HashSet<Integer>();
        HashSet<String> hypo = new HashSet<String>();
        for (Map.Entry<Integer, HashSet<String>> entry : hm.entrySet()) {
            if (entry.getValue().contains(word)) {
                keys.add(entry.getKey());
            }
        }

        for (Integer i : GraphHelper.descendants(g, keys)) {
            hypo.addAll(hm.get(i));
        }
        return hypo;

    }
}
