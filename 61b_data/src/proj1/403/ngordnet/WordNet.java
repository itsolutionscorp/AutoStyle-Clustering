package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @author Xizhao Deng
 */

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<String, Set<Integer>> synMap; // key:word, value: set of IDs
                                                  // a word has.
    private Digraph wnGraph; // digraph of the synsets. Vertices are their ID.
    private ArrayList<String[]> wordList; // index: ID, contains arrays of words
                                          // under synset.

    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSyn = new In(synsetFilename);
        In inHyp = new In(hyponymFilename);
        synMap = new HashMap<String, Set<Integer>>();
        wordList = new ArrayList<String[]>();
        String[] aLine; // for reading in each line from synsets.txt
        Integer synID; // get the ID from each synset
        String[] synWords; // get the words from each synset
        int synSize = 0; // the total amount of synsets, this is different from
                         // the size of the map!
        Set<Integer> idList; // the value of each key, it saves the synID of
                             // each word

        while (!inSyn.isEmpty()) {
            synSize++;
            aLine = inSyn.readLine().split(",");
            synID = Integer.parseInt(aLine[0]);
            synWords = aLine[1].split(" ");
            wordList.add(synWords);

            for (String word : synWords) {

                if (synMap.containsKey(word)) {
                    synMap.get(word).add(synID);
                } else {
                    idList = new HashSet<Integer>();
                    idList.add(synID);
                    synMap.put(word, idList);
                }
            }
        }

        // ============================================================================
        wnGraph = new Digraph(synSize);

        while (!inHyp.isEmpty()) {
            aLine = inHyp.readLine().split(",");
            for (int i = 1; i < aLine.length; i++) {
                wnGraph.addEdge(Integer.parseInt(aLine[0]),
                        Integer.parseInt(aLine[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synMap.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {

        Set<Integer> idSet = GraphHelper.descendants(wnGraph, synMap.get(word));
        Set<String> hyps = new HashSet<String>();

        for (Integer intCurser : idSet) {
            for (String wordCur : wordList.get((int) intCurser)) {
                hyps.add(wordCur);
            }
        }
        return hyps;
    }
}
