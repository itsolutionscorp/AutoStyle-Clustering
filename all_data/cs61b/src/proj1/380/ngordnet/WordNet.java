package ngordnet;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Digraph network;
    private List<List<String>> synsetList = new ArrayList<List<String>>();
    private Map<String, List<Integer>> wordToInt = new HashMap<String, List<Integer>>();
    // private Map<Integer, String> int_to_word;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        while (synsets.hasNextLine()) {
            String synsetLine = synsets.readLine();
            String[] synsetTokens = synsetLine.split(",");
            
            //Building synsetList
            List<String> item = new ArrayList<String>();
            for (String word : synsetTokens[1].split(" ")) {
                item.add(word);
            }
            item.add(synsetTokens[2]);
            synsetList.add(Integer.parseInt(synsetTokens[0]), item);

            //Building wordToInt
            for (String word : synsetTokens[1].split(" ")) {
                if (wordToInt.containsKey(word)) {
                    wordToInt.get(word).add(Integer.parseInt(synsetTokens[0]));
                } else {
                    List<Integer> ints = new ArrayList<Integer>();
                    ints.add(Integer.parseInt(synsetTokens[0]));
                    wordToInt.put(word, ints);
                }
            }
            // int_to_word.put(Integer.parseInt(synset_token[0]), synset_token[1]);
        }

        //Building network of hyponyms
        In hyponyms = new In(hyponymFilename);
        network = new Digraph(synsetList.size());
        while (hyponyms.hasNextLine()) {
            String hyponymLine = hyponyms.readLine();
            String[] hyponymTokens = hyponymLine.split(",");
            for (int i = 1; i < hyponymTokens.length; i++) {
                network.addEdge(Integer.parseInt(hyponymTokens[0]), 
                    Integer.parseInt(hyponymTokens[i]));
            }
        }

    }

    public Set<String> hyponyms(String word) {
        Set<Integer> number = new TreeSet<Integer>();
        if (wordToInt.get(word) == null) {
            return null;
        }
        for (int i : wordToInt.get(word)) {
            number.add(i);
        }
        Set<Integer> hyponymsNumSet = GraphHelper.descendants(network, number);
        TreeSet<String> hyponymsStrSet = new TreeSet<String>();
        for (Integer i : hyponymsNumSet) {
            for (int j = 0; j < (synsetList.get(i).size() - 1); j++) {
                hyponymsStrSet.add(synsetList.get(i).get(j));        
            }
        } return hyponymsStrSet;

    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        return wordToInt.keySet();
    }
}
