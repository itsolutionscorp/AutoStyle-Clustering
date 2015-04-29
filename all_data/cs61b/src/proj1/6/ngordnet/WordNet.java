package ngordnet;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.Digraph; //digraph implementation


    /** public Digraph() {
     * Creates a new Digraph with V vertices.
     * public Digraph(int V) {}

     * Adds an edge between vertex v and w.
     * public void addEdge(int v, int w) {}

     * more information at:
     * http://algs4.cs.princeton.edu/42directed/Digraph.java.html
     */

public class WordNet {

    // number of words in the digraph and map.
    private int numWords = 0;

    //input files
    private Scanner file1;
    private Scanner file2;

    //map that stores number values to the word(s) they represent
    private HashMap<Integer, HashSet<String>> numToWord;

    //Digraph of the connections between the words (in relation to their numbers)
    private Digraph dig;

    public WordNet(String f1, String f2) {
        try {
            file1 = new Scanner(new File(f1));
        } catch (FileNotFoundException e) {
            System.out.println("Could not read" + f1);
        }
        try {
            file2 = new Scanner(new File(f2));
            readSynset(file1);
            createDigraph(file2);
        } catch (FileNotFoundException e) {
            System.out.println("Could not read" + f2);
        }
    }

    /*read synsets and places them into a map associating word with number. */
    private boolean readSynset(Scanner file) {
        numToWord = new HashMap<Integer, HashSet<String>>();
        while (file.hasNextLine()) {
            numWords += 1;
            // read file into string
            String stringified = file.nextLine();
            // split the string into an array, divided by commas
            String[] splitString = stringified.split(",");
            //add hyponyms to a hashset
            int key = Integer.parseInt(splitString[0]);
            String[] stringVals = splitString[1].split("\\s+");
            HashSet<String> values = new HashSet<String>();

            for (String val: stringVals) {
                values.add(val);
            }

            //map the numbers to their corresponding hash set
            numToWord.put(key, values);
        }
        return true;
    }

    //create digraph from the hyponym file
    private void createDigraph(Scanner file) {
        dig = new Digraph(numWords);
        while (file.hasNextLine()) {
            String stringified = file.nextLine();
            String[] edges = stringified.split(",");
            int hypernym = Integer.parseInt(edges[0]);
            for (int i = 1; i < edges.length; i++) {
                dig.addEdge(hypernym, Integer.parseInt(edges[i]));
            }
        }
    }

    //returns the keys for a word if it exists, otherwise returns empty set
    private Set<Integer> locationCheck(String word) {
        Set<Integer> matchingKeys = new HashSet<Integer>();
        Set<Integer> keys = numToWord.keySet();

        for (int key: keys) {
            HashSet<String> currWords = numToWord.get(key);
            if (currWords.contains(word)) {
                matchingKeys.add(key);
            }
        }
        return matchingKeys;
    }


    /* returns if a word is a noun or not */
    //ALERT  NOT URE IF THIS IS THE WAY YOU ARE SUPPOSED TO IMPLEMENT IT
    public boolean isNoun(String word) {
        if (word == null) {
            return false;
        } else {
            return (!locationCheck(word).isEmpty());
        }
    }
    /*returns the first item in a list of nouns? (iterator??) of is this the other ADT? */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        Collection<HashSet<String>> temp = numToWord.values();
        for (HashSet<String> nounGroup: temp) {
            for (String word: nounGroup) {
                nouns.add(word);
            }
        }
        return nouns;
    }

    /* returns all the hyponyms of a specific word */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> locs = locationCheck(word);
            Set<Integer> descendants = new HashSet<Integer>();

            for (Integer numloc : locs) {
                Set<Integer> temp = GraphHelper.descendants(dig, locs);
                for (Integer wordloc : temp) {
                    descendants.add(wordloc);
                }
            }

            for (int number : descendants) {
                HashSet<String> temp2 = (numToWord.get(number));
                for (String x : temp2) {
                    hyponyms.add(x);
                }
            }
            return hyponyms;
        } else {
            throw new IllegalArgumentException("That word is not in our database.");
        }
    }
}


