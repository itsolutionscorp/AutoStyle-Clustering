package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class WordNet {
    // Digraph of synsets, and their hyponyms
    private Digraph synHyp;
    // map containing every noun, and all it's indicies
    private Map<String, HashSet<Integer>> synInd = new HashMap<String, HashSet<Integer>>();
    // map containing every index, and all the corresponding nouns
    private Map<Integer, HashSet<String>> synonyms = new HashMap<Integer, HashSet<String>>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        // Makes 2 maps of synsets: one with each words and all its indexes, 
        //and one with each index and all its words
        In in = new In(synsetFilename);
        String[] S = in.readAllLines();
        for (int i = 0; i < S.length; i++) {
            HashSet<String> syn = new HashSet<String>();
            HashSet<Integer> indexes = new HashSet<Integer>();
            String[] comma = S[i].split(",");
            int id = Integer.parseInt(comma[0]);
            String[] syns = comma[1].split(" ");
            indexes.add(id);
            for (int k = 0; k < syns.length; k++) {
                syn.add(syns[k]);
                if (synInd.containsKey(syns[k])) {
                    HashSet<Integer> tmp = synInd.get(syns[k]);
                    indexes.addAll(tmp);
                } 
                synInd.put(syns[k], indexes);
            }     
            synonyms.put(id, syn);
        }
        // makes digraph
        synHyp = new Digraph(synonyms.keySet().size());
        int b = synonyms.keySet().size();
        in = new In(hyponymFilename);
        while (!in.isEmpty()) {
            String args = in.readString();
            String[] words = args.split(",");
            int[] num = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                num[i] = Integer.parseInt(words[i]);
            }
            for (int i = 1; i < num.length; i++) {
                synHyp.addEdge(num[0], num[i]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synInd.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synInd.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        // get all indexes extending word, and word itself
        HashSet<Integer> id = synInd.get(word);
        Set<String> words = new HashSet<String>();
        Set<Integer> indicies = new HashSet<Integer>();
        indicies.addAll(GraphHelper.descendants(synHyp, id));
        indicies.addAll(id);
        Object[] iter = indicies.toArray();
        Set<String> hyps = new HashSet<String>();
        for (int n = 0; n < indicies.size(); n++) {
            hyps.addAll(synonyms.get(iter[n]));
        }
        return hyps;
    }
}


