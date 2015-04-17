package ngordnet;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Map<Integer, Synset> synsetMap;
    private Digraph digraph;

    /**Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synIn = new In(synsetFilename);
        In hypoIn = new In(hyponymFilename);

        String[] synsets = synIn.readAllLines();
        String[] hyponyms = hypoIn.readAllLines();

        synsetMap = new HashMap<Integer, Synset>();

        for (String synset : synsets) {

            String[] split = synset.split(",");

            int id = Integer.parseInt(split[0]);

            String[] words = split[1].trim().split(" ");
            Set<String> synsetSet = new HashSet<String>(Arrays.asList(words));

            Synset s = new Synset(id, synsetSet, split[2]);
            synsetMap.put(id, s);
        }

        digraph = new Digraph(synsetMap.size());
 
        for (String hyponym : hyponyms) {
            String[] split = hyponym.split(",");
            int main = Integer.parseInt(split[0]);

            for (int i = 1; i < split.length; i++) {
                digraph.addEdge(main, Integer.parseInt(split[i]));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Synset s : synsetMap.values()) {
            if (s.synset.contains(noun)) {
                return true;
            }
        }

        return false;
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> all = new HashSet<String>();

        for (Synset s : synsetMap.values()) {
            all.addAll(s.synset);
        }

        return all;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> set = new HashSet<String>();

        for (Synset s : synsetMap.values()) {
            if (s.synset.contains(word)) {
                set.addAll(s.synset);

                addHyponyms(s, set);
            }
        }

        return set;
    }

    private void addHyponyms(Synset s, Set<String> set) {
        Iterator<Integer> iter = digraph.adj(s.id).iterator();
        
        while (iter.hasNext()) {

            Synset hypo = synsetMap.get(iter.next());

            set.addAll(hypo.synset);
            addHyponyms(hypo, set);
        }

    }
    
    private class Synset {
        private int id;
        private Set<String> synset;
        private String definition;

        /** Creates a synset with three properties ID, SYNSET, and 
          * DEFINITION*/
        public Synset(int i, Set<String> syn, String def) {
            id = i;
            synset = syn;
            definition = def;
        }
    }
}
