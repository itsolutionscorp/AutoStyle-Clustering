package ngordnet;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/** An object that stores the WordNet graph 
 *  in a manner useful for extracting all hyponyms of a word. */
public class WordNet { 
    private HashMap<Integer, HashSet<String>> synsetsByID;
    private HashMap<String, HashSet<Integer>> synsetsByNoun;
    private Digraph wordNet;
    
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetsByID = new HashMap<Integer, HashSet<String>>();
        synsetsByNoun = new HashMap<String, HashSet<Integer>>();
        
        try {
            processSynsetFile(synsetFilename);
            wordNet = new Digraph(synsetsByID.size());
            processHyponymFile(hyponymFilename);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("WordNet configuration aborted.");
            System.exit(1);
        }
    }
    
    /** Processes the synset file specified by SYNSET_FILENAME. */
    private void processSynsetFile(String synsetFilename) {
        In synsetStream = new In(synsetFilename);
        if (!synsetStream.exists()) {
            throw new IllegalArgumentException("Synset file could not be opened");
        }
        
        String line;
        String[] synsetData = new String[3]; // There should be 3 data components per line
        
        while (synsetStream.hasNextLine()) {
            line = synsetStream.readLine();
            if (!line.matches("\\d+,.+,.+")) { 
                throw new IllegalArgumentException("Invalid synset file");
            }
            
            // Split line into its individual synset data components
            synsetData = line.split(",");
            // Split synset into individual nouns
            String[] synonyms = synsetData[1].split(" ");
            
            HashSet<Integer> idSet;
            HashSet<String> nounSet = new HashSet<String>();
            
            for (String noun : synonyms) {
                nounSet.add(noun);
                
                if (isNoun(noun)) {
                    idSet = synsetsByNoun.get(noun);
                    idSet.add(Integer.parseInt(synsetData[0]));
                } else {
                    idSet = new HashSet<Integer>();
                    idSet.add(Integer.parseInt(synsetData[0]));
                    synsetsByNoun.put(noun, idSet);
                }
            }
            
            synsetsByID.put(Integer.parseInt(synsetData[0]), nounSet);
        }
        
        synsetStream.close();
    }
    
    /** Processes the hyponyms file specified by HYPONYM_FILENAME. */
    private void processHyponymFile(String hyponymFilename) {
        In hyponymStream = new In(hyponymFilename);
        if (!hyponymStream.exists()) {
            throw new IllegalArgumentException("Hyponym file could not be opened");
        }
        
        String line;
        
        while (hyponymStream.hasNextLine()) {
            line = hyponymStream.readLine();
            String[] hyponymData = line.split(",");
            Integer hypernymID = Integer.parseInt(hyponymData[0]);
            
            for (int i = 1; i < hyponymData.length; i++) {
                wordNet.addEdge(hypernymID, Integer.parseInt(hyponymData[i]));
            }
        }
        
        hyponymStream.close();
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        if (!synsetsByNoun.containsKey(word)) {
            return new HashSet<String>(); // If word is not in the database, return an empty set
        }
        
        HashSet<Integer> synsetIDs = synsetsByNoun.get(word);
        Set<Integer> hyponymIDs = GraphHelper.descendants(wordNet, synsetIDs);
        
        HashSet<String> hyponymSet = new HashSet<String>();
        // Include all synonyms of WORD
        for (Integer id : synsetIDs) { 
            hyponymSet.addAll(synsetsByID.get(id));
        }
        // Include all hyponyms of WORD
        for (Integer id : hyponymIDs) {
            hyponymSet.addAll(synsetsByID.get(id));
        }
        
        return hyponymSet;
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetsByNoun.containsKey(noun);
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetsByNoun.keySet();
    }
}
