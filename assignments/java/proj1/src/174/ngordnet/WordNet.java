package ngordnet;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/**
 * WordNet Class
 * Created by gurpreet on 3/2/15.
 */

public class WordNet {

    private Map<Integer, SynNet> synNet = new HashMap<Integer, SynNet>();
    private Digraph wordNet;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);

        //Parse the synNet file and store it as a map of IDs
        for (String line : synFile.readAllLines()) {
            SynNet temp = new SynNet(line);
            synNet.put(temp.getId(), temp);
        }

        //Create wordNet
        wordNet = new Digraph(synNet.size());

        //Parse hypFile and create links between the synNets
        for (String line :  hypFile.readAllLines()) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                wordNet.addEdge(id, Integer.parseInt(parts[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (SynNet currSynNet : synNet.values()) {
            nouns.addAll(currSynNet.getWords());
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> keys = new HashSet<Integer>();
        Set<String> words = new HashSet<String>();

        //Get keys of all the synNets that contain WORD
        for (SynNet currSynNet : synNet.values()) {
            if (currSynNet.getWords().contains(word)) {
                keys.add(currSynNet.getId());
            }
        }

        //Add keys of all the hyponyms
        keys.addAll(GraphHelper.descendants(wordNet, keys));

        for (int key : keys) {
            words.addAll(synNet.get(key).getWords());
        }

        return words;
    }
}
