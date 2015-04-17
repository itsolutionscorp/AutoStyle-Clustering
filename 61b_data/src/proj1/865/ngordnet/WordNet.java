package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private Map<Integer, String> wordMap; 
    private Digraph pointers;

    /** Creates a WordNet using files from SYNSETFILENAME and ADJFILENAME*/
    public WordNet(String synsetFilename, String hyponymFilename) {
        Map<Integer, String> words = new TreeMap<Integer, String>();
        In in1 = new In(synsetFilename);
        String[] wordData;

        while (in1.hasNextLine()) {
            wordData = in1.readLine().split(",");
            words.put((Integer) Integer.parseInt(wordData[0]), wordData[1]);
        }

        this.wordMap = words;

        Digraph g = new Digraph(words.size());
        In in2 = new In(hyponymFilename);
        while (in2.hasNextLine()) {
            wordData = in2.readLine().split(",");
            for (int j = 1; j < wordData.length; j++) {
                g.addEdge(Integer.parseInt(wordData[0]), Integer.parseInt(wordData[j]));
            }
        }
        this.pointers = g;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        String[] subset;
        for (String x: wordMap.values()) {
            subset = x.split(" ");
            for (String y: subset) {
                if (y.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        String[] subset;
        Set<String> set = new TreeSet<String>();
        for (String x: wordMap.values()) {
            subset = x.split(" ");
            for (String y: subset) {
                set.add(y);
            }
        }
        return set;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new TreeSet<Integer>();
        for (int j = 0; j < wordMap.size(); j++) {
            for (String x: wordMap.get(j).split(" ")) {
                if (x.equals(word)) {
                    ids.add(j);
                }
            }
        }

        Set<Integer> hypos = GraphHelper.descendants(pointers, ids);

        Set<String> results = new TreeSet<String>();
        for (int y: hypos) {
            for (String z: wordMap.get(y).split(" ")) {
                results.add(z);
            }
        }

        results.add(word);
        return results;
    }
}
