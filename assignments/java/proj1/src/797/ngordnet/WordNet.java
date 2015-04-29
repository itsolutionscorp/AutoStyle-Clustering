package ngordnet;
//import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import edu.princeton.cs.introcs.In;

public class WordNet {
    private Set<String> nounSet = new HashSet<String>(); // Set of Nouns in synsetFile
    // Keys: ID Vals: Set of Words
    private  HashMap<Integer, Set<String>> idWords = new HashMap<Integer, Set<String>>(); 
    // Keys: Word Vals: ID
    private  HashMap<String, Set<Integer>> wordsID = new HashMap<String, Set<Integer>>(); 
    // Keys: ID Vals: Set of Hyponyms
    private  HashMap<Integer, Set<Integer>> idHyps = new HashMap<Integer, Set<Integer>>(); 
    private  String currentLine; 
    private  int currentID;
    private  HashSet<Integer> currHypids;
    private  HashSet<String> currWords;
    private  HashSet<Integer> idSet;
    private  In synsetFile;
    private  In hyponymFile;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME.
    * Makes 3 Maps: idWords, wordsID, idHyps 
    * Map name is in format keyValues
    * Makes nounSet */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);

        /* Initializes idHyps Word Map =] */
        while (hyponymFile.hasNextLine()) { 
            currentLine = hyponymFile.readLine();
            /* gets rid of stuff that's not a digit, stores digits into array. 
            * src: http://stackoverflow.com/questions/8467241/
            * how-to-parse-multi-digit-integers-from-string */
            String[] array = currentLine.split("[^\\d]+"); 
            currentID = Integer.parseInt(array[0]); //sets ID to first int in array 
            currHypids = new HashSet<Integer>(); // new set of hyponyms corresponding to curr ID
            for (int i = 1; i < array.length; i++) {
                // puts the rest of the ints in the Hyponym set 
                currHypids.add(Integer.parseInt(array[i])); 
            }
            idHyps.put(currentID, currHypids); // adds the ID and Hyp set to the Map 
        }

        /* Initializes idWords and wordsID Word Maps */
        while (synsetFile.hasNextLine()) {
            currentLine = synsetFile.readLine();
            String[] array = currentLine.split(","); //splits currentLine by commas 
            currentID = Integer.parseInt(array[0]);
            String[] wordsarray = array[1].split(" "); //splits string of words by spaces
            currWords = new HashSet<String>();

            for (int i = 0; i < wordsarray.length; i++) {
                currWords.add(wordsarray[i]); // puts the words into currWords set 
                /* Checks to see if any IDs are already associated with the Word*/
                if (wordsID.containsKey(wordsarray[i])) { // adds IDs already associated 
                    idSet = new HashSet<Integer>(wordsID.get(wordsarray[i]));
                } else { // otherwise creates a new empty idSet
                    idSet = new HashSet<Integer>(); 
                }  
                idSet.add(currentID); // adds current id 
                wordsID.put(wordsarray[i], idSet); // puts the word and it's idSet in the map 
            }
            idWords.put(currentID, currWords); // puts the id and the words in the map  
        }

        nounSet = wordsID.keySet();
    }


     /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      * 
      * Strategy : get id(s) of word from wordsID
      *     get hyponym id(s) of word id(s) from idHyps
      *     get words of the hyponym id(s) from idWords 
      */

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            //gets ids of word
            idSet = new HashSet<Integer>(wordsID.get(word)); 
            // iterator of word's ids
            Iterator<Integer> idIter = idSet.iterator(); 
            //set of ids of hyponyms of word's ids
            currHypids = new HashSet<Integer>();
            while (idIter.hasNext()) { //looking @ each the word's ids 
                currentID = idIter.next();
                 // id under speculation 

                /* TRYING 2 get SYNONYMS */
                Iterator<String> synonymIter = idWords.get(currentID).iterator();
                while (synonymIter.hasNext()) {
                    currWords.add(synonymIter.next());
                }
                /*END TRY */
                if (idHyps.get(currentID) != null) { // makes sure that there are hyponyms of id
                    // iterates currentID's set of hyponymIDs
                    Iterator<Integer> curr = idHyps.get(currentID).iterator(); 
                    while (curr.hasNext()) { // while there are more hyponym IDs 
                        currHypids.add(curr.next()); // add them to our list of hyponym IDs
                    }
                }
                
            }
            if (currHypids.isEmpty()) {
                return new HashSet<String>(); //returns empty set if there aren't any hyponyms
            }
            Iterator<Integer> hypidIter = currHypids.iterator();
             // iterator over all the hyponymIDs
            currWords = new HashSet<String>(); // set to hold the corresponding words
            while (hypidIter.hasNext()) { // looking @ each hyponym id
                currentID = hypidIter.next(); // hyponym id under speculation 
                // iterator over this hypID's words
                Iterator<String> curr = idWords.get(currentID).iterator(); 
                while (curr.hasNext()) { //while there are more words in the set 
                    currWords.add(curr.next()); //add them to our list of all the words 
                }
            }
            return currWords;
        } else {
            throw new IllegalArgumentException("That isn't a noun");
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nounSet.contains(noun); 
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.nounSet;
    }


}
