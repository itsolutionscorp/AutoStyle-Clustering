package ngordnet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, String[]> wordMap = new HashMap<Integer, String[]>();
    private Digraph g;
    private Set<String> allWords;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        while (!synset.isEmpty()) {
            String line = synset.readLine();
            String[] elem = line.split(",");
            String[] word = elem[1].split(" ");
            wordMap.put(Integer.parseInt(elem[0]), word);
        }
        g = new Digraph(wordMap.size());
        while (!hyponym.isEmpty()) {
            String line = hyponym.readLine();
            String[] elements = line.split(",");
            for (int i = 0; i < elements.length; i++) {
                if (i != 0) {
                    g.addEdge(Integer.parseInt(elements[0]),
                            Integer.parseInt(elements[i]));
                }
            }
        }
        allWords = new HashSet<String>();
        for (Integer k : wordMap.keySet()) {
            for (String w : wordMap.get(k)) {
                allWords.add(w);
            }
        }
    }

    public boolean isNoun(String noun) {
        return allWords.contains(noun);
    }

    public Set<String> nouns() {
        return allWords;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> vertexs = new TreeSet<Integer>();
        Set<String> wordSet = new HashSet<String>();
        for (Integer k : wordMap.keySet()) {
            String[] value = wordMap.get(k);
            if (Arrays.asList(value).contains(word)) {
                vertexs.add(k);
            }
        }
        Set<Integer> vNeed = GraphHelper.descendants(g, vertexs);
        for (Integer v : vNeed) {
            for (String w : wordMap.get(v)) {
                wordSet.add(w);
            }
        }
        return wordSet;
    }
}
