package ngordnet;


import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet; // cannot put away
import java.util.HashMap; 
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<String, String> nouns; //map of syns
    private HashMap<String, Set<String>> hyps; //map of hyp
    private Digraph g;
    private HashSet<String> nounsSet;
    private Set<Integer> hypSet;
    private HashMap<String, Set<Integer>> nounsReversed;
    private HashSet<String> allnouns;

  // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {  
        In in;
        int nVert = 0;
        String line = "";
        in = new In(synsets);  //read file      
        nouns = new HashMap<String, String>(); // NOUNS
        while ((line = in.readLine()) != null) { //create map syn
            String[] parts = line.split(",");   
            nouns.put(parts[0], parts[1]);  
            nVert += 1;
        }

        nounsReversed = new  HashMap<String, Set<Integer>>();
        for (String s : nouns.keySet()) {
            String[] str = nouns.get(s).split(" "); 
            for (String y : str) {
                if (!nounsReversed.containsKey(y)) {
                    Set<Integer> tm = new HashSet<Integer>();
                    tm.add(Integer.parseInt(s));
                    nounsReversed.put(y, tm);
                } else {
                    nounsReversed.get(y).add(Integer.parseInt(s));
                }                      
            }          
        }

        allnouns = new HashSet<String>();
        String[] noun;
        for (String p: nouns.keySet()) {  // set of all nouns
            noun = nouns.get(p).split(" "); // array of syncs need to iterate
            for (String s : noun) {
                allnouns.add(s);
            }
        }      

        in = new In(hypernyms);  //read file  hyp    
        hyps = new HashMap<String, Set<String>>();

        while ((line = in.readLine()) != null) { //create map hyp
            String[] parts = line.split(",");  
            Set<String> parts2 = new HashSet<String>();
            String key = parts[0];
            for (int i = 1; i < parts.length; i++) { //fill in array
                parts2.add(parts[i]);
            }        
           // hyps.put(key, parts2);
            if (!hyps.containsKey(key)) {
                hyps.put(key, parts2);
            } else {
                hyps.get(key).addAll(parts2);
            }                   
        } 

        g = new Digraph(nVert);    
        Set<String>  temp;
        for (String s: hyps.keySet()) {  //fill Dgraph with ids from syn and hyp maps
            temp = hyps.get(s);  //get str of hyps for key i  split 
            for (String m : temp) { //
                g.addEdge(Integer.parseInt(s), Integer.parseInt(m)); //create adges for DG 
            }
        }

        nounsSet = new HashSet<String>();
        for (String id : nouns.keySet()) {  //creating set af all nouns iter through keys
            nounsSet.add(nouns.get(id)); // add noun to set
        }
    }

  // returns set of all WordNet nouns
    public Set<String> nouns() {  //
        return allnouns;
    }

  // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return allnouns.contains(word);
    }

    public Set<String> hyponyms(String word) {              
        hypSet = GraphHelper.descendants(g, nounsReversed.get(word)); // set  of ints, need conver
        HashSet<String> h = new HashSet<String>();
        for (int id: hypSet) {
            String[] p = nouns.get(Integer.toString(id)).split(" ");
            for (String k : p) {
                h.add(k);
            }
        }
        return h;
    }  
}
