package ngordnet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * An object that stores the WordNet graph in a manner useful for extracting all
 * hyponyms of a word.
 * 
 * @author allenz
 */
public class WordNet {

    /** Maps a synset id to the corresponding synset. */
    private final HashMap<Integer, String[]> synsets = new HashMap<Integer, String[]>();

    /** Maps a noun to all synsets it belongs to. */
    private final HashMap<String, HashSet<Integer>> nouns = new HashMap<String, HashSet<Integer>>();

    /** Graph of hyponym relationships. Edges point from hypernym to hyponym. */
    private final Digraph hyponyms;

    /** Creates a WordNet using files from synsetFilename and hyponymFilename */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            String[] synset = line[1].split(" ");
            Integer setID = Integer.parseInt(line[0]);
            synsets.put(setID, synset);
            for (String s : synset) {
                HashSet<Integer> curr = nouns.get(s);
                if (curr == null) {
                    curr = new HashSet<Integer>();
                    curr.add(setID);
                    nouns.put(s, curr);
                } else {
                    curr.add(setID);
                }
            }
        }
        in.close();

        hyponyms = new Digraph(nouns.size());
        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            Integer hypernym = Integer.parseInt(line[0]);
            Integer[] hypArr = new Integer[line.length - 1];
            for (int i = 0; i < hypArr.length; i++) {
                hyponyms.addEdge(hypernym, Integer.parseInt(line[i + 1]));
            }
        }
        in.close();
    }

    /** @return whether NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
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
        if (!nouns.containsKey(word)) {
            HashSet<String> s = new HashSet<String>();
            s.add(word);
            return s;
        }
        Set<Integer> dsc = GraphHelper.descendants(hyponyms, nouns.get(word));
        Set<String> hyps = new HashSet<String>();
        for (Integer synId : dsc) {
            hyps.addAll(Arrays.asList(synsets.get(synId)));
        }
        return hyps;
    }
}
