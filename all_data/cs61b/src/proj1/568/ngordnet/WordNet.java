
package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Collections;


public class WordNet {
    
    // Graph with integer nodes
    private Digraph web;
    // Map of integers used in graph to sets of synonyms
    private TreeMap<Integer, TreeSet<String>> synsetIndices = 
        new TreeMap<Integer, TreeSet<String>>();
    // Map of nouns to indices of all synsets they are part of
    private TreeMap<String, TreeSet<Integer>> nounLocations = 
        new TreeMap<String, TreeSet<Integer>>();

    public WordNet(String synsetsFile, String hyponymsFile) {
        // Read in synsets data, create sets and put them into the synsetIndices map
        In synsetsIn = new In(synsetsFile);
        String[] synsetsData = synsetsIn.readAllLines();

        for (String line : synsetsData) {
            String[] lineArray = line.split(",");
            
            int index = Integer.parseInt(lineArray[0]);

            String synsetString = lineArray[1];
            String[] synsetArray = synsetString.split(" ");
            TreeSet<String> synset = new TreeSet<String>(Arrays.asList(synsetArray));

            // Add entry (index: synset) to map, for each line
            synsetIndices.put(index, synset);
        }

        web = new Digraph(Collections.max(synsetIndices.keySet()) + 1);


        // Read in hyponyms file and create graph
        In hyponymsIn = new In(hyponymsFile);
        String[] hyponymsData = hyponymsIn.readAllLines();

        for (String line : hyponymsData) {
            String[] lineArray = line.split(",");

            int v = Integer.parseInt(lineArray[0]);
            for (String number : lineArray) {
                int w = Integer.parseInt(number);
                if (w == v) {
                    continue;
                }
                web.addEdge(v, w);
            }
        }


        // Iterate through synsetIndices map to create nounLocations map
        for (int i : synsetIndices.keySet()) {
            TreeSet<String> currentSynset = synsetIndices.get(i);
            for (String noun : currentSynset) {
                if (!nounLocations.containsKey(noun)) {
                    // Instantiates set if it's the first time
                    nounLocations.put(noun, new TreeSet<Integer>());
                }
                // Adds this integer to the set of nodes that contain this noun
                nounLocations.get(noun).add(i);
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String word) {
        return nounLocations.containsKey(word);
    }   

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return new TreeSet<String>(nounLocations.keySet());
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            throw new RuntimeException("That word is not in this WordNet!");
        }
        TreeSet<String> synsAndHypos = new TreeSet<String>();

        // Use Graph Helper to get all of the indices of descendants
        Set<Integer> descendantVertices = GraphHelper.descendants(web, nounLocations.get(word));
        for (int v : descendantVertices) {
            synsAndHypos.addAll(synsetIndices.get(v));
        }
        return synsAndHypos;
    }
}
