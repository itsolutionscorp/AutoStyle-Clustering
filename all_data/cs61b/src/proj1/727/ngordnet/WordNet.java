package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;

public class WordNet {

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Map<Integer, Set<String>> synsetMap;
    private Set<String> nounsSet;
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {

        synsetMap = new HashMap<Integer, Set<String>>();
        nounsSet = new LinkedHashSet<String>();
        In synsetIn = new In(new File(synsetFilename));
        In hyponymIn = new In(new File(hyponymFilename));

        String[] synsetInArray = synsetIn.readAllLines(); //found to be faster than hasNextLine()
        for (String s : synsetInArray) {
            String[] synsetTokens = s.split(",");
            Integer synsetID = Integer.parseInt(synsetTokens[0]);
            String[] synsetWordsArray = synsetTokens[1].split(" ");
            Set<String> synsetWordsSet = new LinkedHashSet<String>(Arrays.asList(synsetWordsArray));
            synsetMap.put(synsetID, synsetWordsSet);
            nounsSet.addAll(synsetWordsSet);
        }

        digraph = new Digraph(synsetMap.size());
        String[] hyponymArray = hyponymIn.readAllLines();
        for (String hyponymString : hyponymArray) {
            String[] hyponymLine = hyponymString.split(",");
            int initialID = Integer.parseInt(hyponymLine[0]); 
            for (int i = 1; i < hyponymLine.length; i++) {
                digraph.addEdge(initialID, Integer.parseInt(hyponymLine[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounsSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounsSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        Set<Integer> hyponymIDSet = new LinkedHashSet<Integer>();
        for (Integer i : synsetMap.keySet()) {
            if (synsetMap.get(i).contains(word)) {
                hyponymIDSet.add(i);
            }
        }
        hyponymIDSet = GraphHelper.descendants(digraph, hyponymIDSet);
        Set<String> allHyponymsSet = new LinkedHashSet<String>();
        for (Integer x : hyponymIDSet) {
            allHyponymsSet.addAll(synsetMap.get(x));
        }
        return allHyponymsSet;
    }
}
