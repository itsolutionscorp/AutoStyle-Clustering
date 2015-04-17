package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class WordNet {

    private Map<Integer, String> synsetMap;
    private Set<Integer> gHelper;
    private Set<String> allWords;
    private Map<String, Set<Integer>> hyponymMap;
    private Digraph dGraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename); 
        // Found a Piazza post that suggested we use In and readLine();
        synsetMap = new HashMap<Integer, String>();
        allWords = new HashSet<String>();
        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();
            String words = line.split(",")[1];
            synsetMap.put(Integer.valueOf(line.split(",")[0]), words);
            for (String x : words.split(" ")) {
                allWords.add(x);
            }
        }

        In hyponymIn = new In(hyponymFilename);
        hyponymMap = new HashMap<String, Set<Integer>>();
        dGraph = new Digraph(synsetMap.size());

        for (Integer key : synsetMap.keySet()) {
            String terms = synsetMap.get(key);
            for (String term : terms.split(" ")) {
                Set<Integer> id = new HashSet<Integer>();
                id.add(key);
                if (hyponymMap.containsKey(term)) {
                    Set<Integer> innerSet = hyponymMap.get(term);
                    for (int num : innerSet) {
                        id.add(num);
                    }
                }
                hyponymMap.put(term, id);
            }
        }

        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();
            Integer synID = Integer.valueOf(line.split(",")[0]);
            String[] synIDHyponyms = line.split(",");

            for (int i = synIDHyponyms.length - 1; i > 0; i -= 1) {
                dGraph.addEdge(synID, Integer.valueOf(synIDHyponyms[i]));
            }

            String word = synsetMap.get(synID);
            for (String x : word.split(" ")) {
                Set<Integer> idSet = new HashSet<Integer>();
                idSet.add(synID);
                for (int i = synIDHyponyms.length - 1; i > 0; i -= 1) {
                    idSet.add(Integer.valueOf(synIDHyponyms[i]));
                }
                if (hyponymMap.containsKey(x)) {
                    Set<Integer> innerSet2 = hyponymMap.get(x);
                    for (int num : innerSet2) {
                        idSet.add(num);
                    }
                }
                hyponymMap.put(x, idSet);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allWords;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> wordHyponymSet = new HashSet<String>();
        Set<Integer> synsetIDs = hyponymMap.get(word);
        gHelper = GraphHelper.descendants(dGraph, synsetIDs);
        for (int x : gHelper) {
            String foundWord = synsetMap.get(x);
            for (String y : foundWord.split(" ")) {
                wordHyponymSet.add(y);
            }
        }
        return wordHyponymSet;
    }

}
