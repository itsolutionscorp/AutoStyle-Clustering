package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashSet;


/** An object that stores the WordNet graph in a manner 
 *  useful for extracting all hyponyms of a word.
 *  @author Simon Maude
 */
public class WordNet {
    private HashMap<Integer, ArrayList<String>> synData;
    private TreeMap<Integer, ArrayList<Integer>> hypData;
    private HashSet<String> hyponymsSet = new HashSet<String>();
    private HashSet<Integer> completed = new HashSet<Integer>();
    private boolean eject = false; 
    private boolean ejectTest = false; 
    private int ejectLen; 
    private String ejectWord; 
        

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        /* Process Synset Data into HashMap ADT */
        In synFile = new In(synsetFilename);
        synData = new HashMap<Integer, ArrayList<String>>();
        // read in the data from csv file - adapted from Princeton 
        // library: http://algs4.cs.princeton.edu/35applications/LookupCSV.java
        while (synFile.hasNextLine()) {
            String line = synFile.readLine();
            String[] synTokens = line.split(",");
        // add data to Map-value Arraylist    
            int synID = Integer.parseInt(synTokens[0]);
            ArrayList<String> lst = new ArrayList<String>();
            lst.add(synTokens[1]);
            lst.add(synTokens[2]);
            synData.put(synID, lst);
        }

        /* Process Hyponym Data into TreeMap ADT */
        In hypFile = new In(hyponymFilename);
        hypData = new TreeMap<Integer, ArrayList<Integer>>();
        // read in the data from csv file - adapted from Princeton 
        // library: http://algs4.cs.princeton.edu/35applications/LookupCSV.java
        while (hypFile.hasNextLine()) {
            String line = hypFile.readLine();
            String[] hypTokens = line.split(",");
            int synID = Integer.parseInt(hypTokens[0]);
        // add data to Map-value ArrayList
            if (hypData.containsKey(synID)) {
                ArrayList<Integer> hypLst = hypData.get(synID);
                for (int i = 1; i < hypTokens.length; i += 1) {
                    hypLst.add(Integer.parseInt(hypTokens[i]));
                }
            } else {
                ArrayList<Integer> hypLst = new ArrayList<Integer>();
                for (int i = 1; i < hypTokens.length; i += 1) {
                    hypLst.add(Integer.parseInt(hypTokens[i]));
                }
                hypData.put(synID, hypLst);
            }
        }            
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> nounSet = new HashSet<String>();
        nounSet = nouns(); 
        if (nounSet.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all multiple synonyms separated. */
    private Set<String> separateNouns(Set<String> input) {
        // creates output set
        HashSet<String> separateSet = new HashSet<String>(); 
        // picks every word(s) from synData 
        for (String output : input) {
            // Accounts for multiple word synsets
            if (output.contains(" ")) {
                multipleMeanings(output, separateSet);
            // if not multiple, just adds it 
            } else {
                separateSet.add(output);
            }
        }
        return separateSet;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounsSet = new HashSet<String>();
        for (int i = 0; i < synData.size(); i += 1) {
            nounsSet.add(synData.get(i).get(0));
        }
        return separateNouns(nounsSet);
    }

    private void multipleMeanings(String words, Set<String> set) {
        String[] multipleMeanings = words.split(" ");
        if (ejectTest) {
            if (words.matches(ejectWord)) {
                eject = false;
            }
            for (int i = 0; i < multipleMeanings.length; i += 1) {
                String mm = multipleMeanings[i]; 
                if (mm.matches(ejectWord)) {
                    eject = false;
                }      
            }
        }        
        if (!eject) {
            for (int i = 0; i < multipleMeanings.length; i += 1) {
                set.add(multipleMeanings[i]);      
            }
        }
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */ 
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> idsToGet = new ArrayList<Integer>();
        ArrayList<Integer> nounsToGet = new ArrayList<Integer>();
            
        // Scan synonyms for instances of word and collect synIds of all occurences
        for (int i = 0; i < synData.size(); i += 1) {
            String testing = synData.get(i).get(0);
        //// protect against infinite loop when recursively calling 
            if (!completed.contains(i)) {  
                if (testing.contains(word)) {
        //// if there are multiple words in the synonym, 
        //// split them up and add them to Returned Set. Also   
        //// protect against "event" and "natural_event" being considered the same
                    if ((testing.contains(" ")) || (testing.contains("_"))) { 
                        ejectWord = word;
                        ejectTest = true;
                        eject = true;
                        multipleMeanings(testing, hyponymsSet);
                        if (!eject) {
                            idsToGet.add(i);
                            completed.add(i);
                        }
                        eject = false;
                        ejectTest = false;
        //// add individual words to the Returned Set            
                    } else if (testing.matches(word)) {
                        idsToGet.add(i);
                        completed.add(i);
                        hyponymsSet.add(word);
                    }
                }
            }
        }
        // Get the corresponding synonym word IDs from the hyponym Data
        // set if they are present there    
        for (int h = 0; h < idsToGet.size(); h += 1) {
            int currentId = idsToGet.get(h);
            if (hypData.containsKey(currentId)) {
                ArrayList<Integer> hypDataValues = hypData.get(currentId);
                for (int t : hypDataValues) {
                    nounsToGet.add(t);
                }
            }      
        }
        // Add the hyponym nouns
        for (int k = 0; k < nounsToGet.size(); k += 1) {
        //// Check for recursive hyponym calls
            Set<String> recursives; 
            int currentId = nounsToGet.get(k);   
            String currentWord = synData.get(currentId).get(0);
            if (hypData.containsKey(currentId)) {
                recursives = hyponyms(currentWord);
                for (String p : recursives) {
                    hyponymsSet.add(p);
                }
            } else {
        //// add the nouns that aren't recursive
        ////// if it's multiple words, split them up and add them to 
            // the Returned Set
                if (currentWord.contains(" ")) { 
                    multipleMeanings(currentWord, hyponymsSet);
        ////// if not, add the word to the Returned Set
                } else {
                    hyponymsSet.add(currentWord);
                }
            }
        }   
        HashSet<String> temp = hyponymsSet;
        hyponymsSet = new HashSet<String>();
        completed = new HashSet<Integer>();
        return temp;
    }
}
