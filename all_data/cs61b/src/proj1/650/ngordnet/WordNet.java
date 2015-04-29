package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    private HashMap<Integer, String> synsets;
    private Digraph hyponyms;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new HashMap<Integer, String>();
        In readSynsets = new In(synsetFilename);
        In readHyponyms = new In(hyponymFilename);
        String line;
        String[] items;
        while (readSynsets.hasNextLine()) {
            line = readSynsets.readLine();
            items = line.split(",");
            synsets.put(Integer.parseInt(items[0]), items[1]);
        }
        String[] hyponymsLines = readHyponyms.readAllLines();
        int size = 0;
        for (String ln: hyponymsLines) {
            size += ln.length();
        }
        hyponyms = new Digraph(size);
        for (String ln: hyponymsLines) {
            String[] nums = ln.split(",");
            int tailVertex = Integer.parseInt(nums[0]);
            for (int i = 1; i < nums.length; i += 1) {
                hyponyms.addEdge(tailVertex, Integer.parseInt(nums[i]));
            }
        }

    } 
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String word : nouns()) {
            if (word.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (String value : synsets.values()) {
            for (String word: value.split(" ")) {
                nouns.add(word);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> nouns = new HashSet<String>();
        ArrayList<Integer> wordKeys = new ArrayList<Integer>();
        for (int key: synsets.keySet()) {
            for (String noun: synsets.get(key).split(" ")) {
                if (noun.equals(word)) {
                    wordKeys.add(key);
                }
            }
        }  
        while (!wordKeys.isEmpty()) {
            Integer wordKey = wordKeys.get(0);
            ArrayList<Integer> copies = new ArrayList<Integer>();
            for (int adjKey: hyponyms.adj(wordKey)) {
                for (String noun : synsets.get(adjKey).split(" ")) {
                    nouns.add(noun);
                }
                wordKeys.add(adjKey);
            }
            for (String noun: synsets.get(wordKey).split(" ")) { 
                nouns.add(noun);
            }
            wordKeys.remove(wordKey);
        }
        return nouns;
    }
}
