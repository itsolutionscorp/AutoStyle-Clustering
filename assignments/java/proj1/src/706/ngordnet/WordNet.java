package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;

public class WordNet {
    private HashMap<Integer, String[]> synsetFromID;
    private HashMap<String, ArrayList<Integer>> wordToID;
    private Digraph d;
    private int lineCount;
  
    // Constructor has a few parts.
    // Mapping in both directions: from ID to word and from word to ID.
    // Digraph shows the relationship between IDs. Need to convert from ID to words later.
    //
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFromID = new HashMap<Integer, String[]>();
        wordToID = new HashMap<String, ArrayList<Integer>>();
        lineCount = 0;
        In synset = new In(synsetFilename);
        while (synset.hasNextLine()) {
            lineCount++;
            String[] ids = synset.readLine().split(",");
            int id = Integer.parseInt(ids[0]);
            String words = ids[1];
            String[] words2 = words.split(" ");
            for (int i = 0; i < words2.length; i++) { 
                synsetFromID.put(id, words2);
                if (!wordToID.containsKey(words2[i])) {
                    ArrayList<Integer> wordIDs = new ArrayList<Integer>();
                    wordIDs.add(id);
                    wordToID.put(words2[i], wordIDs);
                } else {
                    ArrayList<Integer> keyIDs = wordToID.get(words2[i]);
                    keyIDs.add(id);
                    wordToID.put(words2[i], keyIDs);
                }  
            }  
        }
        In hyponyms = new In(hyponymFilename);
        d = new Digraph(lineCount);
        while (hyponyms.hasNextLine()) {
            String[] hypoids = hyponyms.readLine().split(",");
            int head = Integer.parseInt(hypoids[0]);
            for (int i = 1; i < hypoids.length; i++) {
                d.addEdge(head, Integer.parseInt(hypoids[i]));
            }  
        }  
    }

    // Checks to see if the word is somewhere in the digraph. //
    public boolean isNoun(String noun) {
        if (wordToID.containsKey(noun)) {
            return true;
        }
        return false;
    }  


    // Gives the set of nouns in the digraph. //
    public Set<String> nouns() {
        return wordToID.keySet();
    }

    // Finds the IDs of all hyponyms and synonyms of the word.
    // Iterates through these to output the words. //
    public Set<String> hyponyms(String word) {
    // Easiest case: the word is already in the synset. //
        if (isNoun(word)) {
            Set<Integer> val = new HashSet<Integer>();    
            if (wordToID.keySet().contains(word)) {
                ArrayList<Integer> v = wordToID.get(word); 
                while (!v.isEmpty()) {
                    val.add(v.get(0));
                    v.remove(0);
                }
            }      
            Set<Integer> ansids = GraphHelper.descendants(d, val);
            Set<String> newans = new HashSet<String>();
            //Iterate through the IDs to get the corresponding words. //  
            for (Integer s : ansids) {
                String[] inputwords = synsetFromID.get(s);
                for (int i = 0; i < inputwords.length; i++) {
                    newans.add(inputwords[i]);
                }
            }  
            return newans;
        }
        throw new IllegalArgumentException();
    }  
}
