package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class WordNet {
    private Map<Integer, String[]> mapIDvalue = new HashMap<Integer, String[]>();
    private Map<String, ArrayList<Integer>> mapValueID = new HashMap<String, ArrayList<Integer>>();
    private Map<Integer, ArrayList<Integer>> mapHyponyms = 
                    new HashMap<Integer, ArrayList<Integer>>();
    private Digraph wordNet;

    public WordNet(String synsetFilename, String hyponymFilename) {
        String synsets = synsetFilename;
        String hyponyms = hyponymFilename;
        int numVertices = 0;
        In s = new In(synsets);
        In h = new In(hyponyms);
        while (s.hasNextLine()) {
            String syn = s.readLine();
            numVertices += 1;
            String[] splitted = syn.split(",");
            String[] multiSyn = splitted[1].split(" "); // Split again in case there are synonyms
            Integer id = Integer.parseInt(splitted[0]); // Get synset ID
            mapIDvalue.put(id, multiSyn);
            for (String synonym : multiSyn) {
                if (mapValueID.containsKey(synonym)) { // Already an existing key
                    mapValueID.get(synonym).add(id); // Add IDs if word appears more than once
                } else {
                    ArrayList<Integer> otherID = new ArrayList<Integer>();
                    otherID.add(id);
                    mapValueID.put(synonym, otherID);
                }
            }
        }

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Integer idHyp = 0;
        wordNet = new Digraph(numVertices);

        while (h.hasNextLine()) { // Still more hyponyms
            String hyp = h.readLine();
            String[] split = hyp.split(",");
            for (String string : split) {
                numbers.add(Integer.parseInt(string)); // Add all numbers to ArrayList
            }
            numbers.remove(0); // Get rid of synset ID
            idHyp = Integer.parseInt(split[0]); // Save synset ID as integer
            mapHyponyms.put(idHyp, numbers); // Put ArrayList of hyponyms in as values for ID key
            for (Integer eachHyponym : numbers) {
                wordNet.addEdge(idHyp, eachHyponym);
            }
            numbers.clear(); // Clear so numbers will only contain hyponyms for the next ID
        }
    }

    public boolean isNoun(String noun) {
        for (String word : mapValueID.keySet()) {
            if (word.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> allWords = new HashSet<String>();
        for (String word : mapValueID.keySet()) {
            allWords.add(word);
        }
        return allWords;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new HashSet<String>();
        if (mapValueID.containsKey(word)) { // If the word is associated with an ID
            ArrayList<Integer> saveWord = mapValueID.get(word); // Gets word's ID
            Set<Integer> saveIDs = GraphHelper.descendants(wordNet, new HashSet<>(saveWord));
            for (Integer ids : saveIDs) { // For each hyponym ID
                String[] hyponymWord = mapIDvalue.get(ids);
                for (String hypoWord : hyponymWord) { // For each word, add it to final set
                    hyponymSet.add(hypoWord);
                }
            }
            for (Integer hypID : saveWord) { 
                String[] getSyn = mapIDvalue.get(hypID); // Get word(s) associated with ID(s)
                for (String hypoID : getSyn) { // For each word, add it to final set
                    hyponymSet.add(hypoID);
                }
            }
            return hyponymSet;
        }
        return null;
    }
}
