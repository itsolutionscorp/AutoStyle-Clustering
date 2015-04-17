package ngordnet; import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/*
 * ADT's to be used to create WordNet:
 * TreeMap: KEY will represent the noun synsets as a STRING[]
 *          every VALUE will map to a Digraph of hyponym ID's
 *          
 * Digraph: ID representation of hyponyms
 * 
 */

public class WordNet {
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph shDigraph;  //keeps track of hyponyms
    private int digraphSize;
    private HashMap<Integer, String[]> id2str;  //maps every id to its string

    public WordNet(String synsetFilename, String hyponymFilename) {
        id2str = new HashMap<Integer, String[]>();
        
        //turn synsetFileName and hyponymFilename into readable files for StdIn
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        //create TreeMap id2str
        id2strCreator(synsets);
        
        //initialize Digraph HYPONYMMAP
        digraphSize = id2str.size();
        shDigraph = new Digraph(digraphSize);
        
        //map hyponyms to corresponding synsets
        shDigraph = hyponymCreator(shDigraph, hyponyms);
    }
    
    
  /** Put ID's of synsets in SYNSET as id2str.key, 
   *  words associated with each ID in SYNSET as id2str.value */
    private void id2strCreator(In synset) {
        while (!synset.isEmpty()) {
            String line = synset.readLine();
            String[] synsetLine = line.split(",");
            String synsetID = synsetLine[0];
            String[] synsetStr = synsetLine[1].split(" ");
            id2str.put(Integer.parseInt(synsetID), synsetStr);
        }
    }

    /** Assigns synset ID's to hyponym ID's in a Digraph */
    private Digraph hyponymCreator(Digraph d, In file) {
        while (!file.isEmpty()) {
            String line = file.readLine();
            String[] wordArray = line.split(",");
            int firstNum = Integer.parseInt(wordArray[0]);
            for (int i = 1; i < wordArray.length; i++) {
                d.addEdge(firstNum, Integer.parseInt(wordArray[i]));
            }
        }
        return d;
    }
    
    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (this.nouns().contains(noun)) {
            return true;
        } 
        return false;
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (int i = 0; i < id2str.size(); i++) {
            String[] singleNouns = id2str.get(i);
            for (String n : singleNouns) {
                allNouns.add(n);
            }
        } 
        return allNouns;
    }
    
    /** 
     * Returns set of Integer keys of a Hashmap<Integer, String> M 
     * whose value matches some String WORD
     * */
    private Set<Integer> keys(HashMap<Integer, String[]> m, String word) {
        boolean found = false;
        Set<Integer> key = new TreeSet<Integer>();

        for (int i = 0; i < id2str.size(); i++) {
            for (int j = 0; j < id2str.get(i).length; j++) {
                if (word.equals(id2str.get(i)[j])) {
                    key.add(i);
                    found = true;
                }
            }
        }
        if (found) {
            return key;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new TreeSet<String>();
        Set<Integer> hyponyms = GraphHelper.descendants(shDigraph, keys(id2str, word));
        for (Integer h : hyponyms) {
            for (String sh : id2str.get(h)) {
                allHyponyms.add(sh);
            }
        }
        return allHyponyms; 
    }
}
