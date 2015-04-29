package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private Digraph words;
    private HashMap<String, HashSet<Integer>> resolver;
    private HashMap<Integer, HashSet<String>> reverseResolver;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        resolver = new HashMap<String, HashSet<Integer>>();
        reverseResolver = new HashMap<Integer, HashSet<String>>();

        In synsets = new In(synsetFilename);
        String[] lines = synsets.readAllLines();
        words = new Digraph(lines.length);

        for (String line : lines) {
            String[] csv = line.split(",");
            String[] names = csv[1].split(" ");
            reverseResolver.put(Integer.parseInt(csv[0]), new HashSet<String>());
            for (String name : names) {
                if (!resolver.containsKey(name)) {
                    resolver.put(name, new HashSet<Integer>());
                }
                resolver.get(name).add(Integer.parseInt(csv[0]));
                reverseResolver.get(Integer.parseInt(csv[0])).add(name);
            }
            
        }

        In hyponyms = new In(hyponymFilename);
        lines = hyponyms.readAllLines();

        for (String line : lines) {
            String[] csv = line.split(",");
            for (int i = 1; i < csv.length; i++) {
                words.addEdge(Integer.parseInt(csv[0]), Integer.parseInt(csv[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return resolver.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return resolver.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponyms = new HashSet<String>();
        Set<Integer> descendants = GraphHelper.descendants(words, resolver.get(word));
        for (int v : descendants) {
            hyponyms.addAll(reverseResolver.get(v));
        }
        return hyponyms;
    }
} 
