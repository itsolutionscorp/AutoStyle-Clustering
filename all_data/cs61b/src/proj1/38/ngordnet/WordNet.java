package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class WordNet {

    private Digraph relations;
    private ArrayList<Synset> synsets;
    
    public WordNet(String sfn, String hfn) {
        In sf = new In(sfn);
        In hf = new In(hfn);
        
        if (sf == null || hf == null) {
            throw new IllegalArgumentException();
        }
        
     
        String[] syns = sf.readAllLines();
        synsets = new ArrayList<Synset>(syns.length);  
        for (String s : syns) {
            Synset sy = new Synset(s);
            synsets.add(sy.getID(), sy);
        }
        
        relations = new Digraph(syns.length);        
        String[] hyps = hf.readAllLines();
        for (String h : hyps) {
            String[] nums = h.split(",");
            int parent = Integer.parseInt(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                relations.addEdge(parent, Integer.parseInt(nums[i]));
            }
        }
    }
    
    public boolean isNoun(String n) {
        for (Synset s : synsets) {
            if (s.contains(n)) {
                return true;
            }
        }
        return false;
    }
    
    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        for (Synset s : synsets) {
            nouns.addAll(s.getWords());
        }
        return nouns;
    }
    
    public Set<String> hyponyms(String word) {
        HashSet<String> hyps = new HashSet<String>();
        ArrayList<Integer> toAdd = new ArrayList<Integer>();
        
        for (Synset s : synsets) {
            if (s.contains(word)) {
                hyps.addAll(s.getWords());
                toAdd.addAll(traverseFrom(s.getID(), new HashSet<Integer>()));
            }
        }

        for (Integer i : toAdd) {
            hyps.addAll(synsets.get(i).getWords());
        }
        return hyps;
    }
    
    private Set<Integer> traverseFrom(int v, Set<Integer> path) {
        Iterable<Integer> neighbors = relations.adj(v);
        path.add(v);
        for (Integer n : neighbors) {
            path.addAll(traverseFrom(n, path));
        }
        return path;
    }
}
