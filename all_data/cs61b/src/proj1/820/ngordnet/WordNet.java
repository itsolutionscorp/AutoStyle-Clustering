package ngordnet;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private List<Set<String>> nouns;
    private Set<String> nounSet;
    private Digraph nounGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        String[] syn = new In(synsetFilename).readAllLines();
        String[] hyp = new In(hyponymFilename).readAllLines();
        int V = syn.length;

        nounGraph = new Digraph(V);
        for (String line : hyp) {
            String[] ids = line.split(",");
            int root = Integer.parseInt(ids[0]);
            for (int i = 1; i < ids.length; i++) {
                nounGraph.addEdge(root, Integer.parseInt(ids[i]));
            }
        }

        nouns = new ArrayList<Set<String>>(V);
        nounSet = new TreeSet<String>();

        // synsets must be in numerical order
        for (int i = 0; i < V; i++) {
            String[] data = syn[i].split(",");
            //int id = Integer.parseInt(data[0]);
            String[] elements = data[1].split(" ");
            //String gloss = data[2];

            Set<String> n = new TreeSet<String>();
            for (String s : elements) {
                n.add(s);
                nounSet.add(s);
            }
            nouns.add(n);
        }
    }

    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    public Set<String> nouns() {
        return nounSet;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> wordIds = new TreeSet<Integer>();
        for (int i = 0; i < nouns.size(); i++) {
            if (nouns.get(i).contains(word)) {
                wordIds.add(i);
            }
        }
        Set<Integer> hypIds = GraphHelper.descendants(nounGraph, wordIds);
        Set<String> result = new TreeSet<String>();
        for (int i : hypIds) {
            result.addAll(nouns.get(i));
        }
        return result;
    }
}
