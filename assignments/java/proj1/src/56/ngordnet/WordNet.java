package ngordnet;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/**
 * WORDNET CLASS
 * WordNet is responsible for reading input files
 * and storing the data in ADTs that will allow for
 * fast queries.
 *
 * @author Alvin Wan
 * @CS61B ace
 */
public class WordNet {

    /* Digraph for all relationships */
    private Digraph digraph;
    /* sorted arraylist for all noun data */
    private ArrayList<Noun> data;
    /* for quick word-to-id lookup and if-noun-exists */
    private HashMap<String, Set<Integer>> nouns;

    /****************************
     * Required Methods for API *
     **************************** */

    /**
     * CONSTRUCTOR
     * separates functionality into three steps:
     * - read data from file
     * - load data into digraph
     * - optimize data using additional ADTs
     *
     * @param file_syn -- filename for synsets
     * @param file_hyp -- filename for hyponyms
     */
    public WordNet(String synsetsFilename, String hyponymsFilename) {
        nouns = new HashMap<String, Set<Integer>>();
        data = new ArrayList<Noun>();

        // read data
        In synsets = new In(synsetsFilename);
        In hyponyms = new In(hyponymsFilename);
        String line;

        // optimize data
        while ((line = synsets.readLine()) != null) {
            processSynset(line);
        }

        // load data
        digraph = new Digraph(data.size());
        while ((line = hyponyms.readLine()) != null) {
            processHyponym(line);
        }
    }

    /* boolean if string is noun */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns all nouns in digraph as set */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /**
     * Hyponyms
     * Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets.
     */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Noun does not exist in WordNet.");
        }
        Set<Integer> ids = GraphHelper.descendants(digraph, nouns.get(word));
        Set<String> words = new HashSet<String>();

        for (int id : ids) {
            words.addAll(data.get(id).synset);
        }

        return words;
    }

    /**************************
     * Helper, Hidden Methods *
     ************************** */

    /**
     * Process Synset
     * This method splits line data into parts, then:
     * - creates a new Noun object
     * - saves data in a reversed dictionary, for
     * quick searches
     * - registers Noun in a list, for future id-to-
     * word lookups
     *
     * @param line -- String line for parsing
     */
    private void processSynset(String line) {

        String[] blobs = line.split(",");

        int id = Integer.parseInt(blobs[0]);
        HashSet<String> synset = new HashSet<String>(Arrays.asList(blobs[1].split(" ")));

        // create Noun object
        Noun noun = new Noun(id, synset, blobs[2]);

        // save Noun in reverse map
        for (String n : synset) {
            Set<Integer> list = nouns.containsKey(n) ? nouns.get(n) : new HashSet<Integer>();
            list.add(id);
            nouns.put(n, list);
        }

        // would NOT work if IDs did not correspond to 0-indexed line number
        data.add(noun);
    }

    // process hypnonyms by adding edges to the digraph
    private void processHyponym(String line) {

        String[] ids = line.split(",");

        for (int i = 1; i < ids.length; i++) {
            digraph.addEdge(Integer.parseInt(ids[0]), Integer.parseInt(ids[i]));
        }
    }

    /**
     * ***********************
     * Helper, Hidden Classes *
     * *************************
     */

    private class Noun {
        private int id;
        private Set<String> synset;
        private String gloss;

        public Noun(int d, Set<String> ynset, String loss) {
            id = d;
            synset = ynset;
            gloss = loss;
        }
    }
}
