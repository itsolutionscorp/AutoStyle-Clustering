package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, String[]> idWords = new HashMap<>();
    private HashMap<Integer, Set<Integer>> idHyponyms = new HashMap<>();
    private Digraph wordNetGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        File synsetsfile = new File(synsetFilename);
        File hyponymsfile = new File(hyponymFilename);
        In synsfile = new In(synsetsfile);
        In hypsfile = new In(hyponymsfile);
        int numsynsets = 0;
        String[] synsetArray;
        String[] wordsArray;
        while (synsfile.hasNextLine()) {
            synsetArray = synsfile.readLine().split(",");
            wordsArray = synsetArray[1].split(" ");
            idWords.put(Integer.parseInt(synsetArray[0]), wordsArray);
            numsynsets += 1;
        }
        wordNetGraph = new Digraph(numsynsets);
        String[] hyperHyponymsArray;
        while (hypsfile.hasNextLine()) {
            hyperHyponymsArray = hypsfile.readLine().split(",");
            Set<Integer> hyponymsSet = new HashSet<>();
            for (int i = 0; i < hyperHyponymsArray.length; i++) {
                if (i != 0) {
                    hyponymsSet.add(Integer.parseInt(hyperHyponymsArray[i]));
                }
            }
            if (idHyponyms.containsKey(Integer.parseInt(hyperHyponymsArray[0]))) {
                for (int i: idHyponyms.get(Integer.parseInt(hyperHyponymsArray[0]))) {
                    hyponymsSet.add(i); 
                } 
            } 
            idHyponyms.put(Integer.parseInt(hyperHyponymsArray[0]), hyponymsSet);  
        }
        for (int i: idHyponyms.keySet()) {
            for (int j: idHyponyms.get(i)) {
                wordNetGraph.addEdge(i, j);
            }
        }
    }

    public boolean isNoun(String word) {
        for (int id: idWords.keySet()) {
            for (String noun: idWords.get(id)) {
                if (noun.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nounsSet = new HashSet<String>();
        for (int id: idWords.keySet()) {
            for (String noun: idWords.get(id)) {
                nounsSet.add(noun);
            }
        }
        return nounsSet;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> hypernymsSet = new HashSet<Integer>();
        for (int id: idWords.keySet()) {
            for (String noun: idWords.get(id)) {
                if (noun.equals(word)) {
                    hypernymsSet.add(id);
                } 
            }
        }
        Set<Integer> descendantsSet = new HashSet<Integer>();
        descendantsSet = GraphHelper.descendants(wordNetGraph, hypernymsSet);
        Set<String> descendantsWordsSet = new HashSet<String>();
        for (Integer descendantid: descendantsSet) {
            for (String descendantword: idWords.get(descendantid)) {
                descendantsWordsSet.add(descendantword);
            }
        }
        return descendantsWordsSet; 
    }   
}
