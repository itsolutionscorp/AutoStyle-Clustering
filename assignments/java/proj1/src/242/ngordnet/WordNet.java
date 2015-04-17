package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph g;
    private HashMap<Integer, HashSet<String>> m;
    private HashSet<String> nouns;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        m = new HashMap<Integer, HashSet<String>>(100);
        nouns = new HashSet<String>(100);
        In file = new In(synsetFilename);
        String line;
        String[] lineArray;
        HashSet<String> synonyms;
        while (!file.isEmpty()) {
            synonyms = new HashSet<String>();
            line = file.readLine();
            lineArray = line.split(",");
            for (String s : lineArray[1].split(" ")) {
                nouns.add(s);
                synonyms.add(s);
            }
            m.put(Integer.parseInt(lineArray[0]), synonyms);
        }
        g = new Digraph(m.keySet().size());
        
        file = new In(hyponymFilename);
        while (!file.isEmpty()) {
            line = file.readLine();
            lineArray = line.split(",");
            for (int i = 1; i < lineArray.length; i++) {
                g.addEdge(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[i]));
            }
        }        
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> hypos = new HashSet<String>();
        Set<Integer> wordIndices = new HashSet<Integer>();
        Set<Integer> hypoIndices = new HashSet<Integer>();
        for (int i = 0; i < m.keySet().size(); i++) {
            Set<String> s = m.get(i);
            if (s != null && s.contains(word)) {
                hypos.addAll(s);
                wordIndices.add(i);
            }
        }
        hypoIndices.addAll(GraphHelper.descendants(g, wordIndices));
        for (Integer i : hypoIndices) {
            hypos.addAll(m.get(i));
        }
        return hypos;
    }

}
