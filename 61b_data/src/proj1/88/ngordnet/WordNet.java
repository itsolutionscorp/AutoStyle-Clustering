package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<Integer>> hInt = new HashMap<Integer, ArrayList<Integer>>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSyn = new In(synsetFilename);
        In inHyp = new In(hyponymFilename);
        while (!inSyn.isEmpty()) {
            String line = inSyn.readLine();
            String[] split = line.split(",");
            String[] words = split[1].split(" ");
            map.put(Integer.parseInt(split[0]), new ArrayList<String>(
                    (Collection<String>) Arrays.asList(words)));
        }
        while (!inHyp.isEmpty()) {
            String intLine = inHyp.readLine();
            String[] intSplit = intLine.split(",");
            int[] newIntSplit = new int[intSplit.length - 1];
            for (int i = 0; i < newIntSplit.length; i++) {
                newIntSplit[i] = Integer.parseInt(intSplit[i + 1]);
            }
            if (!hInt.containsKey(Integer.parseInt(intSplit[0]))) {
                ArrayList<Integer> intArrayList = new ArrayList<Integer>();
                for (Integer y : newIntSplit) {
                    intArrayList.add(y);
                }
                hInt.put(Integer.parseInt(intSplit[0]), intArrayList);
            } else {
                for (Integer y : newIntSplit) {
                    hInt.get(Integer.parseInt(intSplit[0])).add(y);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> all = new HashSet<String>();
        for (Integer i : map.keySet()) {
            for (String s : map.get(i)) {
                all.add(s);
            }
        }
        return all;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyps = new HashSet<String>();
        HashSet<Integer> keys = new HashSet<Integer>();
        for (Integer i : map.keySet()) {
            if (map.get(i).contains(word)) {
                keys.add(i);
            }
        }
        GraphHelper helper = new GraphHelper();
        Digraph graph = new Digraph(map.size());
        for (Integer i : map.keySet()) {
            if (hInt.containsKey(i)) {
                ArrayList<Integer> ints = hInt.get(i);
                for (Integer y : ints) {
                    graph.addEdge(i, y);
                }
            }
        }
        Set<Integer> des = helper.descendants(graph, keys);
        for (Integer x : des) {
            for (String s : map.get(x)) {
                hyps.add(s);
            }
        }
        return hyps;
    }
}
