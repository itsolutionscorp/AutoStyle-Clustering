package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/**
 * read input files and store them in ADTs of your choice that allow for rapid
 * queries.
 * 
 * @author Xiaoyi Cheng
 * 
 */

public class WordNet {
    private HashMap<Integer, HashSet<String>> numAsKeyMap;
    private HashMap<String, HashSet<Integer>> wordAsKeyMap;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        numAsKeyMap = new HashMap<Integer, HashSet<String>>();
        wordAsKeyMap = new HashMap<String, HashSet<Integer>>();
        int counter = 0;

        In synsetIn = new In(synsetFilename);
        while (synsetIn.hasNextLine()) {
            HashSet<String> tempWordSet = new HashSet<String>();
            String[] fragments = synsetIn.readLine().split(",");
            Integer number = Integer.valueOf(fragments[0]);
            String[] words = fragments[1].split(" ");
            for (int i = 0; i < words.length; i++) {
                tempWordSet.add(words[i]);
                HashSet<Integer> numSet = wordAsKeyMap.get(words[i]);
                if (numSet == null) {
                    HashSet<Integer> tempNumSet = new HashSet<Integer>();
                    tempNumSet.add(number);
                    wordAsKeyMap.put(words[i], tempNumSet);
                } else {
                    numSet.add(number);
                    wordAsKeyMap.put(words[i], numSet);
                }

            }
            numAsKeyMap.put(number, tempWordSet);
            counter++;
        }
        graph = new Digraph(counter);
        In hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            String[] parts = hyponymIn.readLine().split(",");
            Integer num = Integer.valueOf(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                graph.addEdge(num, Integer.valueOf(parts[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (wordAsKeyMap == null) {
            return false;
        } else {
            return wordAsKeyMap.containsKey(noun);
        }
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        if (wordAsKeyMap == null) {
            return null;
        } else {
            return wordAsKeyMap.keySet();
        }
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        if (wordAsKeyMap == null) {
            return null;
        }
        Set<String> hypoymsSet = new HashSet<>();
        if (wordAsKeyMap.containsKey(word)) {
            HashSet<Integer> synsetIDs = wordAsKeyMap.get(word);
            Set<Integer> descent = GraphHelper.descendants(graph, synsetIDs);
            Iterator<Integer> numIter = descent.iterator();
            while (numIter.hasNext()) {
                Set<String> wordsSet = numAsKeyMap.get(numIter.next());
                Iterator<String> wordIter = wordsSet.iterator();
                while (wordIter.hasNext()) {
                    hypoymsSet.add(wordIter.next());
                }
            }
        }
        return hypoymsSet;
    }
}
