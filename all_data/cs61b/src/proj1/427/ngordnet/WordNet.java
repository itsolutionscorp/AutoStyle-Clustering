package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private HashMap<Integer, String> map = new HashMap(); // hashmap store id
                                                          // ---> set of words.
    private int size = 0; // size of this hash map
    private Digraph digraph;
    private Set<Integer> keyHolds; // find the keys of a word.

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In readIn = new In(synsetFilename); // stream on synsetFile
        In readInHypo = new In(hyponymFilename); // stream on hyponymFile
        String temp1 = "";
        String temp2 = "";
        String[] parts1;
        String[] parts2;
        while (!readIn.isEmpty()) {
            temp1 = readIn.readLine();
            parts1 = temp1.split(",");
            map.put(Integer.parseInt(parts1[0]), parts1[1]);
            size += 1; // update size
        }
        digraph = new Digraph(size);
        while (!readInHypo.isEmpty()) {
            temp2 = readInHypo.readLine();
            parts2 = temp2.split(",");
            int j = 0;
            for (int i = 1; i < parts2.length; i++) {
                digraph.addEdge(Integer.parseInt(parts2[j]),
                        Integer.parseInt(parts2[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in somze synset. */
    public boolean isNoun(String noun) {
        Set<String> allValue = nouns();
        return allValue.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        String[] hold;
        Set<String> set = new HashSet();
        for (int i = 0; i < size; i++) {
            hold = map.get(i).split(" "); // split words in synsets.
            for (int j = 0; j < hold.length; j++) { // loop synsets;
                if (!set.contains(hold[j])) { // solve dulplicate nouns
                    set.add(hold[j]);
                }
            }
        }
        return set;
    }

    // return the set of keys that has a word
    private Set findkeys(String word) {
        String[] hold1;
        keyHolds = new HashSet();
        // Store the keys that has the word into a set
        for (int i = 0; i < size; i++) {
            hold1 = map.get(i).split(" ");
            for (int j = 0; j < hold1.length; j++) {
                if (hold1[j].equals(word)) {
                    keyHolds.add(i);
                }
            }
        }
        return keyHolds;
    }

    // return the set of hyponyms of a word
    public Set<String> hyponyms(String word) {
        Set<Integer> allID = GraphHelper.descendants(digraph, findkeys(word));
        Set<String> hyponym = new HashSet();
        String[] words;
        Object[] idArray = allID.toArray();
        for (int i = 0; i < idArray.length; i++) {
            words = map.get(idArray[i]).split(" ");
            for (int j = 0; j < words.length; j++) {
                if (!hyponym.contains(words[j])) {
                    hyponym.add(words[j]);
                }
            }
        }
        return hyponym;
    }

}
