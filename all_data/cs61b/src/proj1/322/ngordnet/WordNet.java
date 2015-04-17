package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    private Digraph digraph;

    //Key: synset ID Value: HashSet of all words in the set
    private HashMap<Integer, HashSet<String>> synsets = new HashMap<Integer, HashSet<String>>();

    //nounIds -- Key: word Value: HashSet of synset IDs that contain word
    private HashMap<String, HashSet<Integer>> nounIDs = new HashMap<String, HashSet<Integer>>();

    private HashMap<Integer, HashSet<Integer>> hyponyms = new HashMap<Integer, HashSet<Integer>>();
    private HashSet<String> nouns = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            File file = new File(synsetFilename);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String curLine = input.nextLine();
                String[] idAndWords = curLine.split(","); //breaks line into [id, words, definition]

                HashSet<String> wordList = new HashSet<String>();

                //breaks words into [word1, word2, ...]
                String[] synWords = idAndWords[1].split(" ");
                Integer synID = Integer.parseInt(idAndWords[0]);
                for (String w : synWords) {
                    wordList.add(w); //puts synonyms into a new HashSet
                    nouns.add(w); //adds word to the list of nouns


                    //adds the ID of this synset to the list of IDs associated with w
                    if (nounIDs.containsKey(w)) {
                        nounIDs.get(w).add(synID);
                    } else {
                        HashSet<Integer> ids = new HashSet<Integer>();
                        ids.add(synID);
                        nounIDs.put(w, ids);
                    }
                }
                synsets.put(synID, wordList); //adds the synonyms with id as a key
            }

            file = new File(hyponymFilename);
            input = new Scanner(file);
            while (input.hasNextLine()) {
                String curLine = input.nextLine();
                String[] synHypSet = curLine.split(",");
                Integer synID = Integer.parseInt(synHypSet[0]);

                HashSet<Integer> hyps = new HashSet<Integer>();
                for (int i = 1; i < synHypSet.length; i++) { //puts hyponyms into separate array
                    hyps.add(Integer.parseInt(synHypSet[i]));
                }

                if (hyponyms.containsKey(synID)) {
                    for (Integer i : hyps) {
                        hyponyms.get(synID).add(i);
                    }
                } else {
                    hyponyms.put(synID, hyps); //adds hypIDs with synsetID as key
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An exception has been caught at line 61: " + e);
        }

        digraph = new Digraph(synsets.size());
        Set<Integer> keys = hyponyms.keySet();
        for (Integer synID : keys) { //iterates over every synnset that has a hyponym
            HashSet<Integer> hyps = hyponyms.get(synID); //retrieves the list of hyponyms for i
            for (Integer hypID : hyps) { //iterates over every hyponym for a given synset
                digraph.addEdge(synID, hypID);
            }
        }
    }

    //returns set of all hyponyms and synonyms of word
    //if word belongs to multiple synsets, return all hyponyms
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return new HashSet();
        }
        
        HashSet<Integer> synIDs = nounIDs.get(word); //list of IDs associated with word
        HashSet<String> toReturn = new HashSet<String>();

        for (Integer i : synIDs) {
            HashSet<String> synonyms = synsets.get(i); //synonyms of word
            for (String s : synonyms) {
                toReturn.add(s); //adds synonyms of word to toReturn
            }
        }

        Set<Integer> hyps = GraphHelper.descendants(digraph, synIDs);

        if (hyps.size() != 0) {
            for (Integer k : hyps) {
                //HashSet of each synonym for a given hyponym
                HashSet<String> hypSynonyms = synsets.get(k);
                for (String s : hypSynonyms) {
                    toReturn.add(s);
                }
            }
        }

        return toReturn;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }
}
