package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap wordIDs = new HashMap<String, Set<String>>();
    private HashMap idWords = new HashMap<String, Set<String>>();
    private HashMap ids = new HashMap<String, Set<String>>();
    private Digraph hypDigraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        HashSet<String> idNums;
        HashSet<String> p;
        HashSet<String> words;
        HashSet<String> hypIDs;

        In in = new In(synsetFilename);
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String [] lineTokens = line.split(",");

            String [] multWords = lineTokens[1].split(" ");
            if (multWords[0] != lineTokens[1]) {      //has multiple words with same integer
                for (int i = 0; i < multWords.length; i += 1) {
                    if (wordIDs.containsKey(multWords[i])) {         //if word key exists
                        p = (HashSet<String>) wordIDs.get(multWords[i]);
                        p.add(lineTokens[0]);
                        p = null;
                    } else {                                      //else create new val set
                        idNums = new HashSet<String>();
                        idNums.add(lineTokens[0]);
                        wordIDs.put(multWords[i], idNums);
                    }
                }
                words = new HashSet();
                for (int i = 0; i < multWords.length; i += 1) {
                    words.add(multWords[i]);
                }
                idWords.put(lineTokens[0], words);
            } else {      //only one word per integer
                if (wordIDs.containsKey(lineTokens[1])) {        //if word key exists
                    p = (HashSet<String>) wordIDs.get(lineTokens[1]);
                    p.add(lineTokens[0]);
                    p = null;
                } else {
                    idNums = new HashSet<String>();
                    idNums.add(lineTokens[0]);
                    wordIDs.put(lineTokens[1], idNums);
                }
                words = new HashSet();
                words.add(lineTokens[1]);
                idWords.put(lineTokens[0], words);
            }
        }

        /* Creates ids map that represents all hyponym relations. */
        in = new In(hyponymFilename);
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String [] lineTokens = line.split(",");

            hypIDs = new HashSet();
            for (int i = 1; i < lineTokens.length; i += 1) {
                hypIDs.add(lineTokens[i]);
            }

            if (ids.containsKey(lineTokens[0])) {
                for (String hypID : (Set<String>) hypIDs) {
                    p = (HashSet<String>) ids.get(lineTokens[0]);
                    p.add(hypID);
                }
            } else {
                ids.put(lineTokens[0], hypIDs);
            }
        }

        /* Creates a digraph of hyponyms called hypDigraph. */
        int vertices = idWords.size();
        hypDigraph = new Digraph(vertices);
        for (String k : (Set<String>) ids.keySet()) {
            for (String j : (Set<String>) ids.get(k)) {
                int v = Integer.parseInt(k);
                int w = Integer.parseInt(j);
                hypDigraph.addEdge(v, w);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordIDs.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordIDs.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> synsetIDs = new HashSet<Integer>();
        int id;
        for (String k : (Set<String>) wordIDs.get(word)) {
            id = Integer.parseInt(k);
            synsetIDs.add(id);
        }
        Set<Integer> descendants = GraphHelper.descendants(hypDigraph, synsetIDs);
        HashSet<String> returnWords = new HashSet<String>();
        String hypID;
        for (Integer i : (Set<Integer>) descendants) {
            hypID = String.valueOf(i);
            for (String j : (Set<String>) idWords.get(hypID)) {
                returnWords.add(j);
            }
        }
        return (Set<String>) returnWords;
    }
}
