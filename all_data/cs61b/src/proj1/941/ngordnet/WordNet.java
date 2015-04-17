package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class WordNet {
    private Digraph words;
    // private  HashMap<Integer, String> synsetMap = new HashMap<Integer, String>();
    private HashMap<Integer, ArrayList<String>> synsetMap;
    private HashMap<Integer, ArrayList<Integer>> hyponymMap;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetMap = new HashMap<Integer, ArrayList<String>>();
        hyponymMap = new HashMap<Integer, ArrayList<Integer>>();
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);

        while (synsetFile.hasNextLine()) {
            String[] currentSynsetLine = synsetFile.readLine().split(",");
            String[] copy = currentSynsetLine[1].split(" ");
            ArrayList<String> synsetValues = new ArrayList<String>(Arrays.asList(copy));
            // synsetMap.put(Integer.parseInt(currentSynsetLine[0]), currentSynsetLine[1])
            synsetMap.put(Integer.parseInt(currentSynsetLine[0]), synsetValues);
        }

        while (hyponymFile.hasNextLine()) {
            String[] currentHyponymLine = hyponymFile.readLine().split(",");
            Integer idKey = Integer.parseInt(currentHyponymLine[0]);
            ArrayList<Integer> hyponymValues = new ArrayList<Integer>(currentHyponymLine.length);
            for (int i = 1; i < currentHyponymLine.length; i += 1) {
                hyponymValues.add(Integer.parseInt(currentHyponymLine[i]));
            }
            if (hyponymMap.containsKey(idKey)) {
                hyponymMap.get(idKey).addAll(hyponymValues);
            } else {
                hyponymMap.put(idKey, hyponymValues);  
            }
            
        }
        
        words = new Digraph(synsetMap.size());
        for (Integer key : hyponymMap.keySet()) {
            for (Integer item : hyponymMap.get(key)) {
                words.addEdge(key, item);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (ArrayList<String> value : synsetMap.values()) {
            if (value.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (ArrayList<String> values : synsetMap.values()) {
            allNouns.addAll(values);
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> wordID = new ArrayList<Integer>();
        Set<String> hyponymsSet = new HashSet<String>();
        for (Integer key : synsetMap.keySet()) {
            if (synsetMap.get(key).contains(word)) {
                wordID.add(key);
                hyponymsSet.addAll(synsetMap.get(key));
            }
        }

        for (Integer item : wordID) {       
            Set<Integer> descendantIDSet = new TreeSet<Integer>();
            descendantIDSet.add(item);
            Set<Integer> hyponymsToAdd = GraphHelper.descendants(words, descendantIDSet);
            for (Integer hypo : hyponymsToAdd) {
                hyponymsSet.addAll(synsetMap.get(hypo));
            }
        }

        return hyponymsSet;

    }
}
