package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import java.io.File;

public class WordNet {
    private HashMap<Integer, String[]> idnumbers;
    private HashSet<String> nouns;
    private Digraph words;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        idnumbers = new HashMap<Integer, String[]>();
        nouns = new HashSet<String>();
        In syn = new In(new File(synsetFilename));
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] lineData = line.split(",");
            int idnum = Integer.parseInt(lineData[0]);
            String[] synonyms = lineData[1].split(" ");
            for (String s : synonyms) {
                nouns.add(s);
            }
            idnumbers.put(idnum, synonyms);
        }
        int numVertices = nouns.size();
        words = new Digraph(numVertices);
        In hyp = new In(new File(hyponymFilename));
        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            String[] lineData = line.split(",");
            Integer[] lineDataAsInts = new Integer[lineData.length];
            for (int i = 0; i < lineData.length; i += 1) {
                lineDataAsInts[i] = Integer.parseInt(lineData[i]);
            }
            for (int i = 1; i < lineDataAsInts.length; i += 1) {
                words.addEdge(lineDataAsInts[0], lineDataAsInts[i]);
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

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        //find ID numbers of WORD argument
        HashSet<Integer> wordIDs = new HashSet<Integer>();
        for (Integer k : idnumbers.keySet()) {
            String[] synonyms = idnumbers.get(k);
            for (String s : synonyms) {
                if (s.equals(word)) {
                    wordIDs.add(k);
                }
            }
        }

        HashSet<String> hyponymsAndSynonyms = new HashSet<String>();
        for (Integer wordIDNum : wordIDs) {
            for (String s : idnumbers.get(wordIDNum)) {
                hyponymsAndSynonyms.add(s); //add synonyms
            }
        }
        for (Integer synIDNum : GraphHelper.descendants(words, wordIDs)) {
            for (String s : idnumbers.get(synIDNum)) {
                hyponymsAndSynonyms.add(s);
            }
        }
        return hyponymsAndSynonyms;
    }
}
