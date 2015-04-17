package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /* The actual data from the sysnet files. Keys are the integer ID of the Synset, 
     * and values are an Array of the words in the Synset. The first item in the 
     * string array is an empty space */
    private HashMap<Integer, ArrayList<String>> data = new HashMap<Integer, ArrayList<String>>();

    /* A HashMap of the relationships between Synsets. 
     * Keys are the Integer ID of the hypernym and the values are a list of hyponyms */
    private Digraph relationships;

    /** Stores all recorded nouns into a hashset to facilitate both isNoun() and nouns()*/ 
    private HashSet<String> nouns = new HashSet<String>();

    /** Constructs a wordnet object using the data from synfile and hypfile. */
    public WordNet(String synfile, String hypfile) {
        In synsetFile = new In(synfile);
        In hyponymFile = new In(hypfile);
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] commaSplit = line.split(",");
            int key = Integer.parseInt(commaSplit[0]);
            ArrayList<String> synsetWords = new ArrayList<String>(); 
            for (String word : commaSplit[1].split(" ")) {
                synsetWords.add(word);
            }
            data.put(key, synsetWords);
            for (String word : synsetWords) {
                nouns.add(word);
            }
        }
        relationships = new Digraph(data.size());
        while (hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();
            String[] commaSplit = line.split(",");
            int key = Integer.parseInt(commaSplit[0]);
            for (int i = 1; i < commaSplit.length; i += 1) {
                relationships.addEdge(key, Integer.parseInt(commaSplit[i]));
            }
        }
        nouns.remove(" ");

    }


    /* Returns true if the given word is a noun*/
    public boolean isNoun(String s) {
        if (nouns.contains(s)) {
            return true;
        }
        return false;
    }

    /*Returns a list of all the nouns in the wordnet */
    public Set<String> nouns() {
        return nouns; 
    }
    
    /*Returns a list of all the hyponyms of the word in the wordnet */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> keys = new HashSet<Integer>();
        HashSet<String> returnValue = new HashSet<String>();
        for (int key : data.keySet()) {
            if (data.get(key).contains(word)) {
                keys.add(key);
            }
        }
        for (int relationship : GraphHelper.descendants(relationships, keys)) {
            returnValue.addAll(data.get(relationship));
        }
        return returnValue;
    }

}
