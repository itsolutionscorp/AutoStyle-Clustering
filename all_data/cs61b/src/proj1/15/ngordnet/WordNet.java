package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
    /** Map with keys as each synset's id and with values as each actual synset. */
    private Map<Integer, Set<String>> synsetMap;
    /** A graphical representation of "is-a" relationships among the synsets in the WordNet
      * instance. */
    private Digraph digraph;
    /** A set of strings of all the nouns in the WordNet instance. */
    private Set<String> synsetSet;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetMap = new HashMap<Integer, Set<String>>();
        synsetSet = new HashSet<String>();

        In sInput = new In(synsetFilename);
        In hInput = new In(hyponymFilename);

        /* Creates synsetMap. */
        while (!sInput.isEmpty()) {
            String[] line = sInput.readLine().split(",");
            String[] synsetWords = line[1].split(" ");
            for (String word : synsetWords) {
                synsetSet.add(word);
            }
            Integer tempInt = Integer.parseInt(line[0]);
            synsetMap.put(tempInt, new HashSet<String>(Arrays.asList(synsetWords)));
        }

        digraph = new Digraph(synsetMap.size());

        /* Creates digraph for hyponyms. */
        while (!hInput.isEmpty()) {
            String[] line = hInput.readLine().split(",", 2);
            String[] tempArray = line[1].split(",");
            for (String number : tempArray) {
                digraph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(number));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetSet.contains(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        /* List of ids for synsets that inputted word belongs in. */
        Set<Integer> belongingSynsetIDs = new HashSet<Integer>();
        /* Set of all hyponyms and synonyms of inputted word, even if word belongs to multiple
         * synsets. */
        Set<String> retrSet = new HashSet<String>();

        if (isNoun(word)) {
            /* Extracts the ids for the synsets that the word belongs in. */
            for (Map.Entry<Integer, Set<String>> synsetMapping : synsetMap.entrySet()) {
                Set<String> tempSynset1 = synsetMapping.getValue();
                if (tempSynset1.contains(word)) {
                    belongingSynsetIDs.add(synsetMapping.getKey());
                }
            }
            Set<Integer> reachableIDs = GraphHelper.descendants(digraph, belongingSynsetIDs);
            for (Integer reachableID : reachableIDs) {
                Set<String> tempSynset2 = synsetMap.get(reachableID);
                for (String wrd : tempSynset2) {
                    retrSet.add(wrd);
                }
            }
        }
        return retrSet;
    }
}
