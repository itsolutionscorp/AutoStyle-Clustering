
package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


/*
Author: Won Park
Version: 3/6/15  1:06
*/

public class WordNet {

    private HashMap<Integer, String> synsetList = new HashMap<Integer, String>();
    private HashMap<Integer, String[]> hyponymList = new HashMap<Integer, String[]>();
    private int sizeSynsetList;
    private int sizehyponymList;
    private Digraph digraph;
    private ArrayList<Integer> keyHyponymList = new ArrayList<Integer>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);    //Reads in the files
        In hyponymFile = new In(hyponymFilename);


        /* Reading synset files*/
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine(); //Returns the line as a String

            if (line == null) {  //break out of the looop when there is nothing else to read
                break;
            }

            /*Parsing through the string*/
            String delims = "[,]";            //Credit for this code goes to cs.wisc.edu
            String[] tokens = line.split(delims);
            synsetList.put(Integer.parseInt(tokens[0]), tokens[1]);
        }

        sizeSynsetList = synsetList.size();  //Updates size of each list
        digraph =  new Digraph(sizeSynsetList); //Creates new diagraph


        /*****Puts in values in hyponymFile *******/
        while (hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();

            if (line == null) {  //break out of the looop when there is nothing else to read
                break;
            }

            /*Parsing through the string*/
            String delims = "[,]";            //Credit for this code goes to cs.wisc.edu
            String[] tokens = line.split(delims);

            String[] values = Arrays.copyOfRange(tokens, 1, tokens.length);     

            for (int i = 0; i < values.length; i++) {
                digraph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(values[i]));
            }

            hyponymList.put(Integer.parseInt(tokens[0]), values);
            keyHyponymList.add(Integer.parseInt(tokens[0]));
        }

        sizehyponymList = synsetList.size();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < sizeSynsetList; i++) {
            String synset = synsetList.get(i);
            String delims = "[ ]";      //Splits the synset looking for individual words
            String[] tokens = synset.split(delims);

            for (int j = 0; j < tokens.length; j++) {
                if (tokens[j].equalsIgnoreCase(noun)) {
                    return true;
                }
            }
        }   

        return false;  
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> allNouns = new HashSet<String>();

        for (int i = 0; i < sizeSynsetList; i++) {
            String synset = synsetList.get(i);
            String delims = "[ ]";      //Splits the synset looking for individual words
            String[] tokens = synset.split(delims);

            for (int j = 0; j < tokens.length; j++) {
                if (!allNouns.contains(tokens[j])) {
                    allNouns.add(tokens[j]);
                }
            }
        }   
        return allNouns;
    }



   /*Given a word..finds all IDs in which that word exists*/
    private HashSet<Integer> findID(String word) {
        HashSet<Integer> iDs = new HashSet<Integer>();

        for (int i = 0; i < sizeSynsetList; i++) {
            String synset = synsetList.get(i);
            String delims = "[ ]";      //Splits the synset looking for individual words
            String[] tokens = synset.split(delims);

            for (int j = 0; j < tokens.length; j++) {
                if (tokens[j].equalsIgnoreCase(word) && !iDs.contains(i)) { 
                    iDs.add(i);
                }
            }
        }   

        return iDs;
    }
   /*Given an iD and a word, find all the words  the given ID*/
    private ArrayList<String> findWords(int iD) {
        ArrayList<String> words = new ArrayList<String>();
        String synset = synsetList.get(iD);
        String delims = "[ ]";      //Splits the synset looking for individual words
        String[] tokens = synset.split(delims);

        for (int j = 0; j < tokens.length; j++) {
            if (!words.contains(tokens[j])) { 
                words.add(tokens[j]);
            }
        }
        return words;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponyms = new HashSet<String>();

        HashSet<Integer> targetID = findID(word); //Finds all instances of the word 
        Set<Integer> hyponymValues = GraphHelper.descendants(digraph, targetID);
        Iterator<Integer> iter = hyponymValues.iterator();
        
        while (iter.hasNext()) {
            int key = iter.next();
            ArrayList<String> words = findWords(key);

            for (int i = 0; i < words.size(); i++) {
                if (!hyponyms.contains(words.get(i))) {
                    hyponyms.add(words.get(i));
                }
            }
        }
        return hyponyms;
    }
}
