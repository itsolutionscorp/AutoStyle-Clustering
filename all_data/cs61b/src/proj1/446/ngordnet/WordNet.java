package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;

/** Provides a network to store and process data.
 *  @author Kang Tai 
 */
public class WordNet {
    
    private Digraph d;
    private Set<String> allNouns;
    private Map<Integer, Set<String>> nouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        String[] temp;
        Set<String> words;
        allNouns = new HashSet<String>();
        nouns = new HashMap<Integer, Set<String>>();
        while (synset.hasNextLine()) {
            temp = synset.readLine().split(",");
            words = new HashSet<String>();
            for (String s : temp[1].split(" ")) {
                allNouns.add(s);
                words.add(s);
            }
            nouns.put(Integer.parseInt(temp[0]), words);
        }
        d = new Digraph(nouns.size());
        while (hyponym.hasNextLine()) {
            temp = hyponym.readLine().split(",");
            for (int i = 1; i < temp.length; i += 1) {
                d.addEdge(Integer.parseInt(temp[0]), Integer.parseInt(temp[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    public Set<String> nouns() {
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> hypID = new HashSet<Integer>();
        Set<String> hyp = new HashSet<String>();
        for (Integer k : nouns.keySet()) {
            if (nouns.get(k).contains(word)) {
                hypID.add(k);
            }
        }
        for (int i : GraphHelper.descendants(d, hypID)) {
            hyp.addAll(nouns.get(i));
        }
        return hyp;
    }
}
