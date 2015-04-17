package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import java.util.ArrayList;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> synMap;
    private Digraph hypGraph;
    private HashSet nouns;
    private int size;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset, hyponym;
        synset = new In(synsetFilename); //need to create file?
        hyponym = new In(hyponymFilename);
        
        synMap = new HashMap();
        nouns = new HashSet();
        size = 0;

      // each element in synMap is of key: Integer syn#, value: String[] words
        while (!synset.isEmpty()) {
            String l = synset.readLine();
            String[] tokens = l.split(",");
            //CITED: googled how to do split w/ regex
            ArrayList<String> syns = new ArrayList<String>(Arrays.asList(tokens[1].split("[ ]+")));
            synMap.put(Integer.valueOf(tokens[0]), syns);
            addNouns(syns);
            size++;
        }

        hypGraph = new Digraph(size);

        // draws edges FROM synset TO hyponyms
        while (!hyponym.isEmpty()) {
            String l = hyponym.readLine();
            int[] nums = strToInts(l);
            int s = nums[0];
            for (int i = 1; i < nums.length; i++) {
                hypGraph.addEdge(s, nums[i]);
            }
        }

    }

    private void addNouns(ArrayList<String> words) {
        for (String n : words) {
            nouns.add(n);
        }
    }

    private int[] strToInts(String line) {
        String[] strs = line.split(",");
        int[] ints = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            ints[i] = Integer.parseInt(strs[i]);
        }

        return ints;
    }

    /* Returns true if NOUN is a word in some synset. */
    // isNoun iff word is found in
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!nouns.contains(word)) {
            throw new IllegalArgumentException("word is not in this wordnet!");
        }
        

        Set<Integer> inds = new HashSet<Integer>();
        for (Integer i : synMap.keySet()) {
            if (synMap.get(i).contains(word)) {
                inds.add(i);
            }
        }
        
        HashSet<String> h = new HashSet<String>();
        //CITED: Thong Dinh found the code for descendants
        Set<Integer> hIndex = GraphHelper.descendants(hypGraph, inds);
        for (Integer i : hIndex) {
            ArrayList<String> s = synMap.get(i);
            for (String w: s) {
                h.add(w);
            }
        }
        return h;

    }

}
