package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> idToWordMap;
    private HashMap<String, ArrayList<Integer>> wordToIDMap;
    private Digraph wordGraph;
    private int size;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /* if synset file contains
         * 0,action,dummy
         * ...
         * 4,jump parachuting,dummy
         * then
         * idToWordMap.get(0) is [action]
         * idToWordMap.get(4) is [jump, parachuting]
         */
        idToWordMap = new HashMap<Integer, ArrayList<String>>();
        In synsetData = new In(synsetFilename);
        size = 0;
        wordToIDMap = new HashMap<String, ArrayList<Integer>>();
        
        while (synsetData.hasNextLine()) {
            String line = synsetData.readLine();
            String[] rawTokens = line.split(",");
            int id = Integer.parseInt(rawTokens[0]);
            ArrayList<String> synset = new ArrayList<String>();
            String[] synsetTokens = rawTokens[1].split(" ");
            for (String s: synsetTokens) {
                synset.add(s);
                if (wordToIDMap.containsKey(s)) {
                    wordToIDMap.get(s).add(id);
                } else {
                    ArrayList<Integer> wordID = new ArrayList<Integer>();
                    wordID.add(id);
                    wordToIDMap.put(s, wordID);
                }

            }
            idToWordMap.put(id, synset);
            size += 1;
        }       
        
        wordGraph = new Digraph(size);
        In hyponymData = new In(hyponymFilename);
        
        while (hyponymData.hasNextLine()) {
            String line = hyponymData.readLine();
            String[] hyponyms = line.split(",");
            int id = Integer.parseInt(hyponyms[0]);
            for (int i = 1; i < hyponyms.length; i++) {
                int child = Integer.parseInt(hyponyms[i]);
                wordGraph.addEdge(id, child);
            }
            
        }
        
        
        
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (wordToIDMap.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> nounSet = new TreeSet<String>();
        for (String s: wordToIDMap.keySet()) {
            nounSet.add(s);
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (wordToIDMap.containsKey(word)) {
            Set<Integer> wordIDs = new TreeSet<Integer>();
            Set<Integer> hyponymIDs = new TreeSet<Integer>();
            Set<String> hyponyms = new TreeSet<String>();
            for (Integer id: wordToIDMap.get(word)) {
                wordIDs.add(id);
            }
    
            hyponymIDs = GraphHelper.descendants(wordGraph, wordIDs);
    
//          for (Integer id: wordIDs) {
//              for (String synonym: idToWordMap.get(id)) {
//                  hyponymIDs.addAll(wordToIDMap.get(synonym));
//              }
//              
//          }
            for (Integer id: hyponymIDs) {
                for (String hyponym: idToWordMap.get(id)) {
                    hyponyms.add(hyponym);
                }
            }
            return hyponyms;
        }
        System.out.println("No word");
        return null;
    }
}
