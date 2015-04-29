package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In synsetReader;
    private In hyponymReader;
    private int numIDs;
    private HashMap<Integer, HashSet<String>> idToSynset;
    private Digraph hyponymGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetReader = new In(synsetFilename);
        this.hyponymReader = new In(hyponymFilename);
        this.parseSynsetandHyponyms();
    }

    // puts id of synsets as keys and their words as set in hash map,
    // puts id of hyponyms as keys and ids that are hyponyms in hash map
    private void parseSynsetandHyponyms() {
        numIDs = 0;
        idToSynset = new HashMap<Integer, HashSet<String>>();
        
        while (synsetReader.hasNextLine()) {
            String line = synsetReader.readLine();
            numIDs += 1;
            String[] splitLine = line.split(",");
            Integer id = Integer.parseInt(splitLine[0]);
            String[] wordSet = splitLine[1].split(" ");
            HashSet<String> words = new HashSet<String>(Arrays.asList(wordSet));
            idToSynset.put(id, words);
        }

        hyponymGraph = new Digraph(numIDs);
        while (hyponymReader.hasNextLine()) {
            String line = hyponymReader.readLine();
            String[] splitLine = line.split(",");
            Integer[] hyponymIntegers = new Integer[splitLine.length];
            for (int i = 0; i < hyponymIntegers.length; i += 1) {
                hyponymIntegers[i] = Integer.parseInt(splitLine[i]);
            }
            // citation: http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set
            HashSet<Integer> hyponyms = new HashSet<Integer>(Arrays.asList(hyponymIntegers)); 
            Integer id = Integer.parseInt(splitLine[0]);
            for (Integer hypo: hyponyms) {
                hyponymGraph.addEdge(id, hypo);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Integer i: idToSynset.keySet()) {
            for (String word: idToSynset.get(i)) {
                nouns.add(word);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> wordToIDS = getKeysByValue(idToSynset, word); // get ID from word
        if (wordToIDS.size() == 0) {
            return hyponyms;
        }
        Set<Integer> hyponymsToIDs = GraphHelper.descendants(hyponymGraph, wordToIDS);
        for (Integer id: hyponymsToIDs) {
            hyponyms.addAll(idToSynset.get(id));
        }
        return hyponyms;
    }

    private static <ID, S> Set<ID> getKeysByValue(HashMap<ID, HashSet<S>> map, S noun) {
    // citation: http://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
        HashSet<ID> keys = new HashSet<ID>();
        for (Map.Entry<ID, HashSet<S>> entry : map.entrySet()) {
            HashSet<S> nounSet = entry.getValue();
            if (nounSet.contains(noun)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }
}
