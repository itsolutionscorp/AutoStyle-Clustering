package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

public class WordNet {

    private HashMap<Integer, HashSet<String>> idToSynset;
    private HashMap<String, HashSet<Integer>> wordToSynsets;
    private Digraph hyponymsGraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        idToSynset = new HashMap<Integer, HashSet<String>>(); 
        wordToSynsets = new HashMap<String, HashSet<Integer>>();

        In synsetFile = new In(synsetFilename);
        In hyponymsetFile = new In(hyponymFilename);
        String[] currentLine;
        HashSet<String> currentSynset;
        HashSet<Integer> tempSet;

        ArrayList<String> tempArray;
        while (synsetFile.hasNextLine()) {
            currentLine = synsetFile.readLine().split(",");
            tempArray = new ArrayList<String>(Arrays.asList(currentLine[1].split(" ")));
            currentSynset = new HashSet<String>(tempArray);
            idToSynset.put(Integer.parseInt(currentLine[0]), currentSynset);
            for (String word : currentSynset) {
                if (wordToSynsets.containsKey(word)) {
                    wordToSynsets.get(word).add(Integer.parseInt(currentLine[0]));
                } else {
                    tempSet = new HashSet<Integer>();
                    tempSet.add(Integer.parseInt(currentLine[0]));
                    wordToSynsets.put(word, tempSet);
                }
            }
        }

        String[] hyponymsList;
        hyponymsGraph = new Digraph(idToSynset.size());
        
        while (hyponymsetFile.hasNextLine()) {
            hyponymsList = hyponymsetFile.readLine().split(",");
            Integer parent;
            Integer child;
            for (int i = 1; i < hyponymsList.length; i++) {
                parent = Integer.parseInt(hyponymsList[0]);
                child = Integer.parseInt(hyponymsList[i]);
                hyponymsGraph.addEdge(parent, child);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToSynsets.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToSynsets.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponymsSet = new HashSet<String>();
        Set<Integer> ids = GraphHelper.descendants(hyponymsGraph, wordToSynsets.get(word));
        for (int id : ids) {
            hyponymsSet.addAll(idToSynset.get(id));
        }
        return hyponymsSet;
    }
}
