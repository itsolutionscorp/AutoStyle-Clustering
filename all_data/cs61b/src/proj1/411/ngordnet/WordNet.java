package ngordnet;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, HashSet<String>> synsets;
    private Digraph wordnet;
    private HashSet<String> nouns;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        String[] synsetArray = synsetIn.readAllLines();
        String[] hyponyms = hyponymIn.readAllLines();
        // System.out.println(synsetArray.length);
        // System.out.println(hyponyms.length);

        String[] buffer;
        HashSet<String> nounBuffer;

        // Initialize synsets HashMap
        synsets = new HashMap<Integer, HashSet<String>>();
        nouns = new HashSet<String>();

        for (int i = 0; i < synsetArray.length; i++) {
            buffer = synsetArray[i].split(",", 3);
            nounBuffer = new HashSet<String>(Arrays.asList(buffer[1].split(" ")));

            synsets.put(Integer.parseInt(buffer[0]), nounBuffer);
            nouns.addAll(nounBuffer);
        }

        // System.out.println(nouns.size());

        // Initialize wordnet Digraph
        wordnet = new Digraph(synsetArray.length);
        for (int i = 0; i < hyponyms.length; i++) {
            buffer = hyponyms[i].split(",");
            for (int j = 1; j < buffer.length; j++) {
                wordnet.addEdge(Integer.parseInt(buffer[0]), Integer.parseInt(buffer[j]));
            }
        }
        // System.out.println(wordnet.toString());
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for  an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> synonyms, hyponyms = new HashSet<String>();
        HashSet<Integer> synsetIDs = new HashSet<Integer>();
        hyponyms.add(word);
        if (!isNoun(word)) {
            return hyponyms;
        }
        
        for (int i = 0; i < synsets.size(); i++) {
            synonyms = synsets.get(i);
            if (synonyms.contains(word)) {
                hyponyms.addAll(synonyms);
                synsetIDs.add(i);
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(wordnet, synsetIDs);
        for (Integer i: descendants) {
            hyponyms.addAll(synsets.get(i));
        }
        return hyponyms;
    }

}
