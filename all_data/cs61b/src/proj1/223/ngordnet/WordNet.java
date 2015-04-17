package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    /** the set of all nouns */
    private HashSet<String> allNouns;

    /** the map from a word to a list of synset ids */
    private HashMap<String, HashSet<Integer>> nounToSetOfSynsetID;

    /** the map from a synset id to a set of synonyms */
    private HashMap<Integer, HashSet<String>> synsetIDToSetOfNoun;

    /** the Digraph between synset ids */
    private Digraph hyponymGraph;


    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        allNouns = new HashSet<String>();
        nounToSetOfSynsetID = new HashMap<String, HashSet<Integer>>();
        synsetIDToSetOfNoun = new HashMap<Integer, HashSet<String>>();
        readSynsetsFromIn(synsetIn);
        readHyponymsFromIn(hyponymIn);
    }

    private void readSynsetsFromIn(In in) {
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            Integer id = Integer.parseInt(tokens[0]);
            String[] words = tokens[1].split(" ");
            HashSet<String> synset = new HashSet<String>();
            for (String word : words) {
                synset.add(word);
                allNouns.add(word);
                if (nounToSetOfSynsetID.containsKey(word)) {
                    nounToSetOfSynsetID.get(word).add(id);
                } else {
                    HashSet<Integer> synsetIDs = new HashSet<Integer>();
                    synsetIDs.add(id);
                    nounToSetOfSynsetID.put(word, synsetIDs);
                }
            }
            synsetIDToSetOfNoun.put(id, synset);
        }
        hyponymGraph = new Digraph(synsetIDToSetOfNoun.size());
    }

    private void readHyponymsFromIn(In in) {
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            Integer hypernymID = Integer.parseInt(tokens[0]);
            for (String idstr : tokens) {
                Integer id = Integer.parseInt(idstr);
                if (id.equals(hypernymID)) {
                    continue;
                } else {
                    hyponymGraph.addEdge(hypernymID, id);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> set = new HashSet<String>();
        set.add(word);
        if (nounToSetOfSynsetID.containsKey(word)) {
            Set<Integer> synsetIDs = nounToSetOfSynsetID.get(word);
            Set<Integer> hyponymIDs = GraphHelper.descendants(hyponymGraph, synsetIDs);
            for (Integer hyponymID : hyponymIDs) {
                addAllWordsBySynsetID(hyponymID, set);
            }
        }
        // else no hyponyms found
        return set;
    }

    private void addAllWordsBySynsetID(Integer synsetID, Set<String> set) {
        if (synsetIDToSetOfNoun.containsKey(synsetID)) {
            HashSet<String> synonyms = synsetIDToSetOfNoun.get(synsetID);
            for (String synonym : synonyms) {
                set.add(synonym);
            }
        }
        // else no synset found
    }
}
