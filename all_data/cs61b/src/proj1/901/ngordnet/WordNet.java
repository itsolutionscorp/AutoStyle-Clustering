package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Map<Integer, String[]> sMap;
    private Map<String, ArrayList<Integer>> siMap;
    private Digraph hyps;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);
        sMap = new HashMap<Integer, String[]>();
        siMap = new HashMap<String, ArrayList<Integer>>();

        while (syIn.hasNextLine()) {
            String s = syIn.readLine();
            String[] parts = s.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String[] syn = parts[1].split(" ");
            for (int x = 0; x < syn.length; x++) {
                if (siMap.containsKey(syn[x])) {
                    siMap.get(syn[x]).add(id);
                } else {
                    ArrayList<Integer> i = new ArrayList<Integer>();
                    i.add(id);
                    siMap.put(syn[x], i);
                }
            }
            sMap.put(id, syn);
        }
        hyps = new Digraph(sMap.size());
        // Hyponym stuff
        while (hypIn.hasNextLine()) {
            String s = hypIn.readLine();
            String[] parts = s.split(",");
            Integer[] h = new Integer[parts.length];
            for (int x = 0; x < parts.length; x++) {
                h[x] = Integer.parseInt(parts[x]);
                if (x > 0) {
                    hyps.addEdge(h[0], h[x]);
                }
            }
        }

    }

    public boolean isNoun(String noun) {
        if (siMap.containsKey(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return siMap.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> id = new TreeSet<Integer>(siMap.get(word));
        Set<Integer> h = GraphHelper.descendants(hyps, id);
        Set<String> newH = new TreeSet<String>();
        for (Integer i : h) {
            String[] s = sMap.get(i);
            for (String sx : s) {
                newH.add(sx);
            }
        }
        return newH;
    }

}
