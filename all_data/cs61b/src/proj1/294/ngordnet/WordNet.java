package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph g;
    private Map<String, Set<Integer>> nouns;
    private HashMap<Integer, String[]> synsets;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        nouns = new HashMap<String, Set<Integer>>();
        synsets = new HashMap<Integer, String[]>();
        int count = 0;
        while (!syn.isEmpty()) {
            String[] elem = syn.readLine().split(",");
            int index = Integer.parseInt(elem[0]);
            String[] synSet = elem[1].split(" ");
            for (String e : synSet) {
                if (!nouns.containsKey(e)) {
                    Set<Integer> a = new HashSet<Integer>();
                    a.add(index);
                    nouns.put(e, a);
                } else {
                    nouns.get(e).add(index);
                }
            }
            synsets.put(count, synSet);
            count++;
        }
        g = new Digraph(count);
        while (!hyp.isEmpty()) {
            String[] pair = hyp.readLine().split(",");
            int id = Integer.parseInt(pair[0]);
            for (int i = 1; i < pair.length; i++) {
                int hypo = Integer.parseInt(pair[i]);
                g.addEdge(id, hypo);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> hypo = GraphHelper.descendants(g, nouns.get(word));
        Set<String> result = new HashSet<String>();
        for (int e : hypo) {
            String[] r = synsets.get(e);
            for (String s : r) {
                result.add(s);
            }
        }
        return result;
    }
}
