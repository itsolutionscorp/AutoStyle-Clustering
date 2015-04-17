package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set; 
import java.util.HashSet; 
import java.util.HashMap;
import java.util.NoSuchElementException;

public class WordNet {

    private HashMap<Integer, Set<String>> keyval;
    private HashMap<String, Set<Integer>> valkey; // how many different keys,or numbers,
    // correspod to one value/ or string / word example change - > 2, 8,alteration - > 2,11
    private HashSet<String> nouns;
    private Digraph g;
    //private HashMap<Integer, Set<Integer>> hyponymsMap;


    public WordNet(String synsetFilename, String hyponymFilename) {
        // initializing local variables
        In in = new In(synsetFilename);

        String curLine = null;
        HashSet<String> curSynset; 
        keyval = new HashMap<Integer, Set<String>>();
        nouns = new HashSet<String>();

        valkey = new HashMap<String, Set<Integer>>(); 
        while (in.hasNextLine()) {
            curLine = in.readLine();
            curSynset = new HashSet<String>();
            
            String[] tokens = curLine.split(",");
            Integer synsetID = Integer.parseInt(tokens[0]);
            String[] syns = tokens[1].split(" "); 
                
            for (int q = 0; q < syns.length; q = q + 1) {       
                curSynset.add(syns[q]); // adding value for keyval
                nouns.add(syns[q]); // adding element for all-nouns set

                Set<Integer> st = new HashSet<Integer>();
                Set<Integer> st1 = new HashSet<Integer>();

                if (valkey.containsKey(syns[q])) {

                    st1 = valkey.put(syns[q], st);
                    st1.add(synsetID);
                        
                    valkey.put(syns[q], st1);
                } else {
                    
                    st.add(synsetID);
                    valkey.put(syns[q], st);
                }
            }
            keyval.put(synsetID, curSynset); 
             
        }
        in.close();

        In in2 = new In(hyponymFilename);
        g = new Digraph(keyval.size());

        String curLine2 = null;
        while (in2.hasNextLine()) {
            curLine2 = in2.readLine();
            String[] tokens2 = curLine2.split(",");
            
            int vert0 = Integer.parseInt(tokens2[0]);
            
            for (int i = 1; i < tokens2.length; i = i + 1) {
                int vert = Integer.parseInt(tokens2[i]);
                g.addEdge(vert0, vert);
            }
        }
        in2.close();

    }

    public boolean isNoun(String noun) {

        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> out = new HashSet<String>();        
        if (isNoun(word)) {
            Set<Integer> st3 = valkey.get(word);

            if (st3.equals(null)) {
                System.out.println("No such elements");
                throw new NoSuchElementException();
            }

            Set<Integer> s4 = GraphHelper.descendants(g, st3);
            for (int descendant : s4) {
                Set<String> val = keyval.get(descendant);
                for (String w : val) {
                    out.add(w);
                }
            }
        }
        return out;
    } 
}

