package ngordnet;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import java.io.File;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Digraph digraph;
    private Map<Integer, Set<String>> idToWordMap;
    private Map<String, Set<Integer>> wordToIdMap;

    public WordNet(String synsetFilename, String hyponymFilename) {
        //Generate maps
        idToWordMap = new TreeMap<Integer, Set<String>>();
        wordToIdMap = new TreeMap<String, Set<Integer>>();

        //Parse synsets...
        In synsetStream = new In(new File(synsetFilename));
        if (!synsetStream.exists()) {
            throw new RuntimeException("file doesn't exist!");
        }

        String[] synsetLines = synsetStream.readAllLines();
        int maxId = -1;
        for (String line : synsetLines) {
            String[] components = line.split(",");
            int id = Integer.parseInt(components[0]);
            String[] synsetWords = components[1].split(" ");
            Set<String> synset = new HashSet<>();
            for (String word : synsetWords) {
                synset.add(word);
            }
            addSynset(id, synset);
            maxId = Math.max(id, maxId);
        }

        //Generate digraph
        digraph = new Digraph(maxId + 1);

        //Parse hyponyms...
        In hyponymStream = new In(new File(hyponymFilename));
        if (!hyponymStream.exists()) {
            throw new RuntimeException("file doesn't exist!");
        }
        for (String line : hyponymStream.readAllLines()) {
            String[] components = line.split(",");
            int parentId = Integer.parseInt(components[0]);
            for (int i = 1; i < components.length; i++) {
                int childId = Integer.parseInt(components[i]);
                digraph.addEdge(parentId, childId);
            }
        }
    }

    private void addSynset(int id, Set<String> synset) {
        //store id to word
        if (!idToWordMap.containsKey(id)) {
            idToWordMap.put(id, new HashSet<String>());
        }
        Set<String> pertinent = idToWordMap.get(id);
        pertinent.addAll(synset);

        //store word to id
        for (String word : synset) {
            if (!wordToIdMap.containsKey(word)) {
                wordToIdMap.put(word, new HashSet<Integer>());
            }
            Set<Integer> apposite = wordToIdMap.get(word);
            apposite.add(id);
        }
    }

    public boolean isNoun(String noun) {
        return wordToIdMap.containsKey(noun);
    }

    public Set<String> nouns() {
        return new HashSet<String>(wordToIdMap.keySet());
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> idSet = wordToIdMap.get(word);
        Set<Integer> descendants = GraphHelper.descendants(digraph, idSet);
        idSet.addAll(descendants);
        Set<String> result = new HashSet<>();
        for (int i : idSet) {
            result.addAll(idToWordMap.get(i));
        }
        return result;
    }

//    /**
//     * Attempts to reassemble synset and hyponym file contents.
//     */
//    void debugDump() {
//        System.out.println("****** SYNSET FILE (int -->S(String)) *******");
//        SortedMap<Integer, Set<String>> sorted = new TreeMap<>(idToWordMap);
//        for (Integer i : sorted.keySet()) {
//            System.out.printf("%d, %s %n", i, sorted.get(i).toString());
//        }
//
//        System.out.println("****** SYNSET FILE (String -->S(int)) *******");
//        SortedMap<String, Set<Integer>> sortedStr = new TreeMap<>(wordToIdMap);
//        for (String s : sortedStr.keySet()) {
//            System.out.printf("%s, %s %n", s, sortedStr.get(s).toString());
//        }
//
//        System.out.println("****** HYPONYM FILE (from digraph) *******");
//        for (int i = 0; i < digraph.V(); i++) {
//            if (digraph.outdegree(i) == 0) {
//                continue;
//            }
//            System.out.printf("%d", i);
//            for (int v : digraph.adj(i)) {
//                System.out.printf(",%d", v);
//            }
//            System.out.printf("%n");
//        }
//    }

//    private int lookupId(String word) {
//        return wordToIdMap.get(word).get(0);
//    }
//
//    private String lookupWord(int id) {
//        return idToWordMap.get(id).get(0);
//    }
}
