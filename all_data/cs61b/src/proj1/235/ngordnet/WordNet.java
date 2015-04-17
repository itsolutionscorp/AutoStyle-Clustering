package ngordnet;

import static org.junit.Assert.*;
import java.util.Set;
import java.io.File;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;

//Citations: Anokhi Kastia, Lakshay Badlani

public class WordNet {
    private In synset;
    private In hyponym;
    private HashMap<Integer, String> synsetID;
    private HashMap<String, Boolean> synBoolean;
    private Integer[] hyponyms;
    private Digraph di;
    private int synSize;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(new File(synsetFilename));
        hyponym = new In(new File(hyponymFilename));
        synset = new In(new File(synsetFilename));
        hyponym = new In(new File(hyponymFilename));
        synSize = 0;
        synsetID = new HashMap<Integer, String>();
        synBoolean = new HashMap<String, Boolean>();

        while (synset.isEmpty() == false) {
            while (!synset.isEmpty()) {
                String lines = synset.readLine();
                String[] srawTokens = lines.split(",");
                synsetID.put(Integer.parseInt(srawTokens[0]), srawTokens[1].toString());
                String[] split = srawTokens[1].split(" ");
                synSize = synSize + 1;

                for (int k = 0; k < split.length; k = k + 1) {
                    synBoolean.put(split[k], true);
                }
            }
        }

        di = new Digraph(synSize);
        while (hyponym.isEmpty() == false) {
            while (!hyponym.isEmpty()) {
                String lines = hyponym.readLine();
                String[] hrawTokens = lines.split(",");
                hyponyms = new Integer[hrawTokens.length];

                for (int n = 0, h = 1; n < hyponyms.length - 1; n++, h++) {
                    hyponyms[n] = Integer.parseInt(hrawTokens[h]);
                }

                for (int p = 0; p < hyponyms.length - 1; p = p + 1) {
                    di.addEdge(Integer.parseInt(hrawTokens[0]), hyponyms[p]);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (synBoolean.containsKey(noun) == true);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synBoolean.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> set = new HashSet<Integer>();
        Set<String> synSet = new HashSet<String>();
        String[] splits = new String[synsetID.size()];
        String[] splitz;

        for (Integer s : synsetID.keySet()) {
            splits = synsetID.get(s).split(" ");
            for (int i = 0; i < splits.length; i++) {
                if (word.equals(splits[i])) {
                    set.add(s);
                }
            }
        }
        Set<Integer> final_set = GraphHelper.descendants(di, set);
        for (Integer p : final_set) {
            splitz = synsetID.get(p).split(" ");
            for (String s : splitz) {
                synSet.add(s);
            }
        }
        return synSet;
    }
}
