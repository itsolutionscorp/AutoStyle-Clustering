package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private Digraph _relations; // Digraph containing the hypernym relations of synsets
    private Synset[] _synsets;  // List of noun synsets indexed by synset id

    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymnFilename) {
        // Instantiation
        In synsetScanner = new In(synsetFilename);
        List<String> synsetLines = new ArrayList<String>();
        while (synsetScanner.hasNextLine()) {
            synsetLines.add(synsetScanner.readLine());
        }
        _relations = new Digraph(synsetLines.size());
        _synsets = new Synset[synsetLines.size()];

        // Storing synonymn sets to _synsets
        for (String line : synsetLines) {
            String[] vals = line.split(",");
            _synsets[Integer.parseInt(vals[0])] = new Synset(vals[2]);
            String[] synonyms = vals[1].split(" ");
            for (String synonym : synonyms) {
                _synsets[Integer.parseInt(vals[0])].addSyn(synonym);
            }
        }

        // Adding hyponym relations to _relations
        In hyponymScanner = new In(hyponymnFilename);
        while (hyponymScanner.hasNextLine()) {
            String[] ids = hyponymScanner.readLine().split(",");
            for (int i = 1; i < ids.length; i += 1) {
                _relations.addEdge(Integer.parseInt(ids[0]), Integer.parseInt(ids[i]));
            }
        }
    }

    /* Below is ADT for a Synset, a set of noun synonyms with a definition */
    private class Synset {
        private Set<String> _syns; // Set of nouns in synset
        private String _def;       // String of shared definition of nouns in synset

        /* @PARAM definition is the string of shared definition of nouns
         * Constructor for ADT of Synset */
        private Synset(String definition) {
            _syns = new HashSet<String>();
            _def = definition;
        }

        /* Inserts SYNONYM into set */
        private void addSyn(String synonym) {
            _syns.add(synonym);
        }

        /* @Return if WORD is a member of this synset */
        private boolean hasNoun(String word) {
            return (_syns.contains(word));
        }

        /* @Return a List of nouns in this synset */
        private List<String> getSynsList() {
            List<String> synsList = new ArrayList<String>();
            for (String noun : _syns) {
                synsList.add(noun);
            }
            return synsList;
        }

        /* @Return shared definition string of nouns in this synset */
        private String getDef() {
            return _def;
        }
    }

    /* @PARAM noun is a given String
     * @Return if noun is a noun synset in this WordNet */
    public boolean isNoun(String noun) {
        for (Synset s : _synsets) {
            if (s.hasNoun(noun)) {
                return true;
            }
        }
        return false;
    }

    /* @Return a Set of the noun synsets in this WordNet */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Synset s : _synsets) {
            for (String n : s.getSynsList()) {
                nouns.add(n);
            }
        }
        return nouns;
    }

    /* @Return the set of all hyponyms of WORD as well as all synonyms of WORD. 
     * If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
     * Does not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<String> result = new HashSet<String>();

            // Finds the indexes of all synsets containing word
            Set<Integer> indices = new HashSet<Integer>();
            for (int i = 0; i < _synsets.length; i += 1) {
                if (_synsets[i].hasNoun(word)) {
                    indices.add(i);
                    // Adds synonyms to result
                    List<String> synonyms = _synsets[i].getSynsList();
                    for (String syn : synonyms) {
                        result.add(syn);
                    }
                }
            }

            // Adds all hyponyms to result
            Set<Integer> hypoIndices = GraphHelper.descendants(_relations, indices);
            for (int i : hypoIndices) {
                List<String> hyponyms = _synsets[i].getSynsList();
                for (String hyn : hyponyms) {
                    result.add(hyn);
                }
            }

            return result;
        }
        return new HashSet<String>(); // No hyponyms or synonyms if not in synsets
    }

    /* public static void main(String[] args) {
     *    // Is this method required by the javadocs??
     * } */
}
