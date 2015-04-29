package ngordnet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Wordtree[] net = new Wordtree[1000];
    private Digraph graph;   
    private int numsynsets = 0;
    private Set<String> nouns = new HashSet<String>();
    private Set<String> hyps = new HashSet<String>();
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        In synsets = new In(synsetFilename);
        
        while (synsets.hasNextLine()) {
            String str = synsets.readLine();
            String[] fields = str.split(",");
            int id = Integer.parseInt(fields[0]);
            String[] synonym = fields[1].split(" ");
            net[id] = new Wordtree(synonym, null, id);
            if (id == net.length - 1) { 
                resize();
            }
            numsynsets += 1;
            for (String s:synonym) {
                nouns.add(s);
            }
        }
        
        graph = new Digraph(numsynsets);
        
        In hyponymsets = new In(hyponymFilename);
        
        while (hyponymsets.hasNextLine()) {
            String str2 = hyponymsets.readLine();
            String[] fields2 = str2.split(",");
            int id2 = Integer.parseInt(fields2[0]);
            int len = fields2.length - 1;
            /*int[] hyponym = new int[len];*/ 
            if (net[id2].hyponym == null) {
                net[id2].hyponym = new ArrayList<Integer>();
            }
            for (int i = 0; i < len; i++) {
                /*hyponym[i] = Integer.parseInt(fields2[i + 1]);*/
                int sub = Integer.parseInt(fields2[i + 1]);
                net[id2].hyponym.add(sub);
                graph.addEdge(id2, sub);
            }
            /*net[id2].hyponym = hyponym;*/
        }       
    }
    
    private void resize() {
        int len = net.length * 2;
        Wordtree[] newnet = new Wordtree[len];
        System.arraycopy(net, 0, newnet, 0, net.length);
        net = newnet;
    }
    
    public boolean isNoun(String noun) {
        for (int i = 0; i < numsynsets; i++) {
            if (Arrays.asList(net[i].synonym).contains(noun)) {
                return true;
            }
        }
        return false;
    }
    
    public Set<String> nouns() {        
        return nouns;
    }
    
    public Set<String> hyponyms(String word) {
        hyps = new HashSet<String>();
        for (int i = 0; i < numsynsets; i++) {
            if (Arrays.asList(net[i].synonym).contains(word)) {
                /*if (net[i].hyponym != null) {
                    for (int k:net[i].hyponym) {
                        hyps.addAll(Arrays.asList(net[k].synonym));
                    }
                }   
                hyps.addAll(Arrays.asList(net[i].synonym));
                */
                findhyps(i);               
            }
        }
        return hyps;
    }
    
    private void findhyps(int i) {
        if (net[i].hyponym != null) {
            for (int k:net[i].hyponym) {
                findhyps(k);
            }
        }
        hyps.addAll(Arrays.asList(net[i].synonym));
    }
}
