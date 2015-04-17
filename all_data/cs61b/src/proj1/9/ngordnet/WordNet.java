package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
//import java.util.Digraph;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
//import java.io.*;


public class WordNet {
    private int size = 0;
    private HashMap<Integer, Set<String>> id2synsets;
    private HashMap<String, Set<Integer>> word2ids;
    private Digraph g;
  //HashSet<String> descHyponyms = new HashSet<String>();


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // read in the two files
        In givenSynset = new In(synsetFilename);
        In givenHyponym = new In(hyponymFilename);
        // creates string objects for reading through the file line by line. 
        //the first is for adding to the hashmaps.
        // second is for the digraph
        String parsedStringset;
        String parsedIntset;
        String parsedHyponym;
        /* 
         * two hashmaps are created. the first takes in the ID 
         * as key and the synonyms under that ID as a set.
         * the second hashmap takes in a noun as the key and stores 
         * all the IDs that contain that word
         * in the value as a set. 
         */
        id2synsets = new HashMap<Integer, Set<String>>();
        word2ids = new HashMap<String, Set<Integer>>();
        /*
         * while loop checks if each line is not null and assigns 
         * variable parsedStringset to each line every time
         * it iterates through the loop.
         */
        while ((parsedStringset = givenSynset.readLine()) != null) {
            
            String[] splitString = parsedStringset.split(",");
            
            int id = Integer.parseInt(splitString[0]);
            
            String[] splitString2 = splitString[1].split(" ");       
            
            HashSet<String> words = new HashSet<String>();
            for (String noun : splitString2) {
                words.add(noun);
            }
           
            id2synsets.put(id, words);
            size = size + 1; 
            for (String noun : words) {
                Set<Integer> values = word2ids.get(noun);
                if (values == null) {
                    HashSet<Integer> blah = new HashSet<Integer>();
                    blah.add(id);
                    word2ids.put(noun, blah);
                } else if (values != null) {
                    values.add(id);
                    word2ids.put(noun, values);
                }
            }
        }
        g = new Digraph(size);
        while ((parsedIntset = givenHyponym.readLine()) != null) {

            String[] idString = parsedIntset.split(",");
            int[] idInt = new int[idString.length];
            
            for (int i = 0; i < idString.length; i = i + 1) {
              
                String noun = idString[i];
                int eachId = Integer.parseInt(noun);
                idInt[i] = eachId;
                //g = new Digraph(size);
                if (i != 0) {
                    g.addEdge(idInt[0], idInt[i]);
                  
                }
            }
          
        }          
    } 

      /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return word2ids.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> allNouns = new HashSet<String>();
        for (String key : word2ids.keySet()) {          
            allNouns.add(key);
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> descHyponyms = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> listIDs = word2ids.get(word);         
            Set<Integer> finalIDs = GraphHelper.descendants(g, listIDs);          
            for (int key : finalIDs) {
                Set<String> listSynsets = id2synsets.get(key);            
                for (String key3 : listSynsets) {
                    descHyponyms.add(key3);
                }
            }
        }       
        return descHyponyms;
    }
}
