package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

import edu.princeton.cs.introcs.In;

/**
 * Created by ajaykrishnan on 2/27/2015.
 */
public class WordNet {
    private TreeMap<Integer, String[]> ref;
    private TreeSet<String> nouns;
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In in1 = new In(synsetFilename);
        nouns = new TreeSet<>();
        ref = new TreeMap<>();
        /** Populate synset and map of references */
        while (in1.hasNextLine()) {
            String[] separated1 = in1.readLine().split(",");
            if (separated1[1].contains(" ")) {
                String[] separated2 = separated1[1].split(" ");
                for (String s : separated2) {
                    nouns.add(s);
                    ref.put(Integer.parseInt(separated1[0]), separated2);
                }
            } else {
                nouns.add(separated1[1]);
                String[] temp = new String[1];
                temp[0] = separated1[1];
                ref.put(Integer.parseInt(separated1[0]), temp);
            }
        }
        digraph = new Digraph(ref.size());
        In in2 = new In(hyponymFilename);
        /** Add edges */
        while (in2.hasNextLine()) {
            String[] separated = in2.readLine().split(",");
            for (int i = 1; i < separated.length; i++) {
                digraph.addEdge(Integer.parseInt(separated[0]), Integer.parseInt(separated[i]));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> hyp = new TreeSet<>();
        TreeSet<Integer> synIDs = new TreeSet<>();
        /** Create set of relevant synIDs */
        for (int id : ref.keySet()) {
            for (String s : ref.get(id)) {
                if (s.equals(word)) {
                    synIDs.add(id);
                }
            }
        }
        /** Determine reachable vertices */
        Set<Integer> reachable = GraphHelper.descendants(digraph, synIDs);
        for (int k : reachable) {
            for (String s : ref.get(k)) {
                hyp.add(s);
            }
        }
        return hyp;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }
}
