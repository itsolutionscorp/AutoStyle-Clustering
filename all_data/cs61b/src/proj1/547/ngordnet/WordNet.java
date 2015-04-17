package ngordnet;

//@author Gabriel Gardner
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;



public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, Synset> wn;
    private Digraph graph;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        wn = new HashMap<Integer, Synset>();
        In hyponymFile = new In(hyponymFilename);
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] vals = line.split(",");
            String[] words = vals[1].split(" ");
            ArrayList<String> synWords = new ArrayList<String>(Arrays.asList(words));
            Synset syn = new Synset(synWords, new ArrayList<Integer>());
            wn.put(Integer.parseInt(vals[0]), syn);
        }
        while (hyponymFile.hasNextLine()) {
            String hypLine = hyponymFile.readLine();
            String[] nums = hypLine.split(",");
            ArrayList<Integer> hyps = wn.get(Integer.parseInt(nums[0])).hyponyms;
            for (Integer i = 1; i < nums.length; i++) {
                hyps.add(Integer.parseInt(nums[i]));
            }    
        }
        hyponymFile.close();
        synsetFile.close();   
        graph = new Digraph(wn.size());    
        for (Map.Entry<Integer, Synset> entry : wn.entrySet()) {
            for (Integer hyp : entry.getValue().hyponyms) {
                graph.addEdge(entry.getKey(), hyp);
            }
        }       
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        //taken from http://stackoverflow.com/questions/46898/iterate-over-each-entry-in-a-map
        for (Map.Entry<Integer, Synset> entry : wn.entrySet()) {
            if (entry.getValue().words.contains(noun)) {
                return true;
            }
        }               
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounsSet = new HashSet<String>();
        for (Map.Entry<Integer, Synset> entry : wn.entrySet()) {
            ArrayList<String> nounList = entry.getValue().words;
            for (Integer i = 0; i < nounList.size(); i++) {
                nounsSet.add(nounList.get(i));
            }       
        }               
        return nounsSet;

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> wordSet = new HashSet<String>();
        Set<Integer> keys = new HashSet<Integer>();
        for (Map.Entry<Integer, Synset> entry : wn.entrySet()) {
            if (entry.getValue().words.contains(word)) {
                keys.add(entry.getKey());
            }
        }
        Set<Integer> hypoIndexes = GraphHelper.descendants(graph, keys);
        for (Integer hyp : hypoIndexes) {
            for (String hyponym : wn.get(hyp).words) {
                wordSet.add(hyponym);
            }
        }
        return wordSet;
    }

    //Data structure of two ArrayLists to represent Synset
    private class Synset {
        private ArrayList<String> words;
        private ArrayList<Integer> hyponyms;
    
        public Synset(ArrayList<String> w, ArrayList<Integer> h) {
            words = w;
            hyponyms = h;            
        }
    }
}
