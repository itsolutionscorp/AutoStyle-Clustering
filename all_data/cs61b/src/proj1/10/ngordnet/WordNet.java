package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class WordNet {

    private Digraph graph;
    private ArrayList<String[]> synsets;
    private HashMap<String, HashSet<Integer>> wordMap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new ArrayList<String[]>();
        wordMap = new HashMap<String, HashSet<Integer>>();

        synsetFiller(synsetFilename);
        graph = new Digraph(synsets.size());
        hyponymFiller(hyponymFilename);
        
    }

    private void synsetFiller(String fileName) {
        In reader = new In(fileName);
        while (reader.hasNextLine()) {
            String[] line = reader.readLine().split(",");
            String[] synsetline = line[1].split(" ");
            for (String s : synsetline) {
                if (!wordMap.containsKey(s)) {
                    wordMap.put(s, new HashSet<Integer>());
                }
                wordMap.get(s).add(Integer.parseInt(line[0]));
            }
            synsets.add(synsetline);
        }
    }

    private void hyponymFiller(String fileName) {
        In reader = new In(fileName);
        while (reader.hasNextLine()) {
            String[] line = reader.readLine().split(",");
            int hypernym = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                graph.addEdge(hypernym, Integer.parseInt(line[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> temp = wordMap.get(word);
        temp.addAll(GraphHelper.descendants(graph, wordMap.get(word)));
        Set<String> result = new HashSet<String>();
        for (Integer i : temp) {
            Collections.addAll(result, synsets.get(i));
        }
        return result;
    }
}
