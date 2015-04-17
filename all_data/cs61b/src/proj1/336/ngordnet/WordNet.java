package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ankit Mathur
 *
 */
public class WordNet {
    private HashMap<Integer, String[]> synSetMap;
    private Set<String> allNouns;
    private Digraph synNet;

    /**
     * Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME
     * @param synsetFilename
     * @param hyponymFilename
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(new File(synsetFilename));
        In hyponymIn = new In(new File(hyponymFilename));
        synSetMap = new HashMap<Integer, String[]>();
        allNouns = new HashSet<String>();
        while (synsetIn.hasNextLine()) {
            String[] synInTokens = synsetIn.readLine().split(",");
            int synId = Integer.parseInt(synInTokens[0]);
            String nouns = synInTokens[1];
            String[] splitNouns = nouns.split(" ");
            synSetMap.put(synId, splitNouns);
            allNouns.addAll(Arrays.asList(splitNouns));
        }
        synNet = new Digraph(synSetMap.size());
        while (hyponymIn.hasNextLine()) {
            String[] hypoInTokens = hyponymIn.readLine().split(",");
            int startVertex = Integer.parseInt(hypoInTokens[0]);
            for (int i = 1; i < hypoInTokens.length; i++) {
                synNet.addEdge(startVertex, Integer.parseInt(hypoInTokens[i]));
            }
        }
        synsetIn.close();
        hyponymIn.close();
    }

    /**
     * @param noun
     * @return true if NOUN is a word in some synset.
     */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /**
     * @return the set of all nouns.
     */
    public Set<String> nouns() {
        return allNouns;
    }
    
    /**
     * @param word
     * @return Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new HashSet<String>();
        Set<Integer> wordLocations = new HashSet<Integer>();
        for (Integer k : synSetMap.keySet()) {
            for (String s : synSetMap.get(k)) {
                if (s.equals(word)) {
                    wordLocations.add(k);
                }
            }
        }
        wordLocations.addAll(GraphHelper.descendants(synNet, wordLocations));
        for (Integer i : wordLocations) {
            allHyponyms.addAll(Arrays.asList(synSetMap.get(i)));
        }
        return allHyponyms;
    }

}
