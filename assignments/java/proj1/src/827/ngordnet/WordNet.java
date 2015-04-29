package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private Map<String, Entry> holdEntries;
    private Map<Integer, Entry> accessInts;
    private int size;
    private Digraph connections;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        //Uses the file locations to initialize In instances of the files themselves
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename); 

        /*Initializes holdEntries (a map) and accessInts (a map)*/
        holdEntries = new HashMap<String, Entry>();
        accessInts = new HashMap<Integer, Entry>();

        /* Puts all of the words in the synsetFile into data structures
         * To Do:
         ***      create entries of synsets
         ***      add entries of synsets to each map OR 
         ***       update the current entry by linking it to the new one and updating the value
         */
        readSynset(synsetFile);

        /*Create a digraph to understand the relationships between the many words in your hashmap */
        readHyponym(hyponymFile);
    }

    private void readHyponym(In file) {
        connections = new Digraph(this.size);

        while (file.hasNextLine()) {                                                    
            String line = file.readLine();                                              
            //Get the current line
            
            String[] hypConnections = line.split(",");                                 
            //Split it by commas, into a bunch of numbers

            int[] hypConnectionsInts = new int[hypConnections.length];               
            //Creates an int array that is the same length as the string array

            for (int i = 0; i < hypConnections.length; i++) {
                hypConnectionsInts[i] = Integer.parseInt(hypConnections[i]);
                //Gets the synsets from the rest of the line
            }

            int bigPapa = hypConnectionsInts[0];                                      
            //The big papa is the "top" link. All following numbers are subcategories of it

            for (int number : hypConnectionsInts) {
                if (bigPapa != number) {
                    connections.addEdge(bigPapa, number);                               
                    //Connects the bigPapa to all his children in the digraph 
                }
            }
        }
    }

    private void readSynset(In file) {
        while (file.hasNextLine()) {                                                    
        //While the file is not empty

            String line = file.readLine();                                              
            //Get the current line

            int index = Integer.parseInt(line.split(",")[0]);                           
            //Get the synsetID
            
            String syns = line.split(",")[1];                                           
                
            String[] individualWords = syns.split(" ");                                
            //Gets each individual word in the synset
            
            Entry singleEntry;
            
            for (String word : individualWords) {           
                if (isNoun(word))  {                                                     
                    //If the word is already in the dictionary...
                    singleEntry = new Entry(index, syns, holdEntries.get(word));       
                    //Link whatever's already the value to this and make it the new value
                } else {
                    singleEntry = new Entry(index, syns, null);
                }

                holdEntries.put(word, singleEntry);
            }

            accessInts.put(index, new Entry(index, syns, null));                        
            //Puts an entry in the integer accessible map as well

            this.size += 1;
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return holdEntries.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return holdEntries.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> setID = new HashSet<Integer>();

        Entry pointer = holdEntries.get(word);
        while (pointer != null) {                        
        //Adds all the synID's of the given word (multiple usages of it will all be linked together)
            setID.add(pointer.getID());
            pointer = pointer.getNext();
        }    
        //Uses GraphHelper to map the descendants of the given set of ID's
        Set<Integer> descendants = GraphHelper.descendants(connections, setID); 

        //For each ID in the set of descendants, add the words of the synset to the eventual return
        Set<String> hypoSet = new HashSet<String>();
        for (int id : descendants) {
            Entry pointer2 = accessInts.get(id);
            for (String indivWord : pointer2.getSynsetWords()) {
                hypoSet.add(indivWord);
            }
        }
        return hypoSet;
    }                   

    private class Entry {
    
        public Entry(int synID, String syns, Entry n) {
            synsetID = synID;
            synset = syns;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */

        public String getSynset() {
            return synset;
        }

        public String[] getSynsetWords() {
            return synset.split(" ");
        }

        public Entry getNext() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public int getID() {
            return synsetID;
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private int synsetID;
        /** Stores the value of the key-value pair of this node in the list. */
        private String synset;
        /** Stores the next Entry in the linked list. */
        private Entry next;
    }
}   
