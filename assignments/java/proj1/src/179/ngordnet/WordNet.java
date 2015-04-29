package ngordnet;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Set<String> nouns;
    private Digraph hypgraph;
    private Map<String, List<String>> synmap;
    private Map<List<String>, String> reversesynmap;
    private ArrayList<List<String>> hyparray;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        In in2 = new In(hyponymFilename);
        String line = "";
        synmap = new HashMap<String, List<String>>();
        reversesynmap = new HashMap<List<String>, String>();
        nouns = new HashSet<String>();
        hyparray = new ArrayList<List<String>>();

        while ((line = in.readLine()) != null) {
            String[] temp = line.split(",");
            List<String> values = new ArrayList<String>();
            String[] tempnouns = temp[1].split(" ");
            for (int x = 0; x < tempnouns.length; x++) {
                nouns.add(tempnouns[x]);
            }
            values.add(temp[1]); //words
            values.add(temp[2]); //definition (not needed)
            synmap.put(temp[0], values);
            reversesynmap.put(values, temp[0]);
        }

        line = "";
        int size = 0;
        while ((line = in2.readLine()) != null) {
            String[] temp = line.split(",");            
            for (int x = 1; x < temp.length; x++) {
                List<String> values = new ArrayList<String>();
                values.add(temp[0]);
                values.add(temp[x]);
                hyparray.add(values);
                size = Math.max(size, Integer.parseInt(temp[0]));
                size = Math.max(size, Integer.parseInt(temp[x]));
            }
        }

        hypgraph = new Digraph(size + 1);

        for (int x = 0; x < hyparray.size(); x++) {
            int a = Integer.parseInt(hyparray.get(x).get(0));
            int b = Integer.parseInt(hyparray.get(x).get(1));
            hypgraph.addEdge(a, b);
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    private Set<Integer> getID(String word) {
        Set<Integer> id = new TreeSet<Integer>();
        for (Map.Entry<List<String>, String> entry : reversesynmap.entrySet()) {
            List<String> key = entry.getKey();
            int value = Integer.parseInt(entry.getValue());
            String[] keycheck = key.get(0).split(" ");
            
            for (int x = 0; x < keycheck.length; x++) {    
                if (keycheck[x].equals(word)) {
                    id.add(value);
                }
            }
        }
        return id;
    }
    
    public Set<String> hyponyms(String word) {
        Set<Integer> hypkeys = getID(word);
        if (hypkeys == null) {
            return new HashSet<String>();
        }

        Set<Integer> hyptree = GraphHelper.descendants(hypgraph, hypkeys);
        Set<String> hyponyms = new HashSet<String>();
        
        for (int k : hyptree) {
            List<String> temp = synmap.get(Integer.toString(k));
            String values = temp.get(0);
            String[] words = values.split(" ");
            for (String w : words) {
                hyponyms.add(w);
            }
        }   
        return hyponyms;
    }
}
