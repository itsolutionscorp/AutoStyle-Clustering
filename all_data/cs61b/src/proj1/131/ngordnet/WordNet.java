package ngordnet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * Provides a way to organise and visualise hyponyms and other word related
 * methods
 * 
 * @author Yilun Chen
 */
public class WordNet {
    private HashMap<Integer, String[]> nouns;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nouns = new HashMap<Integer, String[]>();

        In synsetIn = new In(synsetFilename);
        In hypoIn = new In(hyponymFilename);

        String[] tempSynsetArr;

        String[] tempUtilArr;

        int digraphSize = 0;

        // loop to add synsets to the nouns HashMap
        while (synsetIn.hasNextLine()) {
            tempSynsetArr = synsetIn.readLine().split(",");
            tempUtilArr = tempSynsetArr[1].split(" ");

            nouns.put(Integer.parseInt(tempSynsetArr[0]), tempUtilArr);
        }

        // calculate the size of the digraph
        for (Integer keyOuter : nouns.keySet()) {
            for (int i = 0; i < nouns.get(keyOuter).length; i++) {
                digraphSize += 1;
            }
        }

        hyponyms = new Digraph(digraphSize);

        // populate the digraph
        while (hypoIn.hasNextLine()) {
            tempUtilArr = hypoIn.readLine().split(",");

            for (int i = 1; i < tempUtilArr.length; i++) {
                hyponyms.addEdge(Integer.parseInt(tempUtilArr[0]),
                        Integer.parseInt(tempUtilArr[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        // check if the noun exists in all synsets
        for (Integer key : nouns.keySet()) {
            if (Arrays.asList(nouns.get(key)).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toReturn = new TreeSet<String>();

        // add each noun by iterating through synsets
        for (Integer key : nouns.keySet()) {
            for (String elem : nouns.get(key)) {
                toReturn.add(elem);
            }
        }
        return toReturn;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> id = new TreeSet<Integer>();
        Set<String> toReturn = new TreeSet<String>();

        /*
         * get the word int id. ideas retrieved from:
         * http://stackoverflow.com/questions
         * /1383797/java-hashmap-how-to-get-key-from-value
         */
        for (Entry<Integer, String[]> entry : nouns.entrySet()) {
            for (String value : entry.getValue()) {
                if (word.equals(value)) {
                    id.add(entry.getKey());
                }
            }
        }

        // retrieve the hyponyms and store them to return
        for (Integer key : GraphHelper.descendants(hyponyms, id)) {
            for (String elem : nouns.get(key)) {
                toReturn.add(elem);
            }
        }
        return toReturn;
    }
}
