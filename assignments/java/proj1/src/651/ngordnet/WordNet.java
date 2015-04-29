package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
//import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
//import ngordnet.GraphHelper;

//perhaps wordnet should be a Set of Digraphs
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In inSynset;
    private In inHyponym;
    private Map<Integer, String> wordsNet;
    private Digraph wordNetRelations;

    // private int

    public WordNet(String synsetFilename, String hyponymFilename) {
        inSynset = new In(synsetFilename);
        inHyponym = new In(hyponymFilename);
        wordsNet = new HashMap();
        String synsetCurrLine;
        String[] synsetCurrParts;
        String synsetCurrActual;
        int synsetLabel;
        String hyponymCurrLine;
        String[] hyponymCurrParts;

        while (inSynset.hasNextLine()) {
            synsetCurrLine = inSynset.readLine();
            // hyponymCurrLine = inHyponym.readLine();
            synsetCurrParts = synsetCurrLine.split(",");
            synsetLabel = Integer.parseInt(synsetCurrParts[0]);
            synsetCurrActual = synsetCurrParts[1];
            // hyponymCurrParts = Integer.parseInt(hyponymCurr.split(","));
            wordsNet.put(synsetLabel, synsetCurrActual);
        }

        wordNetRelations = new Digraph(wordsNet.size());

        while (inHyponym.hasNextLine()) {
            hyponymCurrLine = inHyponym.readLine();
            hyponymCurrParts = hyponymCurrLine.split(",");

            for (int key : wordsNet.keySet()) {

                if (Integer.parseInt(hyponymCurrParts[0]) == key) {

                    for (int i = 1; i < hyponymCurrParts.length; i++) {
                        wordNetRelations.addEdge(key,
                                Integer.parseInt(hyponymCurrParts[i]));
                    }
                }
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        return nouns().contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        String[] thisSynsetWords;

        for (String value : wordsNet.values()) {
            thisSynsetWords = value.split(" ");

            for (int i = 0; i < thisSynsetWords.length; i++) {
                if (!(nouns.contains(thisSynsetWords[i]))) {
                    nouns.add(thisSynsetWords[i]);
                }
            }

        }

        return nouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> wordID = new TreeSet<Integer>();
        String currSynset;
        String[] currSynsetWords;

        for (int key : wordsNet.keySet()) {
            currSynset = wordsNet.get(key);
            currSynsetWords = currSynset.split(" ");
            for (int i = 0; i < currSynsetWords.length; i++) {
                if (word.equals(currSynsetWords[i])) {
                    wordID.add(key);
                }
            }
        }

        Set<Integer> descendants = ngordnet.GraphHelper.descendants(
                wordNetRelations, wordID);

        for (int key : descendants) {
            currSynset = wordsNet.get(key);
            currSynsetWords = currSynset.split(" ");
            for (int i = 0; i < currSynsetWords.length; i++) {
                if (!(hyponyms.contains(currSynsetWords[i]))) {
                    hyponyms.add(currSynsetWords[i]);
                }
            }
        }

        for (int key : wordID) {
            currSynset = wordsNet.get(key);
            currSynsetWords = currSynset.split(" ");
            for (int i = 0; i < currSynsetWords.length; i++) {
                if (!(hyponyms.contains(currSynsetWords[i]))) {
                    hyponyms.add(currSynsetWords[i]);
                }
            }
        }
        return hyponyms;
    }
}
