package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class WordNet {

    private Map<String, Set<Integer>> words;
    private Digraph graph;
    private Set<String>[] idSet; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename); ///fix for correct directory
        words = new HashMap<String, Set<Integer>>();
        String[] allSynsets = synsetFile.readAllLines();
        idSet = (HashSet<String>[]) new HashSet[allSynsets.length];
        for (String line : allSynsets) {
            String[] split = line.split(",");
            int id = Integer.parseInt(split[0]);
            String[] synset = split[1].split(" ");
            for (String word : synset) {
                if (words.get(word) == null) {
                    words.put(word, new HashSet<Integer>());                
                }
                Set<Integer> key = words.get(word);
                key.add(id);
                words.put(word, key);
                if (idSet[id] == null) {
                    idSet[id] = new HashSet<String>();              
                }
                idSet[id].add(word);
            }
        }
        graph = new Digraph(allSynsets.length);
        String[] allRelationships = hyponymFile.readAllLines();
        for (String line : allRelationships) {
            String[] split = line.split(",");
            for (int i = 1; i < split.length; i++) {
                graph.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[i]));
            }
        }

    }

    public Set<String> nouns() {
        return words.keySet();
    }

    public boolean isNoun(String word) {
        return nouns().contains(word);
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> idInASet = words.get(word);
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> hyponymsId = GraphHelper.descendants(graph, idInASet);
        Iterator<Integer> hyponymsIdIter = hyponymsId.iterator();
        while (hyponymsIdIter.hasNext()) {
            Set<String> y = idSet[hyponymsIdIter.next()];
            hyponyms.addAll(y);
        }
        return hyponyms;
    }
}
