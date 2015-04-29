package ngordnet;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private String hypernymFilenames;
    private String synsetFilenames;
    private Hashtable<Integer, String> numberToWords = new Hashtable<Integer, String>();
    private int countLines;

    // Counts the number of lines in the file
    private void getLines() {
        In synsetIn = new In(synsetFilenames);
        while (!synsetIn.isEmpty()) {
            synsetIn.readLine();
            countLines++;
        }
    }

    private Digraph wordnetDigraph = new Digraph(countLines);

    public WordNet(String synsetFilename, String hypernymFilename) {
        this.synsetFilenames = synsetFilename;
        this.hypernymFilenames = hypernymFilename;
        // Puts all words and numbers in a hashtable
        mapNumberToWords();
        // Gets how many lines (or words) we have
        getLines();
        /*
         * Makes a digraph by using the number of lines as the number of
         * vertices and then loops over the file and adds an edge between the
         * first number and every other number on the line
         */
        Digraph wordnetdigraph = new Digraph(countLines);
        In gamma = new In(hypernymFilename);
        while (!gamma.isEmpty()) {
            String tracker = gamma.readLine();
            // Got idea from Princeton Standard to use a scanner
            // Used stack overflow to figure out how to set a
            // delimiter (this applies to every scanner I wrote)
            Scanner s = new Scanner(tracker).useDelimiter(", *");
            int epsilon = s.nextInt();
            while (s.hasNext()) {
                wordnetdigraph.addEdge(epsilon, s.nextInt());
            }
        }
        this.wordnetDigraph = wordnetdigraph;

    }

    // Makes the hashtable so that the words correspond to their numbers
    /*
     * This is done by first reading the line and then making a scanner which
     * delimits with commas in order to get the number of the synset as well as
     * all words used to define the definition of the word. Then I make another
     * scanner in order to separate each definition and hash the string of words
     * to that particular number
     */
    private void mapNumberToWords() {
        Hashtable<Integer, String> number2words = new Hashtable<Integer, String>();
        In delta = new In(synsetFilenames);
        while (!delta.isEmpty()) {
            String tracker = delta.readLine();
            Scanner s = new Scanner(tracker).useDelimiter(", *");
            int epsilon = s.nextInt();
            String allPossibleDefinitions = s.next();
            number2words.put(epsilon, allPossibleDefinitions);

        }
        this.numberToWords = number2words;

    }

    private boolean keyContainsWord(int key, String word) {
        // Determine whether a given number has a particular word
        // In its list of words
        String epsilon = numberToWords.get(key);
        Scanner s = new Scanner(epsilon);
        while (s.hasNext()) {
            if (s.next().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        Enumeration<Integer> e = numberToWords.keys();
        while (e.hasMoreElements()) {
            String keysOfElement = numberToWords.get(e.nextElement());
            Scanner individualWords = new Scanner(keysOfElement);
            while (individualWords.hasNext()) {
                nouns.add(individualWords.next());
            }
        }
        return nouns;

    }

    public Set<String> hyponyms(String word) {
        // Make a set of all hyponyms
        HashSet<String> hyponymsSet = new HashSet<String>();
        // Make a set of all numbers which contain the key of my word
        HashSet<Integer> numbers = new HashSet<Integer>();
        for (Integer keyToIterateOver : numberToWords.keySet()) {
            int temp = keyToIterateOver.intValue();
            if (keyContainsWord(temp, word)) {
                // add the number to my set
                numbers.add(temp);
            }
        }
        // Got idea from reading Piazza to use the digraph I had built, previously 
        // I made Depth First Search to do hyponyms
        Set<Integer> allHyponymNumbers = GraphHelper.descendants(wordnetDigraph, numbers);
        for (Integer numberToIterateOver : allHyponymNumbers) {
            String allHyponymsOnLine = numberToWords.get(numberToIterateOver);
            Scanner scanHyponyms = new Scanner(allHyponymsOnLine);
            while (scanHyponyms.hasNext()) {
                hyponymsSet.add(scanHyponyms.next());
            }
        }
        return hyponymsSet;
    }

}
