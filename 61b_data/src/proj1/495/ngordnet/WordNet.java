package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
    
public class WordNet {
    private Digraph wordNet; //numbers only
    private int digraphSize = 0; //set in makeMap()
    private HashMap<Integer, HashSet<String>> parseSynsets; //numbers and synsets
    private HashMap<String, String> definitions; // stores definitions
    
    
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        File synsetData = new File(synsetFilename);
        File hyponymData = new File(hyponymFilename);
        
        //Fill in parseSynsets
        parseSynsets = new HashMap<Integer, HashSet<String>>(); //initialize the hashmap
        definitions = new HashMap<String, String>();
        makeMap(synsetData);
        
        //Create the diagraph
        wordNet = new Digraph(digraphSize); 
        makeDigraph(hyponymData);  
    } 
    
    private void makeMap(File x) {
        In stream = new In(x);
        while (stream.exists() && stream.hasNextLine()) {
            //diagraph size is equal to the number of lines in the synset file
            digraphSize = digraphSize + 1; 
            HashSet<String> nouns = new HashSet<String>();
            String line = stream.readLine();
            String[] lineSplit = line.split(",");
            String[] synsets = lineSplit[1].split(" "); //in case there are multiple entries
            String definition = lineSplit[2];
            for (int item = 0; item < synsets.length; item = item + 1) { //add each entry
                nouns.add(synsets[item]);
                definitions.put(synsets[item], definition);
            } //associate the number with the synsets
            parseSynsets.put(Integer.valueOf(lineSplit[0]), nouns);
            //reset nouns for next line 
            nouns = new HashSet<String>();
        }
    }
    
    private void makeDigraph(File x) {
        In stream = new In(x);
        while (stream.exists() && stream.hasNextLine()) {
            String line = stream.readLine();
            String[] lineSplit = line.split(",");
            int parent = Integer.valueOf(lineSplit[0]); //parent is first number
            for (int child = 1; child < lineSplit.length; child = child + 1) {
                wordNet.addEdge(parent, Integer.valueOf(lineSplit[child])); 
            }   
        }       
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (definitions.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return definitions.keySet();
    }   

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> returnSet = new HashSet<String>();
        
        if (definitions.containsKey(word)) {
            //get all the keys in parseSynset that has the word
            Set<Integer> keys = new HashSet<Integer>();
            //Iterating strategy via stackoverflow
            for (Entry<Integer, HashSet<String>> entry : parseSynsets.entrySet()) { 
                for (String w : entry.getValue()) {
                    if (w.equals(word)) {
                        keys.add(entry.getKey()); //add the keys to the arraylist
                    }
                }   
            }
            //add the synonyms
            for (int k : keys) {
                Object[] arrayWords = parseSynsets.get(k).toArray();
                for (Object a : arrayWords) {
                    returnSet.add((String) a); 
                }
            }
            //add the hyponyms
            Set<Integer> children = GraphHelper.descendants(wordNet, keys);
            for (int c : children) {
                Object[] arrayChildren = parseSynsets.get(c).toArray();
                for (Object child : arrayChildren) {
                    returnSet.add((String) child);
                }
            }
                         
        }
        return returnSet;
    }           
} 
