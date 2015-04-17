package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private HashMap<Integer, String[]> dictionarySynset;
    private Digraph wn;

    public WordNet(String synsetFilename, String hyponymFilename) {
        dictionarySynset = new HashMap<Integer, String[]>();
        In hypo = new In(hyponymFilename);
        In synset = new In(synsetFilename);
        //theoretically will read each line, parse the int 
        //and create a string array of the synsets/defs
        //and store it into hashmap dictionary
        while (synset.hasNextLine()) {
            String temp = synset.readLine();
            String[] rawTokens = temp.split(",");
            Integer key = Integer.parseInt(rawTokens[0]);
            String[] val = rawTokens[1].split(" ");
            dictionarySynset.put(key, val);
        }

        wn = new Digraph(dictionarySynset.size());


        //if all works, it will create new digraph, read each line
        //parse the ints (first int is the source, next few are the ends)
        //and create all the edges
        while (hypo.hasNextLine()) {
            String temp = hypo.readLine();
            String[] rawTokens = temp.split(",");
            Integer sourceVertex = Integer.parseInt(rawTokens[0]);
            Integer[] endVertex = new Integer[rawTokens.length - 1];
            for (int i = 1; i < rawTokens.length; i++) {
                endVertex[i - 1] = Integer.parseInt(rawTokens[i]);
            } 
            for (Integer i : endVertex) {
                wn.addEdge(sourceVertex, i);
            }
        }
    }

    public boolean isNoun(String noun) {
        //iterate through everything (2 layers) and check if its noun
        for (String[] val : dictionarySynset.values()) {
            for (String value : val) {
                if (value.equals(noun)) {
                    return true;
                }
            }

        }
        return false;
    }

    public java.util.Set<String> nouns() {
        //iterate through everything (2 layers) and add to hashset
        HashSet<String> set = new HashSet();
        for (String[] val : dictionarySynset.values()) {
            for (String value : val) {
                set.add(value);
            }
        }
        return set;
    }

    public Set<String> hyponyms(String word) {
        Set<String> wordSet = new HashSet();
        Set<Integer> tempValue = new HashSet();

        for (Integer key : dictionarySynset.keySet()) {
            for (String val : dictionarySynset.get(key)) {
                if (val.equals(word)) {
                    tempValue.add(key);
                }
            }
        }
        // HashSet<Integer> descend = GraphHelper.descendants(wn, tempValue);
        //translate desced's integers into the actual string
        for (Integer k : GraphHelper.descendants(wn, tempValue)) {
            for (String val : dictionarySynset.get(k)) {
                wordSet.add(val);
            }
        }
        return wordSet;
    }

}
