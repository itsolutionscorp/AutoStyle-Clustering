package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * @Author: Guangzhao Yang
 */

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Map<Integer, Set<String>> synsetMap;
    private Digraph hyponymDiagraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        Map<Integer, Set<String>> tempSynsetMap = new HashMap<Integer, Set<String>>();
        Map<Integer, Set<Integer>> tempHyponymMap = new HashMap<Integer, Set<Integer>>();
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        while (synsetIn.hasNextLine()) {
            String tempLine1 = synsetIn.readLine();
            String[] synsetArray = tempLine1.split(",");
            Integer synsetKeys = Integer.parseInt(synsetArray[0]);
            Set<String> synsetValues = new HashSet<String>();
            if (tempSynsetMap.keySet().contains(synsetKeys)) {
                synsetValues = tempSynsetMap.get(synsetKeys);
            }
            String[] wordArray = synsetArray[1].split(" ");
            for (String word : wordArray) {
                synsetValues.add(word);
            }
            tempSynsetMap.put(synsetKeys, synsetValues);
        }

        while (hyponymIn.hasNextLine()) {
            String tempLine2 = hyponymIn.readLine();
            String[] hyponymArray = tempLine2.split(",");
            Integer hyponymKeys = Integer.parseInt(hyponymArray[0]);
            Set<Integer> hyponymValues = new HashSet<Integer>();
            if (tempHyponymMap.keySet().contains(hyponymKeys)) {
                hyponymValues = tempHyponymMap.get(hyponymKeys);
            }
            for (int i = 1; i < hyponymArray.length; i += 1) {
                hyponymValues.add(Integer.parseInt(hyponymArray[i]));
            }
            tempHyponymMap.put(hyponymKeys, hyponymValues);
        }

        Digraph tempHyponymDigraph = new Digraph(tempSynsetMap.size());
        for (Integer x : tempHyponymMap.keySet()) {
            for (Integer y : tempHyponymMap.get(x)) {
                tempHyponymDigraph.addEdge(x, y);
            }
        }
        synsetMap = tempSynsetMap;
        hyponymDiagraph = tempHyponymDigraph;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer x : synsetMap.keySet()) {
            if (synsetMap.get(x).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> returnString = new HashSet<String>();
        for (Integer x : synsetMap.keySet()) {
            for (String str : synsetMap.get(x)) {
                returnString.add(str);
            }
        }
        return returnString;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> idKeeper = new HashSet<Integer>();
        Set<String> toBeReturn = new HashSet<String>();
        for (Integer x : synsetMap.keySet()) {
            if (synsetMap.get(x).contains(word)) {
                idKeeper.add(x);
            }
        }
        if (idKeeper.size() == 0) {
            throw new NullPointerException();
        }
        idKeeper = GraphHelper.descendants(hyponymDiagraph, idKeeper);
        for (Integer z : idKeeper) {
            for (String str : synsetMap.get(z)) {
                toBeReturn.add(str);
            }
        }
        return toBeReturn;
    }
}
