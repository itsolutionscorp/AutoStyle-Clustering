package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/** A WordNet is a data structure that holds relationships between words.
 * Specifically, a WordNet can tell you all of the hyponyms of a given
 * word, where a hyponym of X "is an" X. E.g. fish is a hyponym of
 * animal because fish "is an" animal.
 * @author Kasden Bunker
 */ 
public class WordNet {

    private HashMap<Integer, HashSet<String>> idToNounsMap;
    private HashMap<String, HashSet<Integer>> nounToIdsMap;
    private Digraph hyponymDigraph;

    /** Constructs a WordNet using a file of synsets and a file
     * of hyponym relationships between words. */
    public WordNet(java.lang.String synsetFilename,
                   java.lang.String hyponymFilename) {

        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);

        idToNounsMap = new HashMap<Integer, HashSet<String>>();
        nounToIdsMap = new HashMap<String, HashSet<Integer>>();

        while (synFile.hasNextLine()) {
            String[] synsetLine = synFile.readLine().split(",");

            Integer id = Integer.parseInt(synsetLine[0]);
            HashSet<Integer> idSet = new HashSet<Integer>();
            idSet.add(id);

            HashSet<String> words = new HashSet<String>();
            String[] synonyms = synsetLine[1].split(" ");
            for (String syn : synonyms) {
                words.add(syn);
            }
            this.idToNounsMap.put(id, words);

            for (String word : words) {
                HashSet<Integer> fullIdSet = new HashSet<Integer>(idSet);
                if (this.nounToIdsMap.get(word) != null) {
                    fullIdSet.addAll(nounToIdsMap.get(word));
                }
                this.nounToIdsMap.put(word, fullIdSet);
            }
        }

        this.hyponymDigraph = new Digraph(idToNounsMap.size());
        while (hypFile.hasNextLine()) {
            String[] hypLine = hypFile.readLine().split(",");

            Integer hypernymID = new Integer(Integer.parseInt(hypLine[0]));
            for (int i = 1; i < hypLine.length; i++) {
                this.hyponymDigraph.addEdge(hypernymID, Integer.parseInt(hypLine[i]));
            }
        }
    }

    /** Returns true if the given word is in this WordNet */
    public boolean isNoun(String noun) {
        return this.nounToIdsMap.containsKey(noun);
    }

    /** Returns a set of all the words in this WordNet */
    public Set<String> nouns() {
        return this.nounToIdsMap.keySet();
    }

    /** Returns a set of all the hyponyms of the given word, 
     * including its synonyms and the word itself
     */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> hypernymIDs = nounToIdsMap.get(word);
        if (hypernymIDs == null) {
            return null;
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(hyponymDigraph, hypernymIDs);

        HashSet<String> hyponyms = new HashSet<String>();
        for (Integer id : hyponymIDs) {
            hyponyms.addAll(idToNounsMap.get(id));
        }
        return hyponyms;
    }
}
