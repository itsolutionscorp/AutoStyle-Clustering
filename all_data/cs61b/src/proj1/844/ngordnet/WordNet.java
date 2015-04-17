package ngordnet; 

import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In;

import java.util.Arrays; 
import java.util.TreeMap; 
import java.util.Set; 
import java.util.TreeSet; 

/** 
 * An object that stores the WordNet graph in a manner useful 
 * for extracting all hyponyms of a word.
 * @author William Pan
 *
 * Citations: I consulted Java documentation for String's 
 * split method,  Arrays's copyOfRange method, and general 
 * TreeMap & TreeSet operations.
 */

public class WordNet {

    private TreeMap<String, TreeSet<Integer>> wordMap; 
    private TreeMap<Integer, String[]> idMap; 

    private Digraph relationships; 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        wordMap = new TreeMap<String, TreeSet<Integer>>(); 
        idMap = new TreeMap<Integer, String[]>(); 

        In synsetIn = new In(synsetFilename); 
        String[] synsetLines = synsetIn.readAllLines(); 
        for (String sl : synsetLines) {
            String[] synsetFields = sl.split(","); 
            Integer id = Integer.parseInt(synsetFields[0]); 
            String synset = synsetFields[1]; 
            
            String[] words = synset.split(" "); 
            for (String w : words) {
                if (wordMap.get(w) == null) {
                    TreeSet<Integer> idList = new TreeSet<Integer>();
                    idList.add(id);
                    wordMap.put(w, idList); 
                } else {
                    wordMap.get(w).add(id); 
                } 
            }
            idMap.put(id, words); 
        }

        relationships = new Digraph(synsetLines.length); 

        In hyponymIn = new In(hyponymFilename); 
        String[] hyponymLines = hyponymIn.readAllLines();
        for (String hl : hyponymLines) {
            String[] hyponymFields = hl.split(","); 
            Integer hypernym = Integer.parseInt(hyponymFields[0]); 
            String[] hyponyms = Arrays.copyOfRange(hyponymFields, 1, hyponymFields.length); 
            for (String hyp : hyponyms) {
                relationships.addEdge(hypernym, Integer.parseInt(hyp)); 
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String s : wordMap.keySet()) {
            if (s.equals(noun)) {
                return true; 
            }
        }
        return false; 
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordMap.keySet(); 
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new TreeSet<String>(); 
        Set<Integer> ids = wordMap.get(word);

        Set<Integer> hyponymIds = GraphHelper.descendants(relationships, ids); 
        for (Integer hid : hyponymIds) {
            String[] hyponyms = idMap.get(hid); 
            for (String s : hyponyms) {
                hyponymSet.add(s); 
            }
        }
        return hyponymSet; 
    }
}
