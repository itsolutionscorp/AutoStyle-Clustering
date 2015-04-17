package ngordnet;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet extends Object {
    private In synsetFile;
    private In hyponymFile;
    private Set<String> allNouns;                   // all the words
    private Map<String, Set<Integer>> wordToID;     // maps word to all its IDs
    private Map<Integer, Set<String>> idToWords;    // maps an ID to all the words
    private Digraph hyponymGraph;                   // graph plotting the hyponyms
    private int graphSize = 0;                      // keeps track of the size of the digraph


    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);

        wordToID = new HashMap<String, Set<Integer>>();
        idToWords = new HashMap<Integer, Set<String>>();
        allNouns = new TreeSet<String>();

        while (!synsetFile.isEmpty()) {
            /* Reads one line at a time and splits the line when it encounters ',' **/
            String [] entry = synsetFile.readLine().split(",");
            /* Checks the cases where they are multiple nouns, splits them and
             * puts them in the allNouns Set **/
            String [] words = entry[1].split(" ");
            /* entry[0] is the id, entry[1] is the synset and
             * entry[2] is the definition. **/
            int id = Integer.parseInt(entry[0]);
            graphSize += 1;

            /* synValues is the set of the words in the same synset. **/
            Set<String> synValues = new TreeSet<String>();
            /* Construct allNouns and wordToID (word mapped to all its IDs). **/
            for (String word : words) {
                synValues.add(word);
                allNouns.add(word);
                if (wordToID.containsKey(word)) {
                    wordToID.get(word).add(id);
                } else {
                    Set<Integer> idSet = new TreeSet<Integer>();
                    idSet.add(id);
                    wordToID.put(word, idSet);
                }
            }
            /* Maps the synsets(seperated) to their IDs for synonym usage purposes. **/
            idToWords.put(id, new TreeSet(synValues));
        }
        /* After reading the synset file, the number of entries/vertices are known. **/
        hyponymGraph = new Digraph(graphSize);

        /* Constructs the digraph according to the hyponyms file. **/
        while (!hyponymFile.isEmpty()) {
            /* Split the line- entry[0] is the id of the synset 
             * and the rest are ids of hyponyms **/
            String[] entry = hyponymFile.readLine().split(",");
            int id = Integer.parseInt(entry[0]);
            for (int i = 1; i < entry.length; i += 1) {
                hyponymGraph.addEdge(id, Integer.parseInt(entry[i]));
            }
        }
    }

    /* Returns hyponyms(including hyponyms of hyponyms) 
     * combined with the synonyms if it has hyponyms
     * Otherwise, returns just synonyms **/
    public Set<String> hyponyms(String word) {
        /* Gets all the IDs of the word. **/
        Set<Integer> synsetIDs = new TreeSet<Integer>();
        synsetIDs.addAll(wordToID.get(word));

        Set<String> result = new TreeSet<String>();
        /* Gets all the descendents/synsets from all IDs and puts them in a set. **/
        for (Integer descendant : GraphHelper.descendants(hyponymGraph, synsetIDs)) {
            result.addAll(new TreeSet(idToWords.get(descendant)));
        }
        return result;
    }

    public boolean isNoun(String noun) {
        if (allNouns.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return allNouns;
    }

}
