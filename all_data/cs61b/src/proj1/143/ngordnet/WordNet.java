package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;

public class WordNet {
    
    private HashMap<Integer, TreeSet<String>> myMap;
    private HashMap<String, TreeSet<Integer>> myMapInv;
    private Digraph myDigraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        
        myMap = new HashMap<Integer, TreeSet<String>>(); // maps synID to wordSet
        myMapInv = new HashMap<String, TreeSet<Integer>>(); // maps word to synID(s)

        In synsetIn = new In(synsetFilename); 
        while (!synsetIn.isEmpty()) {
            String line = synsetIn.readLine();
            
            // Line parsing from ExampleUI
            String[] rawTokens = line.split(",");
            Integer synID = Integer.valueOf(rawTokens[0]);
            String[] rawWords = rawTokens[1].split(" ");
            
            TreeSet<String> wSet = new TreeSet<String>();
            for (String elem:rawWords) {
                wSet.add(elem);
            }
            myMap.put(synID, wSet);
            
            for (String elem:rawWords) {
                if (myMapInv.containsKey(elem)) {
                    (myMapInv.get(elem)).add(synID);
                } else {
                    TreeSet<Integer> x = new TreeSet<Integer>();
                    x.add(synID);
                    myMapInv.put(elem, x);  
                }
            }
        }

        myDigraph = new Digraph(myMap.size());
        In hypIn = new In(hyponymFilename); 
        while (!hypIn.isEmpty()) {
            String line = hypIn.readLine();
            String[] rawTokens = line.split(",");
            Integer synID = Integer.valueOf(rawTokens[0]);
            for (int i = 1; i < rawTokens.length; i++) {
                myDigraph.addEdge(synID, Integer.valueOf(rawTokens[i]));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> h = new TreeSet<String>();
        if (myMapInv.get(word) != null) {
            Set<Integer> d = GraphHelper.descendants(myDigraph, myMapInv.get(word));
            for (Integer i:d) {
                h.addAll(myMap.get(i));
            }
        }
        return h;
    }

    public boolean isNoun(String noun) {
        return myMapInv.containsKey(noun);
    }

    public Set<java.lang.String> nouns() {
        return myMapInv.keySet();
    }
}
