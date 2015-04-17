package ngordnet;



import java.util.Set;

import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;

public class WordNet {

    //Instance Variables
    private HashMap<Integer, String[]> synMap;
    private Digraph graph;

    //Constructor
    // Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    public WordNet(String synsetFilename, String hyponymFilename) {

        //Parse hyponymfile into a hashmap
        In scan = new In(hyponymFilename);
        HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
        while (scan.hasNextLine()) {
            String line = scan.readLine();
            String[] items = line.split(",");
            int first = Integer.parseInt(items[0]);
            Integer[] others = new Integer[items.length - 1];
            for (int i = 1; i < items.length; i++) {
                others[i - 1] = Integer.parseInt(items[i]);
            }

            if (map.containsKey(first)) {
                Integer[] replace = new Integer[others.length + map.get(first).length];
                System.arraycopy(map.get(first), 0, replace, 0, map.get(first).length);
                System.arraycopy(others, 0, replace, map.get(first).length, others.length);
                map.put(first, replace);
            } else {
                map.put(first, others);
            }
        }

        //Find the largest number from hyponym set
        int max = 0;
        for (int key : map.keySet()) {
            if (key > max) {
                max = key;
            }
            for (int val : map.get(key)) {
                if (val > max) {
                    max = val;
                }
            }
        }

        //Create Digraph, add edges
        graph = new Digraph(max + 1);
        for (int key : map.keySet()) {
            for (int val : map.get(key)) {
                graph.addEdge(key, val);
            }
        }

        //Parse synsetfile into hashmap
        In synScan = new In(synsetFilename);
        synMap = new HashMap<Integer, String[]>();
        while (synScan.hasNextLine()) {
            String row = synScan.readLine();
            String[] elements = row.split(",");
            int idNum = Integer.parseInt(elements[0]);
            String[] words = new String[elements.length - 1];
            for (int i = 1; i < elements.length; i++) {
                words[i - 1] = elements[i];
            }
            synMap.put(idNum, words);
        }
    }

    //Methods

    public Set<String> hyponyms(String word) {

        //Get idNum for inputted word
        HashSet<Integer> synsetIDs = new HashSet<Integer>();
        for (int key : synMap.keySet()) {
            String[] synonyms = synMap.get(key)[0].split(" ");
            for (String syno : synonyms) {
                if (word.equals(syno)) {
                    synsetIDs.add(key);
                }
            }
        }

        //Use GraphHelper to get set of idNumbers for decendnets too
        Set<Integer> reachables = GraphHelper.descendants(graph, synsetIDs);
        //Add words associated with each reachables idNum to a new set of strings.
        HashSet<String> hypos = new HashSet<String>();
        for (int key : reachables) {
            String[] reachedSynonyms = synMap.get(key)[0].split(" ");
            for (String s : reachedSynonyms) {
                hypos.add(s);
            }
        }
        return hypos;
    }

    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    public Set<String> nouns() {
        HashSet<String> nounSet = new HashSet<String>();
        for (int key : synMap.keySet()) {
            String value = synMap.get(key)[0];
            String[] nounArray = value.split(" ");
            for (String noun : nounArray) {
                nounSet.add(noun);
            }
        }
        return nounSet;
    }
}
