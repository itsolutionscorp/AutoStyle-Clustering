package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private HashSet<String> nouns = new HashSet<String>();
    private HashMap<Integer, Set> match = new HashMap<Integer, Set>();
    private Digraph graph;

    public WordNet(String file1, String file2) {
        ArrayList<String> temp1 = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();
        In f1 = new In(file1);
        In f2 = new In(file2);
        while (true) {
            String s1 = f1.readLine();
            if (s1 == null) {
                break;
            }
            temp1.add(s1);
        }
        graph = new Digraph(temp1.size());
        while (true) {                       
            String s2 = f2.readLine();
            if (s2 == null) {
                break;     
            } 
            temp2.add(s2);
        }
        for (String s : temp1) {
            ArrayList<String> tempArray1 = new ArrayList<String>();
            String[] parts = s.split(",");
            String [] partsOfParts = parts[1].split(" ");
            for (String lol : partsOfParts) {
                tempArray1.add(lol);
            }
            Set<String> set = new HashSet<String>();
            for (String elem: tempArray1) {
                set.add(elem);
                nouns.add(elem);
            }
            Integer k = Integer.parseInt(parts[0]);
            match.put(k, set);
        }
        for (String s : temp2) {
            ArrayList<Integer> tempArray2 = new ArrayList<Integer>();
            String[] parts = s.split(",");
            for (String lol: parts) {
                Integer k = Integer.parseInt(lol);
                tempArray2.add(k);
            }
            for (int i = 1; i < tempArray2.size(); i++) {
                graph.addEdge(tempArray2.get(0), tempArray2.get(i));
            }
        }
    }

    public Set<String> nouns() {
        return nouns;
    }

    public boolean isNoun(String str) {
        return (nouns.contains(str));        
    }

    public Set<String> hyponyms(String s) {
        try {
            Set<Integer> cur = new HashSet<Integer>();
            for (Integer k: match.keySet()) {
                if (match.get(k).contains(s)) {
                    cur.add(k);
                } 
            }
            Set<Integer> result = GraphHelper.descendants(graph, cur);
            HashSet<String> toReturn = new HashSet<String>();

            for (Integer keke : result) {
                Set<String> setString = match.get(keke);
                for (String noob : setString) {
                    toReturn.add(noob);
                }
            }
            toReturn.add(s);
            return toReturn;
        } catch (NullPointerException e) {
            System.out.println("Not in list");
            return null;
        }
    }
}
