package ngordnet;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<String, Set<Integer>> wordMap; // Idea of Using String first
                                               // provided by Hubert Jung
    private Digraph hypoGraph;

    public WordNet(String synsets, String hyponyms) {
        wordMap = new HashMap<String, Set<Integer>>();
        In synsetIn = new In(synsets);
        int count = 0; // Counts number of lines (Used in hyponyms section)
        while (synsetIn.hasNextLine()) {
            count += 1; // Increases Line Count
            String line = synsetIn.readLine(); // Reading one line at a time
            String[] numNounDum = line.split(","); // Creates an array of the
                                                   // words in the line
            String[] nouns = numNounDum[1].split(" "); // Creates an array of
                                                       // nouns
            for (String noun : nouns) {
                HashSet<Integer> id = new HashSet<Integer>();
                id.add(Integer.parseInt(numNounDum[0])); // Creates a Set with
                                                         // the given ID
                if (wordMap.containsKey(noun)) { // Adds the new ID if one
                                                 // existed for the word
                    wordMap.get(noun).add(Integer.parseInt(numNounDum[0]));
                } else {
                    wordMap.put(noun, id);
                } // Adds the <Noun, ID> pair to the map
            }
        }

        hypoGraph = new Digraph(count);
        In hyponymIn = new In(hyponyms); // Beginning the hyponyms relations
        while (hyponymIn.hasNextLine()) {
            String hypoLine = hyponymIn.readLine(); // Reading one line at a
                                                    // time
            String[] relations = hypoLine.split(","); // Creates an array of the
                                                      // integers in the line
            for (int i = 1; i < relations.length; i += 1) {
                hypoGraph.addEdge(Integer.parseInt(relations[0]), Integer.parseInt(relations[i]));
                // Adds edges for hyponyms
            }
        }
    }

    public boolean isNoun(String word) {
        return wordMap.containsKey(word);
    }

    public Set<String> nouns() {
        return wordMap.keySet();
    }

    public Set<String> hyponyms(String target) {
        HashSet<String> hypoList = new HashSet<String>();
        if (wordMap.containsKey(target)) {
            Set<Integer> hypoIDs = GraphHelper.descendants(hypoGraph, wordMap.get(target));
            for (String word : wordMap.keySet()) { // Goes through every
                                                   // possible key
                for (int hypo : wordMap.get(word)) { // Checks each ID for each
                                                     // key
                    if (hypoIDs.contains(hypo)) { // Checks if the selected key
                                                  // is a hyponym
                        hypoList.add(word);
                    }
                }
            }
            return hypoList;
        } else {
            return null;
        } // In case the target string is not in the list of words given
    }

}
