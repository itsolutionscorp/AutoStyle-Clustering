package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, String> synsets;
    private HashMap<String, ArrayList<Integer>> nounsToValues;
    private Digraph hyponyms;
    private HashSet<String> hyponymSet = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetStream = new In(synsetFilename);
        synsets = new HashMap<Integer, String>();
        nounsToValues = new HashMap<String, ArrayList<Integer>>();

        while (synsetStream.hasNextLine()) {
            String line = synsetStream.readLine();
            String[] elements = line.split(",");
            Integer currKey = Integer.parseInt(elements[0]);
            String currSynset = elements[1];

            String[] splitSynset = currSynset.split(" ");

            for (int i = 0; i < splitSynset.length; i += 1) {
                String currWord = splitSynset[i];
                if (nounsToValues.containsKey(currWord)) {
                    ArrayList currList = nounsToValues.get(currWord);
                    currList.add(currKey);
                    nounsToValues.put(currWord, currList);
                } else {
                    ArrayList indexVals = new ArrayList<Integer>();
                    indexVals.add(currKey);
                    nounsToValues.put(currWord, indexVals);
                }

            }
            synsets.put(currKey, currSynset);
        }

        int numberNouns = nounsToValues.size();

        hyponyms = new Digraph(numberNouns);
        In hyponymStream = new In(hyponymFilename);

        while (hyponymStream.hasNextLine()) {
            String line = hyponymStream.readLine();
            String[] elements = line.split(",");
            Integer currHyp = Integer.parseInt(elements[0]);
            for (int i = 1; i < elements.length; i += 1) {
                hyponyms.addEdge(currHyp, Integer.parseInt(elements[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounsToValues.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounsToValues.keySet();
    }

    public Set<String> hyponyms(String word) {
        hyponymSet.clear();
        ArrayList<Integer> currKeyList = nounsToValues.get(word);
        for (int i = 0; i < currKeyList.size(); i += 1) {
            Integer currKey = currKeyList.get(i);
            String currSynset = synsets.get(currKey);
            String[] splitSynset = currSynset.split(" ");
            for (int j = 0; j < splitSynset.length; j += 1) {
                hyponymSet.add(splitSynset[j]);
            }
            for (Integer currIndex: hyponyms.adj(currKey)) {
                currSynset = synsets.get(currIndex);
                splitSynset = currSynset.split(" ");
                for (int k = 0; k < splitSynset.length; k += 1) {
                    hyponymSet.add(splitSynset[k]);
                }
            }
        }
        return hyponymSet;
    }
}
