package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    
    private Map<Integer, String[]> synset = new HashMap<Integer, String[]>();
    private Map<Integer, String> definition = new HashMap<Integer, String>();
    private Map<String, Set<Integer>> wordToID = new HashMap<String, Set<Integer>>();
    private Digraph net;
    private Set<String> allUniqueNouns = new HashSet<String>();
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        String[] synsetLines = new In(synsetFilename).readAllLines();
        
        /** Turn each line of document into synset
          * Make synset
          * Make definition
          * Make wordToID */
        for (String line : synsetLines) {
            String[] splitLine = line.split(",");
            String[] synsetWords = splitLine[1].split(" ");
            int synsetID = Integer.parseInt(splitLine[0]);
            for (String word : synsetWords) {
                allUniqueNouns.add(word);
                
                if (!wordToID.containsKey(word)) {
                    Set<Integer> wordID = new HashSet<Integer>();
                    wordID.add(synsetID);
                    wordToID.put(word, wordID);
                } else {
                    Set<Integer> wordID = wordToID.get(word);
                    wordID.add(synsetID);
                    wordToID.put(word, wordID);
                }
            }
            
            synset.put(synsetID, synsetWords);
            definition.put(synsetID, splitLine[2]);
        }
        
        net = new Digraph(synsetLines.length);
        String[] hyponyms = new In(hyponymFilename).readAllLines();
        
        /** Make hypo-/hypernym relations out of synsets */
        for (String line : hyponyms) {
            String[] relations = line.split(",");
            int hypernym = Integer.parseInt(relations[0]);
            
            for (int i = 1; i < relations.length; i++) {
                net.addEdge(hypernym, Integer.parseInt(relations[i]));
            }
            
        }
    }

   
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allUniqueNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allUniqueNouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> res = new HashSet<String>();
        Set<Integer> hyponyms = new HashSet<Integer>();
        hyponyms = GraphHelper.descendants(net, wordToID.get(word));
        for (Integer i : hyponyms) {
            for (String synsetWord : synset.get(i)) {
                res.add(synsetWord);
            }
        } 
        return res;
    }
    
}
