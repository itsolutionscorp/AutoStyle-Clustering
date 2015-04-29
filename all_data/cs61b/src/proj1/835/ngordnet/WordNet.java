package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private In synsetFile;  // an instance of In class that read the synset file
    private In hyponymFile; // an instance of In class that read the hyponym file
    private Digraph hyponym; // an Dipraph that stores all hyponyms, for reference
    private Map<Integer, Set<String>> synset; // an Map class that map an id to a Set of nouns
    private Set<String> allWords;            // a Set of all the nouns
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);
        initSynset();
        initHyponym();        

    }
    
    /* Initialize a Digraph that stores all the ids and edges from hyponymFile */
    private void initHyponym() {
        hyponym = new Digraph(synset.size());
        String[] allLine = hyponymFile.readAllLines();
        for (String line: allLine) {
            String[] rawTokens = line.split(",");
            int hyperid = Integer.parseInt(rawTokens[0]);
            
            for (int i = 1; i < rawTokens.length; i++) {
                int hypoid = Integer.parseInt(rawTokens[i]);
                hyponym.addEdge(hyperid, hypoid);
            }
        }
        
    }
    
    /* initialize a HashMap synset that take id as key and set of nouns as value 
     * at the same time create a Set of all word*/
    
    private void initSynset() {
        synset = new HashMap<Integer, Set<String>>();
        allWords = new HashSet<String>();
        String[] allLine = synsetFile.readAllLines();
        for (String line: allLine) {
            Set<String> val = new HashSet<String>();
            String[] rawTokens = line.split(",");
            String[] words = rawTokens[1].split(" ");
            int id = Integer.parseInt(rawTokens[0]);
            for (String word: words) {
                val.add(word);
                if (!allWords.contains(word)) {
                    allWords.add(word);
                }
            }
            synset.put(id, val);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (allWords.contains(noun)) {
            return true;
        }
        return false;
        
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allWords;
        
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) { 
        Set<Integer> hyperWordid = new HashSet<Integer>();
        Set<String>  hypoWord = new HashSet<String>();
        for (int id: synset.keySet()) {
            if (synset.get(id).contains(word)) {
                hyperWordid.add(id);
            }
        }
        Set<Integer> hypoWordid = GraphHelper.descendants(hyponym, hyperWordid);
        
        for (int id: hypoWordid) {
            for (String x: synset.get(id)) {
                hypoWord.add(x);
            }
        }
        return hypoWord;
    }
}

