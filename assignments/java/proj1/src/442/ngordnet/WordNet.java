/* Deepak Talwar
 * 22666800
 * CS61B Spring 2015 */
package ngordnet;
import java.util.HashMap; 
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;

// Thought about the data structures in this class (WordNet) with 
// Jash Mahipal and Sparsh Mehta. All other classes in this project
// were written and thought of by me alone. 

//***** AG TEST PROBLEMS *****
// FIXED: hyponym() doesn't have conversion in the output

public class WordNet {
    private Map<Integer, Set<String>> synsetMap; //= new HashMap<Integer, Set<String>>();
    private Map<Integer, Set<Integer>> hyponymMap; // = new HashMap<Integer, Set<Integer>>();
    private int vertices = 1;
    private Digraph mainDigraph;

    // Constructor
    public WordNet(String synsetFilename, String hyponymFilename) {
        readSynset(synsetFilename);
        readHyponym(hyponymFilename);
        mainDigraph = new Digraph(vertices);
        for (Integer key : hyponymMap.keySet()) {
            Set<Integer> idSet = hyponymMap.get(key);
            for (Integer hypoID : idSet) {
                mainDigraph.addEdge(key, hypoID);
            }
        }
    }

    private void readSynset(String synsetFilename) {
        In synFile = new In(synsetFilename);
        synsetMap = new HashMap<Integer, Set<String>>();
        String line = synFile.readLine();
        while (line != null) {
            String[] tokens = line.split(",");
            Set<String> setVal = new HashSet<String>();
            Integer id = Integer.parseInt(tokens[0]);
            String synsetString = tokens[1];
            String[] syns = synsetString.split(" ");
            for (Integer i = 0; i < syns.length; i++) {
                setVal.add(syns[i]);
            }
            synsetMap.put(id, setVal);
            line = synFile.readLine();
            vertices += 1;
        }
    }

    private void readHyponym(String hyponymFilename) {
        In hypFile = new In(hyponymFilename);
        hyponymMap = new HashMap<Integer, Set<Integer>>();
        String line = hypFile.readLine();
        while (line != null) {   
            Set<Integer> setVal = new HashSet<Integer>();
            String[] tokens = line.split(",");
            Integer id = Integer.parseInt(tokens[0]);
            for (Integer i = 1; i < tokens.length; i++) {
                setVal.add(Integer.parseInt(tokens[i]));
            }
            if (hyponymMap.containsKey(id)) {
                hyponymMap.get(id).addAll(setVal);
            } else {
                hyponymMap.put(id, setVal);
            }
            line = hypFile.readLine();
        }
    }

    // isNoun method checks whether something 
    public boolean isNoun(String noun) {
        for (Integer key : synsetMap.keySet()) {
            Set<String> nounSet = synsetMap.get(key);
            for (String n : nounSet) {
                if (n.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

   
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (Integer key : synsetMap.keySet()) {
            Set<String> nounSet = synsetMap.get(key);
            allNouns.addAll(nounSet);
        }
        return allNouns;
    }

    // Find the id (integer) of the word (string) and call GraphHelper.
    // descendents(digraph, id)
    public Set<String> hyponyms(String word) {
        if (this.isNoun(word)) {
            Set<Integer> wordIDs = new HashSet<Integer>();
            for (Integer key : synsetMap.keySet()) {
                Set<String> vals = synsetMap.get(key);
                for (String syn : vals) {
                    if (syn.equals(word)) {
                        wordIDs.add(key);
                    }
                }
            }
            Set<String> hypos = new HashSet<String>();
            Set<Integer> desIDs;
            desIDs = GraphHelper.descendants(mainDigraph, wordIDs);
            for (Integer ids : desIDs) {
                for (String s : synsetMap.get(ids)) {
                    hypos.add(s);
                }
            }
            return hypos;
        }
        throw new IllegalArgumentException("Not a valid word");
    }

    /* public static void main(String[] args){
        WordNet wn = new WordNet("./p1data/wordnet/synsets.txt", "./wordnet/hyponyms.txt");
        System.out.println("change" + wn.hyponyms("god"));
    } */
}
