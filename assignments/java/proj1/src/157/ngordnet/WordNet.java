package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

/** WordNet class for proj1
 *  it builds up a structure for words
 *  @author zzy
 */

public class WordNet {
    /* Map is a dictionary with key numbers and value sets of words. */
    private HashMap<Integer, HashSet<String>> map = new HashMap();
    /* Structure is a directed graph of numbers. */
    private Digraph structure;
    /* allWords is a set of words. */
    private HashSet<String> allWords = new HashSet();

    /* Takes in two files and build up the structure. */
    public WordNet(String synFile, String hypFile) {
        In hyp = new In(hypFile);
        In syn = new In(synFile);

        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] rawTokens = line.split(",");
            int key = Integer.parseInt(rawTokens[0]);
            String[] words = rawTokens[1].split(" ");
            HashSet<String> wordSet = new HashSet(words.length);
            for (String a : words) {
                wordSet.add(a);
                allWords.add(a);
            }
            map.put(key, wordSet);
        }

        structure = new Digraph(map.size());
        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            String[] rawTokens = line.split(",");
            int startNode = Integer.parseInt(rawTokens[0]);
            for (int i = 1; i < rawTokens.length; i += 1) {
                structure.addEdge(startNode, Integer.parseInt(rawTokens[i]));
            }
        }
    }

    /* Return if a word is inside the map. */
    public boolean isNoun(String noun) {
        for (Integer i : this.map.keySet()) {
            if (this.map.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns all the nouns of this map. */
    public Set<String> nouns() {
        return this.allWords;
    }

    /* Return all te hyponyms of word, including itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> wordNums = new HashSet();
        for (Integer i : this.map.keySet()) {
            if (this.map.get(i).contains(word)) {
                wordNums.add(i);
            }
        }
        Set<Integer> hypNums = GraphHelper.descendants(this.structure, 
                                                       wordNums);
        Set<String> hypWords = new HashSet();
        for (Integer i : hypNums) {
            System.out.println(i);
            Set<String> temp = this.map.get(i);
            for (String a : temp) {
                hypWords.add(a);
            }
        }
        return hypWords;
    }
}
