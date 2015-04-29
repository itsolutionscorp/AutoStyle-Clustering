/*
 * WordNet.Java
 * 
 * Version 1.0
 *
 * March 6, 2015
 * 
 * Copyright notice Daniel Mockaitis 
 */

package ngordnet;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<Integer, ArrayList<String>> idkeyedToWords; // maps id to words
    private HashMap<String, ArrayList<Integer>> wordKeyedToIds; // maps word to ids 
    private Digraph dgraph; 

    public WordNet(String synsetFilename, String hyponymFilename) {

        String[] rawTokens; String line; int id; int sizeD = 0;     // variables
        wordKeyedToIds = new HashMap<String, ArrayList<Integer>>(); 
        idkeyedToWords = new HashMap<Integer, ArrayList<String>>(); 
        In synsetFile = new In(synsetFilename);     // file inputs using Princeton API IN
        In hyponymFile = new In(hyponymFilename); 
        while (synsetFile.hasNextLine()) {
            sizeD++; 
            line = synsetFile.readLine();
            rawTokens = line.split(",");    // raw Tokens = line split up by comma.
            id = Integer.parseInt(rawTokens[0]);        // first value is the id.
            ArrayList<String> words = new ArrayList<String>();
            ArrayList<Integer> ids = new ArrayList<Integer>();
            ids.add(id);
            for (String w: rawTokens[1].split(" ")) {  //split up words of the line by space
                words.add(w);  // list of words from line
                if (wordKeyedToIds.containsKey(w)) {
                    wordKeyedToIds.get(w).add(id);  // if word in maps, add another id
                } else { 
                    ArrayList<Integer> newList = new ArrayList<Integer>(ids);
                    wordKeyedToIds.put(w, newList);     
                }
            }
            idkeyedToWords.put(id, words); 
        }
        dgraph = new Digraph(sizeD);
        while (hyponymFile.hasNextLine()) {
            line = hyponymFile.readLine();
            rawTokens = line.split(",");
            id = Integer.parseInt(rawTokens[0]);
            for (int i = 0; i < rawTokens.length; i++) {
                dgraph.addEdge(id, Integer.parseInt(rawTokens[i]));
            }
        }
    }

    //Returns true if NOUN is a word in some synset
    public boolean isNoun(String noun) {
        if (wordKeyedToIds.containsKey(noun)) {
            return true;
        }
        return false;
    }


    //Returns the set of all nouns
    public Set<String> nouns() {
        return wordKeyedToIds.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See  for an example.
    * Do not include hyponyms of synonms http://goo.gl/EGLoysyms. */
    public Set<String> hyponyms(String word) {
        Set<String> retValue = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> parentsID = new HashSet<Integer>();
            for (Integer i: wordKeyedToIds.get(word)) {
                parentsID.add(i);
            }
            Set<Integer> hyponyms = GraphHelper.descendants(dgraph, parentsID);
            for (Integer i: hyponyms) { 
                for (String s: idkeyedToWords.get(i)) { 
                    retValue.add(s);
                }
            }
            return retValue; 
        }
        return null;
    }
}


