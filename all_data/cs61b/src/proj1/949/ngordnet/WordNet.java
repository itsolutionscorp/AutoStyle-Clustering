package ngordnet;

import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet extends java.lang.Object {
    private Digraph graph;
    private HashMap<Integer, Set<String>> id2synet;
    private HashMap<String, Set<Integer>> noun2id;
    private int size = 0;

    // constructs a Wordnet that takes in synsets and hypernyms
    public WordNet(java.lang.String synsetFilename,
            java.lang.String hyponymFilename) {
        In rawsynset = new In(synsetFilename);
        In rawhyponym = new In(hyponymFilename);
        String synetline;
        String hyponymline;

        noun2id = new HashMap<String, Set<Integer>>();
        id2synet = new HashMap<Integer, Set<String>>();

        while ((synetline = rawsynset.readLine()) != null) {

            String[] nocomma = synetline.split(",");
            int id = Integer.parseInt(nocomma[0]);
            String[] synset = nocomma[1].split(" ");
            HashSet<String> words = new HashSet<String>();

            for (String noun1 : synset) {
                words.add(noun1);
            }
            id2synet.put(id, words);
            // System.out.println(id2synet.get(id));
            size += 1;
            for (String noun2 : synset) {
                Set<Integer> keys = noun2id.get(noun2);
                if (keys == null) {
                    HashSet<Integer> set = new HashSet<Integer>();
                    set.add(id);
                    noun2id.put(noun2, set);
                } else {
                    keys.add(id);
                    noun2id.put(noun2, keys);
                }

            }
        }
        graph = new Digraph(size);
        while ((hyponymline = rawhyponym.readLine()) != null) {
            String[] nocomma2 = hyponymline.split(",");
            Integer[] edge = new Integer[nocomma2.length];
            for (int i = 0; i < nocomma2.length; i++) {
                edge[i] = Integer.parseInt(nocomma2[i]);
                if (i != 0) {
                    graph.addEdge(edge[0], edge[i]);
                }
            }

        }

    }

    // Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
    // If WORD belongs to multiple synsets, return all hyponyms of all of these
    // synsets.
    // See http://goo.gl/EGLoys for an example. Do not include hyponyms of
    // synonym.

    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        Set<String> hyponyms = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> keyed = noun2id.get(word);
            Set<Integer> keyed2 = GraphHelper.descendants(graph, keyed);
            for (Integer i : keyed2) {
                hyponyms.addAll(id2synet.get(i));
            }
        } else {
            return hyponyms;
        }
        return hyponyms;
    }

    public java.util.Set<java.lang.String> nouns() {
        return noun2id.keySet();
    }

    public boolean isNoun(java.lang.String noun) {
        return noun2id.containsKey(noun);

    }

}
