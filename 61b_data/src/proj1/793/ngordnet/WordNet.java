package ngordnet;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import java.io.IOException;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/** A repository of information about synsets and their hyponyms.
 *  
 *  @author Nick Gerlock
 */

public class WordNet {

    // Instance Variables
    
    // A dictionary of synsets keyed by ID
    private Map<Integer, Synpack> synMap;
    // A dictionary of synsets keyed by nouns
    private Map<String, Set<Synpack>> nounMap;
    // A directed graph of hyponyms
    private Digraph hypoGraph;

    private int maxID;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Adds each synset to nounMap, and each individual noun to nouns

        try {
            buildSynset(synsetFilename);
        } catch (IOException e) {
            System.out.println("Cannot find/parse given synset file.");
        }

        try {
            buildHypoGraph(hyponymFilename);
        } catch (IOException e) {
            System.out.println("Cannot find/parse given hyponym file.");
        }
    }

    // Helper function that reads in a hyponym file and populates the hypoGraph
    private void buildHypoGraph(String hyponymFilename) throws IOException {
        hypoGraph = new Digraph(maxID + 1);

        // Read all lines
        In hypoFile = new In(hyponymFilename);
        String[] lines = hypoFile.readAllLines();
        hypoFile.close();

        for (String line : lines) {
            String[] pieces = line.split(",");
            if (pieces.length < 1) {
                throw new IOException("Unparsable hyponym file");
            }

            int hypernym = Integer.parseInt(pieces[0]);

            // Add all child edges, making sure we have their synsets
            if (hypernym < hypoGraph.V()) {
                for (int i = 1; i < pieces.length; i++) {
                    int hyponym = Integer.parseInt(pieces[i]);
                    if (hyponym < hypoGraph.V()) {
                        hypoGraph.addEdge(hypernym, hyponym);
                    }
                }
            }
        }
    }

    // Helper function that reads in a synset file and populates synMap and nouns
    private void buildSynset(String synsetFilename) throws IOException {
        nounMap = new HashMap<String, Set<Synpack>>();
        synMap  = new HashMap<Integer, Synpack>();

        // Read all lines
        In synFile = new In(synsetFilename);
        String[] lines = synFile.readAllLines();
        synFile.close();

        // Iterate through every line
        for (String line : lines) {
            String[] pieces = line.split(",");
            
            // If the line is unreadable, throw an exception
            if (pieces.length < 3) {
                throw new IOException("Unparsable synset file");
            }


            // Get fields of this line
            int currID  = Integer.parseInt(pieces[0]);
            String currSynset = pieces[1];
            String currGloss = "";
            for (int i = 2; i < pieces.length; i++) {
                currGloss += pieces[i];
            }

            // Update maximum synset ID
            if (currID > maxID) {
                maxID = currID;
            }

            // Add the synset to our map of synsets
            Synpack currPack = new Synpack(currID, currSynset, currGloss);

            synMap.put(currID, currPack);

            // Find all nouns in the synset and add them to the list
            String[] words = currSynset.split(" ");
            for (String word : words) {
                if (nounMap.containsKey(word)) {
                    nounMap.get(word).add(currPack);
                } else {
                    Set<Synpack> tempSet = new HashSet<Synpack>();
                    tempSet.add(currPack);
                    nounMap.put(word, tempSet);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> nounSet = nounMap.keySet();
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> retSet = new HashSet<String>();

        Set<Synpack> packs = nounMap.get(word);
        Set<Integer> synIDs = new HashSet<Integer>();

        if (packs != null) {
            // Iterate through all synsets containing word
            for (Synpack pack : packs) {
                // Add all synonyms
                retSet.addAll(pack.synset);
                synIDs.add(pack.id);
            }
        }

        // Find all hyponyms
        Set<Integer> hypoIDs = GraphHelper.descendants(hypoGraph, synIDs);

        // Iterate through each hyponym and add each word to the set
        for (Integer id : hypoIDs) {
            Synpack currPack = synMap.get(id);
            retSet.addAll(currPack.synset);
        }

        return retSet;
    }

    // Wrapper class for Synsets and their related attributes
    private class Synpack {
        private int id;
        private String glossString;
        private Set<String> synset;
        
        public Synpack() {
            id = -1;
        }

        public Synpack(int anId, String aSysnet, String aGloss) {
            id = anId;
            glossString = aGloss;
            synset = new HashSet<String>();

            String[] wordArr = aSysnet.split(" ");
            for (String word : wordArr) {
                synset.add(word);
            }
        }
    }

}
