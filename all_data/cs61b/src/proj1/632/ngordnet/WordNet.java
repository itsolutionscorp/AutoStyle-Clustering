package ngordnet;

// Imports
import edu.princeton.cs.introcs.In;

// ADT imports
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

// @author Judiel Salandanan

public class WordNet extends Object {

    // instance variables
    private Digraph hyponyms;
    private TreeMap<Integer, Set<String>> data;
    private HashSet<String> nouns;

    // constructor
    public WordNet(String sysnetFilename, String hyponymFilename) {
        In synsetInput = new In(sysnetFilename);
        In hyponymInput = new In(hyponymFilename);
        int digraphSize = 0;
        data = new TreeMap<Integer, Set<String>>();
        nouns = new HashSet<String>();

        for (String line = synsetInput.readLine(); line != null; line = synsetInput.readLine()) {
            String[] rawTokens = line.split(",");
            // string id
            String temp = rawTokens[0];
            // string synset separated by " "
            String temp1 = rawTokens[1];
            // convert string id to int
            int id = Integer.parseInt(temp);
            // put each word in the sysnet string into a set
            // also adds them into collection of nouns;
            Set<String> synset = new HashSet<String>();
            String[] rawWords = temp1.split(" ");
            for (String word: rawWords) {
                synset.add(word);
                nouns.add(word);
            }
            // adding the values to the wordnet map
            data.put(id, synset);
            // data.put(id, synset);
            digraphSize = digraphSize + 1;
        }       

        // wordnet Digraph initialization
        hyponyms = new Digraph(digraphSize);
        for (String line = hyponymInput.readLine(); line != null; line = hyponymInput.readLine()) {
            String[] rawTokens = line.split(",");
            Integer[] tokens = new Integer[rawTokens.length];
            for (int i = 0; i < rawTokens.length; i = i + 1) {
                tokens[i] = Integer.parseInt(rawTokens[i]);
            }
            int parent = tokens[0];
            Integer[] descendants = new Integer[tokens.length - 1];
            System.arraycopy(tokens, 1, descendants, 0, rawTokens.length - 1);
            for (int descendant: descendants) {
                hyponyms.addEdge(parent, descendant);
            }
        }
    }

    public boolean isNoun(String noun) {
        if (nouns.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        // the return set
        Set<String> descendants = new HashSet<String>();
        for (Map.Entry<Integer, Set<String>> entry: data.entrySet()) {
            Integer key = entry.getKey();
            Set<String> value = entry.getValue();
            // adding id keys of synsets that contain the 'word'
            if (value.contains(word)) {
                ids.add(key);
            }
        }
        // retrieve descendants id through graphHelper
        Set<Integer> temp = GraphHelper.descendants(hyponyms, ids);
        Integer[] descIds = temp.toArray(new Integer[temp.size()]);
        // using id keys to find Set values, and add to return Set<String>
        for (int id: descIds) {
            String[] syns = data.get(id).toArray(new String[data.get(id).size()]);
            for (String syn: syns) {
                descendants.add(syn);
            }
        }       
        return descendants;
    }
}
