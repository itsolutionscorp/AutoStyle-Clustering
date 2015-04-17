package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.io.File;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {

    private Digraph net;
    private Map<Integer, String> dict;
    private Map<String, Integer> revdict;
    private Map<String, HashSet<Integer>> dups;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In reader = new In(new File(synsetFilename));
        dict = new HashMap<Integer, String>();
        revdict = new HashMap<String, Integer>();
        dups = new HashMap<String, HashSet<Integer>>();

        // fills dict with integer keys and String synonyms from synset file
        while (reader.hasNextLine()) {
            String line = reader.readLine(); // reads the whole line

            // this operation splits the integer into index 0, the words
            // into index 1, and the definition into index 2 of split
            String[] split = line.split(",");

            // makes the integer the key
            Integer key = Integer.parseInt(split[0]);

            dict.put(key, split[1]);

            if (revdict.containsKey(split[1])) {
                if (dups.containsKey(split[1])) {
                    dups.get(split[1]).add(key);
                } else {
                    dups.put(split[1], new HashSet<Integer>());
                    dups.get(split[1]).add(key);
                }
            } else {
                revdict.put(split[1], key);
            }
        }

        reader = new In(new File(hyponymFilename));
        net = new Digraph(dict.size());

        // connects the hyponyms in the Digraph
        while (reader.hasNextLine()) {
            String line = reader.readLine();
            String[] hyps = line.split(",");

            for (int i = 1; i < hyps.length; i += 1) {
                net.addEdge(Integer.parseInt(hyps[0]),
                            Integer.parseInt(hyps[i]));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        Collection<String> entries = dict.values();
        Set<String> vals = new HashSet<String>();

        for (String s : entries) {
            if (s.contains(" ")) {
                String[] split = s.split(" ");
                for (int i = 0; i < split.length; i += 1) {
                    vals.add(split[i]);
                }
            } else {
                vals.add(s);
            }
        }

        return vals;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyps = new HashSet<String>();

        // first retrieve the key of the requested word
        Set<Integer> wkey = new HashSet<Integer>();
        for (String s : dups.keySet()) {
            if (word.equals(s)) {
                for (Integer i : dups.get(s)) {
                    wkey.add(i);
                }
            }
        }
        for (String s : revdict.keySet()) {
            if (s.contains(" ")) {
                String[] split = s.split(" ");
                for (int i = 0; i < split.length; i += 1) {
                    if (word.equals(split[i])) {
                        wkey.add(revdict.get(s));
                    }
                }
            } else {
                if (word.equals(s)) {
                    wkey.add(revdict.get(s));
                }
            }
        }

        wkey = GraphHelper.descendants(net, wkey);

        // get all of the words from the integer keys
        // split the words up as well
        for (int w : wkey) {
            if (dict.get(w).contains(" ")) {
                String[] split = dict.get(w).split(" ");
                for (int i = 0; i < split.length; i += 1) {
                    hyps.add(split[i]);
                }
            } else {
                hyps.add(dict.get(w));
            }
        }

        return hyps;
    }

}
