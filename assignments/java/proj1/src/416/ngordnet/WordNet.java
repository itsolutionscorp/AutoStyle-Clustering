package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private Digraph graph;
    private Map<Integer, Set<String>> synmap = new HashMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> revsynmap = new HashMap<String, Set<Integer>>();

    public WordNet(String synset, String hyponyms) {
        In syns = new In(synset);
        In nyms = new In(hyponyms);
        while (syns.hasNextLine()) {
            String[] wordline = syns.readLine().split(",");
            Set<String> wordSet = new TreeSet<String>();
            for (String word : wordline[1].split(" ")) {
                wordSet.add(word);
                if (revsynmap.containsKey(word)) {
                    revsynmap.get(word).add(Integer.parseInt(wordline[0]));
                } else {
                    Set<Integer> num = new TreeSet<Integer>();
                    num.add(Integer.parseInt(wordline[0]));
                    revsynmap.put(word, num);
                }
            }
            synmap.put(Integer.parseInt(wordline[0]), wordSet);
        }
        graph = new Digraph(synmap.size());
        while (nyms.hasNextLine()) {
            String[] nymLine = nyms.readLine().split(",");
            for (String nymNum : nymLine) {
                graph.addEdge(Integer.parseInt(nymLine[0]), Integer.parseInt(nymNum));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> words = new TreeSet<String>();
        for (int num : GraphHelper.descendants(graph, revsynmap.get(word))) {
            for (String noun : synmap.get(num)) {
                words.add(noun);
            }
        }
        return words;
    }

    public boolean isNoun(String word) {
        return revsynmap.containsKey(word);
    }

    public Set<String> nouns() {
        return revsynmap.keySet();
    }
}
