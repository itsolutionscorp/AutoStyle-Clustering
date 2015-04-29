/** WordNet class stores a digraph of synsets and their hyponyms.

@author Leo Steinmetz **/
package ngordnet;


import edu.princeton.cs.algs4.Digraph;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class WordNet {
    // Constructor for WordNet class; takes two text files
    // The first text file is a list of numbert synsets
    // The next text file is a list of associated hyponyms

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordList = new ArrayList<Set<String>>();
        synsets = new LinkedHashMap<String, Set<Integer>>();
        try {
            readSynset(synsetFilename); 
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        d = new Digraph(wordList.size());
        try {
            readHyponym(hyponymFilename); 
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }

    // to use in the constructor: assembles the synsets
    // credit to a stackoverflow answer to "how to read a large text file line by line..."
    private void readSynset(String synsetFilename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(synsetFilename));
        String line;
        int counter = 0;
        while ((line = br.readLine()) != null) {
            String synString = line.split(",")[1];
            String[] synArray = synString.split(" ");
            Set<String> synSet = new LinkedHashSet<String>(Arrays.asList(synArray));
            wordList.add(synSet);
            for (int i = 0; i < synArray.length; i++) {
                if (!synsets.containsKey(synArray[i])) {
                    synsets.put(synArray[i], new LinkedHashSet<Integer>());
                }
                synsets.get(synArray[i]).add(counter);
            }
            counter += 1;
        }
    }

    // to use in the constructor: assembles the hyponyms
    private void readHyponym(String hyponymFilename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(hyponymFilename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] numbers = line.split(",");
            int base = Integer.parseInt(numbers[0]);
            for (int i = 1; i < numbers.length; i++) {
                d.addEdge(base, Integer.parseInt(numbers[i]));
            }
        }
    }


    // Returns true if noun is a noun in our list of words
    public boolean isNoun(String noun) {
        return synsets.keySet().contains(noun);
    }

    // Returns all the nouns in our list of words
    public Set<String> nouns() {
        return synsets.keySet();
    }


    // Returns the hyponyms of a given word
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null; 
        }
        Set<String> hypos = new LinkedHashSet<String>();
        for (Integer synset : synsets.get(word)) {
            hypos.addAll(wordList.get(synset));
            hypos.addAll(deepHyponyms(synset));
        }
        return hypos;
    }


    // Helps with getting hyponyms
    private Set<String> deepHyponyms(Integer synset) {
        Set<String> hypos = new LinkedHashSet<String>();
        for (Integer hyponym : d.adj(synset)) {
            hypos.addAll(wordList.get(hyponym));
            hypos.addAll(deepHyponyms(hyponym));
        }
        return hypos;
    }

    // Digraph keeps track of relationships between word numbers
    private Digraph d;

    // ArrayList keeps track of the synsets;
    private ArrayList<Set<String>> wordList;

    // Map keeps track of numeical bindings;
    private Map<String, Set<Integer>> synsets;


}
