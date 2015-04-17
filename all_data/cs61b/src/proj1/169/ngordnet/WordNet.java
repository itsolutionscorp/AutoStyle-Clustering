package ngordnet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * @author Shafqat Dulal
 */
public class WordNet {
    private Digraph wordGraph;
    private ArrayList<SynSet<String>> synSets;

    /**
     * Represents a synset and contains the id (from the file) and definition of
     * the words.
     */
    private class SynSet<String> extends HashSet<String> {
        private int vertex;

        public SynSet(int i) {
            super();
            vertex = i;
        }

    }

    /** Creates a wordGraph using files from SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFileName) {
        synSets = new ArrayList<SynSet<String>>();
        // Develop synsets by going through the synset file.
        In synsetData = new In(synsetFilename);
        int id;
        String[] synsetLine;

        while (synsetData.hasNextChar()) {
            synsetLine = synsetData.readLine().split(",");
            id = Integer.parseInt(synsetLine[0]);
            SynSet<String> s = new SynSet<String>(id);
            for (String word : synsetLine[1].split(" ")) {
                s.add(word);
            }
            synSets.add(id, s);
        }

        wordGraph = new Digraph(synSets.size());
        // Develop the digraph by adding edges by going through the hyponym
        // file.
        In hyponymData = new In(hyponymFileName);
        int from;
        int to;
        String[] hyponymLine;

        while (hyponymData.hasNextChar()) {
            hyponymLine = hyponymData.readLine().split(",");
            from = Integer.parseInt(hyponymLine[0]);
            for (int hypId = 1; hypId < hyponymLine.length; hypId++) {
                to = Integer.parseInt(hyponymLine[hypId]);
                wordGraph.addEdge(from, to);
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (SynSet<String> set : synSets) {
            if (set.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (SynSet<String> set : synSets) {
            nounSet.addAll(set);
        }

        return nounSet;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> hypSymSet = new HashSet<String>();

        for (SynSet<String> set : synSets) {
            if (set.contains(word)) {
                hypSymSet.addAll(set);

                Set<Integer> setNum = new HashSet<Integer>();
                setNum.add(set.vertex);

                for (int v : GraphHelper.descendants(wordGraph, setNum)) {
                    hypSymSet.addAll(synSets.get(v));
                }
            }
        }

        return hypSymSet;
    }

}
