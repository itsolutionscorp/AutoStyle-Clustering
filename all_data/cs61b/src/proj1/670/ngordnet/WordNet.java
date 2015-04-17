package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    /**
     * @Author Richard Meng
     */
    private Map<String, Integer> vertices;
    private Map<String, Integer> verticesChaos;
    private Map<Integer, String> verticesflip;
    private Digraph structure;
    private Map<Integer, HashSet<String>> overlapped;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponymset = new In(hyponymFilename);
        vertices = new HashMap<String, Integer>();
        verticesChaos = new HashMap<String, Integer>();
        verticesflip = new HashMap<Integer, String>();
        overlapped = new HashMap<Integer, HashSet<String>>();
        while (synset.hasNextLine()) {
            String nextLine = synset.readLine();
            int nextInt = Integer.parseInt(nextLine.split(",")[0]);
            String nextString = nextLine.split(",")[1];
            vertices.put(nextString, nextInt);
            verticesflip.put(nextInt, nextString);
        }
        int size = verticesflip.size();
        structure = new Digraph(size);
        optimizedHashmap();
        while (hyponymset.hasNextLine()) {
            String nextLine = hyponymset.readLine();
            String[] superAndsub = nextLine.split(",");
            int superInt = Integer.parseInt(superAndsub[0]);
            for (int i = 1; i < superAndsub.length; i++) {
                int subInt = Integer.parseInt(superAndsub[i]);
                structure.addEdge(superInt, subInt);
            }

        }
    }

    public boolean isNoun(String noun) {
        Set<String> nounSet = nouns();
        return nounSet.contains(noun);
    }

    public Set<String> nouns() {
        Set<String> nounsSet = verticesChaos.keySet();
        // TreeSet<String> nounsSetCopy = new TreeSet<String>();
        // nounsSetCopy.addAll(nounsSet);
        // for (String s1 : nounsSet) {
        // if (s1.contains(" ")) {
        // String[] splited = s1.split("\\s+");
        // nounsSetCopy.remove(s1);
        // for (String s2 : splited) {
        // System.out.println(s2);
        // nounsSetCopy.add(s2);
        // }
        // }
        // }
        return nounsSet;
    }

    private void optimizedHashmap() {
        for (Map.Entry<Integer, String> entry : verticesflip.entrySet()) {
            String longString = entry.getValue();
            int stringValue = entry.getKey();
            if (longString.contains(" ")) {
                String[] splited = longString.split("\\s+");
                for (String shortString : splited) {
                    verticesChaos.put(shortString, stringValue);
                    if (overlapped.containsKey(stringValue)) {
                        overlapped.get(stringValue).add(shortString);
                    } else {
                        HashSet<String> tempHash = new HashSet<String>();
                        overlapped.put(stringValue, tempHash);
                        tempHash.add(shortString);
                    }
                }
            } else {
                HashSet<String> tempHash = new HashSet<String>();
                overlapped.put(stringValue, tempHash);
                tempHash.add(longString);
                verticesChaos.put(longString, stringValue);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIDset = new TreeSet<Integer>();
        // System.out.println(overlapped);
        // if (vertices.containsKey(word)) {
        // int synsetID = vertices.get(word);
        // synsetIDset.add(synsetID); }
        // else {
        for (Map.Entry<Integer, HashSet<String>> entry : overlapped.entrySet()) {
            if (entry.getValue().contains(word)) {
                synsetIDset.add(entry.getKey());
            }
        }
        // }
        Set<Integer> descendants = GraphHelper.descendants(structure,
                synsetIDset);
        Set<String> hyponym = new TreeSet<String>();
        TreeSet<String> hyponymSetCopy = new TreeSet<String>();
        for (int i : descendants) {
            hyponym.add(verticesflip.get(i));
        }
        hyponymSetCopy.addAll(hyponym);
        for (String s1 : hyponym) {
            if (s1.contains(" ")) {
                String[] splited = s1.split("\\s+");
                hyponymSetCopy.remove(s1);
                for (String s2 : splited) {
                    hyponymSetCopy.add(s2);
                }
            }
        }
        return hyponymSetCopy;
    }
}
