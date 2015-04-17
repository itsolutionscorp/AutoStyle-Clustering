package ngordnet;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private HashMap<Integer, HashSet<String>> dict;
    private Digraph g;

    public WordNet(String file1, String file2) {
        //convert file1 into hashmap
        In synset = new In(file1);
        In hypo = new In(file2);
        dict = new HashMap<Integer, HashSet<String>>();
        while (!synset.isEmpty()) {
            String[] line = synset.readLine().split(",");
            Integer id = Integer.parseInt(line[0]);
            HashSet<String> words = new HashSet<String>();
            for (String word : line[1].split(" ")) {
                words.add(word);
            }
            dict.put(id, words);
        }
        //convert file2 into digraph
        g = new Digraph(dict.size());
        while (!hypo.isEmpty()) {
            String[] line = hypo.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                g.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }
        }
    }

    //whether s is in dict
    public boolean isNoun(String s) {
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).contains(s)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        HashSet<String> result = new HashSet<String>();
        for (int i = 0; i < dict.size(); i++) {
            for (String word: dict.get(i)) {
                result.add(word);
            }
        }
        return result;
    }

    public Set<String> hyponyms(String s) {
        HashSet<Integer> vertices = new HashSet<Integer>();
        HashSet<String> result = new HashSet<String>();
        //check validity
        if (!isNoun(s)) {
            throw new IllegalArgumentException(s + "is not a noun");
        }
        //if s is in the set, put all words of the set into result, add i to vertices
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).contains(s)) {
                vertices.add(i);
            }
        }
        Set<Integer> allV = GraphHelper.descendants(g, vertices);
        for (Integer i : allV) {
            for (String word : dict.get(i)) {
                result.add(word);
            }
        }
        return result;
    }

}
