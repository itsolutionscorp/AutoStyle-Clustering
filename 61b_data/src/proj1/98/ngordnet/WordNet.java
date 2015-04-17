package ngordnet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;

/**
 * WordNet stores the data and relationships between synsets. Data structure is
 * a digraph to store relationships and an arraylist to store all the synset
 * data.
 * 
 * @author Jerry Chen
 *
 */

public class WordNet {

    private HashMap<Integer, Synset> wNet; // Array of all the synsets.
    private Digraph wNGraph; // Stores hyponym relationships
    private int totalSyns; // Total number of synsets

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Initialize our wordnet
        wNet = new HashMap<Integer, Synset>();
        // Setup file readers.
        BufferedReader syns = null;
        BufferedReader hyps = null;
        try {
            InputStreamReader synsIn = new InputStreamReader(
                    new FileInputStream(synsetFilename), StandardCharsets.UTF_8);
            InputStreamReader hypsIn = new InputStreamReader(
                    new FileInputStream(hyponymFilename),
                    StandardCharsets.UTF_8);
            syns = new BufferedReader(synsIn);
            hyps = new BufferedReader(hypsIn);
            String input;
            String[] parsed; // Result after parsing by commas

            // Read in each line for synset.
            int index; // Index for a synset
            while ((input = syns.readLine()) != null) {
                totalSyns += 1;
                parsed = input.split(",");
                index = Integer.parseInt(parsed[0]);
                wNet.put(index, new Synset(index, parsed[1].split(" "),
                        parsed[2]));
            }
            wNGraph = new Digraph(totalSyns);
            // Read in each line for hyponym
            int[] parsedNum; // Result after parsing and converting to ints
            while ((input = hyps.readLine()) != null) {
                parsed = input.split(",");
                parsedNum = new int[parsed.length];
                for (int i = 0; i < parsed.length; i++) {
                    parsedNum[i] = Integer.parseInt(parsed[i]);
                }
                for (int j = 1; j < parsedNum.length; j++) {
                    wNGraph.addEdge(parsedNum[0], parsedNum[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (hyps != null) {
                    hyps.close();
                }
                if (syns != null) {
                    syns.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer id : wNet.keySet()) {
            if (wNet.get(id).wordIn(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (Integer id : wNet.keySet()) {
            String[] syns = wNet.get(id).syns;
            for (String word : syns) {
                allNouns.add(word);
            }
        }
        return allNouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        // ID of all the synsets to check.
        ArrayList<Integer> ids = new ArrayList<Integer>();
        // All of the valid found words.
        TreeSet<String> results = new TreeSet<String>();

        // Find all indexes of desired word.
        for (Integer id : wNet.keySet()) {
            if (wNet.get(id).wordIn(word)) {
                ids.add(id);
            }
        }
        // Find the hyponyms.
        for (int id : ids) {
            results.addAll(addAllHyps(id));
        }
        return results;
    }

    /**
     * Returns a set with all the synonyms in the index synset plus the words in
     * the hyponym of the index.
     * 
     * @param index
     *            - parent id to start at.
     * @return Set of all words in id synset plus all words in all hyponyms.
     */
    private Set<String> addAllHyps(int index) {
        TreeSet<String> results = new TreeSet<String>();
        String[] synList = wNet.get(index).syns;
        for (String syn : synList) {
            results.add(syn);
        }
        for (int id : wNGraph.adj(index)) {
            results.addAll(addAllHyps(id));
        }
        return results;
    }

    /**
     * Stores read in information for a synset. Takes in synset id, all of the
     * words in the synset, and a definition/description.
     */
    private class Synset {
        private int id;
        private String[] syns;
        private String definition;

        /**
         * Default constructor for a synset object.
         * 
         * @param idIn
         *            - Id number of the synset.
         * @param synsIn
         *            - Array of synonyms in the synset.
         * @param definitionIn
         *            - Description/definition of the synset.
         */
        public Synset(int idIn, String[] synsIn, String definitionIn) {
            id = idIn;
            syns = synsIn;
            definition = definitionIn;
        }

        /**
         * Determines if a word is in the list of synonyms.
         * 
         * @param noun
         *            - Word to find.
         * @return True if word is found, false otherwise.
         */
        public boolean wordIn(String noun) {
            for (String word : syns) {
                if (word.equals(noun)) {
                    return true;
                }
            }
            return false;
        }
    }
}
