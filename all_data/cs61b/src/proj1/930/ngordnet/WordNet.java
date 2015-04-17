package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private Digraph net;
    private String[] syn;
    private String[] hyp;
    private String[][] nouns;
    
    public WordNet(String synsetFilename, java.lang.String hyponymFilename) {
        In synf = new In(synsetFilename);
        In hypf = new In(hyponymFilename);
        syn = synf.readAllLines();
        hyp = hypf.readAllLines();
        net = new Digraph(syn.length);
        for (String hypl: hyp) {
            String[] tokens = hypl.split(",");
            for (int i = 1; i < tokens.length; i++) {
                net.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
        nouns = new String[syn.length][];
        for (int i = 0; i < nouns.length; i++) {
            nouns[i] = returnNouns(syn[i]);
        }
    }
    /** Takes a synset line and returns the nouns in it.
     *
     */
    private String[] returnNouns(String s) {
        String[] tokens = s.split(",");
        String[] words = tokens[1].split(" ");
        return words;
    }
    
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> synsetIDs = new TreeSet<Integer>();
        for (int i = 0; i < syn.length; i++) {
            String[] words = syn[i].split(",")[1].split(" ");
            for (String w : words) {
                if (word.equals(w)) {
                    synsetIDs.add(i);
                }
            }
        }
        TreeSet<String> hyps = new TreeSet<String>();
        Set<Integer> tdecs = GraphHelper.descendants(net, synsetIDs);
        for (Integer i : tdecs) {
            String[] twords = returnNouns(syn[i]);
            for (String t: twords) {
                hyps.add(t);
            }
        }
        return hyps;
    }
    
    public boolean isNoun(String noun) {
        for (String[] l: nouns) {
            for (String w: l) {
                if (w.equals(noun)) {
                    return true;
                }
            }
        }
        return false;

    }

    public Set<String> nouns() {
        TreeSet<String> all = new TreeSet<String>();
        for (String[] l: nouns) {
            for (String w: l) {
                all.add(w);
            }
        }
        return all;
    }
    
}
