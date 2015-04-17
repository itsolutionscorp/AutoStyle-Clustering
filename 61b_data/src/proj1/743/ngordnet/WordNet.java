package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.ArrayList;

public class WordNet {
    private Digraph connections;
    private TreeMap<String, ArrayList<Integer>> nounsMap;
    private TreeMap<Integer, String[]> intMap;
    private TreeSet<String> nouns;

    public WordNet(String synset, String hyponyms) {
        nounsMap = new TreeMap<String, ArrayList<Integer>>();
        intMap = new TreeMap<Integer, String[]>();
        nouns = new TreeSet<String>();
        In in1 = new In(hyponyms);
        In in2 = new In(synset);
        String[] hyponymLines = in1.readAllLines();
        String[] synsetLines = in2.readAllLines();
        connections = new Digraph(synsetLines.length);
        for (String line: synsetLines) {
            String[] parts = line.split(",");
            Integer key = Integer.parseInt(parts[0]);
            String[] nounsList = parts[1].split(" ");
            intMap.put(key, nounsList);
            for (String noun: nounsList) {
                nouns.add(noun);
                ArrayList<Integer> keys = nounsMap.get(noun);
                if (keys != null) {
                    keys.add(key);
                } else {
                    keys = new ArrayList<Integer>();
                    keys.add(key);
                    nounsMap.put(noun, keys);
                }
            }
        }
        for (String line: hyponymLines) {
            String[] parts = line.split(",");
            Integer first = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                Integer second = Integer.parseInt(parts[i]);
                connections.addEdge(first, second);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        ArrayList<Integer> i = nounsMap.get(word);
        Set<Integer> wordSet = new TreeSet<Integer>();
        for (int j = 0; j < i.size(); j++) {
            wordSet.add(i.get(j));
        }
        Set<Integer> desc = GraphHelper.descendants(connections, wordSet);
        TreeSet<String> toReturn = new TreeSet<String>();
        for (Integer c: desc) {
            String[] results = intMap.get(c);
            for (String s: results) {
                toReturn.add(s);
            }
        }
        return toReturn;
    }
}
