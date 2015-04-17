package ngordnet; 

import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException; 

public class WordNet {

    private Digraph digraph; 
    private int vertices; 
    private Set<String[]> synset; 
    private Set<int[]> hyponym;
    
    
    /** Creates a WordNet using files from synsetFilename and hypernymFilename. **/
    public WordNet(String synsetFilename, String hyponymFilename) {

        synset = new HashSet<String[]>(); // Will just hold the entire line of info.
        File synsetfile = new File(synsetFilename);
        try {
            Scanner synsetscanned = new Scanner(synsetfile);
            while (synsetscanned.hasNextLine()) {
                String synsetstring = synsetscanned.nextLine();
                String[] synsetarr = synsetstring.split(",");
                synset.add(synsetarr); 
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The synset file is not valid.");
        } 
        

        vertices = synset.size(); // # of synsets should be # of vertices.
        digraph = new Digraph(vertices); 


        hyponym = new HashSet<int[]>();
        File hyponymfile = new File(hyponymFilename); 
        try {
            Scanner hyponymscanned = new Scanner(hyponymfile);
            while (hyponymscanned.hasNextLine()) {
                String hyponymInts = hyponymscanned.nextLine();
                String[] hyponymStringArray = hyponymInts.split(",");
                int[] hyponymArray = new int[hyponymStringArray.length];
                for (int i = 0; i < hyponymStringArray.length; i = i + 1) {
                    try { 
                        // Create int from string.
                        int element = Integer.parseInt(hyponymStringArray[i]);
                        // Add to our int[].
                        hyponymArray[i] = element;
                    } catch (NumberFormatException nf) {
                        System.out.println("Problem in hyponym file - an element is not an int.");
                    }       
                }
                hyponym.add(hyponymArray);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The hyponym file is not valid.");
        }

        // Hyponym set made, now iterate through and start mapping.
        Iterator<int[]> iter = hyponym.iterator(); 
        while (iter.hasNext()) {
            int[] hypRelations = iter.next();
            for (int i = 1; i < hypRelations.length; i = i + 1) {
                // First is synset; rest are offspring.
                digraph.addEdge(hypRelations[0], hypRelations[i]);
            }
        }
    }

    /* Returns true if noun is a word in some synset. */
    public boolean isNoun(String noun) {
        Iterator<String[]> synIter = this.synset.iterator();
        while (synIter.hasNext()) {
            String[] synArray = synIter.next();
            String[] actualWords = synArray[1].split(" ");
            for (int i = 0; i < actualWords.length; i = i + 1) {
                if (actualWords[i].equals(noun)) {
                    return true; 
                }
            }
        }
        return false; 
    }

    /* Returns set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns; 
        allNouns = new HashSet<String>(); 
        Iterator<String[]> synIter = this.synset.iterator();
        while (synIter.hasNext()) {
            String[] synArray = synIter.next();
            String[] words = synArray[1].split(" ");
            for (int i = 0; i < words.length; i = i + 1) {
                allNouns.add(words[i]);
            }
        }
        return allNouns; 
    }


    /* Returns the set of all hyponyms of word including word itself. */
    public Set<String> hyponyms(String word) {

        Set<String> allHyponyms = new HashSet<String>();
        Iterator<String[]> synIter = this.synset.iterator();
        // First figure out all synset IDs of word.
        while (synIter.hasNext()) {
            String[] synArray = synIter.next();
            String[] words = synArray[1].split(" "); 
            for (int i = 0; i < words.length; i = i + 1) {
                if (words[i].equals(word)) {
                    // Get the synset ID.
                    int synID = Integer.parseInt(synArray[0]); 
                    // Get self + synonyms.
                    Set<String> words2 = this.words(synID);
                    for (String addWord : words2) {
                        allHyponyms.add(addWord);
                    }
                    // Then find all descendents, get their words, and store. 
                    Set<Integer> allChildrenIDs = this.allSynIDs(synID);
                    for (int synIDs : allChildrenIDs) {
                        Set<String> addTheseWords = this.words(synIDs);
                        for (String addWords : addTheseWords) {
                            allHyponyms.add(addWords);
                        }
                    }
                }
            }
        }
        allHyponyms.add(word); 
        return allHyponyms; 
    }


    /* Returns ALL the synset IDs (descendents) need to take in, given your original synset ID. */
    private Set<Integer> allSynIDs(int synID) {
        Set<Integer> allchildren = new HashSet<Integer>(); 
        Iterator<int[]> hypIter = this.hyponym.iterator();
        while (hypIter.hasNext()) {
            int[] hypArray = hypIter.next();
            if (hypArray[0] == synID) {
                int[] hypIDs = Arrays.copyOfRange(hypArray, 0, hypArray.length);
                for (int j = 0; j < hypIDs.length; j = j + 1) {
                    allchildren.add(hypIDs[j]);
                }
                // Now find the subchildren - children of synonyms.
                for (int k = 1; k < hypIDs.length; k = k + 1) {
                    // Start at 1 because already found the children of yourself.
                    Set<Integer> subchildren = this.allSynIDs(hypIDs[k]);
                    // Have to take all one by one into allchildren.
                    for (Integer subchild : subchildren) {
                        allchildren.add(subchild);
                    }
                }
            }
        }
        return allchildren;
    }


    /* Returns the words given the syn ID. */
    private Set<String> words(int synID) {
        Set<String> words = new HashSet<String>();
        Iterator<String[]> synIter2 = this.synset.iterator();
        while (synIter2.hasNext()) {
            String[] synArray2 = synIter2.next();
            String[] words2 = synArray2[1].split(" ");
            int checkSynID = Integer.parseInt(synArray2[0]);
            if (checkSynID == synID) {
                // Found right synset, take in words.
                for (int k = 0; k < words2.length; k = k + 1) {
                    words.add(words2[k]);
                }
            }
        }
        return words;
    }


/* Major thanks to dude at office hours.
    * Whose name I never figured out.
    * His Macbook said Soham but there's no Soham on the CS61B staff page.
    * He Spent 2 hours of his life googling weird java errors and explaining how to fix them.
    * Thanks bruh. */
} 
