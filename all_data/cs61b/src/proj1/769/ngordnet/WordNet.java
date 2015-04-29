package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    // constructor...
    // map put(key,value) oooosaddsoo
    private Map<Integer, Set<Integer>> hypnoymsmap = new HashMap<Integer, Set<Integer>>();
    private Map<Integer, Set<String>> synsetmap = new HashMap<Integer, Set<String>>();
    private Set<String> allnouns = new HashSet<String>();
    private In inputsynset;
    private In inputhyponyn;
    private int key;
    private Digraph digraph;
    private int digraphsize = 0;

    public WordNet(String synsetFilename, String hyponymFilename) {
        String temp;
        Set<Integer> tempIntegerset;
        Set<String> tempstringset;
        String[] stringarray;
        String[] tempStringArray;
        inputsynset = new In(synsetFilename);
        inputhyponyn = new In(hyponymFilename);
        while (inputhyponyn.hasNextLine()) {
            tempIntegerset = new HashSet<Integer>();
            temp = inputhyponyn.readLine();
            stringarray = temp.split(",");
            key = Integer.parseInt(stringarray[0]);
            digraphsize = Math.max(digraphsize, key);
            for (int i = 1; i < stringarray.length; i++) {
                if (hypnoymsmap.containsKey(key)) {
                    hypnoymsmap.get(key).add(Integer.parseInt(stringarray[i]));
                    digraphsize = Math.max(digraphsize, Integer
                            .parseInt(stringarray[i]));
                } else {
                    tempIntegerset.add(Integer.parseInt(stringarray[i]));
                    digraphsize = Math.max(digraphsize, Integer
                            .parseInt(stringarray[i]));
                    hypnoymsmap.put(key, tempIntegerset);
                }
            }            
        }
        digraphsize += 1;
        digraph = new Digraph(digraphsize);
        for (int k : hypnoymsmap.keySet()) {
            for (int edge : hypnoymsmap.get(k)) {
                digraph.addEdge(k, edge);
            }
        }

        while (inputsynset.hasNextLine()) {
            tempstringset = new HashSet<String>();
            temp = inputsynset.readLine();
            stringarray = temp.split(",");
            temp = stringarray[1];
            key = Integer.parseInt(stringarray[0]);
            stringarray = temp.split(" ");
            for (String k : stringarray) {
                allnouns.add(k);
                tempstringset.add(k);
            }
            synsetmap.put(key, tempstringset);
        }

    }

    public boolean isNoun(String noun) {
        boolean ishere = false;
        for (int p : synsetmap.keySet()) {
            if (synsetmap.get(p).contains(noun)) {
                ishere = true;
            }
        }
        return ishere;
    }

    public Set<String> nouns() {
        return allnouns;
    }

    public Set<String> hyponyms(String s) {
        Set<String> outcome = new HashSet<String>(); 
        Set<Integer> tempIntegerset = new HashSet<Integer>();
        Set<Integer> hyponym;
        for (int i : synsetmap.keySet()) {
            if (synsetmap.get(i).contains(s)) {
                tempIntegerset.add(i);
                for (String k : synsetmap.get(i)) {
                    outcome.add(k);
                }
            }
        }
        hyponym = GraphHelper.descendants(digraph, tempIntegerset);
        for (int i : hyponym) {
            for (String term : synsetmap.get(i)) {
                outcome.add(term);
            }
        }
        return outcome;
    }

}
