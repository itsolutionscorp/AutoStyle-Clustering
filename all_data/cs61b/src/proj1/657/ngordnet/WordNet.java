package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.Set;

public class WordNet {
   /* Use at least two types of data structure.
    * 
    */
    private String delimiter = ",";
    private HashMap<Integer, String[]> map;
    private int vNum = 0;
    private Digraph g;
    private Set<String> allNouns;
    private String spliter = " ";

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        String s;
        map = new HashMap<Integer, String[]>();
        while ((s = synsetFile.readLine()) != null) {
            String[] tokens = s.split(delimiter);
            Integer key = Integer.parseInt(tokens[0]);
            String[] tokens2 = tokens[1].split(spliter);
            map.put(key, tokens2);
            vNum++;
        }

        g = new Digraph(vNum);
        while ((s = hyponymFile.readLine()) != null) {
            String[] tokens = s.split(delimiter);
            int node = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                int child = Integer.parseInt(tokens[i]);
                g.addEdge(node, child);
            }
        }

        allNouns = new HashSet<String>();
        for (Integer key: map.keySet()) {
            String[] values = map.get(key);
            for (String str: values) {
                allNouns.add(str);
            }
        }
    }

    public boolean isNoun(String noun) {
        for (Integer key: map.keySet()) {
            String[] value = map.get(key);
            for (String val: value) {
                if (val.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }


    public Set<String> nouns() {
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> node = new HashSet<Integer>();
        Set<String> hypo = new HashSet<String>();
        for (Integer key: map.keySet()) {
            String[] values = map.get(key);
            for (String val: values) {
                if (val.equals(word)) {
                    node.add(key);
                }
            }
        }
        Set<Integer> strr =  GraphHelper.descendants(g, node);
        for (Integer i: strr) {
            String[] temp = map.get(i);
            for (String ss: temp) {
                hypo.add(ss);
            }
        }
        return hypo;
    }
}
