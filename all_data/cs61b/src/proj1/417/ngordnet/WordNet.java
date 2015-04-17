// Received help from Nathan Pannell
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private Map<Integer, Set<String>> synsMap = new HashMap<Integer, Set<String>>();
    private Digraph digraph;
    private Map<String, Set<Integer>> mapReversed = new HashMap<String, Set<Integer>>();
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsets, String hyponyms) {
        In synsetIn = new In(synsets);
        In hyponymIn = new In(hyponyms);
        while (synsetIn.hasNextLine()) {
            String[] lineWords = synsetIn.readLine().split(",");
            Set<String> setWords = new HashSet<String>();
            for (String word : lineWords[1].split(" ")) {
                setWords.add(word);
                if (mapReversed.containsKey(word)) {
                    mapReversed.get(word).add(Integer.parseInt(lineWords[0]));
                } else {
                    Set<Integer> num = new HashSet<Integer>();
                    num.add(Integer.parseInt(lineWords[0]));
                    mapReversed.put(word, num);
                }
            }
            synsMap.put(Integer.parseInt(lineWords[0]), setWords);
        }
        digraph = new Digraph(synsMap.size());
        while (hyponymIn.hasNextLine()) {
            String[] hypLine = hyponymIn.readLine().split(",");
            for (String number : hypLine) {
                digraph.addEdge(Integer.parseInt(hypLine[0]), Integer.parseInt(number));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return mapReversed.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return mapReversed.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> hypSet = new HashSet<String>();
        for (int var : GraphHelper.descendants(digraph, mapReversed.get(word))) {
            for (String str : synsMap.get(var)) {
                hypSet.add(str);
            }
        }
        return hypSet;
    }
}
