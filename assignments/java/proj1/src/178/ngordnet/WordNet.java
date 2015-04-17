package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/* Java Util Imports */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * @author Rohit Lalchandani
 */

public class WordNet {

    private HashMap<Integer, ArrayList<String>> synsets; // Map from synsetID to synset
    private HashMap<String, ArrayList<Integer>> words; // Map from word to synsetIDs
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    /* Will need at least 2 ADTs -- Digraph & ? */
    public WordNet(String synsetFilename, String hyponymFilename) {

        /* Initializing fields */
        synsets = new HashMap<Integer, ArrayList<String>>();
        words = new HashMap<String, ArrayList<Integer>>();

        /* Read the synsets file. */
        In file = new In(synsetFilename);
        while (file.hasNextLine()) {

            /* Reading the synset and breaking the string */
            String line = file.readLine();
            String[] parsedLine = line.split(",");

            /* Values of synset */
            int synsetID = Integer.parseInt(parsedLine[0]); // Current ID
            String[] synsetWords = parsedLine[1].split(" "); // List of nouns
            String synsetDef = parsedLine[2];

            /* Add synset to the SYNSETS Map */
            synsets.put(synsetID, new ArrayList<String>(Arrays.asList(synsetWords)));

            /* Add Words to WORDS */
            for (String word : synsetWords) {
                if (words.containsKey(word)) {

                    /* Gets list associated with noun and adds ID to that list */
                    words.get(word).add(synsetID);

                } else {

                    /* Create a new ArrayList with synsetID */
                    ArrayList<Integer> value = new ArrayList<Integer>();
                    value.add(synsetID);

                    /* Add Key, Value pair to HashMap */
                    words.put(word, value);
                }
            }
        }

        /* Construct the digraph */
        graph = new Digraph(synsets.size());

        /* Read the hyponyms */
        file = new In(hyponymFilename);
        while (file.hasNextLine()) {

            /* Reading the hyponym and breaking the string */
            String line = file.readLine();
            String[] parsedLine = line.split(",");

            /* Values of hyponym */
            int synsetID = Integer.parseInt(parsedLine[0]);
            for (int i = 1; i < parsedLine.length; i += 1) {

                /* Adding hyponym to digraph */
                graph.addEdge(synsetID, Integer.parseInt(parsedLine[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return words.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        /* Creating set that will be returned */
        HashSet<String> set = new HashSet<String>();
        if (words.containsKey(word)) {

            Set<Integer> parentIDs = new HashSet<Integer>(words.get(word));
            Set<Integer> descendantIDs = GraphHelper.descendants(graph, parentIDs);

            /* Adding words from parents to return set */
            for (int i : parentIDs) {
                set.addAll(synsets.get(i));
            }

            /* Adding words from descendants to return set */
            for (int i : descendantIDs) {
                set.addAll(synsets.get(i));
            }
        }
        return set;
    }
}
