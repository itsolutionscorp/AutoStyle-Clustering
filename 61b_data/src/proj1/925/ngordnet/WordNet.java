package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private Digraph graph;
    private ArrayList<ArrayList<String>> synsets;
    private HashMap<String, HashSet<Integer>> wordToId;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        wordToId = new HashMap<String, HashSet<Integer>>();
        In synsetIn = new In(synsetFilename);
        String line;
        String[] temp;
        
        synsets = new ArrayList<ArrayList<String>>();
        ArrayList<String> synset;
        while (synsetIn.hasNextLine()) {
            line = synsetIn.readLine();
            temp = line.split(",")[1].split(" ");
            synset = new ArrayList<String>();
            for (int i = 0; i < temp.length; i++) {
                synset.add(temp[i]);
                if (!wordToId.containsKey(temp[i])) {
                    wordToId.put(temp[i], new HashSet<Integer>());
                }
                wordToId.get(temp[i]).add(synsets.size());
            }
            synsets.add(synsets.size(), synset);
        }
        
        graph = new Digraph(synsets.size());
        
        In hyponymIn = new In(hyponymFilename);
        int hyper;
        while (hyponymIn.hasNextLine()) {
            line = hyponymIn.readLine();
            temp = line.split(",");
            hyper = Integer.parseInt(temp[0]);
            for (int i = 1; i < temp.length; i++) {
                graph.addEdge(hyper, Integer.parseInt(temp[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToId.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToId.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {  
        HashSet<String> result = new HashSet<String>();
        if (wordToId.get(word) != null) {
            for (int descendant : GraphHelper.descendants(graph, wordToId.get(word))) {
                result.addAll(synsets.get(descendant));
            }
            for (int id : wordToId.get(word)) {
                result.addAll(synsets.get(id));
            }
        }
        return result;
    }
}
