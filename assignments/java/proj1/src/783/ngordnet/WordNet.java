package ngordnet;

/**
 *  @author Russell Shen
 */

import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph d;
    private HashMap<Integer, String[]> h;
    private HashSet<String> wordSet;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        h = new HashMap<Integer, String[]>();
        wordSet = new HashSet<String>();
        String[] s1 = synsetIn.readAllLines();
        for (String line : s1) {
            String[] sepLine1 = line.split(",");
            int n = Integer.parseInt(sepLine1[0]);
            String[] words = sepLine1[1].split(" ");
            h.put(n, words);
            for (String word : words) {
                wordSet.add(word);
            }
        }
        d = new Digraph(s1.length);
        In hyponymIn = new In(hyponymFilename);
        String[] s2 = hyponymIn.readAllLines();
        for (String line : s2) {
            String[] sepLine2 = line.split(",");
            for (int i = 1; i < sepLine2.length; i++) {
                d.addEdge(Integer.parseInt(sepLine2[0]), Integer.parseInt(sepLine2[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> locations = new HashSet<Integer>();
        Set<Map.Entry<Integer, String[]>> s = h.entrySet();
        for (Map.Entry<Integer, String[]> entry : s) {
            String[] words = entry.getValue();
            Arrays.sort(words);
            if (Arrays.binarySearch(words, word) >= 0) {
                for (String word1 : words) {
                    hyponyms.add(word1);
                }
                locations.add(entry.getKey());
            }
        }
        Set<Integer> moreLocations = GraphHelper.descendants(d, locations);
        for (Integer key : moreLocations) {
            for (String word2 : h.get(key)) {
                hyponyms.add(word2);
            }
        }
        return hyponyms;
    }
}
