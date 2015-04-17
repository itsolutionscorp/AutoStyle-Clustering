/**
 * Created by Andrew on 3/2/2015.
 */
package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private String synset;
    private String hyponym;
    private HashMap<Integer, String> synsetsMap = new HashMap<Integer, String>();
    private Digraph hyponymGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {

        In readSynsets = new In(synsetFilename);
        while (readSynsets.hasNextLine()) {
            String x = readSynsets.readLine();
            String[] tokens = x.split(",");
            synsetsMap.put(Integer.parseInt(tokens[0]), tokens[1]);

        }
        In readhyponyms = new In(hyponymFilename);

        hyponymGraph = new Digraph(synsetsMap.size());
        while (readhyponyms.hasNextLine()) {
            String x = readhyponyms.readLine();
            String[] tokens = x.split((","));
            Integer start = Integer.parseInt(tokens[0]);
            String[] newTokens = new String[tokens.length - 1];
            int i = 1;
            while (i < tokens.length) {
                hyponymGraph.addEdge(start, Integer.parseInt(tokens[i]));
                i++;
            }

        }

    }

    public boolean isNoun(String noun) {
        for (String x : nouns()) {
            if (x.equals(noun)) {
                return true;
            }
        }
        return false;

    }

    public Set<String> nouns() {
        TreeSet<String> nounsSet = new TreeSet<String>();

        for (String x : synsetsMap.values()) {
            String[] words = x.split(" ");
            for (String y : words) {
                nounsSet.add(y);
            }

        }
        return nounsSet;
    }

    public Set<String> hyponyms(String noun) {
        TreeSet<Integer> keySet = new TreeSet<Integer>();
        int x = 0;
        while (x != synsetsMap.size()) {
            String[] wanted = synsetsMap.get(x).split(" ");
            int count = 0;
            for (String i : wanted) {
                if (i.equals(noun)) {
                    count += 1;
                }
            }
            if (count != 0) {
                keySet.add(x);
            }
            x++;
        }
        TreeSet<Integer> descendentSet = (TreeSet<Integer>) GraphHelper
                .descendants(hyponymGraph, keySet);
        TreeSet<String> hyponymSet = new TreeSet<String>();
        for (int y : descendentSet) {
            String words = synsetsMap.get(y);
            String[] hypos = words.split(" ");
            for (String w : hypos) {
                hyponymSet.add(w);
            }
        }
        return hyponymSet;
    }

}
