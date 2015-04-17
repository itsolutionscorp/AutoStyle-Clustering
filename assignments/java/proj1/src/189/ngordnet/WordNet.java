package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class WordNet {

    /* Map from SynsetID to the corresponding word(s). */
    private HashMap<Integer, String> intToString;
    /* The almighty digraph. */
    private Digraph graph;
    /* Map from SynsetID that has more than one corresponding word
     * to a list of those very words. */
    private HashMap<Integer, ArrayList<String>> repeats;
    /* Set of all words. */
    private Set<String> allWords;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        intToString = new HashMap<Integer, String>();
        repeats = new HashMap<Integer, ArrayList<String>>();
        allWords = new HashSet<String>();
        In in = new In(synsetFilename); 
        while (in.hasNextLine()) {
            String all = in.readLine();
            String[] temp = all.split(",");
            intToString.put(Integer.parseInt(temp[0]), temp[1]);
            if (temp[1].split(" ").length > 1) { //if the SynsetID has > 1 word referring to it.
                ArrayList<String> repeatWords = new ArrayList<String>();
                for (int a = 0; a < temp[1].split(" ").length; a++) {
                    allWords.add(temp[1].split(" ")[a]);
                    repeatWords.add(temp[1].split(" ")[a]);
                }
                repeats.put(Integer.parseInt(temp[0]), repeatWords);
            } else {
                allWords.add(temp[1]);
            } 
        }

        graph = new Digraph(intToString.size());
        In hIn = new In(hyponymFilename);
        while (hIn.hasNextLine()) {
            String allHypo = hIn.readLine();
            String[] temp2 = allHypo.split(",");
            int initial = Integer.parseInt(temp2[0]);
            for (int x = 1; x < temp2.length; x++) {
                int index = Integer.parseInt(temp2[x]);
                graph.addEdge(initial, index);
            }
        }
    }

    

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) { 
        return allWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allWords;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> nums = new HashSet<Integer>();
        /* Adding all the synsetIDs that contain WORD to a set. */
        for (Integer i: intToString.keySet()) {
            if (Arrays.asList(intToString.get(i).split(" ")).contains(word)) {
                nums.add(i);
            }
        }
        Set<Integer> hypos = GraphHelper.descendants(graph, nums);
        Set<String> wordHyponyms = new HashSet<String>();
        for (Integer index: hypos) {
            /* Sees if the hyponym is just one word or more than one. 
             * If more than one, then all words in the hyponym are added.
             * These are stored in the REPEATS hashmap. */
            int entryLength = intToString.get(index).split(" ").length;
            if (entryLength == 1) {
                wordHyponyms.add(intToString.get(index));
            } else {
                wordHyponyms.addAll(repeats.get(index));
            }
        }
        return wordHyponyms;
    }
}
