package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
public class WordNet {

    private TreeMap<String, Set<Integer>> keyNames;
    private TreeMap<Integer, String[]> nameKeys;
    private Digraph theGraph;

    public WordNet(String events, String vertices) {
        keyNames = new TreeMap<String, Set<Integer>>();
        nameKeys = new TreeMap<Integer, String[]>();
        In one = new In(events);
        String line = one.readLine();
        int count = 0;
        while (line != null) {
            String[] rawtokens = line.split(",");
            Integer id = Integer.parseInt(rawtokens[0]);
            String tokens = rawtokens[1];
            String[] mid = tokens.split(" ");
            nameKeys.put(id, mid);
            for (String fin: mid) {
                if (keyNames.containsKey(fin)) {
                    (keyNames.get(fin)).add(id);
                    count += 1; 
                } else {
                    Set<Integer> idSet = new HashSet<Integer>();
                    idSet.add(id);
                    keyNames.put(fin, idSet);
                    count += 1;
                }
            }
            line = one.readLine();
        }
        theGraph = new Digraph(count);
        In two = new In(vertices);
        String line2 = two.readLine();
        while (line2 != null) {
            String[] rawtokens2 = line2.split(",");
            String[] tokens2 = new String[rawtokens2.length - 1];
            Integer name2 = Integer.parseInt(rawtokens2[0]);
            System.arraycopy(rawtokens2, 1, tokens2, 0, rawtokens2.length - 1);
            for (String s: tokens2) {
                theGraph.addEdge(name2, Integer.parseInt(s));
            }
            line2 = two.readLine();
        }
    }

    public boolean isNoun(String x) {
        Set<String> temp = keyNames.keySet();
        for (String t : temp) {
            if (t.equals(x)) {
                return true; 
            }
        }
        return false;
    }

    public Set<String> nouns() {
        return keyNames.keySet();
    }

    public Set<String> hyponyms(String item) {
        Set<Integer> input = keyNames.get(item);
        Set<Integer> temp = GraphHelper.descendants(theGraph, input);
        Set<String> ret = new HashSet<String>();
        for (Integer i: temp) {
            for (String n: nameKeys.get(i)) {
                ret.add(n);
            }
        }
        return ret;
    }

}
